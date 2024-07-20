package net.java.fallme.examples.preferences.ui;

import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.List;

import net.java.fallme.beans.factory.BeanFactory;
import net.java.fallme.examples.preferences.PreferencesMobile;
import net.java.fallme.ui.MEForm;


public class MainForm extends MEForm implements CommandListener { 
    private static final Command exitCommand = new Command("Exit",
            Command.EXIT, 2);
    private static final Command backToMainCommand = new Command("Back",
            Command.BACK, 3);
    private static final Command preferenceCommand = new Command("Pref",
            Command.SCREEN, 1);
    private PreferenceForm preferenceForm;
    ChoiceGroup group = new ChoiceGroup("My Group",
            ChoiceGroup.EXCLUSIVE);
    List menuScreen;

    public MainForm() {
        super("Preferences Example");
    }

    public void init() {
        createForm();
        load();
        makeCommands();
    }

    public void showNotify() {
        makeCommands();
    }

    private void makeCommands() {
        addCommand(exitCommand);
        addCommand(preferenceCommand);
        setCommandListener(this);
    }

    private void load() {
    }

    protected void createForm() {
    }

    public void commandAction(Command c, Displayable d) {
        if (c == backToMainCommand) {
            navigateTo(this);
        } else if (c == preferenceCommand) {
            navigateTo(getPreferenceForm());
        } else if (c == exitCommand) {
        	PreferencesMobile mobile = (PreferencesMobile) BeanFactory.getMidlet();
        	mobile.exitApp();
        }
    }

    public PreferenceForm getPreferenceForm() {
        preferenceForm.setPreviousScreen(this);

        return preferenceForm;
    }

    public void setPreferenceForm(PreferenceForm preferenceForm) {
        this.preferenceForm = preferenceForm;
    }
}
