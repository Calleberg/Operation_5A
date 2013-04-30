package view;

import java.util.Calendar;

/**
 * An animation with the ability to start and stop. It will
 * loop automatically.
 * 
 * @author 
 *
 */
public class Animation {

	private int[] frames;
	private int delay;
	private long creation;
	private long lastPull;
	private boolean running;
	
	/**
	 * Creates a new animation.
	 * @param frames the frames to handle.
	 * @param delay the delay in ms between each frame.
	 */
	public Animation(int[] frames, int delay, boolean startRunning) {
		this.frames = frames;
		this.delay = delay;
		if(startRunning) {
			this.start();
		}
	}
	
	/**
	 * Starts the animation.
	 */
	public void start() {
		running = true;
		creation = Calendar.getInstance().getTimeInMillis();
	}
	
	/**
	 * Stops the animation.
	 */
	public void stop() {
		running = false;
	}

	/**
	 * Gives the current frame.
	 * @return the current frame. If the animation is not running, the frame which
	 * was supposed to display when the animation stopped will be returned.
	 */
	public int getFrame() {
		if(running) {
			lastPull = Calendar.getInstance().getTimeInMillis();
		}
		return frames[(int)((lastPull - creation)/delay)%frames.length];
	}
}
