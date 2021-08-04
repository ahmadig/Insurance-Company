import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SaveToDB {
	
	private static Connection conn = null;
	private static SaveToDB _instance=null;
	
	private SaveToDB() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
	}
	public static SaveToDB getinstance() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		if(_instance == null) {
			conn = connectToDB();
			_instance = new SaveToDB();
		}
		return _instance;
	}
	
public static Connection connectToDB() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		
	    try 
	    {
	    	 Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		        System.out.println("Driver definition succeed");
		      	 conn = DriverManager.getConnection("jdbc:mysql://localhost/task?serverTimezone=IST","root","combat@123");
	        System.out.println("SQL connection succeed");
	        return conn;
	        
	      //  saveUserToDB( conn, msg);
	 	} catch (SQLException ex) 
	 	    {/* handle any errors*/
	        System.out.println("SQLException: " + ex.getMessage());
	        System.out.println("SQLState: " + ex.getSQLState());
	        System.out.println("VendorError: " + ex.getErrorCode());
	        }
		return conn;
	  
	}
public void saveUserToDB(ArrayList<String> msg) {
	ArrayList<String> arr = msg;
	PreparedStatement updatearrival;
	try {
		updatearrival = conn.prepareStatement("insert into orders(FirstName,LastName,date,remarks,type) values (?,?,?,?,?)");
		updatearrival.setString(1,arr.get(0));
		updatearrival.setString(2,arr.get(1));
		updatearrival.setString(3,arr.get(2));
		updatearrival.setString(4,arr.get(3));
		updatearrival.setString(5,arr.get(4));
		updatearrival.executeUpdate();
	} catch (SQLException e) {
		e.printStackTrace();
	}

}
public ResultSet showAll() throws SQLException {
	Statement stmt;
		stmt = conn.createStatement();
	ResultSet rs;
	rs = stmt.executeQuery("SELECT * FROM orders;");
	return rs;
}
public boolean checkLogin(String username,String password) throws SQLException {
	Statement stmt;
	stmt = conn.createStatement();
	ResultSet rs;
	rs = stmt.executeQuery("SELECT * FROM login WHERE username = "+"'"+username+"'" +" AND " + "password="+"'"+password+"'"+" ;");
	if (rs.next() == false) {
	      return false;
	    }
	return true;
}

}
