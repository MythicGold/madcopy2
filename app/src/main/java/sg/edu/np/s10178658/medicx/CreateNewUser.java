package sg.edu.np.s10178658.medicx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateNewUser extends AppCompatActivity {
    DbHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_user);

        Intent intent = getIntent();
        db = new DbHandler(this, null, null, 1);

        //Cancel button
        Button cancel = findViewById(R.id.btnCancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateNewUser.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //Create button
        Button create = findViewById(R.id.btnCreate);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText etUser = findViewById(R.id.editTextUsername);
                EditText etPass = findViewById(R.id.editTextPassword);
                String userName = etUser.getText().toString();
                String password = etPass.getText().toString();

                Pattern pattern = Pattern.compile("^[0-9a-zA-Z]{6,12}$"); //means must be between 6-12 characters and alpha numeric
                Matcher matcher = pattern.matcher(userName);

                Pattern pattern2 = Pattern.compile("^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*+=]).*$"); //means at least 1 symbol, 1 uppercase, 1 numeric
                Matcher matcher2 = pattern2.matcher(password);

                //calling matches method, return true or false, searching for the pattern, and display output
                if(matcher.matches() == true && matcher2.matches() == true) //if Username and Password matches the pattern
                {
                    //Create Account
                    Account a = new Account(userName, password);
                    //Add Account into database
                    db.addAccount(a);

                    //Show toast message saying that new user is created
                    Toast tt = Toast.makeText(CreateNewUser.this, "New User Created.", Toast.LENGTH_LONG); //LENGTH_LONG - toast appear for 3 seconds
                    tt.show();
                    //Bring user to Login page
                    startActivity(new Intent(CreateNewUser.this, MainActivity.class));
                }
                else
                {
                    //Show toast message saying that login credentials are invalid
                    Toast tt = Toast.makeText(CreateNewUser.this, "Invalid Input.", Toast.LENGTH_LONG); //LENGTH_LONG - toast appear for 3 seconds
                    tt.show();
                }
            }
        });
    }
}
