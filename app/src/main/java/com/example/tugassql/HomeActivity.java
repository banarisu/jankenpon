package com.example.tugassql;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class HomeActivity extends AppCompatActivity {
    TextView welcUser, txtSelected, txtOpponent, txtResult, txtRound, txtW, txtL, txtD;
    ImageView dimR, dimP, dimS, oppR, oppP, oppS;
    Button buttonLogout, buttonReset, buttonConfirm;
    ImageButton btnRock, btnPaper, btnScissors;
    int buttonSelected = 0, com, round = 0, wn = 0, ln = 0, dn = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        welcUser = findViewById(R.id.textWelcome);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            welcUser.setText("Welcome, " + bundle.getString("Username"));
        }

        defAllComponents();
        setDefaultVisibility();

        btnRock.setOnClickListener(view -> {
            buttonSelected = 1;
            txtSelected.setText("You Choose : Rock");
            txtSelected.setVisibility(View.VISIBLE);
            dimR.setVisibility(View.VISIBLE);
            dimP.setVisibility(View.GONE);
            dimS.setVisibility(View.GONE);
        });
        btnPaper.setOnClickListener(view -> {
            buttonSelected = 2;
            txtSelected.setText("You Choose : Paper");
            txtSelected.setVisibility(View.VISIBLE);
            dimR.setVisibility(View.GONE);
            dimP.setVisibility(View.VISIBLE);
            dimS.setVisibility(View.GONE);
        });
        btnScissors.setOnClickListener(view -> {
            buttonSelected = 3;
            txtSelected.setText("You Choose : Scissors");
            txtSelected.setVisibility(View.VISIBLE);
            dimR.setVisibility(View.GONE);
            dimP.setVisibility(View.GONE);
            dimS.setVisibility(View.VISIBLE);
        });

        buttonReset.setOnClickListener(view -> {
            buttonSelected = 0;
            round = 0;
            wn = 0;
            ln = 0;
            dn = 0;
            setDefaultVisibility();
        });

        buttonConfirm.setOnClickListener(view -> {
            if (buttonSelected == 0){
                Toast.makeText(getApplication(), "Please choose a hand!", Toast.LENGTH_SHORT).show();
            }
            else if (buttonSelected == 1){
                setOpponentMove();
            }
            else if (buttonSelected == 2){
                setOpponentMove();
            }
            else if (buttonSelected == 3){
                setOpponentMove();
            }
        });

        buttonLogout.setOnClickListener(view -> {
            Intent logoutIntent = new Intent(HomeActivity.this, MainActivity.class);
            logoutIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            logoutIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            logoutIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(logoutIntent);
        });
    }

    private void defAllComponents(){
        btnRock = findViewById(R.id.imgBtnRock);
        btnPaper = findViewById(R.id.imgBtnPaper);
        btnScissors = findViewById(R.id.imgBtnScissors);
        dimR = findViewById(R.id.dimRock);
        dimP = findViewById(R.id.dimPaper);
        dimS = findViewById(R.id.dimScissors);

        txtSelected = findViewById(R.id.txtSelected);
        buttonConfirm = findViewById(R.id.btnConfirm);
        txtRound = findViewById(R.id.txtRound);

        txtOpponent = findViewById(R.id.textOpponent);
        oppR = findViewById(R.id.oppRock);
        oppP = findViewById(R.id.oppPaper);
        oppS = findViewById(R.id.oppScissors);
        txtResult = findViewById(R.id.txtResult);

        buttonLogout = findViewById(R.id.btnLogout);
        buttonReset = findViewById(R.id.btnReset);

        txtW = findViewById(R.id.txtW);
        txtL = findViewById(R.id.txtL);
        txtD = findViewById(R.id.txtD);
    }

    private void setDefaultVisibility(){
        dimR.setVisibility(View.GONE);
        dimP.setVisibility(View.GONE);
        dimS.setVisibility(View.GONE);
        txtSelected.setVisibility(View.GONE);
        txtOpponent.setVisibility(View.GONE);
        oppR.setVisibility(View.INVISIBLE);
        oppP.setVisibility(View.INVISIBLE);
        oppS.setVisibility(View.INVISIBLE);
        txtResult.setVisibility(View.GONE);
        txtRound.setVisibility(View.INVISIBLE);
        txtW.setVisibility(View.INVISIBLE);
        txtL.setVisibility(View.INVISIBLE);
        txtD.setVisibility(View.INVISIBLE);
        txtW.setText("Win : "+wn);
        txtL.setText("Lose : "+ln);
        txtD.setText("Draw : "+dn);
    }

    private void setOpponentMove(){
        Random rps = new Random();
        com = rps.nextInt(3);
        round++;
        String rou = String.format("Round : %d", round);
        txtRound.setText(rou);
        if (com == 0){ //Rock
            txtOpponent.setVisibility(View.VISIBLE);
            oppR.setVisibility(View.VISIBLE);
            oppP.setVisibility(View.INVISIBLE);
            oppS.setVisibility(View.INVISIBLE);
            txtRound.setVisibility(View.VISIBLE);
            txtW.setVisibility(View.VISIBLE);
            txtL.setVisibility(View.VISIBLE);
            txtD.setVisibility(View.VISIBLE);
            if (buttonSelected == 1){
                txtResult.setText("Draw!");
                dn++;
                txtD.setText("Draw : "+dn);
            }
            else if (buttonSelected == 2){
                txtResult.setText("You Win!");
                wn++;
                txtW.setText("Win : "+wn);
            }
            else if (buttonSelected == 3){
                txtResult.setText("You Lose!");
                ln++;
                txtL.setText("Lose : "+ln);
            }
            txtResult.setVisibility(View.VISIBLE);
        }
        else if (com == 1){ //Paper
            txtOpponent.setVisibility(View.VISIBLE);
            oppR.setVisibility(View.INVISIBLE);
            oppP.setVisibility(View.VISIBLE);
            oppS.setVisibility(View.INVISIBLE);
            txtRound.setVisibility(View.VISIBLE);
            txtW.setVisibility(View.VISIBLE);
            txtL.setVisibility(View.VISIBLE);
            txtD.setVisibility(View.VISIBLE);
            if (buttonSelected == 1){
                txtResult.setText("You Lose!");
                ln++;
                txtL.setText("Lose : "+ln);
            }
            else if (buttonSelected == 2){
                txtResult.setText("Draw!");
                dn++;
                txtD.setText("Draw : "+dn);
            }
            else if (buttonSelected == 3){
                txtResult.setText("You Win!");
                wn++;
                txtW.setText("Win : "+wn);
            }
            txtResult.setVisibility(View.VISIBLE);
        }
        else if (com == 2){ //Scissors
            txtOpponent.setVisibility(View.VISIBLE);
            oppR.setVisibility(View.INVISIBLE);
            oppP.setVisibility(View.INVISIBLE);
            oppS.setVisibility(View.VISIBLE);
            txtRound.setVisibility(View.VISIBLE);
            txtW.setVisibility(View.VISIBLE);
            txtL.setVisibility(View.VISIBLE);
            txtD.setVisibility(View.VISIBLE);
            if (buttonSelected == 1){
                txtResult.setText("You Win!");
                wn++;
                txtW.setText("Win : "+wn);
            }
            else if (buttonSelected == 2){
                txtResult.setText("You Lose!");
                ln++;
                txtL.setText("Lose : "+ln);
            }
            else if (buttonSelected == 3){
                txtResult.setText("Draw!");
                dn++;
                txtD.setText("Draw : "+dn);
            }
            txtResult.setVisibility(View.VISIBLE);
        }
    }
}
