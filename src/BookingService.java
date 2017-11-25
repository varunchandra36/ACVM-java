import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

public class BookingService {
	
	public static String performBooking(Booking booking){
		Connection conn = null;
		PreparedStatement pstmt = null;
		Statement stmt = null;
		try{
			Class.forName(ConnConfig.JDBC_DRIVER);
			conn = DriverManager.getConnection(ConnConfig.DB_URL, ConnConfig.USERNAME, "");
			String sql = "SELECT Representatives FROM Bank WHERE Branch_ID= ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, booking.getBranchid());
			
			
			ResultSet rs = pstmt.executeQuery();
			

			
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
