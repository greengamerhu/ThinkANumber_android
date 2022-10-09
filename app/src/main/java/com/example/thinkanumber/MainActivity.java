package com.example.thinkanumber;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.TextureView;
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

        ujJatek();

        buttonTippel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (gondolSzam < tippeltSzam) {
                    Toast.makeText(MainActivity.this, "Gondolt szám kisebb", Toast.LENGTH_SHORT).show();
                    egyediToast.show();
                    eletlevon();
                } else if (gondolSzam > tippeltSzam){
                    Toast.makeText(MainActivity.this, "Gondolt szám nagyobb", Toast.LENGTH_SHORT).show();
                    egyediToast.show();
                    eletlevon();
                } else {
                    builderJatekVege.setTitle("Nyerél").create();
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
        buttonKonnyu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nehezseg = false;
                builderNehezseg.setTitle("Könnyű");
                builderNehezseg.create();
                builderNehezseg.show();
            }
        });
        buttonNehez.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nehezseg = true;
                builderNehezseg.setTitle("Nehéz");
                builderNehezseg.create();
                builderNehezseg.show();
            }
        });




    }

    private void ujJatek() {
        maxSzam = 10;
        Random rnd = new Random();
        gondolSzam = rnd.nextInt(maxSzam) +1;
        elet = 3;
        tippeltSzam = 0;
        textViewTipp.setText(String.valueOf(tippeltSzam));
        elet1.setImageResource(R.drawable.heart2);
        elet2.setImageResource(R.drawable.heart2);
        elet3.setImageResource(R.drawable.heart2);
        elet4.setImageResource(R.drawable.heart2);
        elet5.setImageResource(R.drawable.heart2);
        if (nehezseg) {
            maxSzam = 40;
            gondolSzam = rnd.nextInt(maxSzam) +1;
            elet4.setVisibility(View.VISIBLE);
            elet5.setVisibility(View.VISIBLE);
            elet = 5;
            tippeltSzam = 0;
        }
        else {
            elet4.setVisibility(View.GONE);
            elet5.setVisibility(View.GONE);
        }

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
            builderJatekVege.setTitle("Vesztettél").create();
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


        nehezseg = false;
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
                        ujJatek();
                    }
                })
                .create();
        //Játék nehézség felugró ablak
        builderNehezseg = new AlertDialog.Builder(MainActivity.this);
        builderNehezseg.setCancelable(false)
                .setTitle("Nehéz / köönyü")
                .setMessage("Szeretné változtatni a nehézséget? ")
                .setNegativeButton("nem", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                        ujJatek();
                    }
                })
                .setPositiveButton("igen", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ujJatek();
                    }
                })
                .create();

        egyediToast = new Toast(MainActivity.this);
        egyediToast.setDuration(Toast.LENGTH_SHORT);
        View view = getLayoutInflater().inflate(R.layout.egyedi_toast, findViewById(R.id.customToast));
        egyediToast.setView(view);
        egyediToast.setGravity(Gravity.CENTER, 0,0);

    }

}