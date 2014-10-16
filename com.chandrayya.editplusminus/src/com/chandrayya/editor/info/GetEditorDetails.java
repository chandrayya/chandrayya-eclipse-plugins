/*******************************************************************************
 * Copyright (c) 2014 Chandrayya G K
 * All rights reserved.
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

import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

public class GetEditorDetails {

    public String getDetails() {
        StringBuffer details = new StringBuffer();
        IEditorPart edi = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
        FileEditorInput file = (FileEditorInput)edi.getEditorInput();
        details.append("Encoding: ");
        File mm = file.getPath().toFile();
        try {
            details.append(file.getFile().getCharset());
        } catch (CoreException e) {
            e.printStackTrace();
        }
        details.append("\nLast Modified: ");
        details.append(mm.lastModified());
        return details.toString();
    }
}
