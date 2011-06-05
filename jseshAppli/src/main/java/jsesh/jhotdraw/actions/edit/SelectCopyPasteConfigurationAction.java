/**
 * author : Serge ROSMORDUC
 * This file is distributed according to the LGPL (GNU lesser public license)
 */
package jsesh.jhotdraw.actions.edit;

import java.awt.event.ActionEvent;

import org.jhotdraw_7_4_1.app.Application;
import org.jhotdraw_7_4_1.app.View;
import org.jhotdraw_7_4_1.app.action.AbstractViewAction;

import jsesh.editorSoftware.MDCDisplayerAppliWorkflow;
import jsesh.editorSoftware.actions.generic.BasicAction;
import jsesh.jhotdraw.ExportType;
import jsesh.jhotdraw.JSeshApplicationModel;
import jsesh.jhotdraw.JSeshView;
import jsesh.jhotdraw.actions.BundleHelper;

/**
 * Select one of the copy and paste configuration
 * 
 * @author rosmord
 * 
 */
@SuppressWarnings("serial")
public class SelectCopyPasteConfigurationAction extends AbstractViewAction {

	private ExportType exportType;

	/**
	 * ID for resources. Add the configuration number to this ID.
	 */
	public static final String partialID = "edit.selectConfiguration_";

	public SelectCopyPasteConfigurationAction(Application app, View view,
			ExportType exportType) {
		super(app, view);
		this.exportType = exportType;
		BundleHelper.getInstance().configure(this, getID() );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		JSeshApplicationModel model = (JSeshApplicationModel) getApplication()
				.getModel();
		model.selectCopyPasteConfiguration(exportType);
	}

	public String getID() {
		return partialID + exportType.toString();
	}
}