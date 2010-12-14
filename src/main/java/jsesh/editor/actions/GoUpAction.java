/*
 * Created on 10 nov. 2004
 *
 * This file is distributed under the LGPL.
 */
package jsesh.editor.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import jsesh.editor.JMDCEditor;

/**
 * @author S. Rosmorduc
 * 
 */
public class GoUpAction extends EditorAction {
	
	
	public GoUpAction(JMDCEditor editor) {
		super(editor);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (editor.getDrawingSpecifications().getTextOrientation().isHorizontal())
			editor.getWorkflow().cursorUp();
		else
			editor.getWorkflow().cursorPrevious();

	}

}