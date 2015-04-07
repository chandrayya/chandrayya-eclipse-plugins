/*******************************************************************************
 *
 * This component and the accompanying materials are made available
 * under the terms of the "Eclipse Public License v1.0"
 * which is available at the URL "http://www.eclipse.org/legal/epl-v10.html".
 *
 * Contributors:
 * Chandrayya G K - initial version
 *******************************************************************************/
package com.chandrayya.search.actions;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.search.internal.ui.SearchDialog;
import org.eclipse.search.ui.ISearchPageContainer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.ResourceUtil;

@SuppressWarnings("restriction")
public class MyDialog extends SearchDialog {

	public MyDialog(IWorkbenchWindow window, String pageId) {
		super(window, pageId);
	}

	@Override
	protected Control createPageArea(Composite parent) {
		return super.createPageArea(parent);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite compo = (Composite) super.createDialogArea(parent);
		Control[] childs = compo.getChildren();
		Control[] fdf = ((Composite) childs[0]).getChildren();
		TabFolder tab = (TabFolder) fdf[0];
		Control[] kj = tab.getChildren();
		Composite klo = (Composite) kj[0];
		Control[] kloi = klo.getChildren();
		Composite juh = (Composite) kloi[1];
		Control[] l3 = juh.getChildren();
		Group hy = (Group) l3[0];

		Button but = new Button(hy, SWT.RADIO);
		but.setText("Search In Open Files");
		return compo;
	}

	@Override
	protected void buttonPressed(int buttonId) {
		if (buttonId == SearchDialog.OK) {
			IEditorReference[] openEditors = PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow().getActivePage()
					.getEditorReferences();
			IFile[] files = new IFile[openEditors.length];
			for (int i = 0; i < openEditors.length; i++) {
				try {
					files[i] = ResourceUtil.getFile(openEditors[i]
							.getEditorInput());
				} catch (PartInitException e) {
					e.printStackTrace();
				}
			}
			IViewPart nave = PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow().getActivePage()
					.findView("org.eclipse.ui.views.ResourceNavigator");
			TreeViewer viewer = (TreeViewer) nave.getSite()
					.getSelectionProvider();
			viewer.setSelection(new StructuredSelection(files));
			try {
				PlatformUI.getWorkbench().getActiveWorkbenchWindow()
						.getActivePage()
						.showView("org.eclipse.ui.views.ResourceNavigator");
			} catch (PartInitException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			setSelectedScope(ISearchPageContainer.SELECTION_SCOPE);
			super.buttonPressed(buttonId);
		}
	}

	@Override
	protected Control createContents(Composite parent) {
		return super.createContents(parent);
	}

}
