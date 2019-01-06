package com.cubiktimer;

import java.util.regex.Pattern;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cubiktimer.util.EncryptService;
import com.cubiktimer.util.Util;

import junit.framework.TestCase;

public class TestEncriptService extends TestCase{

    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testEncriptarClave(){
        assertEquals("Prueba de encripcion de clave", "50c0bbbfc0458f0e480bbc800d079d73", EncryptService.encriptarClave("123456"));
        for (int i=0;i<10;i++) {
        assertTrue("Prueba de encripcion de clave expresion regular:"+(i+1), Pattern.matches("[0-9a-f]{32}", EncryptService.encriptarClave(Util.generarClaveAleatoria())));
        }
        assertNull("Prueba de clave vacía", EncryptService.encriptarClave(""));
    }
    
}