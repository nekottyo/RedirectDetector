/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jp.ac.ipu.soft.ds;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author taro
 */
public class RedirectDetector {
    final static private String configFileName = "./conf/config.xml";
    /**
     * @param args the command line arguments
     */
    @SuppressWarnings("empty-statement")
    public static void main(String[] args) {
        LineNumberReader nr = null;
        PrintWriter pw = null;
        ConfigrationXML config = new ConfigrationXML(configFileName);
        
        
        int tripleCount = 0;
        int redirectCount = 0;
        int notFoundCount = 0;
        
        /*
            保存データの書式設定
        */
        Calendar myCal = Calendar.getInstance();
        DateFormat myFormat = new SimpleDateFormat("yyMMdd_HHmm");
        String readFileName = config.getProperty("readFileName");
        String writeFileName = "./data/" + myFormat.format(myCal.getTime()) + "redirects.txt";        

        try {              
            tripleCount = Integer.parseInt(config.getProperty("tripleCount"));

            //file open
            nr = new LineNumberReader(new FileReader(readFileName));
            
            if(config.getProperty("tripleCount").equals("0")) {
                //1行目を読み飛ばす
                String s =  nr.readLine();
            }
            //前回読んだところまで進む
            for(int i = 0; i <= tripleCount; i++) {
                nr.readLine();
            }
        
            //未読トリプルの検査
            while((s = nr.readLine()) != null){
                 //configFileに50行やったら書き込み
                if(tripleCount%50 == 0){
                    config.addProperty("tripleCount", String.valueOf(tripleCount));
                    config.storeToXML(configFileName);
                    System.out.println("CurrentCount: " + tripleCount);
                }
                
                //コネクションを確立
                DBpediaConnector connector = new DBpediaConnector(new URL(getSubjectString(s)));

                //現在の状況を表示
                System.out.println(connector.toString());
                tripleCount++;
                
                int i = 1;
                while(400 < connector.getResponseCode() &&  connector.getResponseCode() < 600) {
                    Thread.sleep(45000*i++);
                    connector = new DBpediaConnector(new URL(getSubjectString(s)));
                    if(i < 5){
                        break;
                    }
                }
               
                //リダイレクト検出
                if(connector.isRedirect()) {
                    System.out.println("\tFind Redirect :"  + ++redirectCount);
                    config.addProperty("redirectCount", String.valueOf(redirectCount));
                    config.storeToXML(readFileName);

                    /* 
                     * 出力ファイルに、以前のURL, 現在のURL, レスポンスメッセージ, レスポンスコード, 行番号を書き込み
                     * ファイルへ書き込み
                     */               
                    pw = new PrintWriter(new BufferedWriter(new FileWriter(new File(writeFileName), true)));
                    pw.println("Redirected\t<" +connector.preUrl.toString() + ">, <" + connector.currentUrl + "> " 
                            + connector.getResponseCode() + " " + connector.getResponseMessage() + "count: " + (tripleCount));
                    pw.close();
                }
                
                if(connector.isNoEntry()) {
                    System.out.println("\tFind NoEntry :" + ++notFoundCount);
                    config.addProperty("notFoundCount", String.valueOf(notFoundCount));
                    config.storeToXML(readFileName);
                    
                    /* 
                     * 出力ファイルに、以前のURL, 現在のURL, レスポンスメッセージ, レスポンスコード, 行番号を書き込み
                     * ファイルへ書き込み
                     */               
                    pw = new PrintWriter(new BufferedWriter(new FileWriter(new File(writeFileName), true)));
                    pw.println("NoEntry\t<" +connector.preUrl.toString() + ">, <" + connector.currentUrl + "> " 
                            + connector.getResponseCode() + ", " + connector.getResponseMessage() + "  count: " + (tripleCount));
                    pw.close();
                }
                
                Thread.sleep(30000);
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

