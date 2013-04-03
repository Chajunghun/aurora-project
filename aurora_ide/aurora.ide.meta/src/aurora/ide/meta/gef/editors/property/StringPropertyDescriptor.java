package aurora.ide.meta.gef.editors.property;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.PropertyDescriptor;

public class StringPropertyDescriptor extends StylePropertyDescriptor {
	private boolean readOnly = false;

	public StringPropertyDescriptor(Object id, String displayName) {
		super(id, displayName);
	}

	public StringPropertyDescriptor(Object id, String displayName, int style) {
		super(id, displayName, style);
	}

	public StringPropertyDescriptor(Object id, String displayName, int style,
			boolean readOnly) {
		super(id, displayName, style);
		this.readOnly = readOnly;
	}

	public StringPropertyDescriptor(Object id, String displayName,
			boolean readOnly) {
		super(id, displayName);
		this.readOnly = readOnly;
	}

	public CellEditor createPropertyEditor(Composite parent) {
		StringCellEditor editor = new StringCellEditor(parent);
		editor.setReadOnly(readOnly);
		if (getValidator() != null) {
			editor.setValidator(getValidator());
		}
		return editor;
	}

}
