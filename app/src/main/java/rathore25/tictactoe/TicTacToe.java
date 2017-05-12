package rathore25.tictactoe;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class TicTacToe extends AppCompatActivity {
    private String _Player1Id = "";
    private String _Player2Id = "";
    private int _PlayersTurn = 1;
    private int _GridStatus[][] = new int[3][3];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);

        Intent intent = getIntent();
        _Player1Id = intent.getStringExtra(MainActivity.Player1IdText);
        _Player2Id = intent.getStringExtra(MainActivity.Player2IdText);

        SetPlayer1Active();
    }

    public void OnBoxSelect(View view){
        ImageButton button = (ImageButton) findViewById(view.getId());
        button.setImageResource(GetGridImage());
        button.setClickable(false);
        button.setEnabled(false);
        SetGridStatus(button, _PlayersTurn);
        TextView statusText = (TextView) findViewById(R.id.statusView);
        int Status = CheckStatus();
        TogglePlayer();
        if(Status == 1 || Status == 2){
            statusText.setText(Status == 1 ? "Player 1 has won" : "Player 2 has won");
        }
        else if(Status == -1)
            statusText.setText("It's a draw");
        else{
            statusText.setText(_PlayersTurn == 1 ? "Player 1's turn" : "Player 2's turn");
        }
    }

    private void SetGridStatus(ImageButton button, int player){
        String tag = button.getTag().toString();
        int row = tag.charAt(0) - '0';
        int col = tag.charAt(1) - '0';
        _GridStatus[row][col] = player;
    }

    private int CheckStatus(){
        boolean hasEmptyBoxes = false;
        for(int i =0; i<3;i++){
            if(_GridStatus[i][0] != 0 && _GridStatus[i][1] != 0 && _GridStatus[i][2] != 0 ){
                int sum = _GridStatus[i][0] + _GridStatus[i][1] + _GridStatus[i][2];
                if(sum == 3)
                    return 1;
                else if( sum == 6)
                    return 2;
            }
            else {
                hasEmptyBoxes = true;
            }
        }
        for(int i=0; i<3;i++){
            if(_GridStatus[0][i] != 0 && _GridStatus[1][i] != 0 && _GridStatus[2][i] != 0 ){
                int sum = _GridStatus[0][i] + _GridStatus[1][i] + _GridStatus[2][i];
                if(sum == 3)
                    return 1;
                else if( sum == 6)
                    return 2;
            }
            else {
                hasEmptyBoxes = true;
            }
        }
        if(_GridStatus[0][0] != 0 && _GridStatus[1][1] != 0 && _GridStatus[2][2] != 0 ){
            int sum = _GridStatus[0][0] + _GridStatus[1][1] + _GridStatus[2][2];
            if(sum == 3)
                return 1;
            else if( sum == 6)
                return 2;
        }
        else {
            hasEmptyBoxes = true;
        }
        if(_GridStatus[0][2] != 0 && _GridStatus[1][1] != 0 && _GridStatus[2][0] != 0 ){
            int sum = _GridStatus[0][2] + _GridStatus[1][1] + _GridStatus[2][0];
            if(sum == 3)
                return 1;
            else if( sum == 6)
                return 2;
        }
        else {
            hasEmptyBoxes = true;
        }
        if(hasEmptyBoxes)
            return 0;
        else
            return -1;
    }

    private void TogglePlayer(){
        _PlayersTurn = _PlayersTurn == 1 ? 2 : 1;

        if(_PlayersTurn == 1)
            SetPlayer1Active();
        else
            SetPlayer2Active();
    }

    private int GetGridImage(){
        if(_PlayersTurn == 1)
            return R.drawable.img_cross;
        else
            return R.drawable.img_circle;
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

    private int GetInActivePlayerIcon(String playerId) {
        switch (playerId) {
            case "Batman": return R.drawable.img_player_batman;
            case "Superman": return R.drawable.img_player_superman;
            case "Ironman": return R.drawable.img_player_ironman;
            case "CaptainAmerica": return R.drawable.img_player_captainamerica;
            default:return 0;
        }
    }

    private void SetPlayer1Active(){
        ImageButton Player1 = (ImageButton) findViewById(R.id.player1);
        Player1.setImageResource(GetActivePlayerIcon(_Player1Id));
        ImageButton Player2 = (ImageButton) findViewById(R.id.player2);
        Player2.setImageResource(GetInActivePlayerIcon(_Player2Id));
    }

    private void SetPlayer2Active(){
        ImageButton Player1 = (ImageButton) findViewById(R.id.player1);
        Player1.setImageResource(GetInActivePlayerIcon(_Player1Id));
        ImageButton Player2 = (ImageButton) findViewById(R.id.player2);
        Player2.setImageResource(GetActivePlayerIcon(_Player2Id));
    }
}
