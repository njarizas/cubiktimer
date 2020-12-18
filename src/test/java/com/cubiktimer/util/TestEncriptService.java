/* CUBIKTIMER - SOFTWARE DE APOYO AL APRENDIZAJE Y LA PRÁCTICA DEL SPEEDCUBING EN COLOMBIA
 * Copyright (c) 2020-present Nelson Ariza
 * Licensed under GPLv3 (https://github.com/njarizas/cubiktimer/blob/master/LICENSE.md) */
package com.cubiktimer.util;

import java.util.regex.Pattern;

import org.junit.Test;

import junit.framework.TestCase;

public class TestEncriptService extends TestCase{
    
    @Test
    public void testEncriptarClave(){
        assertEquals("Prueba de encripcion de clave", "50c0bbbfc0458f0e480bbc800d079d73", EncryptService.encriptarClave("123456"));
        for (int i=0;i<10;i++) {
        assertTrue("Prueba de encripcion de clave expresion regular:"+(i+1), Pattern.matches("[0-9a-f]{32}", EncryptService.encriptarClave(Util.generarClaveAleatoria())));
        }
        assertNull("Prueba de clave vacía", EncryptService.encriptarClave(""));
    }
    
}
