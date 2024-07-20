/**
 * FallME from java.net (https://fallme.dev.java.net)
 * Copyright Jim Shingler (jimshing@aol.com) and Christopher M. Judd (cjudd@juddsolutions.com)
 * License: Apache 2.0
 */
package net.java.fallme.beans.factory;

import java.util.Enumeration;
import java.util.Hashtable;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;

import net.java.fallme.beans.BeanBuilder;
import net.java.fallme.beans.BeanConfig;
import net.java.fallme.beans.BeanConfigs;

public class BeanFactory {

    private static Hashtable beanConfigs = new Hashtable();

    public static void clearBeanConfigs() {
        beanConfigs = new Hashtable();
    }

    public static Hashtable getBeanConfigs() {
        return beanConfigs;
    }

    public static void setBeanConfigs(BeanConfigs beanConfigs) {
//		BeanFactory.beanConfigs.putAll(beanConfigs.getBeans());

        Hashtable ht = beanConfigs.getBeans();
        Enumeration eKeys = ht.keys();
        while (eKeys.hasMoreElements()) {
            Object key = eKeys.nextElement();
            Object value = ht.get(key);
            BeanFactory.beanConfigs.put(key, value);
        }
    }

    public static void setBeanConfigs(Class configClass) {
        BeanConfigs configs = null;
        try {
            configs = (BeanConfigs) configClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        if (configs != null) {
            BeanFactory.setBeanConfigs(configs);
        }
    }

    public static void setBeanConfigs(String configName) {
        Class configClass;
        try {
            configClass = Class.forName(configName);
            BeanFactory.setBeanConfigs(configClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Object getBean(String name) {
        BeanConfig beanConfig = (BeanConfig) beanConfigs.get(name);
        if (beanConfig != null && beanConfig.getBean() != null && beanConfig.isSingleton()) {
            return beanConfig.getBean();
        }

        Object bean = null;

        if (beanConfig != null) {
            bean = build(beanConfig);
            beanConfig.setBean(bean);
        }

        return bean;
    }

    private static Object build(BeanConfig beanConfig) {
        if (beanConfig == null) {
            return null;
        }
        Object bean = null;
        BeanBuilder builder = null;

        Class clazz = beanConfig.getBuilderClass();
        if (clazz == null) {
            clazz = classForName(beanConfig.getBuilderClassName());
        }
        try {
            builder = (BeanBuilder) clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        // Call the Specific Builder
        bean = builder.build(beanConfig);

        return bean;
    }

    public static Display getDisplay() {
        return (Display) getBean(BeanConfigs.DISPLAY);
    }

    public static void setDisplay(Display display) {
        beanConfigs.put(BeanConfigs.DISPLAY, new BeanConfig(BeanConfigs.DISPLAY).setBean(display));
    }

    public static MIDlet getMidlet() {
        return (MIDlet) getBean(BeanConfigs.MIDLET);
    }

    public static void setMidlet(MIDlet midlet) {
        beanConfigs.put(BeanConfigs.MIDLET, new BeanConfig(BeanConfigs.MIDLET).setBean(midlet));
        if (getDisplay() == null) {
            setDisplay(Display.getDisplay(midlet));
        }
    }

    private static Class classForName(String name) {
        Class c = null;
        try {
            c = Class.forName(name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return c;
    }
}
