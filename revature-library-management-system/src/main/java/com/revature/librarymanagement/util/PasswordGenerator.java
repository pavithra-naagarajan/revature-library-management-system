package com.revature.librarymanagement.util;


import org.springframework.stereotype.Component;

@Component
public class PasswordGenerator {

	

public static String generatePassword() 
{  
    long code   =(long)((Math.random()*9*Math.pow(10,15))+Math.pow(10,15));
    String uniquePassword="";
    for (long i=code;i!=0;i/=100)
        {
            long digit=i%100;
            if (digit<=90)
            	digit=digit+32; 
            char ch=(char) digit;
           
            uniquePassword=ch+uniquePassword;
        }
    return uniquePassword;
}
    

}