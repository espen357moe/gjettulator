package no.espenmoe.gjettulator;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {

    private String playerName;
    private String playerTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //setter opp inputfelt for spillerens navn og spinner for spillerens tittel
        final EditText playerNameEditText = (EditText) findViewById(R.id.player_name_EditText);
        final Spinner spinner = (Spinner) findViewById(R.id.title_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.titles_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //setter opp startknapp som starter GameActivity med en intent og sender med en bundle med spillerens navn og tittel
        Button startButton = (Button) findViewById(R.id.start_game_button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(playerNameEditText.getText().toString().equals("")){
                    Context context = getApplicationContext();
                    CharSequence text = "Du må skrive inn et navn!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                } else {
                playerName = (playerNameEditText.getText().toString());
                playerTitle = spinner.getSelectedItem().toString();

                Intent intent = new Intent(SplashActivity.this, GameActivity.class);
                    Bundle b = new Bundle();
                    b.putString("playerName", playerName);
                    b.putString("playerTitle", playerTitle);
                    intent.putExtra("playerBundle", b);
                    startActivity(intent);
                }
            }
        });
    }
}
