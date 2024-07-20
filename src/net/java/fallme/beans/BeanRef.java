/**
 * FallME from java.net (https://fallme.dev.java.net)
 * Copyright Jim Shingler (jimshing@aol.com) and Christopher M. Judd (cjudd@juddsolutions.com)
 * License: Apache 2.0
 */
package net.java.fallme.beans;

public class BeanRef {

    private String name;

    public BeanRef() {
    }

    public BeanRef(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public BeanRef setName(String name) {
        this.name = name;
        return this;
    }

    public String name() {
        return getName();
    }

    public BeanRef name(String name) {
        return setName(name);
    }
}
