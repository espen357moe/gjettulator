package no.espenmoe.gjettulator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.Timer;

public class GameActivity extends Activity {

    private boolean gameStarted = false;

    private String playerName;
    private String playerTitle;
    long timeElapsedInMilliseconds;

    int min = 1;
    int max = 99;
    int numberToGuess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //setter opp referanser til UI-kontrollene
        final Button checkAnswerButton      = (Button)findViewById(R.id.checkAnswerButton);
        final ImageView hintArrow           = (ImageView)findViewById(R.id.hintArrow);
        final TextView playerInfoTextView   = (TextView)findViewById(R.id.playerInfo);
        final TextView gameOverTextView     = (TextView)findViewById(R.id.gameOverTextView);
        final EditText playerInput          = (EditText)findViewById(R.id.playerInput);

        //setter opp tidtakerfunksjonalitet
        Context context = getApplicationContext();
        final Chronometer chronometer = new Chronometer(context);

        //henter bundle fra initierende aktivitet, skriver spillernavn til skjermen
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("playerBundle");
        playerName = bundle.getString("playerName");
        playerTitle = bundle.getString("playerTitle");

        playerInfoTextView.setText(playerTitle + " " + playerName+", gjett et tall mellom 1 og 99");

        //starter tidteller når spiller begynner å taste inn første tall
        playerInput.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (!gameStarted) {
                    gameStarted = true;
                    chronometer.start();
                    Random rnd = new Random();
                    numberToGuess = rnd.nextInt(max - min + 1) + min;
                }

                return false;
            }
        });

        //sjekker spillerens inntastede tall mot det tilfeldig genererte tallet
        checkAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int playerinput = Integer.parseInt(playerInput.getText().toString());

                if (playerinput < numberToGuess){
                    hintArrow.setImageDrawable((getDrawable(R.drawable.arrow_up)));
                    playerInput.setText("");
                }

                if (playerinput > numberToGuess){
                    playerInput.setText("");
                    hintArrow.setImageDrawable((getDrawable(R.drawable.arrow_down)));
                }

                if (playerinput == numberToGuess){
                    hintArrow.setImageDrawable((getDrawable(R.drawable.smiley_face)));
                    chronometer.stop();
                    timeElapsedInMilliseconds = SystemClock.elapsedRealtime() - chronometer.getBase();
                    checkAnswerButton.setEnabled(false);
                    gameOverTextView.setText("Du brukte " + (timeElapsedInMilliseconds / 1000) + " sekunder");

                    GameSession session = new GameSession(playerName, playerTitle, timeElapsedInMilliseconds);
                }
            }
        });
    }
}
