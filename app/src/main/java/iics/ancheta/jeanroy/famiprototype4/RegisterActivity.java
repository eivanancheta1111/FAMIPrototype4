package iics.ancheta.jeanroy.famiprototype4;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends Activity  {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        // btnSave (Save)
        final Button RegisterButton = (Button) findViewById(R.id.btnRegister);
        RegisterButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // If Save Complete
                if(SaveData())
                {
                    // Open Form Main
                    Intent newActivity = new Intent(RegisterActivity.this,LoginActivity.class);
                    startActivity(newActivity);
                }
            }
        });


        // btnLogin (Login)
        final Button backButton= (Button) findViewById(R.id.btnBack);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Open Form Main
                Intent newActivity = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(newActivity);
            }
        });

    }

    public boolean SaveData()
    {
        // txtMemberID, txtName, txtTel
        final EditText tUsername = (EditText) findViewById(R.id.txtUsername);
        final EditText tEmail = (EditText) findViewById(R.id.txtEmail);
        final EditText tPassword = (EditText) findViewById(R.id.txtPassword);

        // Dialog
        final AlertDialog.Builder adb = new AlertDialog.Builder(this);
        AlertDialog ad = adb.create();

        // Check Username
        if(tUsername.getText().length() == 0)
        {
            ad.setMessage("Please input [Username] ");
            ad.show();
            tUsername.requestFocus();
            return false;
        }

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
        String arrData[] = myDb.SelectData(tEmail.getText().toString());
        if(arrData != null)
        {
            ad.setMessage("Email already exists!  ");
            ad.show();
            tEmail.requestFocus();
            return false;
        }

        // Save Data
        long saveStatus = myDb.InsertData(tUsername.getText().toString(),
                tPassword.getText().toString(),
                tEmail.getText().toString());
        if(saveStatus <=  0)
        {
            ad.setMessage("Error!! ");
            ad.show();
            return false;
        }

        Toast.makeText(RegisterActivity.this,"User Data Added Successfully. ",
                Toast.LENGTH_SHORT).show();

        return true;
    }

}
