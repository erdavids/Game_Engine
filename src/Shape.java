import java.io.Serializable;

public class Shape implements Serializable {
	private String shapeName;
	
	public Shape() {
		
	}
	
	public Shape(String shapeName) {
		this.shapeName = shapeName;
	}
	
	public String get_shape_name() {
		return shapeName;
	}
}
