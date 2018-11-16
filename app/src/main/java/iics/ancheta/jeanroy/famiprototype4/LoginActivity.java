package iics.ancheta.jeanroy.famiprototype4;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // buttonRegister (Register)
        final Button btnRegister = (Button) findViewById(R.id.BSignup);
        // Perform action on click
        btnRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Open Form Add
                Intent newActivity = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(newActivity);

            }
        });


        // buttonLogin (Login)
        final Button btnLogin = (Button) findViewById(R.id.BLogin);
        // Perform action on click
        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // If Save Complete
                if(SaveData())
                {
                    //Toast.makeText(LoginActivity.this,"Login Success. ",
                      //      Toast.LENGTH_SHORT).show();
                    Intent newActivity = new Intent(LoginActivity.this,MainMenuActivity.class);
                    startActivity(newActivity);
                }

                // Open Form Show
                //Intent newActivity = new Intent(LoginActivity.this,MenuActivity.class);
                //startActivity(newActivity);

            }
        });
    }

    public boolean SaveData()
    {
        // txtMemberID, txtName, txtTel
        //final EditText tUsername = (EditText) findViewById(R.id.TFemail);
        final EditText tEmail = (EditText) findViewById(R.id.TFemail);
        final EditText tPassword = (EditText) findViewById(R.id.TFpassword);

        // Dialog
        final AlertDialog.Builder adb = new AlertDialog.Builder(this);
        AlertDialog ad = adb.create();

        // Check Email
        if(tEmail.getText().length() == 0)
        {
            ad.setMessage("Please input [Email] ");
            ad.show();
            tEmail.requestFocus();
            return false;
        }

        // Check Password
        if(tPassword.getText().length() == 0)
        {
            ad.setMessage("Please input [Password] ");
            ad.show();
            tPassword.requestFocus();
            return false;
        }

        // new Class DB
        final DatabaseHelper myDb = new DatabaseHelper(this);

        // Check Data (Email exists)
        String emailCheck[] = myDb.SelectData(tEmail.getText().toString());
        String passwordCheck[] = myDb.SelectData(tPassword.getText().toString());

        if(emailCheck == null &&  passwordCheck != null)
        {

            Toast.makeText(LoginActivity.this,"Invalid Email Address",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        if(emailCheck != null &&  passwordCheck == null){
            Toast.makeText(LoginActivity.this,"Invalid Password",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        /**else if(saveStatus <=  0)
        {
            ad.setMessage("Error!! ");
            ad.show();
            return false;
        }
        */

        return true;

    }
}
