import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;
public class Server_Main {
	public static void main(String[] args) {
		Server server = new Server();
		
		Timeline timeline = new Timeline();
		
		EventManager eventManager = new EventManager(timeline);
		
		ArrayList<Entity> listOfEntities = new ArrayList<Entity>();
		ArrayList<Integer> listOfIDs = new ArrayList<Integer>();
		Random rg = new Random();
		
		Entity Enemy1 = new Entity();
		listOfIDs.add(Enemy1.EntityID);
		Enemy1.makeEnemy();
		Enemy1.addComponents(new Velocity(0, 0));
		Enemy1.addComponents(new MyColor(250, 250, 50));
		Enemy1.addComponents(new Position(30, 15));
		Enemy1.addComponents(new Shape("Block2"));
		Enemy1.addComponents(new Size(14, 14));
		listOfEntities.add(Enemy1);
		
		Enemy1 = new Entity();
		listOfIDs.add(Enemy1.EntityID);
		Enemy1.makeEnemy();
		Enemy1.addComponents(new Velocity(0, 0));
		Enemy1.addComponents(new MyColor(250, 250, 50));
		Enemy1.addComponents(new Position(60, 15));
		Enemy1.addComponents(new Shape("Block2"));
		Enemy1.addComponents(new Size(14, 14));
		listOfEntities.add(Enemy1);
		
		Enemy1 = new Entity();
		listOfIDs.add(Enemy1.EntityID);
		Enemy1.makeEnemy();
		Enemy1.addComponents(new Velocity(0, 0));
		Enemy1.addComponents(new MyColor(250, 250, 50));
		Enemy1.addComponents(new Position(90, 15));
		Enemy1.addComponents(new Shape("Block2"));
		Enemy1.addComponents(new Size(14, 14));
		listOfEntities.add(Enemy1);
		
		Enemy1 = new Entity();
		listOfIDs.add(Enemy1.EntityID);
		Enemy1.makeEnemy();
		Enemy1.addComponents(new Velocity(0, 0));
		Enemy1.addComponents(new MyColor(250, 250, 50));
		Enemy1.addComponents(new Position(120, 15));
		Enemy1.addComponents(new Shape("Block2"));
		Enemy1.addComponents(new Size(14, 14));
		listOfEntities.add(Enemy1);
		
		Enemy1 = new Entity();
		listOfIDs.add(Enemy1.EntityID);
		Enemy1.makeEnemy();
		Enemy1.addComponents(new Velocity(0, 0));
		Enemy1.addComponents(new MyColor(250, 250, 50));
		Enemy1.addComponents(new Position(150, 15));
		Enemy1.addComponents(new Shape("Block2"));
		Enemy1.addComponents(new Size(14, 14));
		listOfEntities.add(Enemy1);
		
		Enemy1 = new Entity();
		listOfIDs.add(Enemy1.EntityID);
		Enemy1.makeEnemy();
		Enemy1.addComponents(new Velocity(0, 0));
		Enemy1.addComponents(new MyColor(250, 250, 50));
		Enemy1.addComponents(new Position(180, 15));
		Enemy1.addComponents(new Shape("Block2"));
		Enemy1.addComponents(new Size(14, 14));
		listOfEntities.add(Enemy1);
		
		Enemy1 = new Entity();
		listOfIDs.add(Enemy1.EntityID);
		Enemy1.makeEnemy();
		Enemy1.addComponents(new Velocity(0, 0));
		Enemy1.addComponents(new MyColor(250, 250, 50));
		Enemy1.addComponents(new Position(210, 15));
		Enemy1.addComponents(new Shape("Block2"));
		Enemy1.addComponents(new Size(14, 14));
		listOfEntities.add(Enemy1);
		
		Enemy1 = new Entity();
		listOfIDs.add(Enemy1.EntityID);
		Enemy1.makeEnemy();
		Enemy1.addComponents(new Velocity(0, 0));
		Enemy1.addComponents(new MyColor(250, 250, 50));
		Enemy1.addComponents(new Position(240, 15));
		Enemy1.addComponents(new Shape("Block2"));
		Enemy1.addComponents(new Size(14, 14));
		listOfEntities.add(Enemy1);
		
		Enemy1 = new Entity();
		listOfIDs.add(Enemy1.EntityID);
		Enemy1.makeEnemy();
		Enemy1.addComponents(new Velocity(0, 0));
		Enemy1.addComponents(new MyColor(250, 250, 50));
		Enemy1.addComponents(new Position(270, 15));
		Enemy1.addComponents(new Shape("Block2"));
		Enemy1.addComponents(new Size(14, 14));
		listOfEntities.add(Enemy1);
		
		Enemy1 = new Entity();
		listOfIDs.add(Enemy1.EntityID);
		Enemy1.makeEnemy();
		Enemy1.addComponents(new Velocity(0, 0));
		Enemy1.addComponents(new MyColor(250, 250, 50));
		Enemy1.addComponents(new Position(29, 45));
		Enemy1.addComponents(new Shape("Block2"));
		Enemy1.addComponents(new Size(14, 14));
		listOfEntities.add(Enemy1);
		
		Enemy1 = new Entity();
		listOfIDs.add(Enemy1.EntityID);
		Enemy1.makeEnemy();
		Enemy1.addComponents(new Velocity(0, 0));
		Enemy1.addComponents(new MyColor(250, 250, 50));
		Enemy1.addComponents(new Position(59, 45));
		Enemy1.addComponents(new Shape("Block2"));
		Enemy1.addComponents(new Size(14, 14));
		listOfEntities.add(Enemy1);
		
		Enemy1 = new Entity();
		listOfIDs.add(Enemy1.EntityID);
		Enemy1.makeEnemy();
		Enemy1.addComponents(new Velocity(0, 0));
		Enemy1.addComponents(new MyColor(250, 250, 50));
		Enemy1.addComponents(new Position(89, 45));
		Enemy1.addComponents(new Shape("Block2"));
		Enemy1.addComponents(new Size(14, 14));
		listOfEntities.add(Enemy1);
		
		Enemy1 = new Entity();
		listOfIDs.add(Enemy1.EntityID);
		Enemy1.makeEnemy();
		Enemy1.addComponents(new Velocity(0, 0));
		Enemy1.addComponents(new MyColor(250, 250, 50));
		Enemy1.addComponents(new Position(119, 45));
		Enemy1.addComponents(new Shape("Block2"));
		Enemy1.addComponents(new Size(14, 14));
		listOfEntities.add(Enemy1);
		
		Enemy1 = new Entity();
		listOfIDs.add(Enemy1.EntityID);
		Enemy1.makeEnemy();
		Enemy1.addComponents(new Velocity(0, 0));
		Enemy1.addComponents(new MyColor(250, 250, 50));
		Enemy1.addComponents(new Position(149, 45));
		Enemy1.addComponents(new Shape("Block2"));
		Enemy1.addComponents(new Size(14, 14));
		listOfEntities.add(Enemy1);
		
		Enemy1 = new Entity();
		listOfIDs.add(Enemy1.EntityID);
		Enemy1.makeEnemy();
		Enemy1.addComponents(new Velocity(0, 0));
		Enemy1.addComponents(new MyColor(250, 250, 50));
		Enemy1.addComponents(new Position(179, 45));
		Enemy1.addComponents(new Shape("Block2"));
		Enemy1.addComponents(new Size(14, 14));
		listOfEntities.add(Enemy1);
		
		Enemy1 = new Entity();
		listOfIDs.add(Enemy1.EntityID);
		Enemy1.makeEnemy();
		Enemy1.addComponents(new Velocity(0, 0));
		Enemy1.addComponents(new MyColor(250, 250, 50));
		Enemy1.addComponents(new Position(209, 45));
		Enemy1.addComponents(new Shape("Block2"));
		Enemy1.addComponents(new Size(14, 14));
		listOfEntities.add(Enemy1);
		
		Enemy1 = new Entity();
		listOfIDs.add(Enemy1.EntityID);
		Enemy1.makeEnemy();
		Enemy1.addComponents(new Velocity(0, 0));
		Enemy1.addComponents(new MyColor(250, 250, 50));
		Enemy1.addComponents(new Position(239, 45));
		Enemy1.addComponents(new Shape("Block2"));
		Enemy1.addComponents(new Size(14, 14));
		listOfEntities.add(Enemy1);
		
		Enemy1 = new Entity();
		listOfIDs.add(Enemy1.EntityID);
		Enemy1.makeEnemy();
		Enemy1.addComponents(new Velocity(0, 0));
		Enemy1.addComponents(new MyColor(250, 250, 50));
		Enemy1.addComponents(new Position(269, 45));
		Enemy1.addComponents(new Shape("Block2"));
		Enemy1.addComponents(new Size(14, 14));
		listOfEntities.add(Enemy1);
		
		
		ListOfOutputStreams list = null;
		String tempmessage = null;
		
		
		
		while(true) {
			list = server.getStreams();
			
			if (!list.clientMessages.isEmpty()) {
				Entity tempEnt = list.clientMessages.remove(list.clientMessages.size()-1);
				if (!listOfIDs.contains(tempEnt.EntityID) && tempEnt.EntityID != -1) {
					listOfEntities.add(tempEnt);
					listOfIDs.add(tempEnt.EntityID);
				}
			}
			
			eventManager.updateListOfEntities(listOfEntities);
			

			timeline.updateTimestamp();
			if (!list.events.isEmpty()) {
				Event tempEvent = list.events.remove(0);
				
				if (tempEvent != null) {
					tempEvent.setTimestamp(timeline.getTimestamp());
					eventManager.addToQueue(tempEvent);
				}
				
			}
			
			boolean queueEmpty = false;
			while (queueEmpty == false) {
				queueEmpty = eventManager.handleEvents();
				listOfEntities = eventManager.getUpdatedList();
				
				if (!list.list.isEmpty())
					list.sendToAll(listOfEntities);
			}
			
			listOfEntities = eventManager.getUpdatedList();
			if (!list.list.isEmpty())
				list.sendToAll(listOfEntities);
		}	
	}
}
