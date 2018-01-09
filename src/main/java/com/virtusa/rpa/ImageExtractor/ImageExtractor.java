package com.virtusa.rpa.ImageExtractor;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;

public class ImageExtractor
{
	public ImageExtractor()
	{

	}

	public void extractImage(String fileNameWithPathAndExtension, String outputLocation)
	{
		PDDocument document = null;
		String fileName = fileNameWithPathAndExtension;
		try
		{
			document = PDDocument.load(new File(fileName));
			SaveImagesInPdf printer = new SaveImagesInPdf(outputLocation);
			int pageNum = 0;
			for (PDPage page : document.getPages())
			{
				pageNum++;
				System.out.println("Processing page: " + pageNum);
				printer.processPage(page);
			}
		}
		catch (InvalidPasswordException e)
		{
			System.out.println(e);
		}
		catch (IOException e)
		{
			System.out.println(e);
		}
		finally
		{
			if (document != null)
			{
				try
				{
					document.close();
				}
				catch (IOException e)
				{
					System.out.println(e);
				}
			}
		}
	}
}
