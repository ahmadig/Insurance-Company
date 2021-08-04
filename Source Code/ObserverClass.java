import java.io.IOException;

public abstract class ObserverClass {
	protected Logger log = new Logger(); 
	public abstract void update() throws IOException ;
	public void setCurrentUser(String user) {
		log.setUser(user);
	}
}
