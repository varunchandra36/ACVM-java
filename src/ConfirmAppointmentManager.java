
public class ConfirmAppointmentManager {

	public static String performConfirmAppointment(Bank bank, String apt_date, String apt_time, String userid){
		return ConfirmAppointmentService.performConfirmAppointment(bank, apt_date, apt_time, userid);
}
}
