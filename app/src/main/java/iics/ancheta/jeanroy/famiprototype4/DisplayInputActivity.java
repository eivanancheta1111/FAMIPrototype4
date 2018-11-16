package iics.ancheta.jeanroy.famiprototype4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class DisplayInputActivity extends AppCompatActivity {
    Button BackButton;
    Button SignoutButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_input);

         BackButton = (Button)findViewById(R.id.buttonBack);
         SignoutButton = (Button)findViewById(R.id.buttonSignout);

        final TextView NameResult = (TextView) findViewById(R.id.txtInvestorName);
        final TextView FundTypeResult = (TextView) findViewById(R.id.txtFundType);
        final TextView AmountResult = (TextView) findViewById(R.id.txtAmountInvested);
        final TextView SalesLoadResult = (TextView) findViewById(R.id.txtSalesLoadAmount);

        final TextView NAVPSResult = (TextView) findViewById(R.id.txtNavps);
        final TextView NETResult = (TextView) findViewById(R.id.txtNetAmount);
        final TextView SharesResult = (TextView) findViewById(R.id.txtTotalShares);

        DecimalFormat df = new DecimalFormat("#.##");



        //We create  object of Bundle class that help to get data from MainActivity_mainPage and used its  getString method

        Bundle bn = getIntent().getExtras();
        String Name = bn.getString("TransferredName");
        String FundType = bn.getString("TransferredFundType");
        String Amount = bn.getString("TransferredAmount");



        double AmountInvested = Double.parseDouble(Amount);


        //String.format("Value of a: %.2f", Amount);
        double SalesLoadAmount = 0;
        double NET_Amount_invested = 0;
        double TotalSharesBought =0;




        double SalesLoadPercentage= SalesLoadEquivalent(AmountInvested);
        double NAVPS = NAVPS_Equivalent(String.valueOf(FundType));
        SalesLoadAmount = AmountInvested * SalesLoadPercentage;
        NET_Amount_invested = AmountInvested - SalesLoadAmount;
        TotalSharesBought = AmountInvested / NAVPS;

        String twoDigitNAVPS = df.format(NAVPS);
        String twoDigitSalesLoadAmount = df.format(SalesLoadAmount);
        String twoDigitNetResult = df.format(NET_Amount_invested);
        String twoDigitShares = df.format(TotalSharesBought);

        String twoDigitAmount = df.format(AmountInvested);

        NameResult .setText(String.valueOf(Name));
        FundTypeResult .setText(String.valueOf(FundType));
        AmountResult .setText(twoDigitAmount);

        NAVPSResult.setText(twoDigitNAVPS);

        //String.format("%.2f", Double.toString(NET_Amount_invested))


        SalesLoadResult .setText(twoDigitSalesLoadAmount);
        NETResult .setText( twoDigitNetResult);
        SharesResult.setText(twoDigitShares);



        Signout();
        MainMenu();

    }

    public double SalesLoadEquivalent(double Amount){
        double percentageDecimal = 0;
        if((1000 <= Amount)||(Amount <= 99999.99)){
            percentageDecimal = 0.02;
        }
        else if((100000 <= Amount)||(Amount <= 499999.99)){
            percentageDecimal = 0.15;
        }
        else if((500000.00 <= Amount)||(Amount <= 1999999.99)){
            percentageDecimal = 0.01;
        }
        else {
            percentageDecimal = 0.005;
        }
        return percentageDecimal;

    }
    public double NAVPS_Equivalent(String TypeOfFund) {
        double NAVPS_Decimal=0;
        if(TypeOfFund.contains("SALEF")){
            NAVPS_Decimal = 4.9746;
        }
        else if(TypeOfFund.contains("SALBF ")){
            NAVPS_Decimal = 2.4415;
        }
        else{
            NAVPS_Decimal = 2.2117;
        }
        return NAVPS_Decimal;
    }


    public void Signout(){
        SignoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }

    public void MainMenu(){
        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }
}
