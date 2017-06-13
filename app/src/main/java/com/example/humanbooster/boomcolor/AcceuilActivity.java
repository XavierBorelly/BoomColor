package com.example.humanbooster.boomcolor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Page d'acceuil de l'application
 */
public class AcceuilActivity extends AppCompatActivity {

    boolean validationAlert;
    boolean alert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceuil);

        validationAlert = false;
        alert = true;

        //TODO : voir si le code si dessous ne serais pas utile pour faire
        /*DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);*/
    }

    /**
     * Fonction appeller lors de l'appuit sur le bouton
     */
    public void onValidation (View view)
    {
        //On prépare le déplacement vers le jeu
        Intent i = new Intent(AcceuilActivity.this, MainActivity.class);

        TextView textHauteur = (TextView) findViewById(R.id.textHauteur);
        TextView textLargeur = (TextView) findViewById(R.id.textLargeur);

        //On test avant tout si des données sont rentrés
        if(!(textHauteur.getText().toString().equals("") || textLargeur.getText().toString().equals(""))){
            //Ensuite on test si il y a la taille minimal de grille (soit deux boutons minimum)
            if (Integer.parseInt(textHauteur.getText().toString()) >= 1
                    && Integer.parseInt(textLargeur.getText().toString()) >= 1
                    && (Integer.parseInt(textHauteur.getText().toString()) + Integer.parseInt(textLargeur.getText().toString())) > 2) {
                //On test ensuite si la taille est pas trop grande (plus de 15 boutons sur une taille ou un total supérieur a 100 boutons
                //Si c'est le cas on affiche une alerte une seule fois pour prévenir l'utilisateur
                if (Integer.parseInt(textHauteur.getText().toString()) > 15
                        || Integer.parseInt(textLargeur.getText().toString()) > 15
                        || (Integer.parseInt(textHauteur.getText().toString()) * Integer.parseInt(textLargeur.getText().toString())) > 100) {
                    if (!validationAlert) {
                        alert = false;
                    }
                }

                //Puis on envois les donnée au jeu qui va s'occuper de crée les boutons
                if (alert) {
                    i.putExtra("hauteur", textHauteur.getText().toString());
                    i.putExtra("largeur", textLargeur.getText().toString());
                    startActivity(i);
                } else {
                    alert = true;
                    validationAlert = true;
                    Alerte alerte = new Alerte();
                    alerte.show(this.getFragmentManager(), "");
                }
            }
        }
    }
}
