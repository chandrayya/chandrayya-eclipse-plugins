package com.chandrayya.editor.info.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.chandrayya.editor.info.Activator;

public class ChandrayyaPluginsPreferencePage extends FieldEditorPreferencePage
		implements IWorkbenchPreferencePage {

	public ChandrayyaPluginsPreferencePage() {
		setTitle("Chandrayya Plugins");
	}

	@Override
	public void init(IWorkbench workbench) {
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
	}

	@Override
	protected void createFieldEditors() {
		BooleanFieldEditor autoCopy = new BooleanFieldEditor("AUTO_COPY",
				"Auto Copy", getFieldEditorParent());
		addField(autoCopy);
	}
}