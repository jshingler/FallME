/**
 * FallME from java.net (https://fallme.dev.java.net)
 * Copyright Jim Shingler (jimshing@aol.com) and Christopher M. Judd (cjudd@juddsolutions.com)
 * License: Apache 2.0
 */
package net.java.fallme.beans;

import java.util.Hashtable;

public class BeanConfig implements IBeanConfig {

    public String name;
    public BeanRef ref;
    public Hashtable properties;
    public String beanClassName;
    public Class beanClass;
    public String builderClassName = "com.juddsolutions.me.beans.BeanBuilder";
    public Class builderClass = BeanBuilder.class;
    public boolean singleton = true;
    public Object bean;

    public BeanConfig() {
    }

    public BeanConfig(String name) {
        setName(name);
    }

    public Object getBean() {
        return bean;
    }

    public BeanConfig setBean(Object bean) {
        this.bean = bean;
        return this;
    }

    public String getBuilderClassName() {
        return builderClassName;
    }

    public BeanConfig setBuilderClassName(String builderName) {
        this.builderClassName = builderName;
        return this;
    }

    public String getBeanClassName() {
        return beanClassName;
    }

    public BeanConfig setBeanClassName(String className) {
        this.beanClassName = className;
        return this;
    }

    public BeanConfig setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
        setBeanClassName(beanClass.getName());
        return this;
    }

    public BeanConfig setBean(Class beanClass) {
        return setBeanClass(beanClass);
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public String getName() {
        return name;
    }

    public BeanConfig setName(String name) {
        this.name = name;
        return this;
    }

    public boolean isSingleton() {
        return singleton;
    }

    public BeanConfig setSingleton(boolean singleton) {
        this.singleton = singleton;
        return this;
    }

    public BeanRef getRef() {
        return ref;
    }

    public BeanConfig setRef(BeanRef ref) {
        this.ref = ref;
        return this;
    }

    public BeanConfig ref(BeanRef ref) {
        return setRef(ref);
    }

    public BeanConfig property(String name, BeanRef ref) {
        getProperties().put(name, ref);
        return this;
    }

    // IDEA: create a value class
    public BeanConfig property(String name, String value) {
        getProperties().put(name, value);
        return this;
    }

    public BeanConfig property(String name, BeanConfig beanConfig) {
        getProperties().put(name, beanConfig);
        return this;
    }

    public Hashtable getProperties() {
        if (properties == null) {
            properties = new Hashtable();
        }
        return properties;
    }

    public Class getBuilderClass() {
        return builderClass;
    }

    public BeanConfig setBuilderClass(Class builderClass) {
        this.builderClass = builderClass;
        setBuilderClassName(builderClass.getName());
        return this;
    }

    public BeanConfig setBuilder(Class builderClass) {
        return setBuilderClass(builderClass);
    }
}
