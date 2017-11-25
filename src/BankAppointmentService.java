import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.DateFormat;
//import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import com.google.gson.Gson;

//import com.google.gson.Gson;

import java.sql.ResultSet;

public class BankAppointmentService {
	
	public static String performBankAppointment(Bank bank, String apt_date){
		Connection conn = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		String apt_Time = null;
		String msg1 = "Your Appointment Successfully Booked at ";
		String[] times = {"9:00","9:15","9:30","9:45","10:00","10:15","10:30","10:45","11:00","11:15","11:30","11:45","12:00","12:15","12:30","12:45","13:00","13:15","13:30","13:45","14:00","14:15","14:30","14:45","15:00","15:15","15:30","15:45","16:00","16:15","16:30","16:45"};
		ArrayList<String> al = new ArrayList<String>(Arrays.asList(times));
		//Statement stmt = null;

		try{
			Class.forName(ConnConfig.JDBC_DRIVER);
			conn = DriverManager.getConnection(ConnConfig.DB_URL, ConnConfig.USERNAME, "");
			Date date = new Date();
			String dateString = new SimpleDateFormat("yyyy-MM-dd").format(date);
			String curr_time = new SimpleDateFormat("HH:MM").format(date);
			System.out.println("date: "+dateString);
			String sql = "SELECT Appointment_Time, COUNT(Appointment_Time) AS slots From Booking WHERE Appointment_Date = ? AND Branch_Id = ? GROUP BY Appointment_Time";			
			pstmt1 = conn.prepareStatement(sql);
			pstmt1.setString(1, apt_date);
			pstmt1.setInt(2, bank.getBranchid());

			
			ResultSet rs1 = pstmt1.executeQuery();
			if (apt_date.equals(dateString)){
			DateFormat df2 = new SimpleDateFormat("hh:mm");
			Date date2 = df2.parse(curr_time);			
			for (int i=0;i<al.size();i++){
				String altime = al.get(i);
				DateFormat df1 = new SimpleDateFormat("hh:mm");
				Date date1 = df1.parse(altime);
				if (date2.after(date1)){
					al.remove(i);
					i = i-1;
				}
			}
			}
			while (rs1.next()) {
				String aptTime = rs1.getString("Appointment_Time");
				int slots = rs1.getInt("slots");
				if (slots >= bank.getNoofrepresentatives()){
				
					for (int j=0;j<al.size();j++){
						if (aptTime.equals(al.get(j))){
							al.remove(j);
							j = j-1;
						}
								
				}
			}
			}

			rs1.close();
			if (al.size() >0) {
			Gson gson = new Gson();
			String json = gson.toJson(al);
			return json;
			}
			else 
				return "No slots available on this day";
			//apt_Time = al.get(0);
					
			
		}catch (Exception e) {
			e.printStackTrace();
			return e.getLocalizedMessage();
		}finally {
			try{
				if(pstmt1 != null){
					pstmt1.close();
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
