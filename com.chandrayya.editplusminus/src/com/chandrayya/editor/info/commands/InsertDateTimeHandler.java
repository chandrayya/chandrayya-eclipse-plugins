/*******************************************************************************
 *
 * This component and the accompanying materials are made available
 * under the terms of the "Eclipse Public License v1.0"
 * which is available at the URL "http://www.eclipse.org/legal/epl-v10.html".
 *
 * Contributors:
 * Chandrayya G K - initial version
 *******************************************************************************/
package com.chandrayya.editor.info.commands;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.IPreferencesService;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;

import com.chandrayya.editor.info.Activator;

public class InsertDateTimeHandler extends AbstractHandler {

	/**
	 * Default handler
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow();
		IEditorPart ed = activeWorkbenchWindow.getActivePage()
				.getActiveEditor();
		ITextEditor editor = (ITextEditor) ed;
		IDocumentProvider dp = editor.getDocumentProvider();
		IDocument doc = dp.getDocument(editor.getEditorInput());
		int offset = 0;
		ITextSelection textSelection = (ITextSelection) ed.getSite()
				.getSelectionProvider().getSelection();
		offset = textSelection.getOffset();
		Date curDate = new Date();
		IPreferencesService service = Platform.getPreferencesService();
		String dtf = service.getString(Activator.PLUGIN_ID,
				"DATE_TIME_FORMAT", "EEE, d MMM yyyy HH:mm:ss Z", null); //$NON-NLS-1$
		SimpleDateFormat df = new SimpleDateFormat(dtf);
		// System.out.println(df.format(curDate));
		try {
			doc.replace(offset, 0, df.format(curDate));
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		return null;
	}
}
