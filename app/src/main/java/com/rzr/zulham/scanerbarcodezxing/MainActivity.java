package com.rzr.zulham.scanerbarcodezxing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);

    }
    //code ini untuk memanggil scanner dengan menggunakan button
    public void scanBarcode(View view){
        new IntentIntegrator((Activity)this).initiateScan();
    }

    @Override
    //code ini untuk mengambil hasil dari barcode yang di scan
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                boolean isInserted =  myDb.insertData(result.getContents().toString());
                if (isInserted = true)
                    Toast.makeText(this, "Code Berhasil Disimpan", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(this, "Code Gagal Disimpan", Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }




}
