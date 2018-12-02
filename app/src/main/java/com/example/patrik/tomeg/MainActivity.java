package com.example.patrik.tomeg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button szamol_button;
    EditText atmero_edit,hossz_edit,eredmeny_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        szamol_button = findViewById(R.id.szamol_button);
        atmero_edit = findViewById(R.id.atmero_edit);
        hossz_edit = findViewById(R.id.hossz_edit);
        eredmeny_edit = findViewById(R.id.eredmeny_edit);
        szamol_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double atmero,hossz;
                try {
                    atmero = Double.parseDouble(atmero_edit.getText().toString());
                    hossz = Double.parseDouble(hossz_edit.getText().toString());
                    szamol(atmero,hossz);
                }
                catch(Exception e){
                    Toast.makeText(MainActivity.this,"Hülye vagy fiam!!!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void szamol(Double atmero, Double hossz){
        Double eredmeny;

        eredmeny = (((((atmero*atmero)*Math.PI)/4)*hossz/1000000)*7.8);
        eredmeny_edit.setText(String.valueOf(eredmeny));
    }

}
