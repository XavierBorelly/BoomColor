package com.example.humanbooster.boomcolor;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Affichage de la page de résulta
 */
public class PageDeScore extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_de_score);


        String scoreRecuperer = null;
        //On va récupérer le score envoyer par le jeu
        Bundle extras = getIntent().getExtras();
        if(extras.getString("ScoreRenvoyer") != null) {
            scoreRecuperer = extras.getString("ScoreRenvoyer");
        }

        //Puis l'afficher à l'écrant
        TextView textScore = (TextView) findViewById(R.id.textScore);
        textScore.setText(scoreRecuperer);

        RelativeLayout activity = (RelativeLayout) findViewById(R.id.activity_page_de_score);

        //Renvois a l'acceuil en cas de click sur la page des scores
        activity.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                try {
                    Thread.sleep(1500);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
