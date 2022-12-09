package com.mapbox.evsahayak;

import static android.widget.Toast.makeText;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class payment extends AppCompatActivity {

    public static final String PAYTM_PACKAGE_NAME = "net.one97.paytm";
    EditText name, upiId, amount, note;
    TextView msg;
    Button pay;
    public static String payername, vpa, msgNote, status, sendAmount;
    Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        name = findViewById(R.id.name);
        upiId = findViewById(R.id.upi_id);
        amount = findViewById(R.id.amount);
        note = findViewById(R.id.txn_note);
        msg = findViewById(R.id.msg);
        pay = findViewById(R.id.payNow);


        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                payername = name.getText().toString();
                vpa = upiId.getText().toString();
                sendAmount = amount.getText().toString();
                msgNote = note.getText().toString();
                if(payername != null && vpa != null && sendAmount != null && msgNote != null){
                    uri = getPayTmUri(payername, vpa, msgNote, sendAmount);
                    payWithPayTm(PAYTM_PACKAGE_NAME);
                }

                else{
                    makeText(payment.this, "Please fill all details.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private static Uri getPayTmUri(String name, String upiId, String note, String amount){
        return new Uri.Builder()
                .scheme("upi")
                .authority("pay")
                .appendQueryParameter("pa", upiId)
                .appendQueryParameter("pn", name)
                .appendQueryParameter("tn", note)
                .appendQueryParameter("am", amount)
                .appendQueryParameter("cu", "INR")
                .build();
    }

    private void payWithPayTm(String packageName){
        if(isAppInstalled(this, packageName)){
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(uri);
            i.setPackage(packageName);
            startActivityForResult(i, 0);
        }

        else{
            makeText(this, "Paytm is not installed Please install and try again.", Toast.LENGTH_SHORT).show();
        }
    }

    public static boolean isAppInstalled(Context context, String packageName){
        try{
            context.getPackageManager().getApplicationInfo(packageName,0);
            return true;
        }catch(PackageManager.NameNotFoundException e){
            return false;
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(data != null){
            status = data.getStringExtra("status").toLowerCase();
        }
        if(RESULT_OK == resultCode && status.equals("success")){

            Toast.makeText(this, "Transaction Successful" + data.getStringExtra("ApprovalRefNo"), Toast.LENGTH_SHORT).show();
            msg.setText("Transaction Successful of INR "+ sendAmount);
            msg.setTextColor(Color.GREEN);
        }
        else{
            Toast.makeText(this, "Transaction Failed", Toast.LENGTH_SHORT).show();
            msg.setText("Transaction Failed of INR "+ sendAmount);
            msg.setTextColor(Color.RED);
        }
    }}
