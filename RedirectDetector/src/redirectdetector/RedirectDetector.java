/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package redirectdetector;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author taro
 */
public class RedirectDetector {
    static String configFileName = "./conf/config.cnf";

    /**
     * @param args the command line arguments
     */
    @SuppressWarnings("empty-statement")
    public static void main(String[] args) {
        // TODO code application logic here
        String readFileName ="./data/labels_en.nt";
        String writeFileName = "./data/redirects.txt";
        int redirectCount = 0;
        
        LineNumberReader nr = null;
        URL url = null;
        Map<String, String> redirectsMap = new HashMap<>();
        BufferedReader br = null;
        PrintWriter pw = null;
        int tripleCount = 0;
      
       
        
        try {              
            br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(configFileName))));
            tripleCount = Integer.parseInt(br.readLine());

            //file open
            nr = new LineNumberReader(new FileReader(readFileName));
            String s =  nr.readLine();;
            for(int i = 0; i <= tripleCount; i++) {
                nr.readLine();
            }
            br.close();
        
            while((s = nr.readLine()) != null){
                 //configFileに50行やったら書き込み
                if(tripleCount%50 == 0){
                    PrintWriter pw2 = new PrintWriter(new BufferedWriter(new FileWriter(new File(configFileName))));
                    pw2.println(tripleCount);
                    pw2.close();
                    System.out.println("CurrentCount: " + tripleCount);
                }
                
                url = new URL(getSubjectString(s));
                
                //コネクションを確立
                connectionEstablish(url);

                //現在の状況を表示
                System.out.println("Connecting: " + url.toString()+ "\t" + "Code:" + responseCode + " Message:" + responseMessage);
                connection.getURL().toString();

                //リダイレクト検出
                if(isRedirect(url) ||) {
                    System.out.println("\tFind Redirect :" + redirectCount++);
                    //redirectsMap.put(uri.toString(), redirectUri.toString());
                    pw = new PrintWriter(new BufferedWriter(new FileWriter(new File(writeFileName), true)));
                    pw.println("<" +url.toString() + ">, <" + redirectUri.toString() + "> " + responseCode + " " + responseMessage + "count: " + tripleCount);
                    pw.close();
                }
                tripleCount++;
                Thread.sleep(8000);
            }     
            System.out.println(redirectCount);
            
       } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();;
        }
    }
    
    /**
     * 
     * @param line - nTriplesファイルの1line
     * @return Subject
     */
    public static String getSubjectString(String line) { 
        String buffString = null;
        
        if(line.startsWith("<")){
            buffString = line.substring(line.indexOf("<") + 1, line.indexOf(">")).trim();
        }
        return buffString;
    }
    
    /**
     * 
     * @param line - nTriplesファイルの1line
     * @return Property
     */
    public static String getPropertyString(String line) {
        String buffString = null;
        
        if(line.startsWith("<")){
            line = line.substring(line.indexOf(">") +1);
            buffString = line.substring(line.indexOf("<") + 1, line.indexOf(">")).trim();
        }
        return buffString;
    }
    
    /**
     *
     * @param line - nTriplesファイルの1line
     * @return Object
     */
    public static String getObjectString(String line) { 
        String buffString = null;
        
        if(line.startsWith("<")){
            line = line.substring(line.indexOf(">") + 1);
            line = line.substring(line.indexOf(">") + 1).trim();
            buffString = line.substring(1, line.indexOf("@") - 1);
        }
        return buffString;
    }
    

    

}

class dbpediaConnecter {
    static URL preUrl;
    static HttpURLConnection connection;
    static Integer responseCode;
    static String responseMessage;
    static String currentUrl;
    
    /**
     * 
     * @param url
     * @throws IOException 
     * @param コネクションを確立
     */
    public dbpediaConnecter(URL url) throws IOException {
        preUrl = url;
        connection = (HttpURLConnection) url.openConnection();
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
        return !preUrl.toString().equals(currentUrl.toString());
    }
    
    public Boolean isNoEntry() {
        
        return null;
    }

    
    
    public static URL getPreUrl() {
        return preUrl;
    }

    public static HttpURLConnection getConnection() {
        return connection;
    }

    public static Integer getResponseCode() {
        return responseCode;
    }

    public static String getResponseMessage() {
        return responseMessage;
    }

    public static String getCurrentUrl() {
        return currentUrl;
    }
    
    
    
}
