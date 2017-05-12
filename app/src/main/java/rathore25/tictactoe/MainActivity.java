package rathore25.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private String _Player1Id="";
    private String _Player2Id="";
    public static final String Player1IdText = "TicTacToe.Player1Id";
    public static final String Player2IdText = "TicTacToe.Player2Id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void SelectHero(View view) {
        ImageButton button = (ImageButton) findViewById(view.getId());
        if(_Player1Id == "") {
            _Player1Id = GetHeroName(view.getId());
            button.setClickable(false);
            button.setEnabled(false);
            button.setImageResource(GetActivePlayerIcon(_Player1Id));
            TextView Text = (TextView) findViewById(R.id.MainMessage);
            Text.setText("Player 2, select you hero");
        }
        else if(_Player2Id == "") {
            _Player2Id = GetHeroName(view.getId());
            Intent intent = new Intent(this, TicTacToe.class);
            intent.putExtra(Player1IdText, _Player1Id);
            intent.putExtra(Player2IdText, _Player2Id);
            startActivity(intent);
        }
    }

    private String GetHeroName(int viewId) {
        switch (viewId){
            case R.id.hero00: return "Batman";
            case R.id.hero01: return "Superman";
            case R.id.hero10: return "Ironman";
            case R.id.hero11: return "CaptainAmerica";
            default: return "";
        }
    }

    private int GetActivePlayerIcon(String playerHeroName) {
        switch (playerHeroName) {
            case "Batman": return R.drawable.img_player_batman_active;
            case "Superman": return R.drawable.img_player_superman_active;
            case "Ironman": return R.drawable.img_player_ironman_active;
            case "CaptainAmerica": return R.drawable.img_player_captainamerica_active;
            default:return 0;
        }
    }
}
