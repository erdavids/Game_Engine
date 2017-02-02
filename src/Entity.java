import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Entity implements Serializable {
	//Unique for each game object
	int EntityID;
	
	Event event = null;
	
	String intent;
	boolean enemy;
	boolean bullet;
	int score;
	//List of all components for this entity
	ArrayList<Object> components;
	
	
	public Entity() {
		Random rg = new Random();
		EntityID = rg.nextInt(500);
		components = new ArrayList<Object>();
		score = 0;
		intent = "Add";
		
	}
	
	public void addComponents( Object c) {
		components.add(c);
	}
	
	/*
	 * May have to adjust this (not remove?)
	 */
	public void removeComponents( Object c) {
		components.remove(c);
	}
	
	public ArrayList<Object> returnComponents() {
		return components;
	}
	
	public void updateComponents(ArrayList<Object> updatedList) {
		components = updatedList;
	}
	
	public void makeEnemy() {
		enemy = true;
	}
	
	public void increaseScore() {
		score++;
	}

	public void update() {
		Velocity tempVelocity = new Velocity();
		for (Object obj : components) {
			if (obj.getClass() == Velocity.class)
				tempVelocity = (Velocity)obj;
		}
		
		Position tempPosition = new Position();
		int posIndex = 0;
		for (Object obj : components) {
			if (obj.getClass() == Position.class) {
				posIndex = components.indexOf(obj);
				tempPosition = (Position)obj;
			}
		}
		double new_x = tempPosition.get_x_position() + tempVelocity.get_x_speed();
		double new_y = tempPosition.get_y_position() + tempVelocity.get_y_speed();
		components.set(posIndex, new Position(new_x, new_y));
		
	}
	
	public void createEvent(String type) {
		event = new Event(type);
		
	}
	
	public Event getEvent() {
		return event;
	}
	
	public void addInput(String input) {
		event.setInput(input);
	}
}
