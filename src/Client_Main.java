import java.util.ArrayList;

import processing.core.PApplet;

public class Client_Main extends PApplet {
	
	static Client client;
	static ListOfClientStuff list;
	static Entity ent1;
	GameMessage message;
	boolean start = false;
	int timer = 0;
	String st = "Press 'P' To Start";
	public static void main(String[] args) {
		PApplet.main("Client_Main");
	}
	
	public void settings() {
		client = new Client();
		list = client.getStuff();
		ent1 = new Entity();
		ent1.addComponents(new MyColor(75, 50, 200));
		ent1.addComponents(new Intent("Add"));
		ent1.addComponents(new Velocity(0, 0));
		ent1.addComponents(new Size(20, 20));
		ent1.addComponents(new Position(150, 280));
		
		list.sendToServer(ent1);
		
		message = new GameMessage();
		size(300, 300);
	}
	
	public void setup() {
		fill(120, 50, 240);
	}
	
	public void draw() {
		timer++;
		background(51);
		move();
		list = client.getStuff();
		
		ScriptManager.bindArgument("game_object", message);
		//ScriptManager.loadScript("Scripts/string_start.js");
		//ScriptManager.executeScript();
		
		if (timer > 50){
			//st = "";
			ScriptManager.bindArgument("game_object", message);
			ScriptManager.loadScript("Scripts/string_empty.js");
			ScriptManager.executeScript();
		}
		if (timer > 100) {
			//st = "Press 'P' To Start";
			ScriptManager.bindArgument("game_object", message);
			ScriptManager.loadScript("Scripts/string_start.js");
			ScriptManager.executeScript();
			timer = 0;
		}
		
		if (start == false) {
			textSize(32);
			text(message.message, 13, 150);
		}
		if (!list.entityList.isEmpty()) {
			for (Entity ent : list.entityList) {
				Shape tempShape = null;
				Position tempPosition = null;
				Size tempSize = null;
				MyColor tempColor = null;
				ArrayList<Object> tempList = ent.returnComponents();
				for (Object comp : tempList) {
					if (comp.getClass() == Shape.class)
						tempShape = (Shape)comp;
					if (comp.getClass() == Position.class)
						tempPosition = (Position)comp;
					if (comp.getClass() == Size.class)
						tempSize = (Size)comp;
					if (comp.getClass() == MyColor.class) {
						tempColor = (MyColor)comp;
					}
				}
				rectMode(CENTER);
				fill(tempColor.r, tempColor.g, tempColor.b);
				rect((float)tempPosition.get_x_position(), (float)tempPosition.get_y_position(), tempSize.get_width(), tempSize.get_height());
			}
		}
	}

	private void move() {
		//Entity tempEnt = new Entity();
		//tempEnt.createEvent("input");
		Event input = new Event("input");
		input.setInput("NONE");
		if (keyPressed) {
			if (keyCode == LEFT)
				input.setInput("LEFT");
			else if (keyCode == RIGHT)
				input.setInput("RIGHT");
			else if (keyCode == UP)
				input.setInput("UP");
			else if (key == 'r' || key == 'R')  {
				Event record = new Event("record");
				list.sendEventToServer(record);
			}
			else if (key == 'i' || key == 'I'){
				Event endRecord = new Event("1");
				list.sendEventToServer(endRecord);
			}
			else if (key == 'p' || key == 'P') {
				start = true;
				Event start = new Event("start");
				list.sendEventToServer(start);
			}
			else if (key == 2)
				input.setInput("2");
			else if (key == 0)
				input.setInput("0");
			else
				input.setInput("NONE");
		} else {
			input.setInput("NONE");
		}
		input.setID(ent1.EntityID);
		list.sendEventToServer(input);
		
	}
}
