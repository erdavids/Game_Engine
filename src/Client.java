import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class Client {
	
	public static Socket soc = null;
	public static ListOfClientStuff list = null;
	public static ObjectOutputStream oos;
	public static ObjectInputStream ois;
	
	//public static void main(String[] args) throws IOException {
	public Client() {
		String hostname = "127.0.0.1";
		list = new ListOfClientStuff();
		try {
			//Connect to the socket
			soc = new Socket(hostname, 9998);
			
			oos = new ObjectOutputStream(soc.getOutputStream());
			ois = new ObjectInputStream(soc.getInputStream());
			
			//Create the output and input streams
			if (list.to_server == null)
				list.add(oos);
			if (list.from_server == null)
				list.addInput(ois);
			System.out.println("Client Connected.");
			
			new Thread(new Client_Listener(soc, list, ois)).start();
			//new Thread(new Client_Sender(soc, list, oos)).start();
			//in = new BufferedReader(new InputStreamReader(echo.getInputStream()));
		} catch (UnknownHostException e) {
			System.err.println("Error with host: " + hostname);
			System.exit(1);
			
		} catch (IOException e ) {
			System.err.println("Error with connection to: " + hostname);
			
		}
	}
	
	public ListOfClientStuff getStuff() {
		return list;
	}
}

class Client_Listener implements Runnable
{
	Socket soc = null;
	public static ObjectInputStream in = null;
	public static ObjectOutputStream out = null;
	
	public static List<Entity> input_line = null;
	public static ListOfClientStuff list = null;
	
	public Client_Listener(Socket soc, ListOfClientStuff list, ObjectInputStream ois)
	{
		this.soc = soc;
		this.list = list;
		this.in = ois;
	}

	@Override
	public void run() {
		try {
			while((input_line = (List<Entity>)list.from_server.readObject()) != null){
				list.updateList(input_line);
			}
		}
		catch(IOException e) {
			e.printStackTrace();
			System.out.println("Error [Run Loop]");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
}

class Client_Sender implements Runnable
{
	Socket soc = null;
	public static ObjectOutputStream out = null;
	public static BufferedReader in = null;
	public static String input_line = null;
	
	public Client_Sender(Socket soc, ListOfClientStuff list, ObjectOutputStream oos) {
		this.soc = soc;
		this.out = oos;
	}

	@Override
	public void run() {
		try {
			System.out.println("CLIENT: ");
			while ( true) {
				//System.out.println("CLIENT: ");
				
				//out.println(input_line);
			}
		}
		catch (Exception e)
		{
			System.out.println("Error [Run Loop]2");
		}
		
	}
}

class ListOfClientStuff {
	//PrintWriter to_server;
	ObjectOutputStream to_server;
	ObjectInputStream from_server;
	List<String> serverMessages;
	List<Entity> entityList;
	
	public ListOfClientStuff() {
		to_server = null;
		from_server = null;
		serverMessages = Collections.synchronizedList(new ArrayList<String>());
		entityList = Collections.synchronizedList(new ArrayList<Entity>());
	}
	
	public synchronized void add(ObjectOutputStream oos) {
		to_server = oos;
	}
	
	public synchronized void addInput(ObjectInputStream ois) {
		from_server = ois;
	}
	
	public synchronized void sendToServer(Entity ent) {
		try {
			to_server.flush();
			to_server.writeObject(ent);
			to_server.flush();
			to_server.reset();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public synchronized void addMessage(String message) {
		serverMessages.add(message);
	}
	
	public synchronized void updateList(List<Entity> list) {
		this.entityList = list;
	}

	public synchronized void sendEventToServer(Event event) {
		try {
			to_server.flush();
			to_server.writeObject(event);
			to_server.flush();
			to_server.reset();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}