package com.example.patrik.tomeg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.DOMStringList;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button szamol_button;
    EditText atmero_edit, hossz_edit, eredmeny_edit;
    Double suruseg;
    ArrayList<Anyag> anyagok = new ArrayList<>();
    Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anyagok.add(new Anyag("Vas", 7.87));
        anyagok.add(new Anyag("Bronz", 8.2));
        anyagok.add(new Anyag("Aluminium", 2.7));
        //TODO: Objektumokat XML fájlból
        szamol_button = findViewById(R.id.szamol_button);
        atmero_edit = findViewById(R.id.atmero_edit);
        hossz_edit = findViewById(R.id.hossz_edit);
        eredmeny_edit = findViewById(R.id.eredmeny_edit);
        spinner = findViewById(R.id.anyag_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Nevlist());
        spinner.setAdapter(adapter);
        szamol_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double atmero, hossz;
                try {
                    atmero = Double.parseDouble(atmero_edit.getText().toString());
                    hossz = Double.parseDouble(hossz_edit.getText().toString());
                    szamol(atmero, hossz);
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Hülye vagy fiam!!!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void szamol(Double atmero, Double hossz) {
        Double eredmeny;
        suruseg = anyagok.get(spinner.getSelectedItemPosition()).getSuruseg();
        eredmeny = (((((atmero * atmero) * Math.PI) / 4) * hossz / 1000000) * suruseg);
        eredmeny_edit.setText(String.valueOf(eredmeny));
    }

    class Anyag {
        String nev;
        Double suruseg;

        public Anyag(String nev, Double suruseg) {
            this.nev = nev;
            this.suruseg = suruseg;
        }

        public String getNev() {
            return nev;
        }

        public double getSuruseg() {
            return suruseg;
        }


    }

    public ArrayList<String> Nevlist() {
        ArrayList<String> nevlist = new ArrayList<>();
        for (int i = 0; i < anyagok.size(); i++) {
            nevlist.add(anyagok.get(i).getNev());
        }
        return nevlist;
    }

}
