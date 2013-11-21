/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jp.ac.ipu.soft.ds;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author taro
 */
class DBpediaConnector {
    URL preUrl;
    HttpURLConnection connection;
    Integer responseCode;
    String responseMessage;
    String currentUrl;

    
    public DBpediaConnector(URL url) throws IOException {
        preUrl = new URL(url.toString().replaceAll("resource", "page"));
        connection = (HttpURLConnection) preUrl.openConnection();
        responseCode = connection.getResponseCode();
        responseMessage = connection.getResponseMessage();   
        currentUrl = connection.getURL().toString();
    }
    
    /**
     * 
     * @param url
     * @return リダイレクト(接続URLと現在のURLが別)を検出したらtrue
     * @throws IOException 
     */
    public Boolean isRedirect() throws IOException {
        return !preUrl.toString().equals(getCurrentUrl());
    }
    
    /**
     *  HTMLをParseして、NotFoundになったら
     * @return
     * @throws MalformedURLException 
     */
    public Boolean isNoEntry() throws MalformedURLException {
        HtmlParser parser = new HtmlParser(new URL(getCurrentUrl()));
        if(parser.getElementContents(getCurrentUrl())){
            return false;
        } else {
            return true;
        }
    }
    
//    /**
//     * dbpedia.org/resource/**** -> dbpedia.org/page/****
//     * @param url
//     * @return 
//     */
//    public URL replaceSubjectmURL(URL url) throws MalformedURLException {
//        return new URL(url.toString().replaceAll("resource", "page"));
//    }
    
    
    public URL getPreUrl() {
        return preUrl;
    }

    public HttpURLConnection getConnection() {
        return connection;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public String getCurrentUrl() {
        return currentUrl;
    }

    @Override
    public String toString() {
        return "DBpediaConnector{PreviousURL: "+ getPreUrl().toString() 
                + "\n\tCurrentURL: " + getCurrentUrl() 
                + "\tResponseCode: " + getResponseCode()
                + "  ResponseMessage: " + getResponseMessage()
                +" }";
    }
}