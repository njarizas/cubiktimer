package com.cubiktimer;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cubiktimer.config.ConexionDatabase;

import junit.framework.TestCase;

public class TestConexionDatabase extends TestCase {
	
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
    public void testConexion(){
        assertNotNull("Probando conexion JDBC", ConexionDatabase.getInstance());
    }

}
