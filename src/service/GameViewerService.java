package service;

import DBStore.InMemoryDB;
import models.Ladder;
import models.Snake;

public class GameViewerService{

    public void showSnakeAndLadderView(){

        int[] grid = new int[101];

        for(Snake snake: InMemoryDB.snakes){
            grid[snake.getStart()] = snake.getEnd();
        }
        for(Ladder ladder : InMemoryDB.ladders){
            grid[ladder.getStart()] = ladder.getEnd();
        }

        int[][] view = new int[10][10];

        for(int i = 0 ; i <100; i ++){
            view[10 - i/10 - 1][i%10] = grid[ i +1 ];
        }
        String viewString = "";
        for(int i = 0 ; i <=9;  i ++){
            for(int j = 0 ; j<=9; j ++){
                viewString += view[i][j] + " ";
            }
            viewString+="\n";
        }
        System.out.println(viewString);
    }

}