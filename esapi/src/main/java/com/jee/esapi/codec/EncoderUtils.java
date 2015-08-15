package com.jee.esapi.codec;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.Encoder;
import org.owasp.esapi.PreparedString;
import org.owasp.esapi.codecs.CSSCodec;
import org.owasp.esapi.codecs.Codec;
import org.owasp.esapi.codecs.DB2Codec;
import org.owasp.esapi.codecs.HTMLEntityCodec;
import org.owasp.esapi.codecs.JavaScriptCodec;
import org.owasp.esapi.codecs.MySQLCodec;
import org.owasp.esapi.codecs.OracleCodec;
import org.owasp.esapi.codecs.PercentCodec;
import org.owasp.esapi.errors.EncodingException;

import java.io.IOException;

/**
 * Created by Administrator on 2015/8/14.
 */
public class EncoderUtils {

    private static final Encoder encoder = ESAPI.encoder();

    public static final char[] EMPTY_CHAR_ARRAY = new char[0];
    public static final HTMLEntityCodec HTML_CODEC = new HTMLEntityCodec();
    public static final PercentCodec PERCENT_CODEC = new PercentCodec();
    public static final JavaScriptCodec JAVASCRIPT_CODEC = new JavaScriptCodec();
    public static final CSSCodec CSS_CODEC = new CSSCodec();

    /**
     * SQL 编码公共函数
     */
    public static String sqlEncode(String inputString, DBCODEC dbcodec) {
        return encoder.encodeForSQL(dbcodec.codec(), inputString);
    }


    /**
     * 处理preapredStatement sql
     *
     * @param sqlTemplate
     * @param paras
     * @param dbcodec
     * @return
     */
    public static String sqlPreparedString(String sqlTemplate, String[] paras, DBCODEC dbcodec) {
        PreparedString sqlPreparedString = new PreparedString(sqlTemplate, dbcodec.codec());
        for (int i = 0; i < paras.length; i++) {
            sqlPreparedString.set(i + 1, paras[i]);
        }

        return sqlPreparedString.toString();
    }

    /**
     * HTML 转码公共函数，包括HTML、CSS、JavaScript、URL
     */
    public static String htmlEncode(String inputString) {
        return encoder.encodeForHTML(inputString);
    }

    //HTML属性需要与HTML采用不同的编码方法
    public static String htmlAttributeEncode(String inputString) {
        return encoder.encodeForHTMLAttribute(inputString);
    }

    public static String cssEncode(String inputString) {
        return encoder.encodeForCSS(inputString);
    }

    public static String javaScriptEncode(String inputString) {
        return encoder.encodeForJavaScript(inputString);
    }

    public static String urlEncode(String inputString) throws Exception {
        try {
            return encoder.encodeForURL(inputString);
        } catch (EncodingException e) {
            // 将EncodingException转换成Exception，隔离ESAPI Exception。
            // 遵循Law of Demeter，或最少知识原则（Least Knowledge Principle)
            throw new Exception(e);
        }
    }

    public static String urlDecode(String url) throws Exception {
        try {
            return encoder.decodeFromURL(url);
        } catch (EncodingException e) {
            throw new Exception(e);
        }
    }


    /**
     * XML encoding转码公共函数
     *
     * @param inputString
     * @return 转码后的字符串
     */
    public static String xmlEncode(String inputString) {
        return encoder.encodeForXML(inputString);
    }

    public static String xmlAttributeEncode(String inputString) {
        return encoder.encodeForXMLAttribute(inputString);
    }

    /**
     * 处理客户端负责场景脚本编码问题。在HTML客户端脚本包括以下几类：即HTML、CSS、JavaScript、URL等。
     * 不同的脚本由不同的解析器解析，不同的解析器有不同的关键字集合。因此需要采用不同的编码器进行编码。
     *
     * @param strTemplate 模板字符串
     * @param paras       参数数据
     * @param codecs      编码器数据
     * @param placeholder 模板字符串中的占位符
     * @return 转码并格式化之后的字符串
     */
    public static String clientSidePreparedString(String strTemplate, String[] paras, Codec[] codecs, char placeholder) {
        PreparedString clientSidePreparedString = new PreparedString(strTemplate, placeholder, HTML_CODEC);
        for (int i = 0; i < paras.length; i++) {
            clientSidePreparedString.set(i + 1, paras[i], codecs[i]);
        }
        return clientSidePreparedString.toString();
    }

    /**
     * 使用缺省的占位符“？”
     *
     * @param strTemplate
     * @param paras
     * @param codecs
     * @return
     */
    public static String clientSidePreparedString(String strTemplate, String[] paras, Codec[] codecs) {
        return clientSidePreparedString(strTemplate, paras, codecs, '?');
    }


    public static String encodeForBase64(byte[] data) {
        return encoder.encodeForBase64(data, false);
    }

    public static byte[] decodeFromBase64(String text) throws IOException {
        return encoder.decodeFromBase64(text);
    }

    public enum DBCODEC {
        ORACLE(new OracleCodec()),
        MYSQL_ANSI(new MySQLCodec(MySQLCodec.Mode.ANSI)),
        MYSQL_STANDARD(new MySQLCodec(MySQLCodec.Mode.ANSI)),
        DB2(new DB2Codec());

        private Codec codec;

        private DBCODEC(Codec codec) {
            this.codec = codec;
        }

        public Codec codec() {
            return codec;
        }
    }
}
