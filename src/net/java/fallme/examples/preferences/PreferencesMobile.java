package net.java.fallme.examples.preferences;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.midlet.MIDlet;

import net.java.fallme.beans.factory.BeanFactory;
import net.java.fallme.examples.preferences.ui.MainForm;

/**
 * Preferences MIDLet
 * 
 */
public class PreferencesMobile extends MIDlet implements CommandListener {

	private Command exitCommand;

	private Display display;

	private Form mainForm;

	/** Constructor */
	public PreferencesMobile() {
		// Initialize Application Context
		BeanFactory.setBeanConfigs(PreferencesMobileBeanConfigs.class.getName());
		BeanFactory.setMidlet(this);

		display = Display.getDisplay(this);
		exitCommand = new Command("Exit", Command.EXIT, 2);
		mainForm = (MainForm) BeanFactory.getBean(PreferencesMobileBeanConfigs.MAINFORM);
	}

	/**
	 * Process commands
	 * 
	 * @param c
	 *            command to process
	 * @param s
	 *            associated displayable component
	 */
	public void commandAction(Command c, Displayable s) {
		if (c == exitCommand) {
			destroyApp(false);
			notifyDestroyed();
		}
	}
	
	public void exitApp() {
		destroyApp(false);
		notifyDestroyed();
	}

	/**
	 * Method called by J2ME when application is destoried. Used for cleanup.
	 */
	protected void destroyApp(boolean parm1) {
	}

	/** Method called by J2ME when application is paused */
	protected void pauseApp() {
	}

	/**
	 * Method called by J2ME when application is started. Used for
	 * initialization
	 */
	protected void startApp() {
		display.setCurrent(mainForm);
	}
}
