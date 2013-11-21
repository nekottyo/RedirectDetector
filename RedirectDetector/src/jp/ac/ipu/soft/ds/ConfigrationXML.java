/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jp.ac.ipu.soft.ds;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author taro
 */
public class ConfigrationXML {
    private Properties conf;
    
    public ConfigrationXML(String fileName) {
        conf = new Properties();
        try{
            conf.loadFromXML(new FileInputStream(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public String getProperty(String key) {
        if(conf.containsKey(key)) {
            return conf.getProperty(key);
        } else {
            System.err.println("Key not found: " + key);
            return "";
        }
    }
    
    public void addProperty (String key, String value) {
            conf.setProperty(key, value);
    }
    
    public void storeToXML(String fileName) {
        try {
            conf.storeToXML(new FileOutputStream(fileName), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }        
}
