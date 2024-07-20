/**
 * FallME from java.net (https://fallme.dev.java.net)
 * Copyright Jim Shingler (jimshing@aol.com) and Christopher M. Judd (cjudd@juddsolutions.com)
 * License: Apache 2.0
 */
package net.java.fallme.rms;

import java.io.DataOutputStream;

public interface ByteMapper {

    public void map(DataOutputStream out) throws Exception;
}
