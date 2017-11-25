import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
//import java.sql.Statement;
import java.util.ArrayList;

import com.google.gson.Gson;

import java.sql.ResultSet;

public class LoginService {
	
	public static String performLogin(Login login){
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		//Statement stmt = null;

		try{
			Class.forName(ConnConfig.JDBC_DRIVER);
			conn = DriverManager.getConnection(ConnConfig.DB_URL, ConnConfig.USERNAME, "");
			String sql = "SELECT User_Id, Password FROM Users WHERE User_Id= ? AND Password = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, login.getUserid());
			pstmt.setString(2, login.getPassword());
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {

				String userid = login.getUserid();
				String password = login.getPassword();

				if (userid.equalsIgnoreCase(rs.getString("User_Id")) &&
					password.equals(rs.getString("Password"))){
					String username = rs.getString("User_Id");
					sql = "SELECT * FROM Booking WHERE User_Id = ?";
					pstmt1 = conn.prepareStatement(sql);
					pstmt1.setString(1, username);
					ResultSet rs1 = pstmt1.executeQuery();
					ArrayList<Appointment> appointments = new ArrayList<>();
					while (rs1.next()){
						String aptDate = rs1.getString("Appointment_Date");
						String aptTime = rs1.getString("Appointment_Time");
						String user_id = rs1.getString("User_Id");
						int bid = rs1.getInt("Branch_Id");
						String bname = rs1.getString("Branch_Name");
						Appointment appointment = new Appointment(aptDate, aptTime, user_id, bname, bid);
						appointments.add(appointment);
						
					}
					System.out.println(appointments );
					if (appointments.size() > 0){
						Gson gson = new Gson();
						String json = gson.toJson(appointments);
						return json;
					
					}
					else
						return "Success";
				}else {
					return "Failure";
				}

			}
		}catch (Exception e) {
			e.printStackTrace();
			return e.getLocalizedMessage();
		}finally {
			try{
				if(pstmt != null){
					pstmt.close();
				}
				if(conn != null){
					conn.close();
				}
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		return "Failure";
	} 
}
