/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jp.ac.ipu.soft.ds;

import java.io.IOException;
import java.net.URL;
import static jp.ac.ipu.soft.ds.RedirectDetector.getSubjectString;
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
public class RedirectDetectorTest {
    
    public RedirectDetectorTest() {
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
     * Test of main method, of class RedirectDetector.
     */
    @Test
    public void testMain() throws IOException, InterruptedException {
         //コネクションを確立
        DBpediaConnector connector = new DBpediaConnector(new URL("http://dbpedia.org/resource/Abbath_Doom_Occuldfdeta"));

        //現在の状況を表示
        System.out.println(connector.toString());

        //リダイレクト検出
        if(connector.isRedirect()) {
            System.out.println("\tFind Redirect :");
        }

        if(connector.isNoEntry()) {
            System.out.println("\tFind NoEntry :");
        }
    }

    /**
     * Test of getSubjectString method, of class RedirectDetector.
     */
    @Test
    public void testGetSubjectString() {
        System.out.println("getSubjectString");
        String line = "";
        String expResult = "";
        String result = RedirectDetector.getSubjectString(line);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPropertyString method, of class RedirectDetector.
     */
    @Test
    public void testGetPropertyString() {
        System.out.println("getPropertyString");
        String line = "";
        String expResult = "";
        String result = RedirectDetector.getPropertyString(line);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getObjectString method, of class RedirectDetector.
     */
    @Test
    public void testGetObjectString() {
        System.out.println("getObjectString");
        String line = "";
        String expResult = "";
        String result = RedirectDetector.getObjectString(line);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
