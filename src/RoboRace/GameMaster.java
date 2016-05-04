package RoboRace;

import java.util.*;
import java.io.*;

public class GameMaster {
    
    private int numberPlayers;
    private Robot[] robots;
    private Board board;
    private CardFactory cardFactory;
    private Port[] port;
        
    public GameMaster(String[] names, int numberPlayers, Port[] port){
        this.numberPlayers = numberPlayers;
        this.port = port;
        robots = new Robot[numberPlayers];
        for (int i = 0; i < numberPlayers; i++){
            robots[i] = new Robot(names[i], 2*i + 1);
        }
        Factory factory = Factory.load("factory.xml");
        // Create board
        board = new Board(factory, numberPlayers, robots);
        cardFactory = new CardFactory();
    }
    
    public void run() {
        for (int i = 0; i < numberPlayers; i++){
            port[i].send(board.toXMLString());
        }
        CardList[] playerHand  = new CardList[numberPlayers];
        for (int i = 0; i < numberPlayers; i++){
            playerHand[i] = new CardList();
        }
        CardList compareCards = new CardList();
        EventCounter counter = new EventCounter();
        EventList events = new EventList();
        
        while (!events.containsVictory()) {
            board.revitalize();
            for (int i = 0; i < numberPlayers; i++) {
                playerHand[i].clear();
                for (int j = 0; j < 7; j++){
                    playerHand[i].add(cardFactory.createCard());
                }
            }
            for (int i = 0; i < numberPlayers; i++) {
                port[i].send(playerHand[i].toXMLString());
            }
            for (int i = 0; i < numberPlayers; i++) {
                playerHand[i] = CardList.read(new StringReader(port[i].recieve()));
            }
            counter.reset();
            events.clear();
            for (int i = 0; i < 5; i++){
                if (events.containsVictory()) break;
                compareCards.clear();
                for (int j = 0; j < numberPlayers; j++){
                    compareCards.add(playerHand[j].get(i));
                }
                Collections.sort(compareCards);
                for (Card card : compareCards) {
                    int player = -1;
                    for (int j = 0; j < numberPlayers; j++) {
                        if (playerHand[j].get(i) == card) {
                            player = j;
                        }
                    }
                    Robot robot = robots[player];
                    if (robot.isAlive()) {
                        card.execute(counter,events,robot,board);
                    }
                }
                for (Robot robot : robots) {
                    if (robot.isAlive()) {
                        Location location = board.getLocation(robot.getLocation());
                        location.effect(counter,events,i,robot,board);
                    }
                }
            }
            for (int i = 0; i < numberPlayers; i++){
                port[i].send(events.toXMLString());
            }
        }
        GameDialogs.showMessageDialog("End of Game","The winner is " + events.getWinner() + "!!!");	
        //for(Player p : players) p.close();
    }
}
		