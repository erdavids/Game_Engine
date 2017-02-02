import java.io.Serializable;

public class Intent implements Serializable{
	String intent;
	
	public Intent() {
		
	}
	
	public Intent(String intent) {
		this.intent = intent;
	}
	
	public void change(String change) {
		intent = change;
	}
	
	public String get() {
		return intent;
	}
}
