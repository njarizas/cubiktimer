package xyz.njas;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.TestCase;
import xyz.njas.config.ConexionDatabase;
import xyz.njas.util.EncryptService;

public class Test1 extends TestCase{

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
    }
    
    @Test
    public void testClaveVacia(){
        assertNull("Prueba de clave vacía", EncryptService.encriptarClave(""));
    }
    
    @Test
    public void testConexion(){
        assertNotNull("Probando conexion JDBC", ConexionDatabase.getInstance());
    }

}
