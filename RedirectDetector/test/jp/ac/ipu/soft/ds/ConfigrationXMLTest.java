/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jp.ac.ipu.soft.ds;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author taro
 */
public class ConfigrationXMLTest {
    
    public ConfigrationXMLTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test of getProperty method, of class ConfigrationXML.
     */
    @Test
    public void testGetProperty() {
        System.out.println("getProperty");
        ConfigrationXML instance = new ConfigrationXML("./conf/config.xml");
        
        assertEquals(instance.getProperty("readFileName"), "./data/38/labels_en.nt");
        assertEquals(instance.getProperty("tripleCount"), "0");
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    /**
     * Test of addProperty method, of class ConfigrationXML.
     */
    @Test
    public void testAddProperty() {
        System.out.println("addProperty");
        String key = "tripleCount";
        String value = "0";
        ConfigrationXML instance = new ConfigrationXML("./conf/config.xml");
        instance.addProperty(key, value);
        
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of storeToXML method, of class ConfigrationXML.
     */
    @Test
    public void testStoreToXML() {
        System.out.println("storeToXML");
        String fileName = "./conf/config.xml";
        ConfigrationXML instance = new ConfigrationXML("./conf/config.xml");
        instance.storeToXML(fileName);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
