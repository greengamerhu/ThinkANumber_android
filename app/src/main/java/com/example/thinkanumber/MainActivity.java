package com.example.thinkanumber;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ImageView elet1, elet2, elet3, elet4, elet5;
    private TextView textViewTipp;
    private Button buttonNovel, buttonCsokken, buttonTippel, buttonKonnyu, buttonNehez;
    private int gondolSzam, tippeltSzam, elet, maxSzam;
    private AlertDialog.Builder builderJatekVege, builderNehezseg;
    private Toast egyediToast;
    private boolean nehezseg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        //ujJatek();

        buttonTippel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (gondolSzam < tippeltSzam) {
                    Toast.makeText(MainActivity.this, "Gondolt szám kisebb", Toast.LENGTH_SHORT).show();
                    eletlevon();
                } else if (gondolSzam > tippeltSzam){
                    Toast.makeText(MainActivity.this, "Gondolt szám nagyobb", Toast.LENGTH_SHORT).show();
                    eletlevon();
                } else {
                    builderJatekVege.show();
                }
            }
        });
        buttonCsokken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tippeltSzam > 1) {
                    tippeltSzam--;
                    textViewTipp.setText(String.valueOf(tippeltSzam));
                } else {
                    Toast.makeText(MainActivity.this, "A szám nem lehet kisebb mint 1", Toast.LENGTH_SHORT).show();
                }
            }
        });
        buttonNovel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tippeltSzam < maxSzam) {
                    tippeltSzam++;
                    textViewTipp.setText(String.valueOf(tippeltSzam));
                } else {
                    Toast.makeText(MainActivity.this, "A szám nem lehet kisebb mint " + maxSzam, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void eletlevon() {
        //okositas péteken
        switch (elet) {
            case 5:
                elet5.setImageResource(R.drawable.heart1);
                break;
            case 4:
                elet4.setImageResource(R.drawable.heart1);
                break;
            case 3:
                elet3.setImageResource(R.drawable.heart1);
                break;
            case 2:
                elet2.setImageResource(R.drawable.heart1);
                break;
            case 1:
                elet1.setImageResource(R.drawable.heart1);
                break;
            default:
                break;
        }
        elet--;

        if (elet <1) {
            builderJatekVege.show();
        }
    }

    private void init() {
        elet1 = findViewById(R.id.elet1);
        elet2 = findViewById(R.id.elet2);
        elet3 = findViewById(R.id.elet3);
        elet4 = findViewById(R.id.elet4);
        elet5 = findViewById(R.id.elet5);
        textViewTipp = findViewById(R.id.textViewTipp);
        buttonCsokken = findViewById(R.id.buttonCsokken);
        buttonNovel = findViewById(R.id.buttonNovel);
        buttonKonnyu = findViewById(R.id.buttonKonnyu);
        buttonNehez = findViewById(R.id.buttonNehez);
        buttonTippel = findViewById(R.id.buttonTipp);

        maxSzam = 10;
        Random rnd = new Random();
        gondolSzam = rnd.nextInt(maxSzam) +1;
        elet = 5;
        tippeltSzam = 0;
        builderJatekVege = new AlertDialog.Builder(MainActivity.this);
        builderJatekVege.setCancelable(false)
                .setTitle("Nyert / vesztett")
                .setMessage("Szeretne új Játékot Indítani?")
                .setNegativeButton("Nem", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setPositiveButton("igen", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //ujJatek();
                    }
                })
                .create();
    }

}