package sg.edu.np.s10178658.medicx;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class SelectDateActivity extends AppCompatActivity {

    public static final String DATE_TEXT = "sg.edu.np.s10178658.medicx.DATE_TEXT";
    public static final String TIME_TEXT = "sg.edu.np.s10178658.medicx.TIME_TEXT";
    Button confirm = findViewById(R.id.btnConfirm);
    Button selectDate = findViewById(R.id.btnDate);
    Button back = findViewById(R.id.btnBack);
    TextView date = findViewById(R.id.tvSelectedDate);
    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;

    Calendar calendar1 = Calendar.getInstance();
    int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
    int currentMinute = calendar.get(Calendar.MINUTE);
    String amPm;
    EditText chooseTime = findViewById(R.id.etChooseTime);
    TimePickerDialog timePickerDialog;

    DbHandler db;
    DayOfAppointment da;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_date);

        db = new DbHandler(this, null, null, 1);

        Intent intent = getIntent();
        String un = intent.getStringExtra(MainActivity.this, MainActivity.class);

        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(SelectDateActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                date.setText(day + "/" + (month + 1) + "/" + year);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.show();
            }
        });

        chooseTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar1 = Calendar.getInstance();
                currentHour = calendar1.get(Calendar.HOUR_OF_DAY);
                currentMinute = calendar1.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(SelectDateActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        if (hourOfDay >= 12) {
                            amPm = "PM";
                        } else {
                            amPm = "AM";
                        }
                        chooseTime.setText(String.format("%02d:%02d", hourOfDay, minutes) + amPm);
                        chooseTime.setText(hourOfDay + ":" + minutes);
                    }
                }, currentHour, currentMinute, false);
                timePickerDialog.show();
            }
        });

        confirm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                openPickTimeActivity();
                String dbDate = date.getText().toString();
                String dbTime = chooseTime.getText().toString();
                if (dbDate.length() != 0 && dbTime.length() != 0){
                    DayOfAppointment da = new DayOfAppointment(un, dbDate, dbTime);
                    db.addTime(da);
                }

               /* String newEntry = date.getText().toString() + chooseTime.getText().toString();
                if (date.length() != 0){
                    DateTime dt = new DateTime(username, )
                    AddTime(newEntry);
                    date.setText("");
                }
                if (chooseTime.length() != 0){
                    AddTime(newEntry);
                    date.setText("");
                }
                else{
                    Toast.makeText(SelectDateActivity.this, "Please Select A Day",Toast.LENGTH_LONG).show();
                }*/
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SelectDateActivity.this, AppMainActivity.class));
            }
        });

    }
    public void openPickTimeActivity(){
        EditText editTextTime = findViewById(R.id.etChooseTime);
        String time1 = editTextTime.getText().toString();
        TextView textviewDate = findViewById(R.id.tvSelectedDate);
        String date1 = textviewDate.getText().toString();

        Intent intent = new Intent(this, PickTimeActivity.class);
        intent.putExtra(TIME_TEXT, time1);
        intent.putExtra(DATE_TEXT, date1);
        startActivity(intent);
    }



}
