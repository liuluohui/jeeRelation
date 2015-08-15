package com.jee.esapi;

import com.jee.esapi.codec.OracleEncoder;
import org.junit.Test;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.Encoder;
import org.owasp.esapi.codecs.Codec;
import org.owasp.esapi.codecs.MySQLCodec;
import org.owasp.esapi.codecs.OracleCodec;

/**
 * Created by Administrator on 2015/6/11.
 */
public class TestSQLInjection {


    @Test
    public void testSQLInjection() {
        String query = "SELECT user_id FROM user_data WHERE user_name = '" + 1
                + "' and user_password = '" + 2 + "'";

//{}' or '1=1
        String injectionParam = "{}";
        String tableName = "emall_info_attr;drop table emall_info_attr;select * from emall_info_attr";

        System.out.println("=======origin========");
        System.out.println(appendParam(injectionParam, tableName));

        System.out.println("=======oracle========");
        Codec oracleCodec = new OracleCodec();
        System.out.println(appendParam(ESAPI.encoder().encodeForSQL(oracleCodec, injectionParam), tableName));

        System.out.println("=======custom oracle========");
        OracleEncoder encoder = new OracleEncoder();
        System.out.println(appendParam(encoder.encode(injectionParam), tableName));

        System.out.println("=======MySQLCodec STANDARD========");
        Codec standardMySQLCodec = new MySQLCodec(MySQLCodec.Mode.STANDARD);
        System.out.println(appendParam(ESAPI.encoder().encodeForSQL(standardMySQLCodec, injectionParam), ESAPI.encoder().encodeForSQL(standardMySQLCodec, tableName)));


        System.out.println("=======MySQLCodec ANSI========");
        Codec ansiMySQLCodec = new MySQLCodec(MySQLCodec.Mode.ANSI);
        System.out.println(appendParam(ESAPI.encoder().encodeForSQL(ansiMySQLCodec, injectionParam), ESAPI.encoder().encodeForSQL(ansiMySQLCodec, tableName)));


    }


    private String appendParam(String param, String tableName) {
        return "select id,attr_code,attr_name,attr_value,goods_id,attr_id,is_use from " + tableName + " where attr_code = '" + param + "'";
    }

}
