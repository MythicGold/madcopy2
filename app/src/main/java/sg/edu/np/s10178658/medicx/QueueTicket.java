package sg.edu.np.s10178658.medicx;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class QueueTicket extends AppCompatActivity {
    Account a;
    TextView qNo;
    TextView txtUsername;

    CountDownTimer cdt;
    TextView timeCount;

    TextView counter;
    TextView timeStamp;

    Button btnBack;
    Button btnLeave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queue_ticket2);

        txtUsername = findViewById(R.id.textView13);
        qNo = findViewById(R.id.textView15);
        timeCount = findViewById(R.id.textView16);
        counter = findViewById(R.id.textView17);
        timeStamp = findViewById(R.id.textView18);
        btnBack = findViewById(R.id.button2);
        btnLeave = findViewById(R.id.button);

        //txtUsername.setText("Welcome " + a.getUsername() + "you are currently in queue.");

        //generate string with 5 symbols
        qNo.setText(generateString(5));

        cdt = new CountDownTimer(25 * 60000, 1000) {
            @Override
            public void onTick(long l) {
                Long min = l / 60000;
                Long s = l % 60000 / 1000;
                timeCount.setText("Time before your turn: " + "\n" + Long.toString(min) + ":" + Long.toString(s));
            }

            @Override
            public void onFinish() {
                timeCount.setText("Time before your turn: 0:00");
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
}
