/*******************************************************************************
 *
 * This component and the accompanying materials are made available
 * under the terms of the "Eclipse Public License v1.0"
 * which is available at the URL "http://www.eclipse.org/legal/epl-v10.html".
 *
 * Contributors:
 * Chandrayya G K - initial version
 *******************************************************************************/

package com.chandrayya.jdt.custom.editor.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

/**
 * Displays source code corresponding to the selected/marked cycles.
 */
public class StyleHandler extends AbstractHandler {

	/**
	 * Default handler
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		/*
		 * Event selEvent = (Event)event.getTrigger(); ToolItem item =
		 * (ToolItem)selEvent.widget; CompilationUnitEditor comEditor; if
		 * (item.getSelection()) { TextPresentation style = new
		 * TextPresentation(); IEditorPart editor =
		 * PlatformUI.getWorkbench().getActiveWorkbenchWindow
		 * ().getActivePage().getActiveEditor(); if (editor instanceof
		 * CompilationUnitEditor) { comEditor = (CompilationUnitEditor)editor;
		 * ISourceViewer view = comEditor.getViewer(); IEditorInput input =
		 * comEditor.getEditorInput(); IDocument docu =
		 * comEditor.getDocumentProvider().getDocument(input); if (input
		 * instanceof IFileEditorInput) { IFileEditorInput fg =
		 * (IFileEditorInput)input; IFile file = fg.getFile();
		 * 
		 * try { LineNumberReader red = new LineNumberReader(new
		 * FileReader(file.getLocation().toString())); String line = null;
		 * boolean coloron = false; boolean coloroff = true; int sum = 0; int
		 * offset = 0; Color bgc = null; Color fgc = null; while ((line =
		 * red.readLine()) != null) { if
		 * (line.trim().toLowerCase().matches("//\\s*task\\s+.*")) { IViewPart
		 * tView =
		 * PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
		 * .findView("org.eclipse.ui.views.TaskList"); ISelectionProvider selPro
		 * = tView.getViewSite().getSelectionProvider();
		 * 
		 * if (selPro instanceof ITreeSelection) { ITreeSelection sel =
		 * (ITreeSelection)selPro; // sel.toList().add(new ); } } if (coloroff
		 * && line.trim().toLowerCase().matches("//\\s*color\\s*on\\s*:.*")) {
		 * offset = docu.getLineOffset(red.getLineNumber()); line = line.trim();
		 * String[] clr = line.substring(line.indexOf("bg:") + 3,
		 * line.indexOf("fg")).split(",");
		 * 
		 * bgc = new Color(Display.getCurrent(),
		 * Integer.parseInt(clr[0].trim()), Integer.parseInt(clr[1] .trim()),
		 * Integer.parseInt(clr[2].trim())); clr =
		 * line.substring(line.indexOf("fg:") + 3).split(","); fgc = new
		 * Color(Display.getCurrent(), Integer.parseInt(clr[0].trim()),
		 * Integer.parseInt(clr[1] .trim()), Integer.parseInt(clr[2].trim()));
		 * coloroff = false; coloron = true; continue; }
		 * 
		 * if (line.trim().toLowerCase().matches("//\\s*color\\s*off")) {
		 * coloron = false; coloroff = true; System.out.println(offset);
		 * System.out.println(sum); System.out.println(fgc.toString());
		 * System.out.println(bgc.toString()); StyleRange range = new
		 * StyleRange(offset, sum, fgc, bgc); style.addStyleRange(range); //
		 * view.getTextWidget().setStyleRange(range); } if (coloron) { sum +=
		 * line.length() + 1; } } } catch (FileNotFoundException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } catch (Exception e)
		 * {
		 * 
		 * } } view.changeTextPresentation(style, true); //
		 * view.getTextWidget().redraw(); }
		 * 
		 * } else { // Remove style }
		 */
		return null;
	}
}
