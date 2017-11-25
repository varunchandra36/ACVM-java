import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.DateFormat;
//import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

//import com.google.gson.Gson;

import java.sql.ResultSet;

public class ConfirmAppointmentService {
	
	public static String performConfirmAppointment(Bank bank, String apt_date, String apt_time, String userid){
		Connection conn = null;
		PreparedStatement pstmt = null;
		String apt_Time = null;
		String msg1 = "Your Appointment Successfully Booked at ";
		
		try{
			Class.forName(ConnConfig.JDBC_DRIVER);
			conn = DriverManager.getConnection(ConnConfig.DB_URL, ConnConfig.USERNAME, "");
			String sql = "INSERT INTO Booking(Appointment_Date, Appointment_Time, User_Id, Branch_Id, Branch_Name) VALUES (?,?,?,?,?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, apt_date);
			pstmt.setString(2, apt_time);
			pstmt.setString(3, userid);
			pstmt.setInt(4, bank.getBranchid());
			pstmt.setString(5, bank.getBranchname());
				
			int result = pstmt.executeUpdate();
			String output = msg1+apt_time;
			if(result == 1){
				return output;
			}else{
				return "Booking Failed";
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
		
	} 
}
