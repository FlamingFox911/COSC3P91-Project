package RoboRace;

import java.util.*;
import java.io.*;

public class GameMaster {
    
    private int numberPlayers;
    private Board board;
    private Factory factory;
    private Player[] player;
    private Robot[] robot;
    private CardFactory cardFactory;
    private EventList eventList;
    private EventCounter eventCounter;
    
    public GameMaster (int numberPlayers, String[] name){
        this.numberPlayers = numberPlayers;
        this.player = new Player[numberPlayers];
        this.robot = new Robot[numberPlayers];
        this.eventList = new EventList();
        this.eventCounter = new EventCounter();
        for (int i = 0; i < numberPlayers; i++){
            robot[i] = new Robot(name[i], 2*i + 1);
        }
        this.factory = Factory.load("factory.xml");
        this.board = new Board(this.factory, numberPlayers, this.robot);
        for (int i = 0; i < numberPlayers; i++){
            this.player[i] = new Player(name[i]);
        }
    }
    
    private class SortCard implements Comparable<SortCard>{
	private Card card;
	private int player;
	
	public SortCard(Card card, int player) {
		super();
		this.card = card;
		this.player = player;
	}
	
        @Override
	public int compareTo(SortCard cCard) {
            int cPriority = ((SortCard) cCard).card.getPriority(); 
            return this.card.getPriority() - cPriority;	
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
        cardFactory = new CardFactory();
        // loop
        while (!eventList.containsVictory()){
            // Revitalize robots
            board.revitalize();
            // Generate new card set and distribute
            for (int i = 0; i < numberPlayers; i++){
                playerHand[i] = new CardList();
                resultHand[i] = new CardList();
                for (int j = 0; j < 7; j++){
                    playerHand[i].add(cardFactory.createCard());
                }
                // Receive chosen card list
                resultHand[i] = player[i].selectCards(playerHand[i]);
            }
            // Sort Cards, execute and effect robots per action.
            SortCard sc[] = new SortCard[numberPlayers];
            for (int i = 0; i < 5; i++){
                for (int j = 0; j < numberPlayers; j++){
                    sc[j] = new SortCard(resultHand[j].pop(), j);
                }
                Arrays.sort(sc);
                for (int j = 0; j < numberPlayers; j++){
                    int p = sc[j].player;
                    if (robot[p].isAlive()){
                        Card c = sc[j].card;
                        c.execute(eventCounter, eventList, robot[p], board);
                        board.getLocation(robot[p].getLocation()).effect(eventCounter, eventList, i, robot[p], board);
                    }
                }
                if (eventList.containsVictory()) break;
            }

        }
        GameDialogs.showMessageDialog("End of Game", "The winner is " + eventList.getWinner() + "!!!");
        for (int i = 0; i < numberPlayers; i++){
            player[i].close();
        }
        // end loop
    }
}