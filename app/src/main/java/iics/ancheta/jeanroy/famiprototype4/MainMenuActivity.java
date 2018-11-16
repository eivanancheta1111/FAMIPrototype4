package iics.ancheta.jeanroy.famiprototype4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenuActivity extends AppCompatActivity {
    Button btnProceed;
    //Button btnSignout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        btnProceed = (Button)findViewById(R.id.proceedButton);
        InputPage();
    }


    public void InputPage(){
        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newActivity = new Intent(MainMenuActivity.this,InputActivity.class);
                startActivity(newActivity);


            }
        });
    }
}
