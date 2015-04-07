/*******************************************************************************
 *
 * This component and the accompanying materials are made available
 * under the terms of the "Eclipse Public License v1.0"
 * which is available at the URL "http://www.eclipse.org/legal/epl-v10.html".
 *
 * Contributors:
 * Chandrayya G K - initial version
 *******************************************************************************/
package com.chandrayya.editor.info;

import java.io.File;
import java.text.DateFormat;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

public class GetEditorDetails {

	public String getDetails() {
		StringBuffer details = new StringBuffer();
		IEditorPart edi = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
				.getActivePage().getActiveEditor();
		FileEditorInput file = (FileEditorInput) edi.getEditorInput();
		details.append("Encoding: ");
		File mm = file.getPath().toFile();
		try {
			details.append(file.getFile().getCharset());
		} catch (CoreException e) {
			e.printStackTrace();
		}
		details.append("\nLast Modified: ");
		DateFormat format = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.MEDIUM);
		details.append(format.format(mm.lastModified()));
		File fl = new File(file.getFile().getRawLocation().toOSString());
		details.append("\nFile Size:  " + fl.length());
		return details.toString();
	}
}
