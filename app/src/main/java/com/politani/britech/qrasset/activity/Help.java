package com.app.britech.riung.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.app.britech.riung.R;
import com.app.britech.riung.Utility.TypeFaceTextView;


public class Help extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initControl();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void initControl () {
        TypeFaceTextView btn_call_1,btn_call_2;
        btn_call_1 = (TypeFaceTextView) findViewById(R.id.call_1);
        btn_call_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = "+6285393952736";
                Uri call = Uri.parse("tel:" + number);
                Intent surf = new Intent(Intent.ACTION_DIAL, call);
                startActivity(surf);
            }
        });
        btn_call_2 = (TypeFaceTextView) findViewById(R.id.call_2);
        btn_call_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = "+6285257476232";
                Uri call = Uri.parse("tel:" + number);
                Intent surf = new Intent(Intent.ACTION_DIAL, call);
                startActivity(surf);
            }
        });
    }
}
