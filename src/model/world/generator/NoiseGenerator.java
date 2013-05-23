package model.world.generator;

import java.util.Random;

/**
 * This class generates noise.
 * 
 * @author Martin Calleberg
 *
 */
public class NoiseGenerator {

	private float[][] noise;
	private Random random;
	
	/**
	 * Creates a new noise generator which will be able to generate a noise map with the 
	 * specified size.
	 * @param w the width of the noise map.
	 * @param h the height of the noise map.
	 * @param seed the seed to use when generating the noise map.
	 */
	public NoiseGenerator(int w, int h, long seed) {
		this.noise = new float[w][h];
		this.random = new Random(seed);
		this.initNoise();
		
	}
	
	/*
	 * Initializes the noise map.
	 */
	private void initNoise(){
		for (int x = 0; x < noise.length; x++) {
			for (int y = 0; y < noise[0].length; y++) {
				noise[x][y] = random.nextInt(255);
			}
		}
	}
	
	/**
	 * Gives the noise at the specified position and with the specified blur value.
	 * @param x the X coordinate.
	 * @param y the Y coordinate.
	 * @param blur the size of the blur. Higher values give smoother noise maps.
	 * @return the noise at the specified position.
	 */
	public float getNoise(float x, float y, float blur) {
		float value = 0f;
		float initialSize = blur;

		while(blur >= 1){
			value += generatePointNoise(x / blur, y / blur) * blur;
			blur /= 2.0;
		}

		return(value / (initialSize * 2));
	}

	/*
	 * Gives the width of the noise map.
	 */
	private int width() {
		return this.noise.length;
	}
	
	/*
	 * Gives the height of the noise map.
	 */
	private int height() {
		return this.noise[0].length;
	}

	/*
	 * Generates the noise at the specified point.
	 */
	private float generatePointNoise(float x, float y){  
		//get fractional part of x and y
		float fractX = x - (int)(x);
		float fractY = y - (int)(y);

		//wrap around
		int x1 = ((int)(x) + width()) % width();
		int y1 = ((int)(y) + height()) % height();

		//neighbor values
		int x2 = (x1 + width() - 1) % width();
		int y2 = (y1 + height() - 1) % height();

		//smooth the noise with bilinear interpolation
		float value = 0f;
		value += fractX       * fractY       * noise[x1][y1];
		value += fractX       * (1 - fractY) * noise[x1][y2];
		value += (1 - fractX) * fractY       * noise[x2][y1];
		value += (1 - fractX) * (1 - fractY) * noise[x2][y2];
		
		return value;
	}
}
