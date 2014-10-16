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
package com.chandrayya.openquickly.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import org.eclipse.ui.views.IViewDescriptor;
import org.eclipse.ui.views.IViewRegistry;

public class QuickOpenHandler extends AbstractHandler {

    /**
     * Default handler
     */
    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        ElementListSelectionDialog dialog = new ElementListSelectionDialog(Display.getCurrent().getActiveShell(),
                new LabelProvider());

        dialog.setTitle("Type launcher Name");
        dialog.setMessage("Select a String (* = any string, ? = any char):");
        IViewRegistry viewRegistry = PlatformUI.getWorkbench().getViewRegistry();
        IViewDescriptor[] views = viewRegistry.getViews();
        String[] viewNames = new String[views.length];
        for (int i = 0; i < viewNames.length; i++) {
            viewNames[i] = views[i].getLabel();
        }

        dialog.setElements(viewNames);
        dialog.open();
        return null;
    }
}
