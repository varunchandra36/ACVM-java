import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;

import com.google.gson.Gson;

import java.sql.ResultSet;

public class BankService {
	
	public static String performBank(){
		Connection conn = null;
		PreparedStatement pstmt1 = null;
		//Statement stmt = null;
		try{
			Class.forName(ConnConfig.JDBC_DRIVER);
			conn = DriverManager.getConnection(ConnConfig.DB_URL, ConnConfig.USERNAME, "");
			String sql = "SELECT * From Bank";
			
			pstmt1 = conn.prepareStatement(sql);
		
			ResultSet rs1 = pstmt1.executeQuery();
			ArrayList<Bank> banks = new ArrayList<>();
			while (rs1.next()) {
				int branchid = rs1.getInt("Branch_ID");
				String name = rs1.getString("Branch_Name");
				String address1 = rs1.getString("Address_Line1");
				String address2 = rs1.getString("Address_Line2");
				String city = rs1.getString("City");
				String state = rs1.getString("State");
				int zipcode = rs1.getInt("Zipcode");
				int representatives = rs1.getInt("Representatives");
				Bank bank = new Bank(name,address1,address2,city,state,branchid,zipcode,representatives);
				banks.add(bank);
					
				}	
			Gson gson = new Gson();
			String json = gson.toJson(banks);
			//System.out.println(json);
			return json;

			
		}catch (Exception e) {
			e.printStackTrace();
			return "Failed";
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
