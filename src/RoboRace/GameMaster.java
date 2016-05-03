package RoboRace;

import java.util.*;
import java.io.*;

public class GameMaster {
    
    private int numberPlayers;
    private Board board;
    private Factory factory;
    private Player[] player;
    private CardList[] resultHand;
    private CardFactory cardFactory;
    
    public GameMaster (int numberPlayers, String[] name){
        this.numberPlayers = numberPlayers;
        this.player = new Player[numberPlayers];
        Robot[] robot = new Robot[numberPlayers];
        for (int i = 0; i < numberPlayers; i++){
            robot[i] = new Robot(name[i], 2*i + 1);
        }
        factory = Factory.load("factory.xml");
        board = new Board(factory, numberPlayers, robot);
        for (int i = 0; i < numberPlayers; i++){
            player[i] = new Player(name[i]);
        }
    }
    
    public void run(){
        System.out.print("Hello, World! I am the GameMaster.\n");
        System.out.print("Are you you the PlayerKeeper?");
        // Give Board to Players.
        for (int i = 0; i < numberPlayers; i++){
            player[i].receiveBoard(board);
        }
        CardList[] playerHand = new CardList[numberPlayers];
        CardList[] resultHand = new CardList[numberPlayers];
        // loop
        // Revitalize robots
        // Generate new card set and distribute
        cardFactory = new CardFactory();
        for (int i = 0; i < numberPlayers; i++){
            playerHand[i] = new CardList();
            resultHand[i] = new CardList();
            for (int j = 0; j < 7; j++){
                playerHand[i].add(cardFactory.createCard());
            }
            // receive chosen card list
            resultHand[i] = player[i].selectCards(playerHand[i]);
        }
        // Sort Cards and execute per action.
        Card Card[] = new Card[5];
        int associate[] = new int[5];
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < numberPlayers; j++){
                Card[j] = resultHand[j].pop();
            }
            // Sort
            for (int j = 0; j < numberPlayers; j++){
                //
            }
        }
        // execute tile effect
        // end loop
    }
}