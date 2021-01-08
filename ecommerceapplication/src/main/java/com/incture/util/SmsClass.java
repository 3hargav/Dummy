package com.incture.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.stereotype.Service;

@Service
public class SmsClass {

	public void sms(String message,String number) throws IOException{
		  
		 String api="NPY2pXLc01kVURo8WDeMnTQ6ZfsJqG7wahi5F9jIg3AmBxlySOwLjTPf5N0E4JHspCh7Qx9nXOb12UtG";
		 String sendId="FSTSMS";
		 
		 try {
			message=URLEncoder.encode(message, "UTF-8");
		} catch (UnsupportedEncodingException e) {
		   System.out.println("Error while encoding the message");
		}
		 
		 System.out.println(message);
		 
		 String language="english";
		 String route="p";
		 String url="https://www.fast2sms.com/dev/bulk?authorization="+api+"&sender_id="+sendId+"&message="+message+"&language="+language+"&route="+route+"&numbers="+number;
	 
		  System.out.println(url);
		  
			URL myurl=new URL(url);
		  
		  HttpsURLConnection con=(HttpsURLConnection)myurl.openConnection();
		   
		  con.setRequestMethod("GET");
		  con.setRequestProperty("User-Agent", "Mozilla Firefox");
		  con.setRequestProperty("cache-control", "no-cache");
		   System.out.println("Wait");
		  
		  int code=con.getResponseCode();
	 
	     System.out.println("Response code is"+code);
		  
	 }
}
