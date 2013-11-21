/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jp.ac.ipu.soft.ds;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
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
public class DBpediaConnectorTest {
    static DBpediaConnector instance;
    final static String urls = "http://dbpedia.org/page/Anbar_(town)";
    public DBpediaConnectorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws Exception {
        instance = new DBpediaConnector(new URL("http://dbpedia.org/resource/Anbar_(town)"));
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test of isRedirect method, of class DBpediaConnector.
     */
    @Test
    public void testIsRedirect() throws Exception {
        System.out.println("isRedirect");
        Boolean expResult = false;
        Boolean result = instance.isRedirect();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of isNoEntry method, of class DBpediaConnector.
     */
    @Test
    public void testIsNoEntry() throws IOException {
        System.out.println("isNoEntry");
        Boolean expResult = false;
        Boolean result = instance.isNoEntry();
        assertEquals(expResult, result);
        
        instance = new DBpediaConnector(new URL("http://dbpedia.org/page/lkjeidl_()"));
        assertEquals(true, instance.isNoEntry());

        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getPreUrl method, of class DBpediaConnector.
     */
    @Test
    public void testGetPreUrl() throws IOException {
        System.out.println("getPreUrl");
        URL expResult = new URL(urls);
        URL result = instance.getPreUrl();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getConnection method, of class DBpediaConnector.
     */
    @Test
    public void testGetConnection() {
//        try {
//            System.out.println("getConnection");
//            HttpURLConnection expResult =  instance.getCurrentUrl();
//            
//            HttpURLConnection result = instance.getConnection();
//            assertEquals(expResult, result);
//            // TODO review the generated test code and remove the default call to fail.
//           // fail("The test case is a prototype.");
//        } catch (Exception e) {
//            Logger.getLogger(DBpediaConnectorTest.class.getName()).log(Level.SEVERE, null, e);
//        }
    }

    /**
     * Test of getResponseCode method, of class DBpediaConnector.
     */
    @Test
    public void testGetResponseCode() {
        System.out.println("getResponseCode");
        Integer expResult = 200;
        Integer result = instance.getResponseCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getResponseMessage method, of class DBpediaConnector.
     */
    @Test
    public void testGetResponseMessage() {
        System.out.println("getResponseMessage");
        String expResult = "OK";
        String result = instance.getResponseMessage();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getCurrentUrl method, of class DBpediaConnector.
     */
    @Test
    public void testGetCurrentUrl() {
        System.out.println("getCurrentUrl");
        String expResult = urls;
        String result = instance.getCurrentUrl();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
     //   fail("The test case is a prototype.");
    }

    /**
     * Test of replaceSubjectmURL method, of class DBpediaConnector.
     */
    @Test
    public void testReplaceSubjectmURL() throws MalformedURLException {
        System.out.println("replaceSubjectmURL");
        URL url = new URL(urls);
        URL expResult = new URL("http://dbpedia.org/page/Anbar_(town)");
        URL result = DBpediaConnector.replaceSubjectmURL(url);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class DBpediaConnector.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
       
        String expResult = "";
        String result = instance.toString();
        System.out.println(instance.toString());
        // assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }
    
}
