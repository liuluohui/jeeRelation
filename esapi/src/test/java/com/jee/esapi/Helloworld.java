package com.jee.esapi;

import static org.junit.Assert.*;

import org.junit.Test;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.Validator;

import java.io.InputStream;

/**
 * Created by Administrator on 2015/6/9.
 */
public class Helloworld {

    @Test
    public void testValidEmail() {
        Validator validator = ESAPI.validator();
        assertTrue(validator.isValidInput("test", "jeff.williams@aspectsecurity.com", "Email", 100, false));
        assertTrue(validator.isValidInput("test", "jeff.williams@aspectsecurity.com", "Email", 100, false));
    }

    @Test
    public void testInput(){
        InputStream in = getClass().getClassLoader().getResourceAsStream("csrfguard.properties");
        System.out.println(in);
    }
}
