package DBStore;

import java.util.ArrayList;
import java.util.HashMap;

import models.Ladder;
import models.Snake;
import models.User;

public class InMemoryDB {
    public static ArrayList<Ladder> ladders = new ArrayList<>();
    public static ArrayList<Snake> snakes = new ArrayList<>();
    public static HashMap<Integer, User> userMap = new HashMap<>();
    public static int playerCount = 0 ;
    

    public static int getPlayerCount() {
        return playerCount;
    }

    public static void setPlayerCount(int playerCount) {
        InMemoryDB.playerCount = playerCount;
    }

    public static String getUserId(String userName){
        return userName + "_" + System.currentTimeMillis();
    }

    public static void addUserInDB(User user){
        userMap.put(user.getPlayerNumber(), user);
    }

    public static User getUser(int playerNum){
        return userMap.get(playerNum);
    }

    public static void addSnake(Snake snake){
        snakes.add(snake);
    }
    
    public static void addLadder(Ladder ladder){
        ladders.add(ladder);
    }

    public static int isSnakePosition(int position){
        int value = -1;

        for(Snake snake: snakes){
            if(snake.getStart() == position){
                value = snake.getEnd();
            }
        }
        return value;
    }

    public static int isLadderPosition(int position){
        int value = -1;

        for(Ladder ladder: ladders){
            if(ladder.getStart() == position){
                value = ladder.getEnd();
            }
        }
        return value;
    }
    
}
