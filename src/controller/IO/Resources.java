package controller.IO;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

/**
 * Class used to load resources.
 * 
 * @author Calleberg
 *
 */
public class Resources {

	public static final GraphicsConfiguration CONFIG = GraphicsEnvironment
			.getLocalGraphicsEnvironment().getDefaultScreenDevice()
			.getDefaultConfiguration();
	
	/**
	 * Gives an array of all the images on the texture sheet at the specified path.
	 * @param url the path to the texture sheet.
	 * @param col the number of columns.
	 * @param row the number of rows.
	 * @return an array of all the images on the sheet.
	 */
	public static BufferedImage[] splitImages(String url, int col, int row) {
		BufferedImage image = null;
		try{
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			InputStream input = classLoader.getResourceAsStream(url);
			image = ImageIO.read(input);
		}catch (IOException e) {
			System.out.println("Could not find resources");
		}
		int frame = 0; //counter, or array position.
		int width = image.getWidth() / col;
		int height = image.getHeight() / row;
		BufferedImage[] images = new BufferedImage[col * row];
		
		for(int y = 0; y < row; y++) {
			for(int x = 0; x < col; x++) {
				images[frame] = new BufferedImage(width, height, image.getTransparency());
				Graphics2D g = images[frame].createGraphics();
				g.drawImage(image, 0, 0, width, height,	//destination of image.
						x * width, y * height, (x + 1) * width, (y + 1) * height, //source of the image on the sheet.
						null);
				g.dispose();
				frame++;
			}//x
		}//y
		
		return images;
	}

	/**
	 * Gives a rotated copy of the specified image.
	 * @param image the image to get a rotated copy of.
	 * @param rotation the amount in radians to rotate.
	 * @return a rotated copy of the specified image.
	 */
	public static BufferedImage getRotatedImage(BufferedImage image, double rotation) {
		//New image
		BufferedImage temp = new BufferedImage(image.getWidth(), image.getHeight(), image.getTransparency());
		
		//Draws and saves the rotated image
		Graphics2D g2d = (Graphics2D)temp.getGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		AffineTransform transformer = new AffineTransform();
		transformer.setToRotation(rotation, image.getWidth()/2, image.getHeight()/2);
		g2d.transform(transformer);
		g2d.drawImage(image, 0, 0, image.getWidth(), image.getHeight(),	//destination of image.
				0, 0, image.getWidth(), image.getHeight(), //source of the image on the sheet.
				null);
		g2d.dispose();

		return temp;
	}
}
