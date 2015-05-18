package com.chandrayya.editor.info.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.StringFieldEditor;
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
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		store.setDefault("AUTO_COPY", false);
		store.setDefault("DATE_TIME_FORMAT", "EEE, d MMM yyyy HH:mm:ss Z");
		setPreferenceStore(store);

	}

	@Override
	protected void createFieldEditors() {
		BooleanFieldEditor autoCopy = new BooleanFieldEditor("AUTO_COPY",
				"Auto Copy", getFieldEditorParent());
		addField(autoCopy);
		StringFieldEditor dateTimeFormat = new StringFieldEditor(
				"DATE_TIME_FORMAT", "DATE TIME FORMAT", getFieldEditorParent());
		dateTimeFormat.setStringValue("EEE, d MMM yyyy HH:mm:ss Z");
		dateTimeFormat.setEmptyStringAllowed(false);
		addField(dateTimeFormat);
	}

}