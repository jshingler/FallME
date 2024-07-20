/**
 * FallME from java.net (https://fallme.dev.java.net)
 * Copyright Jim Shingler (jimshing@aol.com) and Christopher M. Judd (cjudd@juddsolutions.com)
 * License: Apache 2.0
 */
package net.java.fallme.beans;

import java.util.Enumeration;
import java.util.Hashtable;

public class BeanConfigs {

    public static String MIDLET = "MIDLet";
    public static String DISPLAY = "Display";
    private static Hashtable beans = new Hashtable();

    public static void set(BeanConfig beanConfigs) {
        beans.put(beanConfigs.name, beanConfigs);
    }

    public static Hashtable getBeans() {
        return beans;
    }

    public static void importConfig(Class beanConfigClass) {
        beanConfigClass.getName();
        BeanConfigs beanConfigs;

        try {
            beanConfigs = (BeanConfigs) beanConfigClass.newInstance();
        } catch (InstantiationException e) {
            return;
        } catch (IllegalAccessException e) {
            return;
        }

//		beans.putAll(beanConfigs.getBeans());

        Hashtable ht = beanConfigs.getBeans();
        Enumeration eKeys = ht.keys();
        while (eKeys.hasMoreElements()) {
            Object key = eKeys.nextElement();
            Object value = ht.get(key);
            beans.put(key, value);
        }

    }
}
