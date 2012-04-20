package aurora.ide.meta.gef.editors.template.handle;

import java.util.List;
import java.util.Map;

import aurora.ide.meta.gef.editors.models.AuroraComponent;
import aurora.ide.meta.gef.editors.models.Container;
import aurora.ide.meta.gef.editors.models.ViewDiagram;
import aurora.ide.meta.gef.editors.template.BMReference;

public class SerachTemplateHandle extends TemplateHandle {
	
	private Map<BMReference, List<AuroraComponent>> initModeRelated;
	private Map<BMReference, String> queryModelRelated;
	private Map<String, AuroraComponent> auroraComponents;
	
	public SerachTemplateHandle() {
		initModeRelated = TemplateHelper.getInstance().getInitModelRelated();
		queryModelRelated = TemplateHelper.getInstance().getQueryModelRelated();
		auroraComponents = TemplateHelper.getInstance().getAuroraComponents();
	}
	
	public void fill(ViewDiagram viewDiagram) {
		this.viewDiagram = viewDiagram;
		for (BMReference bm : modelRelated.keySet()) {
			for (Container ac : modelRelated.get(bm)) {
				fillContainer(ac, bm, true);
			}
		}
		
		for (BMReference bm : queryModelRelated.keySet()) {
			AuroraComponent ac = auroraComponents.get(queryModelRelated.get(bm));
			if (ac instanceof Container) {
				fillQueryBox((Container) ac, bm);
			}
		}
	}
}
