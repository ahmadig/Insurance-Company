import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

/**
 * 
 */

/**
 * @author omarr
 *
 */
public class Logger {
	private String User;

	public String getUser() {
		return User;
	}

	public void setUser(String user) {
		User = user;
	} 
	
	public void registerNewAction(String action) throws IOException {
		//File yourFile = new File("logger.txt");
		//yourFile.createNewFile(); // if file already exists will do nothing
		BufferedWriter out = new BufferedWriter( new FileWriter("logger.txt",true));
		out.write(action +" "+ LocalDate.now()+"\n");
        out.close();
	}
	

}
