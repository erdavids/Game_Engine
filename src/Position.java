import java.io.Serializable;

/*
 * Position Component for all entities
 * 
 * Update Condition: Should only be updated for entities that also contain a 
 *    Velocity component
 */
public class Position implements Serializable {
	
	private double x_position;
	private double y_position;
	
	/*
	 * Default Constructor: Should not be used
	 */
	public Position() {
		
	}
	
	public Position(double x_p, double y_p) {
		x_position = x_p;
		y_position = y_p;
	}
	
	public double get_x_position() {
		return x_position;
	}
	
	public double get_y_position() {
		return y_position;
	}
	
	public void set_x_position(double x) {
		x_position = x;
	}
	
	public void set_y_position(double y) {
		y_position = y;
	}
}
