/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jp.ac.ipu.soft.ds;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.Source;

/**
 *
 * @author taro
 */
public class HtmlParser {
    private Source html;
    
    public HtmlParser(URL url) {
        try {
            html = new Source(url);
        } catch (FileNotFoundException e) {
            System.err.println("\tNotFound");
        } catch (Exception e) {
            
        }
        
    }
    
    public Boolean getElementContents(String tag) {
        try {
            html.fullSequentialParse();

            //Element body = html.getElementById("body");

            //tagだけ抜き出す
            List<Element> tagedList = html.getAllElements(tag);
            List<String> targetList = new ArrayList<>();

            for (Element element : tagedList) {
                targetList.add(element.getTextExtractor().toString());
            }
            return true;
        } catch (NullPointerException e) {
            System.err.println("\t" + e.toString());
            return false;
        } 
    }
    
    public static void main(String[] args) {
        try {
            //HtmlParser parser = new HtmlParser(new URL("http://dbpedia.org/resource/Ans"));
            DBpediaConnector connector = new DBpediaConnector(new URL("http://dbpedia.org/page/Japanaaaa"));
            
            HtmlParser parser = new HtmlParser(new URL(connector.getCurrentUrl()));

            parser.getElementContents("a");
        } catch (MalformedURLException ex) {
            Logger.getLogger(HtmlParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HtmlParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
