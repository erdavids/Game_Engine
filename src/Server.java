import java.net.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.*;
import java.lang.Thread;

public class Server{
	
	final static int HOST = 9998;
	Socket client;
	int clientID;
	int listen;
	ServerSocket ss;
	ObjectOutputStream oos;
	ObjectInputStream ois;
	ListOfOutputStreams list;
	
	//public static void main(String[] args) {
	public Server() {
		list = new ListOfOutputStreams();
		try {
			ss = new ServerSocket(HOST);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Thread accept = new Thread() {
            public void run(){
                while(true){
                    try{
                        Socket client = ss.accept();
                        oos = new ObjectOutputStream(client.getOutputStream());
                        ois = new ObjectInputStream(client.getInputStream());
                        list.add(oos);
                        list.addInput(ois);
        				(new Thread(new Server_Listener(client, list, ois))).start();
        				//(new Thread(new Server_Sender(client, list, oos))).start();
                    }
                    catch(IOException e){ System.out.println("Connection"); e.printStackTrace(); }
                }
            }
        };
        
        accept.setDaemon(true);
        accept.start();
	}
	
	public ListOfOutputStreams getStreams() {
		return list;
	}
}

class ListOfOutputStreams {
	List<ObjectOutputStream> list;
	List<ObjectInputStream> listInput;
	List<Entity> clientMessages;
	List<Event> events;
	
	public ListOfOutputStreams() {
		list = Collections.synchronizedList(new ArrayList<ObjectOutputStream>());
		listInput = Collections.synchronizedList(new ArrayList<ObjectInputStream>());
		clientMessages = Collections.synchronizedList(new ArrayList<Entity>());
		events = Collections.synchronizedList(new ArrayList<Event>());
	}
	
	public synchronized void add(ObjectOutputStream oos) {
		list.add(oos);
	}
	
	public synchronized void addInput(ObjectInputStream ois) {
		listInput.add(ois);
	}
	
	public synchronized void sendToAll(ArrayList<Entity> entityList) {
		for (ObjectOutputStream oos : list) {
			try {
				oos.writeObject(entityList);
				oos.flush();
				oos.reset();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public synchronized void addEntity(Entity ent) {
		clientMessages.add(ent);
	}

	public void addEvent(Event input_event) {
		events.add(input_event);
		
	}
}
	//Constructor for the thread
class Server_Listener implements Runnable {
	Socket soc = null;
	public static ObjectOutputStream out = null;
	public static ObjectInputStream in = null;
	public static Entity input_line = null;
	public static Event input_event = null;
	public static ListOfOutputStreams list = null;
	Server_Listener (Socket soc, ListOfOutputStreams list, ObjectInputStream ois) {
			this.soc = soc;
			this.list = list;
			in = ois;
			
		}

	@Override
	public void run() {
		try {
			while (true) {
				//Object obj = in.readObject();
				for (ObjectInputStream in : list.listInput) {
					Object obj = in.readObject();
					if (obj != null) {
						if (obj.getClass() == Entity.class) {
							input_line = (Entity)obj;
							if (input_line.getEvent() != null)
								list.addEvent(input_line.getEvent());
							else 
								list.addEntity(input_line);
						}
						if (obj.getClass() == Event.class) {
							input_event = (Event)obj;
							list.addEvent(input_event);
						}
					}
				}
				
			}
		}
		catch (IOException e) {
			e.printStackTrace();
			System.out.println("Exception [Run Loop]");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}

class Server_Sender implements Runnable {
	Socket soc = null;
	public static ObjectOutputStream out = null;
	public static ObjectInputStream in = null;
	public static List<Entity> input_line = null;
	public static ListOfOutputStreams list = null;
	
	Server_Sender (Socket soc, ListOfOutputStreams list, ObjectOutputStream oos) {
		this.list = list;
		this.soc = soc;
		this.out = oos;

		list.add(oos);
	}


	@Override
	public void run() {
		try {
			
			while(true) {
				//input_line = (List<Entity>)in.readObject();
				//list.sendToAll(input_line);
				//System.out.println("Server: ");
			}
		}
		
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception [Run Loop]2");
		}
		
	}
}
