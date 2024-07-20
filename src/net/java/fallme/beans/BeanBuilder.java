/**
 * FallME from java.net (https://fallme.dev.java.net)
 * Copyright Jim Shingler (jimshing@aol.com) and Christopher M. Judd (cjudd@juddsolutions.com)
 * License: Apache 2.0
 */
package net.java.fallme.beans;

import java.util.Enumeration;
import java.util.Hashtable;

import javax.microedition.lcdui.Display;

import net.java.fallme.beans.factory.BeanFactory;
import net.java.fallme.ui.IMEForm;

public class BeanBuilder {

    public Object build(BeanConfig beanConfig) {
        String className = beanConfig.getBeanClassName();
        Class clazz = beanConfig.getBeanClass();
        if (clazz == null) {
            clazz = classForName(beanConfig.getBeanClassName());
        }

        Object bean = null;

        try {
            bean = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        if (bean != null && beanConfig.properties != null) {
            buildProperties(beanConfig, bean);
        }
        
        // Once the form is build initialize it
        if (bean instanceof IMEForm) {
        	((IMEForm)bean).setDisplay((Display)BeanFactory.getBean(BeanConfigs.DISPLAY));
        	((IMEForm)bean).init();
        }

        return bean;
    }

    private void buildProperties(BeanConfig beanConfig, Object bean) {
        Hashtable properties = beanConfig.getProperties();
        BeanConfig value = null;

        if (properties != null) {
            Enumeration propKeysEnum = properties.keys();
            while (propKeysEnum.hasMoreElements()) {
                String key = (String) propKeysEnum.nextElement();
                Object o = properties.get(key);
                // Resolve BeanReference
                if (o instanceof BeanRef) {
                    value = (BeanConfig) BeanConfigs.getBeans().get(((BeanRef) o).getName());
                    if (value != null && value.getBean() == null) {
                        value.setBean(BeanFactory.getBean(value.getName()));
                    }
                } else if (o instanceof BeanConfig) {
                    value = (BeanConfig) properties.get(key);
                }

                map(bean, key, value);
            }
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

    protected void map(Object bean, String key, BeanConfig value) {
    }
}
