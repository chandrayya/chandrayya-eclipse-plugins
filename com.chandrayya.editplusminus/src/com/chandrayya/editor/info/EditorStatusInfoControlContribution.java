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

import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.NotEnabledException;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentListener;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.menus.WorkbenchWindowControlContribution;
import org.eclipse.ui.services.IServiceLocator;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;

public class EditorStatusInfoControlContribution extends WorkbenchWindowControlContribution {

    private Label sel;
    private Label chars;
    private Label lines;

    private final String SEL = "Sel:";
    private final String CHARS = "Chars:";
    private final String LINES = "Lines:";
    private Clipboard cb;
    private ISelectionListener iSelListener;

    public EditorStatusInfoControlContribution() {
    }

    public EditorStatusInfoControlContribution(String id) {
        super(id);
    }

    @Override
    protected Control createControl(final Composite parent) {
        cb = new Clipboard(parent.getDisplay());
        Composite container = new Composite(parent, SWT.NONE);
        GridLayout glContainer = new GridLayout(3, false);
        glContainer.marginHeight = 0;
        glContainer.marginWidth = 0;
        container.setLayout(glContainer);
        GridData layoutData = new GridData();
        layoutData.widthHint = 100;
        addSelectionListerner();
        MouseListener listener = new MouseListener() {

            @Override
            public void mouseUp(MouseEvent e) {

            }

            @Override
            public void mouseDown(MouseEvent e) {

            }

            @Override
            public void mouseDoubleClick(MouseEvent e) {

                // Obtain IServiceLocator implementer, e.g. from
                // PlatformUI.getWorkbench():
                IServiceLocator serviceLocator = PlatformUI.getWorkbench();
                // or a site from within a editor or view:
                // IServiceLocator serviceLocator = getSite();

                ICommandService commandService = (ICommandService)serviceLocator.getService(ICommandService.class);

                // Lookup commmand with its ID
                Command command = commandService.getCommand("org.eclipse.debug.internal.ui.actions.DebugDropDownAction");

                // Optionally pass a ExecutionEvent instance, default no-param
                // arg creates blank event
                try {
                    command.executeWithChecks(new ExecutionEvent());
                } catch (ExecutionException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (NotDefinedException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (NotEnabledException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (NotHandledException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                GetEditorDetails details = new GetEditorDetails();
                IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
                MessageDialog.openInformation(parent.getShell(), activeWorkbenchWindow.getActivePage().getActiveEditor()
                        .getEditorInput().getName(), details.getDetails());
            }
        };
        lines = new Label(container, SWT.LEFT);
        Font font = lines.getFont();
        FontData[] fontData = font.getFontData();
        for (int i = 0; i < fontData.length; i++) {
            fontData[i].setHeight(9);
        }
        lines.setFont(font);
        lines.addMouseListener(listener);
        lines.setLayoutData(layoutData);
        chars = new Label(container, SWT.LEFT);
        chars.setFont(font);
        chars.addMouseListener(listener);
        layoutData = new GridData();
        layoutData.widthHint = 125;
        chars.setLayoutData(layoutData);
        sel = new Label(container, SWT.LEFT);
        sel.setFont(font);
        sel.addMouseListener(listener);
        layoutData = new GridData();
        layoutData.widthHint = 150;
        sel.setLayoutData(layoutData);

        IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        IEditorPart ed = activeWorkbenchWindow.getActivePage().getActiveEditor();
        PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().addSelectionListener(iSelListener);
        if (ed instanceof ITextEditor) {
            ITextEditor iTextEditor = (ITextEditor)ed;

            IDocumentProvider pr = iTextEditor.getDocumentProvider();
            final IDocument doc = pr.getDocument(ed.getEditorInput());

            doc.addDocumentListener(new IDocumentListener() {

                @Override
                public void documentChanged(DocumentEvent arg0) {
                    chars.setText(CHARS + Integer.toString(doc.getLength()));
                    lines.setText(LINES + Integer.toString(doc.getNumberOfLines()));

                }

                @Override
                public void documentAboutToBeChanged(DocumentEvent arg0) {

                }
            });
            chars.setText(CHARS + Integer.toString(doc.getLength()));
            lines.setText(LINES + Integer.toString(doc.getNumberOfLines()));
            sel.setText(SEL);
        }
        activeWorkbenchWindow.getActivePage().addPartListener(new IPartListener() {

            @Override
            public void partActivated(IWorkbenchPart arg0) {
                if (arg0 instanceof IEditorPart) {
                    IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
                    IEditorPart ed = activeWorkbenchWindow.getActivePage().getActiveEditor();
                    if (ed instanceof ITextEditor) {
                        ITextEditor iTextEditor = (ITextEditor)ed;
                        IDocumentProvider pr = iTextEditor.getDocumentProvider();
                        final IDocument doc = pr.getDocument(ed.getEditorInput());
                        chars.setText(CHARS + Integer.toString(doc.getLength()));
                        lines.setText(LINES + Integer.toString(doc.getNumberOfLines()));
                    }
                }

            }

            @Override
            public void partBroughtToTop(IWorkbenchPart arg0) {

            }

            @Override
            public void partClosed(IWorkbenchPart arg0) {
                if (PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getEditorReferences().length < 1) {
                    resetAll();
                }
            }

            @Override
            public void partDeactivated(IWorkbenchPart arg0) {

            }

            @Override
            public void partOpened(IWorkbenchPart arg0) {

            }

        });
        return container;
    }

    private void addSelectionListerner() {
        iSelListener = new ISelectionListener() {
            @Override
            public void selectionChanged(IWorkbenchPart sourcepart, ISelection selection) {
                if (selection instanceof ITextSelection) {
                    ITextSelection sele = (ITextSelection)selection;
                    if (sele.getLength() > 0) {
                        String textData = sele.getText();
                        TextTransfer textTransfer = TextTransfer.getInstance();
                        cb.setContents(new Object[] { textData }, new Transfer[] { textTransfer });

                        sel.setText(SEL + Integer.toString(sele.getLength()) + "|"
                                + (sele.getEndLine() - sele.getStartLine() + 1));

                    }

                }
            }
        };
    }

    private void resetAll() {
        sel.setText("");
        lines.setText("");
        chars.setText("");
    }

    @Override
    public void dispose() {
        PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().removeSelectionListener(iSelListener);
        super.dispose();
    }
}