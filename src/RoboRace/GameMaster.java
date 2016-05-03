package RoboRace;

import java.util.*;
import java.io.*;

public class GameMaster {
    
    private int numberPlayers;
    private Board board;
    private Factory factory;
    private Player[] player;
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
        // loop
        // Generate new card set.
        CardList playerHand = new CardList();
        cardFactory = new CardFactory();
        // Distribute card.
        for (int i = 0; i < numberPlayers; i++){
            playerHand.clear();
            for (int j = 0; j < 7; j++){
                playerHand.add(cardFactory.createCard());
            }
            player[i].selectCards(playerHand);
        }
        // end loop
    }
}