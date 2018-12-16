package com.example.patrik.tomeg;

import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button szamol_button;
    EditText atmero_edit, hossz_edit, eredmeny_edit;
    Double suruseg;
    ArrayList<Anyag> anyagok = new ArrayList<>();
    Spinner anyag_spinner,alakzat_spinner;
    ConstraintLayout layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Feltolt();
        szamol_button = findViewById(R.id.szamol_button);
        atmero_edit = findViewById(R.id.atmero_edit);
        hossz_edit = findViewById(R.id.hossz_edit);
        eredmeny_edit = findViewById(R.id.eredmeny_edit);
        layout = findViewById(R.id.mainlayout);
        anyag_spinner = findViewById(R.id.anyag_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Nevlist());
        anyag_spinner.setAdapter(adapter);
        alakzat_spinner = findViewById(R.id.alakzat_spinner);
        Addbutton();
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
        suruseg = anyagok.get(anyag_spinner.getSelectedItemPosition()).getSuruseg();
        eredmeny = (((((atmero * atmero) * Math.PI) / 4) * hossz / 1000000) * suruseg);
        eredmeny_edit.setText(String.valueOf(eredmeny));
    }

    public void Feltolt(){
        String[] adatok = getResources().getStringArray(R.array.anyag_array);
        for(int i=0;i<adatok.length;i++){
            String[] adat = adatok[i].split(";");
            anyagok.add(new Anyag(adat[0],Double.parseDouble(adat[1])));
        }
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

    public void Addbutton(){
        //add edit
        ConstraintSet set = new ConstraintSet();
        set.clone(layout);
        EditText boldal_edit = new EditText(this);
        boldal_edit.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
        boldal_edit.setTextSize(TypedValue.COMPLEX_UNIT_SP,24);
        boldal_edit.setId(100);           // <-- Important
        layout.addView(boldal_edit);
        TextView boldal_text = new TextView(this);
        boldal_text.setTextSize(24);
        boldal_text.setText("B oldal");
        boldal_text.setId(200);
        layout.addView(boldal_text);
        set.connect(100, ConstraintSet.TOP, R.id.atmero_edit, ConstraintSet.BOTTOM, 0);
        set.connect(100,ConstraintSet.LEFT,R.id.atmero_edit,ConstraintSet.LEFT,0);
        set.connect(100,ConstraintSet.RIGHT,R.id.atmero_edit,ConstraintSet.RIGHT,0);
        set.connect(200,ConstraintSet.LEFT,R.id.anyag_text,ConstraintSet.LEFT,0);
        set.connect(200,ConstraintSet.TOP,100,ConstraintSet.TOP,0);
        set.connect(200,ConstraintSet.BOTTOM,100,ConstraintSet.BOTTOM,0);
        set.connect(200,ConstraintSet.RIGHT,100,ConstraintSet.LEFT,0);
        set.constrainHeight(boldal_text.getId(), ViewGroup.LayoutParams.WRAP_CONTENT);
        set.constrainHeight(boldal_edit.getId(), ViewGroup.LayoutParams.WRAP_CONTENT);
        //set.connect(R.id.atmero_edit,ConstraintSet.BOTTOM,100,ConstraintSet.TOP,0);
        set.connect(R.id.hossz_edit,ConstraintSet.TOP,100,ConstraintSet.BOTTOM,8);
        set.applyTo(layout);
    }
}
