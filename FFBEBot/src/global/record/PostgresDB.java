package global.record;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Class to handle anything to do with the heroku postgres server
 * @author Allen
 *
 */
public class PostgresDB{
	private static Connection conn;
	private static PreparedStatement useQuery;
	private static PreparedStatement usePost;
	private static PreparedStatement useNew;
	public static void setup(){
		try{
			conn = Secrets.getPostgresConnection();
			useQuery = conn.prepareStatement("SELECT * FROM analyticinfo WHERE classname = ?");
			usePost = conn.prepareStatement("UPDATE analyticinfo SET usecount = ? WHERE classname = ?");
			useNew = conn.prepareCall("INSERT INTO analyticinfo VALUES (?,1,?)");
		}catch(SQLException e){
			Log.log("ERROR", "Failed to connect to postgres server");
		}
	}
	public static void logCommandUse(Class<?> c){
		if(!(conn==null)){
			try{
				useQuery.setString(1, ""+c.getSimpleName());
				ResultSet rs = useQuery.executeQuery();
				if(rs.next()){
					usePost.setInt(1, rs.getInt("USECOUNT")+1);
					usePost.setString(2, ""+c.getSimpleName());
					usePost.executeUpdate();
				}
				else{
					useNew.setString(1, ""+c.getSimpleName());
					useNew.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
					useNew.execute();
				}
			}catch(SQLException e){
				Log.logError(e);
			}
			
		}
	}
}
