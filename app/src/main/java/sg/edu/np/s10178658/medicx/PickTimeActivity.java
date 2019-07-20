package sg.edu.np.s10178658.medicx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PickTimeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_time);

        Intent intent = getIntent();
        String time = intent.getStringExtra(SelectDateActivity.TIME_TEXT);
        String date = intent.getStringExtra(SelectDateActivity.DATE_TEXT);
    }
}
