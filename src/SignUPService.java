import java.sql.*;

public class SignUPService {
	
	public static String performSignUp(User user){
		Connection conn = null;
		PreparedStatement pstmt = null;
		Statement stmt = null;
		try{
			Class.forName(ConnConfig.JDBC_DRIVER);
			conn = DriverManager.getConnection(ConnConfig.DB_URL, ConnConfig.USERNAME, "");
			String sql = "INSERT INTO Users(FName, LName, Email, Phone, User_Id, Password) VALUES (?,?,?,?,?,?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getFirstname());
			pstmt.setString(2, user.getLastname());
			pstmt.setString(3, user.getMailid());
			pstmt.setString(4, user.getMobileno());
			pstmt.setString(5, user.getUserid());
			pstmt.setString(6, user.getPassword());
			
			int result = pstmt.executeUpdate();
			if(result == 1){
				return "Success";
			}else{
				return "Failure";
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
