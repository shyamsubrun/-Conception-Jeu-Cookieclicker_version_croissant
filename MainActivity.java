package com.example.entrainementa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int nproduction = 1 ;
    public static int ncroissant = 0 ;


    public static final String NCROISSANT= "com.example.entrainementa.NCROISSANT";
    public static final String NPRODUCTION= "com.example.entrainementa.NPRODUCTION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if( savedInstanceState != null ){
            ncroissant = savedInstanceState . getInt ( NCROISSANT ,ncroissant) ;
            nproduction = savedInstanceState . getInt ( NPRODUCTION ,nproduction) ;
        }



        TextView croissant = findViewById(R.id.croissant);
        TextView production = findViewById(R.id.production);
        TextView button = findViewById(R.id.button);
        ImageView imagecroissant = findViewById(R.id.imagecroissant);

        croissant.setText("nombre de croissant "+ncroissant);
        production.setText("production : "+nproduction);
        button.setText("augmenter la production "+computeNextProd(nproduction));


        imagecroissant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {                                                    /*compteur marche
                                                                                     mais ne s'actualise pas a l'écrans                               */
                ncroissant++;
                croissant.setText("nombre de croissant :"+Integer.toString(ncroissant));
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ncroissant>=computeNextProd(nproduction)){
                    ncroissant=ncroissant-computeNextProd(nproduction);
                    croissant.setText("nombre de croissant "+ ncroissant);
                    nproduction++;
                    production.setText("Production:" + nproduction);
                    button.setText("augmenter la production "+computeNextProd(nproduction));
                }else{
                    Toast.makeText(getApplicationContext(),
                            "Pas assez de Croissant",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    protected void onSaveInstanceState ( Bundle outState ) {
        outState.putInt(NPRODUCTION,nproduction);
        super.onSaveInstanceState(outState);

        outState.putInt(NCROISSANT,ncroissant);
        super.onSaveInstanceState(outState);
    }



    public int computeNextProd(int croissant){
        return 10*(croissant*croissant);
    }
}