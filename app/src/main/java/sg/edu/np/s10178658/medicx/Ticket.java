package sg.edu.np.s10178658.medicx;

public class Ticket {
    private String username;
    private String ticket;

    public Ticket() {}
    public Ticket(String u, String t) {
        username = u;
        ticket = t;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }
}
