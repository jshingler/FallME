package net.java.fallme.examples.preferences.ui;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
//import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.TextField;

import net.java.fallme.examples.preferences.services.PreferencesService;
//import net.java.fallme.ui.IMEForm;
import net.java.fallme.ui.MEForm;

public class PreferenceForm extends MEForm implements CommandListener {
	
	public PreferenceForm(String title) {
		super(title);
	}
	
	public PreferenceForm() {
		super("Preferences");
	}

	static final Command savePrefCommand = new Command("Save", Command.SCREEN, 1);

	static final Command backCommand = new Command("Back", Command.BACK, 0);

	private TextField idText;

	private PreferencesService preferenceService;
	
//	private Display display;
//	private Displayable previousScreen;
//	private Displayable nextScreen;
	
	private static final String defaultId="";
	
	public void setPreferenceService(PreferencesService preferenceService) {
		this.preferenceService = preferenceService;
	}
	
	public void init() {
		createForm();
		load();
		makeCommands();
	}
	
	private void makeCommands() {
		addCommand(savePrefCommand);
		addCommand(backCommand);
		setCommandListener(this);
	}

	private void load() {
		String id = preferenceService.get(PreferencesService.ID);
		if (id == null) {
			id = defaultId;
		}
		
		idText.setString(id);
	}

	protected void createForm() {
		idText = new TextField("Id:", "?", 38, TextField.ANY);
		append(idText);
	}

	public String getId() {
		return idText.getString();
	}

	public void commandAction(Command c, Displayable d) {
		if (c == backCommand) {
			navigateTo(getPreviousScreen());
		} else if (c == savePrefCommand) {
			savePreferences();
			navigateTo(getPreviousScreen());
		}
	}

	 private void savePreferences() {
		preferenceService.put(PreferencesService.ID, getId());
	}

//		protected Display getDisplay() {
//			return display;
//		}
//		
//		protected void navigateTo(Displayable displayable) {
//			display.setCurrent(displayable);
//		}
//
//		public void setDisplay(Display display) {
//			this.display = display;
//		}
//
//		protected Displayable getPreviousScreen() {
//			return previousScreen;
//		}
//
//		public void setPreviousScreen(Displayable parent) {
//			this.previousScreen = parent;
//		}
//
//		public Displayable getNextScreen() {
//			return nextScreen;
//		}
//
//		public void setNextScreen(Displayable nextScreen) {
//			this.nextScreen = nextScreen;
//		}
}
