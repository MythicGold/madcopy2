package sg.edu.np.s10178658.medicx;

import java.sql.Time;
import java.util.Date;

public class DayOfAppointment {
    private String username;
    private String date;
    private String time;

    public DayOfAppointment(){}
    public DayOfAppointment(String u, String d, String t){
        u = username;
        d = date;
        t = time;
    }

    public String getUsername(){return username;}

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDate(){return date;}

    public void getDate(String date){this.date = date;}

    public String getTime(){return time;}

    public void getTime(String time){this.time = time;}
}
