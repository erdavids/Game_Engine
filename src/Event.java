import java.io.Serializable;

//character collision, character death, character spawn, and user input
public class Event implements Serializable{
	
	private String type;
	private String input;
	private int posIndex;
	private String priority;
	private int objID;
	
	private long timestamp;
	//private Timeline gameTimeline;
	
	public Event() {
		
	}
	
	public Event(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	
	public void setInput(String dir) {
		input = dir;
	}
	
	public String getInput() {
		return input;
	}
	
	public void setID(int ID) {
		this.objID = ID;
	}
	
	public int getID() {
		return objID;
	}
	
	public void setPosIndex(int p) {
		this.posIndex = p;
	}
	
	public int getPosIndex() {
		return posIndex;
	}
	
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
	public long getTimestamp() {
		return timestamp;
	}
	
	public void setPriority(String priority) {
		this.priority = priority;
	}
	
	public String getPriority() {
		return priority;
	}
}
