package com.jee.esapi;

import com.jee.esapi.codec.EncoderUtils;
import junit.framework.TestCase;
import org.owasp.esapi.codecs.Codec;

/**
 * HTML注入漏洞防御示例，包括HTML、CSS、JavaScript、URL。由于不同的脚本使用不同的解释器，不同的脚本需要采用不同的编码
 * 方式，ESAPI针对不同的脚本提供了不同的编码函数，请大家依据不同的场景使用。
 * <p/>
 * 编程示例：
 * 1. HTML编码：testHTMLEncode()；
 * 2. HTML Attribute编码：testHTMLAttributeEncode()；
 * 3. CSS编码：testCSSEncode()；
 * 4. JavaScript编码：testJavaScriptEncode();
 * 5. URL编码：testURLEncode()；
 * 6. XML编码：testXMLEncode();
 * 7. XML Attribute编码：testXMLAttributeEncode();
 * 8. HTML组合编码：testClientSidePreparedStringEncode();
 */
public class HTMLInjectionTest extends TestCase {

    public HTMLInjectionTest(String name) {
        super(name);
    }

    public static void testHTMLEncode() {
        String inputString = "<script>abcd1234567890</script>";
        System.out.println("");
        System.out.println("testHTMLEncode-->编码前：" + inputString + "，编码后：" + EncoderUtils.htmlEncode(inputString));

        inputString = ",.-_ ";
        System.out.println("testHTMLEncode-->编码前：" + inputString + "，编码后：" + EncoderUtils.htmlEncode(inputString));

        inputString = "one&two";
        System.out.println("testHTMLEncode-->编码前：" + inputString + "，编码后：" + EncoderUtils.htmlEncode(inputString));
    }

    public static void testHTMLAttributeEncode() {
        String inputString = "<script>abcd1234567890</script>";
        System.out.println("");
        System.out.println("testHTMLAttributeEncode-->编码前：" + inputString + "，编码后：" + EncoderUtils.htmlAttributeEncode(inputString));

        inputString = ",.-_ ";
        System.out.println("testHTMLAttributeEncode-->编码前：" + inputString + "，编码后：" + EncoderUtils.htmlAttributeEncode(inputString));

        inputString = " !@$%()=+{}[]";
        System.out.println("testHTMLAttributeEncode-->编码前：" + inputString + "，编码后：" + EncoderUtils.htmlAttributeEncode(inputString));
    }

    public static void testCSSEncode() {
        String inputString = "<script>abcd1234567890</script>";
        System.out.println("");
        System.out.println("testCSSEncode-->编码前：" + inputString + "，编码后：" + EncoderUtils.cssEncode(inputString));

        inputString = ",.-_ ";
        System.out.println("testCSSEncode-->编码前：" + inputString + "，编码后：" + EncoderUtils.cssEncode(inputString));

        inputString = "!@$%()=+{}[]";
        System.out.println("testCSSEncode-->编码前：" + inputString + "，编码后：" + EncoderUtils.cssEncode(inputString));
    }

    public static void testJavaScriptEncode() {
        String inputString = "<script>abcd1234567890</script>";
        System.out.println("");
        System.out.println("testJavaScriptEncode-->编码前：" + inputString + "，编码后：" + EncoderUtils.javaScriptEncode(inputString));

        inputString = ",.-_ ";
        System.out.println("testJavaScriptEncode-->编码前：" + inputString + "，编码后：" + EncoderUtils.javaScriptEncode(inputString));

        inputString = "!@$%()=+{}[]";
        System.out.println("testJavaScriptEncode-->编码前：" + inputString + "，编码后：" + EncoderUtils.javaScriptEncode(inputString));
    }

    public static void testURLEncode() throws Exception {
        String inputString = "<script>abcd1234567890</script>";
        System.out.println("");
        System.out.println("testURLEncode-->编码前：" + inputString + "，编码后：" + EncoderUtils.urlEncode(inputString));

        inputString = ",.-_ &";
        System.out.println("testURLEncode-->编码前：" + inputString + "，编码后：" + EncoderUtils.urlEncode(inputString));

        inputString = "!@$%()=+{}[]";
        System.out.println("testURLEncode-->编码前：" + inputString + "，编码后：" + EncoderUtils.urlEncode(inputString));
    }

    public static void testXMLEncode() {
        String inputString = "<script>abcd1234567890</script>";
        System.out.println("");
        System.out.println("testXMLEncode-->编码前：" + inputString + "，编码后：" + EncoderUtils.xmlEncode(inputString));

        inputString = ",.-_ &";
        System.out.println("testXMLEncode-->编码前：" + inputString + "，编码后：" + EncoderUtils.xmlEncode(inputString));

        inputString = "!@$%()=+{}[]";
        System.out.println("testXMLEncode-->编码前：" + inputString + "，编码后：" + EncoderUtils.xmlEncode(inputString));
    }

    public static void testXMLAttributeEncode() {
        String inputString = "<script>abcd1234567890</script>";
        System.out.println("");
        System.out.println("testXMLAttributeEncode-->编码前：" + inputString + "，编码后：" + EncoderUtils.xmlAttributeEncode(inputString));

        inputString = ",.-_ &";
        System.out.println("testXMLAttributeEncode-->编码前：" + inputString + "，编码后：" + EncoderUtils.xmlAttributeEncode(inputString));

        inputString = "!@$%()=+{}[]";
        System.out.println("testXMLAttributeEncode-->编码前：" + inputString + "，编码后：" + EncoderUtils.xmlAttributeEncode(inputString));
    }

    /**
     * A parameterized string that uses escaping to make untrusted data safe before combining it
     * with a command or query intended for use in an interpreter.
     *
     * @throws Exception
     */
    public static void testClientSidePreparedStringEncode() {
        //缺省占位符为"?"，若模板字符串中有正常的"?"，需要用其他字符作为占位符，
        //调用EncoderUtils.webPreparedString(String strTemplate, String[] paras, Codec[] codecs, char placeholder)
        //本示例采用缺省占位符。
        String templateString = "<a href=\"http:\\\\example.com;id=?\" onmouseover=\"alert('?')\">test:?</a>";
        //templateString中第一个?是URL组成部分，第二个？是JavaScript，第三个？是HTML。分别使用不同的Codec进行编码。

        String[] paras = new String[3];
        Codec[] codecs = new Codec[3];
        paras[0] = "ID#001";
        paras[1] = "This is a test.";
        paras[2] = "Push Me!";
        codecs[0] = EncoderUtils.PERCENT_CODEC;
        codecs[1] = EncoderUtils.JAVASCRIPT_CODEC;
        codecs[2] = EncoderUtils.HTML_CODEC;

        System.out.println("");
        System.out.println("testClientSidePreparedStringEncode-->编码前：" + templateString + "，编码后：" + EncoderUtils.clientSidePreparedString
                (templateString, paras, codecs));
    }

}
