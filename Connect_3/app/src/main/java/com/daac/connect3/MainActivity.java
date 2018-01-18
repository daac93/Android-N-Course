package com.daac.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final int BOARD_SIZE = 3;
    private final String RED = "RED";
    private final String YELLOW = "YELLOW";
    private int counter = 0;

    private String [][] board = new String[BOARD_SIZE][BOARD_SIZE];
    private ImageView [][] boardImages = new ImageView[BOARD_SIZE][BOARD_SIZE];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridLayout gridLayout = findViewById(R.id.boardLayout);
        int index = 0;

        for(int i = 0; i < BOARD_SIZE; i++) {
            for(int j = 0; j < BOARD_SIZE; j++) {
                boardImages[i][j] = (ImageView) gridLayout.getChildAt(index);
                index++;
            }
        }
    }

    public void takeTurn(View view) {
        String cellName = (String) view.getTag();
        String [] splitCellName = cellName.split("_");
        String color = null;

        if(canPlayAt(Integer.parseInt(splitCellName[0]), Integer.parseInt(splitCellName[1]))) {
            if (counter % 2 == 0) {
                //Play red
                color = RED;
            } else {
                //Play yellow
                color = YELLOW;
            }

            ImageView chip = (ImageView) view;
            chip.setTranslationY(-1000f);
            switch (color) {
                case RED:
                    chip.setImageResource(R.drawable.red);
                    break;
                case YELLOW:
                    chip.setImageResource(R.drawable.yellow);
                    break;
            }
            chip.animate().translationYBy(1000f).setDuration(300).start();

            board[Integer.parseInt(splitCellName[0])][Integer.parseInt(splitCellName[1])] = color;

            checkWinner();

            counter++;
        }
    }

    public void checkWinner()   {
        String winner = null;

        int [][][] winningPositions = {
                {{0, 0}, {0, 1}, {0, 2}}, //Horizontal
                {{1, 0}, {1, 1}, {1, 2}},
                {{2, 0}, {2, 1}, {2, 2}},
                {{0, 0}, {1, 0}, {2, 0}}, //Vertical
                {{1, 0}, {1, 1}, {1, 2}},
                {{2, 0}, {2, 1}, {2, 2}},
                {{0, 0}, {1, 1}, {2, 2}}, // Diagonals
                {{0, 2}, {1, 1}, {2, 0}}
        };

        for(int [][] winningCoordinate : winningPositions)   {
            String color = board[winningCoordinate[0][0]][winningCoordinate[0][1]];

            if(color != null && color.equals(board[winningCoordinate[1][0]][winningCoordinate[1][1]]))   {
                if(color != null && color.equals(board[winningCoordinate[2][0]][winningCoordinate[2][1]]))   {
                    if(counter % 2 == 0)    {
                        winner = RED;
                    }   else    {
                        winner = YELLOW;
                    }
                }
            }
        }

        if(winner != null) {
            Toast.makeText(this, winner + " wins!", Toast.LENGTH_LONG).show();
            cleanBoard();
        }
    }

    private void animateWinnerPosition(int [][] winnerPosition) {
        for(int i = 0; i < BOARD_SIZE; i++) {
            boardImages[winnerPosition[i][0]][winnerPosition[i][1]].animate().scaleXBy(0.2f).scaleYBy(0.2f).setDuration(2000);
        }
    }

    private boolean canPlayAt(int i, int j)    {
        if(board[i][j] == null && (((i + 1 < BOARD_SIZE) && (board[i + 1][j] != null)) || (i + 1 == BOARD_SIZE))) {
            return true;
        }
        return false;
    }

    private void cleanBoard()   {
        counter %= 2;
        for(ImageView[] row : boardImages)  {
            for(ImageView chip : row)   {
                chip.animate().translationYBy(-1000f).setDuration(600);
                chip.setImageResource(0);
                chip.setTranslationY(-1000f);
                chip.animate().translationY(0).setDuration(0);
            }
        }
        for(int i = 0; i < BOARD_SIZE; i++) {
            for(int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = null;
            }
        }
    }
}
