package com.visa.workshop14prac.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.visa.workshop14prac.Model.Contact;



public class Util {
    private static final Logger logger = LoggerFactory.getLogger(Util.class);

    public static void saveContact(String fileName, Contact contact){
        
        try(    FileWriter filewriter = new FileWriter(fileName);   )
        {   filewriter.write("name:" + contact.getName() + "\n");
            filewriter.write("email:" + contact.getEmail()+ "\n");
            filewriter.write("phonenumber:" + contact.getPhoneNumber()+ "\n");
        }        
        catch(IOException e)
        {   logger.info("IO Error");    }
    }



    public static void loadContact(String fileName, Contact contact){

        try
        (   Reader reader = new FileReader(fileName);
            BufferedReader br = new BufferedReader(reader); )
        {   
            String line;
            while( (line = br.readLine()) != null)
            {   
                if( line.contains("name:"))
                {   contact.setName(line.split(":")[1]);    }
                if(line.contains("email:"))
                {   contact.setEmail(line.split(":")[1]);   }
                if(line.contains("phonenumber:"))
                {   contact.setPhoneNumber(line.split(":")[1]); }
            }
        }
        catch(FileNotFoundException e)
        {   logger.info("file not found "); }
        catch(IOException e)
        {   logger.info("IO error");    }

    }

}