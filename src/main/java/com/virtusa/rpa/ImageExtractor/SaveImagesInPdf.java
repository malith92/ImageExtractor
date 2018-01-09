package com.virtusa.rpa.ImageExtractor;

import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.pdmodel.graphics.form.PDFormXObject;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.contentstream.operator.Operator;
import org.apache.pdfbox.contentstream.PDFStreamEngine;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

/**
 * This is an example on how to extract images from pdf.
 */
public class SaveImagesInPdf extends PDFStreamEngine
{
	public int imageNumber = 1;
	public String pathWithFileNameAndExtension;
	/**
	 * Default constructor.
	 *
	 * @throws IOException
	 *             If there is an error loading text stripper properties.
	 */
	public SaveImagesInPdf(String pathWithFileNameAndExtension) throws IOException
	{
		this.pathWithFileNameAndExtension = pathWithFileNameAndExtension;
	}

	/**
	 * @param operator
	 *            The operation to perform.
	 * @param operands
	 *            The list of arguments.
	 *
	 * @throws IOException
	 *             If there is an error processing the operation.
	 */
	@Override
	protected void processOperator(Operator operator, List<COSBase> operands) throws IOException
	{
		String operation = operator.getName();
		if ("Do".equals(operation))
		{
			COSName objectName = (COSName) operands.get(0);
			PDXObject xobject = getResources().getXObject(objectName);
			if (xobject instanceof PDImageXObject)
			{
				PDImageXObject image = (PDImageXObject) xobject;
				int imageWidth = image.getWidth();
				int imageHeight = image.getHeight();

				// save image to local
				BufferedImage bImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB);
				bImage = image.getImage();
				
				//ImageIO.write(bImage, "PNG", new File(this.pathWithFileNameAndExtension+"/image_" + imageNumber + ".png"));
				
				ImageIO.write(bImage, "PNG", new File(this.pathWithFileNameAndExtension+"/image_" + imageNumber + ".png"));
				
				System.out.println("Image saved.");
				imageNumber++;

			}
			else if (xobject instanceof PDFormXObject)
			{
				PDFormXObject form = (PDFormXObject) xobject;
				showForm(form);
			}
		}
		else
		{
			super.processOperator(operator, operands);
		}
	}

}