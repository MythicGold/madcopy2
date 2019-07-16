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

public class ForgetPasswordActivity extends AppCompatActivity {
    DbHandler db;
    Account a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        //Cancel button
        Button cancel = findViewById(R.id.btnCancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgetPasswordActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //Confirm button
        Button confirm = findViewById(R.id.btnConfirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText etUser = findViewById(R.id.editTextUserName);
                EditText etNewPass = findViewById(R.id.editTextNewPass);
                EditText etNewCfmPass = findViewById(R.id.editTextNewCfmPass);

                String userName = etUser.getText().toString();
                String password = etNewPass.getText().toString();
                String cfmPassword = etNewCfmPass.getText().toString();

                Pattern pattern = Pattern.compile("^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*+=]).*$"); //means at least 1 symbol, 1 uppercase, 1 numeric
                Matcher matcher = pattern.matcher(password);
                Matcher matcher2 = pattern.matcher(cfmPassword);

                a = db.findAccount(userName);

                if (matcher.matches() == true && matcher2.matches() == true && password == cfmPassword && a.getUsername() != null) //if Username and Password matches the pattern
                {
                    //Add Account into database
                    //db.updatePassword(userName, password);

                    //Show toast message saying that password has been reset
                    Toast tt = Toast.makeText(ForgetPasswordActivity.this, "Password has been reset.", Toast.LENGTH_LONG); //LENGTH_LONG - toast appear for 3 seconds
                    tt.show();
                    //Bring user to Login page
                    startActivity(new Intent(ForgetPasswordActivity.this, MainActivity.class));
                }
                else if (a.getUsername() == null){
                    //Show toast message saying that account is not found
                    Toast tt = Toast.makeText(ForgetPasswordActivity.this, "Account not found.", Toast.LENGTH_LONG); //LENGTH_LONG - toast appear for 3 seconds
                    tt.show();
                }
                else if (password != cfmPassword){
                    //Show toast message saying that password does not match
                    Toast tt = Toast.makeText(ForgetPasswordActivity.this, "Password does not match.", Toast.LENGTH_LONG); //LENGTH_LONG - toast appear for 3 seconds
                    tt.show();
                }
                else {
                    //Show toast message saying that inputs are invalid
                    Toast tt = Toast.makeText(ForgetPasswordActivity.this, "Invalid Input.", Toast.LENGTH_LONG); //LENGTH_LONG - toast appear for 3 seconds
                    tt.show();
                }
            }
        });
    }
}
