package com.example.humanbooster.boomcolor;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.TableLayout;

import java.util.ArrayList;
import java.util.List;

//TODO ajouter le fait de devoir appuyer sur plusieur bouton a la fois

/**
 * Activité principal du jeu
 */
public class MainActivity extends AppCompatActivity {

    int hauteurGrille;
    int largeurGrille;
    private List<MyButtun> boutons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle extras = getIntent().getExtras();
        LinearLayout layout = (LinearLayout) findViewById(R.id.activity_main);

        hauteurGrille = Integer.parseInt(extras.getString("hauteur"));
        largeurGrille = Integer.parseInt(extras.getString("largeur"));


        boutons = new ArrayList<>();

        //Création dynamique des éléments
        for (int k = 0; k <= hauteurGrille - 1; k++) {
            //On commence par crée un LinearLayout horizontal avec un poid total du nombre de bouton en largeur
            LinearLayout groupeBouton = new LinearLayout(this);
            groupeBouton.setLayoutParams(new TableLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, 0, 1f));
            groupeBouton.setOrientation(LinearLayout.HORIZONTAL);
            groupeBouton.setWeightSum(largeurGrille);

            //On l'ajoute au Layout principal
            layout.addView(groupeBouton);

            for (int j = 0; j <= largeurGrille - 1; j++) {
                //Puis on crée un bouton que l'on ajoute au Layout principal et a la liste des bouton
                MyButtun newMyButton = new MyButtun(this);
                newMyButton.setLayoutParams(new TableLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1f));
                groupeBouton.addView(newMyButton);
                boutons.add(newMyButton);

                //On calcul la position actuel du nouveau boutons
                int i = j + k * (largeurGrille);

                //Puis on set sa couleur et ce qui ce passe en cas de click
                int colorVide = getResources().getColor(R.color.vide);
                boutons.get(i).setBackgroundColor(colorVide);

                boutons.get(i).setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {

                        MyButtun thisButtun = (MyButtun) view;
                        if (thisButtun.isColorer()) {
                            //Si le bouton est activer on le reset et on augmente son score
                            int colorVide = getResources().getColor(R.color.vide);
                            thisButtun.setBackgroundColor(colorVide);
                            thisButtun.setColorer(false);
                            thisButtun.setScore();
                            thisButtun.setText("");

                            //Avant d'en choisir un nouveau
                            changementAleatoire();
                        } else {
                            //On stop le chrono
                            Chronometer chronometer = (Chronometer) findViewById(R.id.chronometer);
                            chronometer.stop();

                            //On récupére le score
                            int score = score();

                            //On envois le résulta total concaténé en une chaine de caractère
                            String textScore = "Score de : " + score + "point en : " + chronometer.getText().toString() + " sur une grille de " + hauteurGrille + "X" + largeurGrille;

                            Intent i = new Intent(MainActivity.this, PageDeScore.class);

                            i.putExtra("ScoreRenvoyer", textScore);
                            //Puis on ferme le jeu
                            finish();
                            startActivity(i);
                        }

                        //Une fois le bouton crée on continu jusqu'a crée tous les boutons de la ligne, puis on crée toutes les lignes de la même manière
                    }
                });

            }
        }

        //initialise un chronometre
        Chronometer chronometer = (Chronometer) findViewById(R.id.chronometer);
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();

        //Puis une fois les boutons crées on en choisi un aléatoirement
        changementAleatoire();
    }

    /**
     * Stop le chronometre si on sort de l'application (Appel par exemple)
     */
    @Override
    protected void onPause() {
        super.onPause();
        Chronometer chronometer = (Chronometer) findViewById(R.id.chronometer);
        chronometer.stop();
    }

    /**
     * relance le chrono quand on revien sur l'application
     */
    @Override
    protected void onResume() {
        super.onResume();
        Chronometer chronometer = (Chronometer) findViewById(R.id.chronometer);
        chronometer.start();
    }


    private List<MyButtun> getBoutons() {
        return boutons;
    }

    /**
     * Fonction pour choisir le prochain bouton alétoire et le colorie en vert avec le text "push me !"
     */
    public void changementAleatoire() {
        int max = getBoutons().size() - 1;
        int mini = 0;
        int random = (int) (Math.random() * (max - mini + 1)) + mini;
        int color = getResources().getColor(R.color.clicker);
        getBoutons().get(random).setBackgroundColor(color);
        getBoutons().get(random).setColorer(true);
        boutons.get(random).setText(R.string.bouton_actif);
    }

    /**
     * Pour le calcul du score tout les boutons sont appeller et donne leur propre score qui est ensuite additionné
     */
    public int score() {
        int score = 0;
        for (int i = 0; i <= getBoutons().size() - 1; i++) {
            score = score + getBoutons().get(i).getScore();
        }

        return score;
    }
}
