import java.io.Serializable;

/*
 * Velocity for all entities that will experience movement
 * 
 * Update Condition: User Input for Movement
 */
public class Velocity implements Serializable {
	public double x_speed;
	public double y_speed;
	
	public Velocity(){
		x_speed = 0;
		y_speed = 0;
	}
	
	public Velocity(double x, double y) {
		x_speed = x;
		y_speed = y;
	}
	
	public void set_x_speed(double x_s) {
		x_speed = x_s;
	}
	
	public void set_y_speed(double y_s) {
		y_speed = y_s;
	}
	
	public double get_x_speed() {
		return x_speed;
	}
	
	public double get_y_speed() {
		return y_speed;
	}
}
