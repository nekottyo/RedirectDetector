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
import java.util.Iterator;
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
    public static void main(String[] args) {
        // TODO code application logic here
        String readFileName ="./data/labels_en.nt";
        String writeFileName = "./data/redirects.txt";
        int redirectCount = 0;
        
        LineNumberReader nr = null;
        URL uri = null;
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
                uri = new URL(getSubjectString(s));
                HttpURLConnection connection = (HttpURLConnection) uri.openConnection();

                String redirectUri = connection.getURL().toString();

                //configFileに50行やったら書き込み
                if(tripleCount%50 == 0){
                    PrintWriter pw2 = new PrintWriter(new BufferedWriter(new FileWriter(new File(configFileName))));
                    pw2.println(tripleCount++);
                    pw2.close();
                }
                

                int responseCode = connection.getResponseCode();
                String responseMessage = connection.getResponseMessage();
                System.out.println("Connecting: " + uri.toString()+ "\t" + "Code:" + responseCode + " Message:" + responseMessage);


                //リダイレクト検出
                if(!uri.toString().equals(redirectUri.toString())) {

                    System.out.println("\tFind Redirect :" + redirectCount++);
                    //redirectsMap.put(uri.toString(), redirectUri.toString());
                    pw = new PrintWriter(new BufferedWriter(new FileWriter(new File(writeFileName), true)));
                    pw.println("<" +uri.toString() + ">, <" + redirectUri.toString() + "> " + responseCode + " " + responseMessage + "count: " + tripleCount);
                    pw.close();
                }
                Thread.sleep(8000);
            }     
//            for(Iterator it = redirectsMap.entrySet().iterator(); it.hasNext();) {
//                Map.Entry entry = (Map.Entry)it.next();
//                
//               
//            }
//            pw.println(redirectCount);
            System.out.println(redirectCount);
            
       } catch (IOException e) {
            e.toString();
            e.printStackTrace();
       // } catch () {
            
        } catch (Exception e) {
            e.printStackTrace();;
        }
        pw.close();
    }
    
    /**
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
