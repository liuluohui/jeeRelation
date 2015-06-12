package com.jee.esapi.codec;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.Encoder;
import org.owasp.esapi.codecs.Codec;
import org.owasp.esapi.codecs.OracleCodec;

/**
 * Created by Administrator on 2015/6/11.
 */
public class OracleEncoder {

    private static final Codec codec = new OracleCodec();

    private static final char[] IMMUNE_SQL = new char[]{' '};


    public static String encode(String param) {
        return param == null ? null : codec.encode(IMMUNE_SQL, param);
    }
}
