import java.io.Serializable;

public class Size implements Serializable {
	private int width;
	private int height;
	
	public Size() {
		
	}
	
	public Size(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public int get_width() {
		return width;
	}
	
	public int get_height() {
		return height;
	}
}
