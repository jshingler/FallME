/**
 * FallME from java.net (https://fallme.dev.java.net)
 * Copyright Jim Shingler (jimshing@aol.com) and Christopher M. Judd (cjudd@juddsolutions.com)
 * License: Apache 2.0
 */
package net.java.fallme.ui;

import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Item;

public abstract class MEForm extends Form implements IMEForm {

    private Display display;
    private Displayable previousScreen;
    private Displayable nextScreen;

//	Form(String title) 
//    Creates a new, empty Form. 
    public MEForm(String title) {
        super(title);
    }

//	Form(String title, Item[] items) 
//  Creates a new Form with the specified contents. 
    public MEForm(String title, Item[] items) {
        super(title, items);
    }

    /*
     * Signals Form to initialize itself
     */
    /* (non-Javadoc)
     * @see net.java.fallme.ui.IMEForm#init()
     */
    public abstract void init();

    /*
     * Creates the form.  Usually invoked by init();
     */
    protected abstract void createForm();

    protected Display getDisplay() {
        return display;
    }

    protected void navigateTo(Displayable displayable) {
        display.setCurrent(displayable);
    }

    /* (non-Javadoc)
     * @see net.java.fallme.ui.IMEForm#setDisplay(javax.microedition.lcdui.Display)
     */
    public void setDisplay(Display display) {
        this.display = display;
    }

    protected Displayable getPreviousScreen() {
        return previousScreen;
    }

    /* (non-Javadoc)
     * @see net.java.fallme.ui.IMEForm#setPreviousScreen(javax.microedition.lcdui.Displayable)
     */
    public void setPreviousScreen(Displayable parent) {
        this.previousScreen = parent;
    }

    /* (non-Javadoc)
     * @see net.java.fallme.ui.IMEForm#getNextScreen()
     */
    public Displayable getNextScreen() {
        return nextScreen;
    }

    /* (non-Javadoc)
     * @see net.java.fallme.ui.IMEForm#setNextScreen(javax.microedition.lcdui.Displayable)
     */
    public void setNextScreen(Displayable nextScreen) {
        this.nextScreen = nextScreen;
    }
}
