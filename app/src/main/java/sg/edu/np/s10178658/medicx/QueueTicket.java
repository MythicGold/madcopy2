package sg.edu.np.s10178658.medicx;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class QueueTicket extends AppCompatActivity {
    Account a;
    Ticket t;
    TextView qNo; //queue number
    TextView txtUsername; //get username from login page

    CountDownTimer cdt;
    long timeLeft;
    TextView timeCount; //display cdt

    TextView counter; //number of people in queue
    TextView timeStamp; //display timestamp

    Button btnBack;
    Button btnLeave;

    DbHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queue_ticket2);

        db = new DbHandler(this, null, null, 1);
        txtUsername = findViewById(R.id.textView13);
        qNo = findViewById(R.id.textView15);
        timeCount = findViewById(R.id.textView16);
        counter = findViewById(R.id.textView17);
        timeStamp = findViewById(R.id.textView18);
        btnBack = findViewById(R.id.btnBack);
        btnLeave = findViewById(R.id.button);

        //txtUsername.setText("Welcome " + a.getUsername() + ", you are currently in queue.");

        //generate string with 5 symbols
        qNo.setText(generateString(5));
        timeLeft = 1;
        /*db.addTicket(new Ticket(a.getUsername(), qNo.toString()));
        Toast tt = Toast.makeText(QueueTicket.this, "Obtained New Ticket Number", Toast.LENGTH_LONG);
        tt.show();*/

        cdt = new CountDownTimer(timeLeft * 60000, 1000) {
            @Override
            public void onTick(long l) {
                
                Long min = l / 60000;
                Long s = l % 60000 / 1000;
                timeCount.setText("Estimated time before your turn: " + "\n" + Long.toString(min) + ":" + Long.toString(s));
            }

            @Override
            public void onFinish() {
                timeCount.setText("Estimated time before your turn: " + "\n" + "0:00");
                new AlertDialog.Builder(QueueTicket.this)
                        .setMessage("Please proceed to the doctor's room as stated.")
                        .show();
                //addNotification();
            }
        };
        cdt.start();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss");
        String format = simpleDateFormat.format(new Date());
        timeStamp.setText("Current Timestamp: " + format);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QueueTicket.this, AppMainActivity.class));
            }
        });

        btnLeave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QueueTicket.this, AppMainActivity.class));
            }
        });
    }


    //Create alphanumeric string
    private String generateString(int length){
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder builder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            builder.append(chars.charAt(random.nextInt(chars.length())));
        }
        return builder.toString();
    }

    /*private void addNotification(){
        Notification.Builder builder = new Notification.Builder(this)
                .setContentTitle("Madics")
                .setContentText("Check your for your queue status!")
                .setSmallIcon(R.mipmap.ic_launcher);

        Intent notificationIntent = new Intent(QueueTicket.this, QueueTicket.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                Intent.FILL_IN_ACTION);
        //to be able to launch your activity from the notification
        builder.setContentIntent(contentIntent);

        //Add as notification
        NotificationManager notifManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notifManager.notify(001, builder.build());
    }*/

}
