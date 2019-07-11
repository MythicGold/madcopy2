package sg.edu.np.s10178658.medicx;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "AndroidClarified";
    int RC_SIGN_IN = 0;
    private GoogleSignInClient mGoogleSignInClient;
    private SignInButton googleSignInButton;

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

    public void onClick(View v)
    {
        EditText et1 = findViewById(R.id.editText);
        EditText et2 = findViewById(R.id.editText2);
        String userName = et1.getText().toString();
        String password = et2.getText().toString();

        Pattern pattern = Pattern.compile("^[0-9a-zA-Z]{6,12}$"); //means must be between 6-12 characters and alpha numeric
        Matcher matcher = pattern.matcher(userName);

        Pattern pattern2 = Pattern.compile("^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*+=]).*$"); //means at least 1 symbol, 1 uppercase, 1 numeric
        Matcher matcher2 = pattern2.matcher(password);

        //calling matches method, return true or false, searching for the pattern, and display output
        if(matcher.matches() == true && matcher2.matches() == true)
        {
            Toast tt = Toast.makeText(MainActivity.this, "Valid Login Credentials", Toast.LENGTH_LONG); // so when timer runs out, show toast notification that timer has expired LENGTH_LONG will appear for 3 seconds
            tt.show(); // like countdown timer, must show
        }
        else
        {
            Toast tt = Toast.makeText(MainActivity.this, "Invalid Login Credentials", Toast.LENGTH_LONG); // so when timer runs out, show toast notification that timer has expired LENGTH_LONG will appear for 3 seconds
            tt.show(); // like countdown timer, must show
        }
    }
}
