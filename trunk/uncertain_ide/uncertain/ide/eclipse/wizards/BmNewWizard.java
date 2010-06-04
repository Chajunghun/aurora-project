package uncertain.ide.eclipse.wizards;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWizard;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

import uncertain.composite.CompositeMap;
import uncertain.ide.Common;

/**
 * This is a sample new wizard. Its role is to create a new file 
 * resource in the provided container. If the container resource
 * (a folder or a project) is selected in the workspace 
 * when the wizard is opened, it will accept it as the target
 * container. The wizard creates one file with the extension
 * "bm". If a sample multi-page editor (also available
 * as a template) is registered for the same extension, it will
 * be able to open it.
 */

public class BmNewWizard extends Wizard implements INewWizard {
	
	public static String bm_uri = "http://www.aurora-framework.org/schema/bm";
	public static String bm_pre = "bm";
	
	private BmMainPage mainPage;
	private BmTablePage tablePage;
	private BmTableFieldsPage fieldsPage;
	private ISelection selection;
	private CompositeMap initContent;
	
	
	

	
	/**
	 * Constructor for BmNewWizard.
	 */
	public BmNewWizard() {
		super();
		setNeedsProgressMonitor(true);
	}
	
	/**
	 * Adding the page to the wizard.
	 */

	public void addPages() {
		mainPage = new BmMainPage(selection,this);
		tablePage= new BmTablePage(selection,this);
		fieldsPage = new BmTableFieldsPage(selection,this);
		fieldsPage.setPageComplete(false);
		addPage(mainPage);
		addPage(tablePage);
		addPage(fieldsPage);
	}

	/**
	 * This method is called when 'Finish' button is pressed in
	 * the wizard. We will create an operation and run it
	 * using wizard as execution context.
	 */
	public boolean performFinish() {
		final String containerName = mainPage.getContainerName();
		final String fileName = mainPage.getFileName();
		initContent = createInitContent();
		IRunnableWithProgress op = new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor) throws InvocationTargetException {
				try {
					doFinish(containerName, fileName, monitor);
				} catch (CoreException e) {
					throw new InvocationTargetException(e);
				} finally {
					monitor.done();
				}
			}
		};
		try {
			getContainer().run(true, false, op);
		} catch (InterruptedException e) {
			return false;
		} catch (InvocationTargetException e) {
			Throwable realException = e.getTargetException();
			MessageDialog.openError(getShell(), "Error", realException.getMessage());
			return false;
		}
		return true;
	}
	
	/**
	 * The worker method. It will find the container, create the
	 * file if missing or just replace its contents, and open
	 * the editor on the newly created file.
	 */

	private void doFinish(
		String containerName,
		String fileName,
		IProgressMonitor monitor)
		throws CoreException {
		
		//如果用户没有指定文件名后缀，自动加bm后缀
		if(fileName.indexOf(".")==-1){
			fileName = fileName+".bm";
		}
		// create a sample file
		monitor.beginTask("Creating " + fileName, 2);
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IResource resource = root.findMember(new Path(containerName));
		if (!resource.exists() || !(resource instanceof IContainer)) {
			throwCoreException("Container \"" + containerName + "\" does not exist.");
		}
		IContainer container = (IContainer) resource;
		final IFile file = container.getFile(new Path(fileName));
		try {
			InputStream stream = openContentStream();
			if (file.exists()) {
				file.setContents(stream, true, true, monitor);
			} else {
				file.create(stream, true, monitor);
			}
			stream.close();
		} catch (IOException e) {
		}
		monitor.worked(1);
		monitor.setTaskName("Opening file for editing...");
		getShell().getDisplay().asyncExec(new Runnable() {
			public void run() {
				IWorkbenchPage page =
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				try {
					IDE.openEditor(page, file, true);
				} catch (PartInitException e) {
				}
			}
		});
		monitor.worked(1);
	}
	
	/**
	 * We will initialize file contents with a sample text.
	 */

	private InputStream openContentStream() {
		String xmlHint = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		String contents =xmlHint+initContent.toXML();
		return new ByteArrayInputStream(contents.getBytes());
	}
	private CompositeMap createInitContent() {

		CompositeMap model = new CompositeMap(BmNewWizard.bm_pre,BmNewWizard.bm_uri,"model");
		model.put("baseTable", getTableName());
		model.addChild(getSelectedFields());
		
		try {
			CompositeMap pks = getPrimaryKeys();
			if(pks != null && pks.getChilds() != null){
				model.addChild(pks);
			}
		} catch (SQLException e) {
			Common.showExceptionMessageBox(null, e);
		}
		return model;
	}

	private void throwCoreException(String message) throws CoreException {
		IStatus status =
			new Status(IStatus.ERROR, "uncertain_ide", IStatus.OK, message, null);
		throw new CoreException(status);
	}

	/**
	 * We will accept the selection in the workbench to see if
	 * we can initialize from it.
	 * @see IWorkbenchWizard#init(IWorkbench, IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.selection = selection;
	}
/*	public String getUncertainProjectDir() {
		return mainPage.getUncertainProjectDir();
	}*/
	public String getTableName(){
		return tablePage.getTableName();
	}
	public DatabaseMetaData getDBMetaData(){
		return tablePage.getDBMetaData();
	}
	public CompositeMap getPrimaryKeys() throws SQLException{
		return tablePage.getPrimaryKeys();
	}
	//防止所有页面同时初始化
	public void createPageControls(Composite pageContainer) {
		// super.createPageControls(pageContainer); 
	}
	public CompositeMap getSelectedFields(){
		return fieldsPage.getSelectedFields();
	}
	public Connection getConnection() throws Exception{
		String containerName = mainPage.getContainerName();
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IResource resource = root.findMember(new Path(containerName));
		if (!resource.exists() || !(resource instanceof IContainer)) {
			throwCoreException("Container \"" + containerName + "\" does not exist.");
		}
		return Common.getDBConnection(resource.getProject());
	}
	public void refresh(){
		if(fieldsPage.getControl() != null )
			fieldsPage.refresh();
	}
}