package model.other;

import java.util.Calendar;

/**
 * An animation with the ability to start and stop.
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
	private boolean looped;
	
	/**
	 * Creates a new animation.
	 * @param frames the frames to handle.
	 * @param delay the delay in ms between each frame.
	 * @param startRunning specify if the animation starts on.
	 */
	public Animation(int[] frames, int delay, boolean startRunning) {
		this(frames, delay, startRunning, true);
	}
	
	/**
	 * Creates a new animation.
	 * @param frames the frames to handle.
	 * @param delay the delay in ms between each frame.
	 * @param startRunning specify if the animation starts on.
	 * @param loop specify if the animation should loop.
	 */
	public Animation(int[] frames, int delay, boolean startRunning, boolean loop) {
		this.frames = frames;
		this.delay = delay;
		this.looped = loop;
		if(startRunning) {
			this.start();
		}
	}
	
	/**
	 * Sets the delay. This should only be done when the animation isn't running.
	 * @param ms the new delay in ms.
	 */
	public void setDelay(int ms) {
		this.delay = ms;
	}
	
	/**
	 * Gives the length of the animation. E.g. the number of frames.
	 * @return the number of frames.
	 */
	public int getLength() {
		return this.frames.length;
	}
	
	/**
	 * Gives <code>true</code> if the animation is running.
	 * @return <code>true</code> if the animation is running.
	 */
	public boolean isRunning() {
		return this.running;
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
	 * Gives the index of the frame which will be returned.
	 * @return
	 */
	public int getFrameIndex() {
		if(running) {
			lastPull = Calendar.getInstance().getTimeInMillis();
		}
		return (int)((lastPull - creation)/delay);
	}

	/**
	 * Gives the current frame.
	 * @return the current frame. If the animation is not running, the frame which
	 * was supposed to display when the animation stopped will be returned.
	 */
	public int getFrame() {
		int frame = this.getFrameIndex();
		if(!looped && frame >= frames.length) {
			this.stop();
			return frames[frames.length -1];
		}else{
			return frames[frame%frames.length];
		}
	}
}
