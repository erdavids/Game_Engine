import java.lang.*;
public class Timeline {
	
	private long start_time;
	private long real_time_start;
	private long timestamp;
	private long average;
	private int tic;
	
	public Timeline() {
		start_time = System.nanoTime();
		real_time_start = start_time;
		timestamp = start_time;
		tic = 1;
	}
	
	public Timeline(long start) {
		start_time = start;
		real_time_start = System.nanoTime();
		timestamp = start_time;
		tic = 1;
	}
	
	public void updateTimestamp() {
		timestamp = (System.nanoTime() - real_time_start) * tic;
	}
	
	public long getTimestamp() {
		return timestamp;
	}
	
	public void changeTic(int t) {
		tic = t;
	}
	
	public long getCurrentTime() {
		return System.nanoTime();
	}
	
	public long getStartTime() {
		return start_time;
	}
	
	
}
