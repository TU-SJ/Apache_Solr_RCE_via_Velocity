package tools;


import java.io.*;
import java.net.*;

public class HttpTool {
	private static int Timeout = 10000;
    private static String DefalutEncoding = "UTF-8";
    
    public String doGet(String urlAddress){       
        try {    
            URL url = new URL(urlAddress);  
            HttpURLConnection uRLConnection = (HttpURLConnection)url.openConnection();
            InputStream is = uRLConnection.getInputStream();
            //int code = uRLConnection.getResponseCode();
            //System.out.println(code);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));    
            String response = "";    
            String readLine = null;    
            while((readLine =br.readLine()) != null){    
                //response = br.readLine();    
                response = response + readLine + "\n";    
            }    
            is.close();    
            br.close();    
            uRLConnection.disconnect();    
            return response;    
        } catch (MalformedURLException e) {    
            e.printStackTrace();    
            return null;    
        } catch (IOException e) {    
            e.printStackTrace();    
            return null;    
        }
    }
    
    public String doPost(String urlAddress, String content, String Content_Type){    
        try {    
            URL url = new URL(urlAddress);    
            HttpURLConnection uRLConnection = (HttpURLConnection)url.openConnection();    
            uRLConnection.setDoInput(true);    
            uRLConnection.setDoOutput(true);    
            uRLConnection.setRequestMethod("POST");    
            uRLConnection.setUseCaches(false);    
            uRLConnection.setInstanceFollowRedirects(false);    
            uRLConnection.setRequestProperty("Content-Type", Content_Type);
            uRLConnection.connect();    
                
            DataOutputStream out = new DataOutputStream(uRLConnection.getOutputStream());   
            out.writeBytes(content);    
            out.flush();    
            out.close();    
            
            InputStream is = uRLConnection.getInputStream();    
            BufferedReader br = new BufferedReader(new InputStreamReader(is));    
            String response = "";    
            String readLine = null;    
            while((readLine =br.readLine()) != null){    
                //response = br.readLine();    
                response = response + readLine + "\n";    
            }
            is.close();    
            br.close();    
            uRLConnection.disconnect();    
            return response;
        } catch (MalformedURLException e) {    
            e.printStackTrace();    
            return null;    
        } catch (IOException e) {    
            e.printStackTrace();    
            return null;    
        }    
    }  
    
    
    public static void main(String[] args)
    {
    	
    }
}
