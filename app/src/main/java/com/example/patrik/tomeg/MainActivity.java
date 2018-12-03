package com.example.patrik.tomeg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button szamol_button;
    EditText atmero_edit,hossz_edit,eredmeny_edit;
    Double suruseg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        szamol_button = findViewById(R.id.szamol_button);
        atmero_edit = findViewById(R.id.atmero_edit);
        hossz_edit = findViewById(R.id.hossz_edit);
        eredmeny_edit = findViewById(R.id.eredmeny_edit);
        Spinner spinner =findViewById(R.id.anyag_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.anyag_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
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

        eredmeny = (((((atmero*atmero)*Math.PI)/4)*hossz/1000000)*suruseg);
        eredmeny_edit.setText(String.valueOf(eredmeny));
    }

    class Anyag{
        String nev;
        Double suruseg;
        public Anyag(String nev, Double suruseg){
            this.nev = nev;
            this.suruseg = suruseg;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
