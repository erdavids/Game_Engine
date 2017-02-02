public class GameMessage {

	public static int GUID = 0;

	public int ID;
	public int x;
	public int y;
	public String message;

	public GameMessage() {
		ID = ++GUID;
		x = 0;
		y = 0;
		message = "Press 'P' To Start";
	}

	public void move(int x, int y) {
		this.x += x;
		this.y += y;
	}
	
	public void changeText(int x) {
		if (x == 1)
			message = "Press 'P' To Start";
		if (x == 0)
			message = "";
			
	}

	public String toString() {
		return "GameObject " + ID + " has position (" + x + ", " + y + ")";
	}

}
