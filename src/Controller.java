

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller/*")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uri = request.getRequestURI();
		String[] uriparts = uri.split("/");
		String requestModule = uriparts[uriparts.length-1];
		System.out.println();
		if (requestModule.equalsIgnoreCase("signup")) {
			String firstname = request.getParameter("firstname");
			String lastname = request.getParameter("lastname");
			String mailid = request.getParameter("mailid");
			String mobileno = request.getParameter("mobileno");
			String userid = request.getParameter("userid");
			String password = request.getParameter("password");
			User user = new User(firstname,lastname,mailid,mobileno,userid,password);
			System.out.println(user.toString());
			String responseString = SignUPManager.performSignUP(user);
			response.getWriter().print(responseString);
		}else if (requestModule.equalsIgnoreCase("login")) {
			String userid = request.getParameter("userid");
			String password = request.getParameter("password");
			Login login = new Login(userid,password);
			System.out.println(login.toString());
			String responseString = LoginManager.performLogin(login);
			response.getWriter().print(responseString);			
		}else if (requestModule.equalsIgnoreCase("bankbybeacon")) {
			String uid = request.getParameter("uuid");
			String majorString = request.getParameter("major");
			int major = Integer.parseInt(majorString);
			String minorString = request.getParameter("minor");
			int minor = Integer.parseInt(minorString);
			Beacon beacon = new Beacon(uid,major,minor);
			System.out.println("Hello Harish");
			System.out.println(beacon.toString());
			String responseString = BeaconManager.performBeacon(beacon);
			response.getWriter().print(responseString);			
		}else if (requestModule.equalsIgnoreCase("allbanks")) {
			String responseString = BankManager.performBank();
			//System.out.println(beacon.toString());
			//String responseString = BeaconManager.performBeacon(beacon);
			response.getWriter().print(responseString);			
		}else if (requestModule.equalsIgnoreCase("case1")) {
			String bankDetails = request.getParameter("bankdetails");
			System.out.println(bankDetails);
			Gson gson = new Gson();
			Bank bank = gson.fromJson(bankDetails, Bank.class);
			System.out.println(bank.getBranchname());
			String userid = request.getParameter("userid");
			String responseString = BeaconAppointmentManager.performBeaconAppointment(bank, userid);
			//System.out.println(beacon.toString());
			//String responseString = BeaconManager.performBeacon(beacon);
			response.getWriter().print(responseString);			
		}else if (requestModule.equalsIgnoreCase("case2")) {
			String bankDetails = request.getParameter("bankdetails");
			System.out.println(bankDetails);
			Gson gson = new Gson();
			Bank bank = gson.fromJson(bankDetails, Bank.class);
			System.out.println(bank.getBranchname());
			String date = request.getParameter("date");
			String responseString = BankAppointmentManager.performBankAppointment(bank, date);
			//System.out.println(beacon.toString());
			//String responseString = BeaconManager.performBeacon(beacon);
			response.getWriter().print(responseString);			
		}
		else if (requestModule.equalsIgnoreCase("case3")) {
			String bankDetails = request.getParameter("bankdetails");
			System.out.println(bankDetails);
			Gson gson = new Gson();
			Bank bank = gson.fromJson(bankDetails, Bank.class);
			System.out.println(bank.getBranchname());
			String apt_date = request.getParameter("apt_date");
			String apt_time = request.getParameter("apt_time");
			String userid = request.getParameter("user_id");
			String responseString = ConfirmAppointmentManager.performConfirmAppointment(bank, apt_date, apt_time, userid);
			//System.out.println(beacon.toString());
			//String responseString = BeaconManager.performBeacon(beacon);
			response.getWriter().print(responseString);			
		}
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	private int parseInt(String string) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
