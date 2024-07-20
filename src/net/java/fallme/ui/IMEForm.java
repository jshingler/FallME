/**
 * FallME from java.net (https://fallme.dev.java.net)
 * Copyright Jim Shingler (jimshing@aol.com) and Christopher M. Judd (cjudd@juddsolutions.com)
 * License: Apache 2.0
 */
package net.java.fallme.ui;

import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;

/**
 * 
 *  Depending upon the libraries being used, sometime you don't have the ability
 *  to extend MEForm and have to implement it within your application.
 *  (E.g.) ME Polish
 */
public interface IMEForm {

    /*
     * Signals Form to initialize itself
     */
    public void init();

    public void setDisplay(Display display);

    public void setPreviousScreen(Displayable parent);

    public Displayable getNextScreen();

    public void setNextScreen(Displayable nextScreen);
}
