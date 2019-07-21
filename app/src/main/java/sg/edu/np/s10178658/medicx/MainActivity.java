package sg.edu.np.s10178658.medicx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "AndroidClarified";
    int RC_SIGN_IN = 0;
    private GoogleSignInClient mGoogleSignInClient;
    private SignInButton googleSignInButton;
    DbHandler db;
    Account a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing Views
        googleSignInButton = findViewById(R.id.sign_in_button);

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        googleSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //SignIn
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });

        db = new DbHandler(this, null, null, 1);

        TextView newUser = findViewById(R.id.txtNewUser);
        newUser.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    Intent intent = new Intent(MainActivity.this, CreateNewUserActivity.class);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });

        TextView forgetPassword = findViewById(R.id.txtForgetPassword);
        forgetPassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    Intent intent = new Intent(MainActivity.this, ForgetPasswordActivity.class);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            startActivity(new Intent(MainActivity.this, ProfileActivity.class));
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("Google Sign In Error", "signInResult:failed code=" + e.getStatusCode());
            Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void onStart() {
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(account != null) {
            startActivity(new Intent(MainActivity.this, ProfileActivity.class));
        }
        super.onStart();
    }

    public void onClickLogin(View v)
    {
        EditText et1 = findViewById(R.id.editTextUsername);
        EditText et2 = findViewById(R.id.editTextPassword);
        String userName = et1.getText().toString();
        String password = et2.getText().toString();

        a = db.findAccount(userName);

        if(userName.equals(a.getUsername()) && password.equals(a.getPassword())) //checking if matching username and password exists in the database
        {
            //Show toast message saying that login credentials are valid
            Toast tt = Toast.makeText(MainActivity.this, "Valid Login Credentials", Toast.LENGTH_LONG); //LENGTH_LONG - toast appear for 3 seconds
            tt.show();
            //Bring user to AppMain page
            Intent intent = new Intent(MainActivity.this, AppMainActivity.class);
            intent.putExtra("Username", a.getUsername());
            startActivity(intent);
        }
        else
        {
            //Show toast message saying that login credentials are invalid
            Toast tt = Toast.makeText(MainActivity.this, "Invalid Login Credentials", Toast.LENGTH_LONG); //LENGTH_LONG - toast appear for 3 seconds
            tt.show();
        }
    }
}
