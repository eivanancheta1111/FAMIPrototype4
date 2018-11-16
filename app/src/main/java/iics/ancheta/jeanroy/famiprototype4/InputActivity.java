package iics.ancheta.jeanroy.famiprototype4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class InputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        Button SubmitButton = (Button)findViewById(R.id.submitButton);
        final RadioGroup rg = (RadioGroup) findViewById(R.id.mutualFundTypeRadioGroup);
        final EditText InvestorNameTxtfield= (EditText)findViewById(R.id.investorNameTextfield);
        final RadioGroup TypesOfMutualFund  = (RadioGroup)findViewById(R.id.mutualFundTypeRadioGroup);
        final EditText AmountTxtfield = (EditText)findViewById(R.id.investorAmountTextfield);





        SubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                //-----------------VALUES TO BE PASSED-----------------//
                String Name = InvestorNameTxtfield.getText().toString();
                String value =
                        ((RadioButton)findViewById(rg.getCheckedRadioButtonId()))
                                .getText().toString();
                String FundType = value;
                String Amount = AmountTxtfield.getText().toString();
                double num1,sum;

                num1 = Double.parseDouble(Amount);

                sum = num1 + 500;
                String Result = (Double.toString(sum));


                if (
                        (Name.equals("") && !(Amount.equals("")) ) ||
                                (!(Name.equals("")) && Amount.equals("") ) ||
                                ( Name.equals("") &&  Amount.equals("") )
                        )
                {
                    Toast.makeText(getApplicationContext(), "Fill the form", Toast.LENGTH_LONG).show();
                }
                else if(
                        (!(Name.equals("")) && (Double.parseDouble(Amount) < 1000)) ||
                                (Name.equals("") && (Double.parseDouble(Amount) < 1000))
                        )
                {
                    Toast.makeText(getApplicationContext(), "Should invest only greater or equal to 1000", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Intent moveRelative= new Intent (getApplicationContext(), DisplayInputActivity.class);

                    //putExtra method take two parameter,first is key  and second is value. By using key, we retrieve data

                    moveRelative.putExtra("TransferredName", Name);
                    moveRelative.putExtra("TransferredFundType", FundType);
                    moveRelative.putExtra("TransferredAmount", Amount);
                    moveRelative.putExtra("TransferredComputedSalesLoad", Result);
                    startActivity(moveRelative);
                    //finish();
                }
            }
        });

    }

}

