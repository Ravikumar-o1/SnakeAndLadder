package service;

import java.security.DrbgParameters.NextBytes;
import java.util.Random;
import java.util.Scanner;

import DBStore.InMemoryDB;
import models.Ladder;
import models.Snake;
import models.User;

public class SnakeAndLadderRunner {
    private static Scanner sc = new Scanner(System.in);

    private static GameViewerService viewerService = new GameViewerService();

    public static void main(String[] args){

        initializeGame();

    }

    public static void addUser(int playerNum){
        System.out.println("Enter User Name: ");
        String userName = sc.next();
        User user = new User(userName, InMemoryDB.getUserId(userName), playerNum);
        InMemoryDB.addUserInDB(user);
    }

    public static void  initializeGame(){
        int num = 0;
        System.out.println("Enter Number of Players");
        num = sc.nextInt();
        InMemoryDB.setPlayerCount(num);
        int count = 1;
        System.out.println("Enter Details for Players");
        while(count <= num){
            addUser(count);
            count = count + 1;
        }

        System.out.println("Thanks for Filling Details...\n\n");
        System.out.println("Enter Details of Snakes:");
        addSnakes();
        System.out.println("Enter Details of Ladders:");
        addLadders();

        play();
    }

    public static void addSnakes(){
        while(true){
            System.out.println("Enter Start and End for Snake: ");
            System.out.println("Enter start: ");
            int start = sc.nextInt();
            System.out.println("\n Enter End: ");
            int end = sc.nextInt();
            Snake snake = new Snake(start, end);
            InMemoryDB.addSnake(snake);
            System.out.println("Are we done with Snake Addition...(Yes/No):");
            String isDone = sc.next();
            if(isDone.equals("Yes")) break;
        }
    }

    public static void addLadders() {
        while(true){
            System.out.println("Enter Start and End for Ladder: ");
            System.out.println("Enter start: ");
            int start = sc.nextInt();
            System.out.println("\n Enter End: ");
            int end = sc.nextInt();
            Ladder ladder = new Ladder(start, end);
            InMemoryDB.addLadder(ladder);
            System.out.println("Are we done with Ladder Addition...(Yes/No):");
            String isDone = sc.next();
            if(isDone.equals("Yes")) break;
        }
    }
    public static int getRolledNumber(){
        return new Random().nextInt(6) + 1;
    }

    public static void play(){
        while(true){
            int count = 0 ;
            for(int i =1; i <= InMemoryDB.getPlayerCount() ; i ++){
                User user = InMemoryDB.getUser(i);
                if(user.getIsWinner()){
                    count ++;
                    System.out.println("Already won....! Continue to next player.");
                    continue;
                }
                viewerService.showSnakeAndLadderView();

                System.out.println("Rolling dice for user: " + user.getUserName());
                int rolledNumber = getRolledNumber();
                System.out.println("Rolled Outcome:" + rolledNumber);
                int nextPosition = user.getCurrentPosition() + rolledNumber;
                int isSnakePosition = InMemoryDB.isSnakePosition(nextPosition);
                int isLadderPosition = InMemoryDB.isLadderPosition(nextPosition);
                if(isSnakePosition != -1){
                    nextPosition = isSnakePosition;
                }
                if(isLadderPosition != -1){
                    nextPosition = isLadderPosition;
                }
                if(nextPosition > 100){
                    System.out.println("Out of Range move, Continue to Next Player");
                    continue;
                }
                if(nextPosition == 100){
                    user.setIsWinner(true);
                }
                user.setCurrentPosition(nextPosition);

                System.out.println("User : " + user.getUserName() + " next position is: " + nextPosition);
            }
            if(count == InMemoryDB.playerCount - 1) break;
            System.out.println("Continue to next Move ? (Yes/No)");
            String nextMove = sc.next();
            if(nextMove.equals("Yes")){
                continue;
            }else{
                break;
            }
        }
    }
}
