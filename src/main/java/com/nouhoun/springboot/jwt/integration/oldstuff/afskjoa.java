package com.nouhoun.springboot.jwt.integration.oldstuff;

import java.util.ArrayList;

public class afskjoa {
	 public static String convert(String s, int numRows) {
	        String res="";
	       String old=s;
	       String news="";
	     
	       	   //   String[] rows=new String[numRows];
	        int leng=numRows;
	        for(int n=0;n<numRows;n++){
	        for (int i=0;i<old.length();i++){
	        	//if mod by 2(n-1), add to result
	        	//else add to news string 
	            if (i%(2*(leng-1))==0){
	                res+=old.charAt(i);
	            }
	            else{
	                news+=old.charAt(i);
	            }
	         }
	            old=news;
	            news="";
	        }
	        return res;
	        
	        
	    }
	

public static void main(String[] args){
String s="PAYPALISHIRING";
System.out.println(convert(s,3));

}
}