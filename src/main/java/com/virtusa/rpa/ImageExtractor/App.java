package com.virtusa.rpa.ImageExtractor;

import java.io.IOException;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws InvalidPasswordException, IOException
    {
    	String fileNameWithPathAndExtension = "/home/malith/Desktop/apache.pdf";
    	String outputLocation = "/home/malith/Desktop";
    	
    	ImageExtractor imageExtractor = new ImageExtractor();
    	
    	imageExtractor.extractImage(fileNameWithPathAndExtension, outputLocation);
    }
}
