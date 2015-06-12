package com.jee.esapi;

import org.junit.Before;
import org.junit.Test;
import org.owasp.validator.html.*;

/**
 * Created by Administrator on 2015/6/9.
 */
public class TestAntisamy {


    private String xssHtml;

    @Before
    public void setUp() {
        xssHtml = "<body>\n<div>asdf</div>" +
                "<div id=\"foo\">\n" +
                "<img src=\"javascript:xss()\">\n" +
                "</div>\n" +
                "<b><u>\n" +
                "<p style=\"expression(…) \">\n" +
                "samy is my hero</p>\n" +
                "</u></b>\n" +
                "<a href=\"http://www.google.com\">\n" +
                "Google</a><script src=\"hax.js\">\n" +
                "</script>\n" +
                "<script type=\"text/javascript\">\n" +
                "\tdocument.body.write(\"this is a test\");\n" +
                "</script>";
    }


    @Test
    public void testEbayAntisamy() {

        try {
            Policy policy = Policy.getInstance(getClass().getClassLoader().getResourceAsStream("antisamy-ebay-1.4.4.xml"));

            cleanHtml(policy);
        } catch (PolicyException e) {
            e.printStackTrace();
        } catch (ScanException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testslashdotAntisamy() {

        try {
            Policy policy = Policy.getInstance(getClass().getClassLoader().getResourceAsStream("antisamy-slashdot-1.4.4.xml"));

            cleanHtml(policy);
        } catch (PolicyException e) {
            e.printStackTrace();
        } catch (ScanException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMyspaceAntisamy() {

        try {
            Policy policy = Policy.getInstance(getClass().getClassLoader().getResourceAsStream("antisamy-myspace-1.4.4.xml"));

            cleanHtml(policy);
        } catch (PolicyException e) {
            e.printStackTrace();
        } catch (ScanException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testtinymceAntisamy() {

        try {
            Policy policy = Policy.getInstance(getClass().getClassLoader().getResourceAsStream("antisamy-tinymce-1.4.4.xml"));

            cleanHtml(policy);
        } catch (PolicyException e) {
            e.printStackTrace();
        } catch (ScanException e) {
            e.printStackTrace();
        }
    }

    private void cleanHtml(Policy policy) throws ScanException, PolicyException {
        AntiSamy antiSamy = new AntiSamy();
        System.out.println("dirty html is :" + xssHtml);
        CleanResults results = antiSamy.scan(xssHtml, policy);
        System.out.println("==============");
        System.out.println("scan html cost " + results.getScanTime() + "ms");
        System.out.println("==============");
        System.out.println("clean result is :" + results.getCleanHTML());
        System.out.println("==============");
    }

}
