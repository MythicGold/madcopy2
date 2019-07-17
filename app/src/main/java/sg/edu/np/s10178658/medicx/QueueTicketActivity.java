package sg.edu.np.s10178658.medicx;

import android.annotation.SuppressLint;
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

public class QueueTicketActivity extends AppCompatActivity {
    Account a;
    TextView qNo;
    TextView username;

    CountDownTimer cdt;
    TextView timeCount;

    TextView counter;
    TextView timeStamp;

    Button btnBack;
    Button btnLeave;

    @SuppressLint({"ClickableViewAccessibility", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
        setContentView(R.layout.activity_queue_ticket);


        username = findViewById(R.id.tvUsername);
        qNo = findViewById(R.id.tvQNo);
        timeCount = findViewById(R.id.tvTimeCount);
        counter = findViewById(R.id.tvCounter);
        timeStamp = findViewById(R.id.tvTimeStamp);
        btnBack = findViewById(R.id.btnBack);
        btnLeave = findViewById(R.id.btnLeaveQ);

        //Get random tix num
        RandomTicketNumber rtn = new RandomTicketNumber();
        rtn.generateString(5);

        username.setText("Welcome " + a.getUsername() + "you are currently in queue.");
        qNo.setText(rtn.toString());

        cdt = new CountDownTimer(25 * 60000, 1000) {
            @Override
            public void onTick(long l) {
                Long min = l / 60000;
                Long s = l % 60000 / 1000;
                timeCount.setText("Time before your turn: " + Long.toString(min) + ":" + Long.toString(s));
            }

            @Override
            public void onFinish() {
                timeCount.setText("Time before your turn: 0:00");
            }
        };
        cdt.start();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
        String format = simpleDateFormat.format(new Date());
        timeStamp.setText("Current timestamp: " + format);


        btnBack.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                startActivity(new Intent(QueueTicketActivity.this, AppMainActivity.class));
                return true;
            }
        });

        btnLeave.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                startActivity(new Intent(QueueTicketActivity.this, AppMainActivity.class));
                return true;
            }
        });*/
    }

}
