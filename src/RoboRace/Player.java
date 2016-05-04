package RoboRace;

import COSC3P40.midi.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;

public class Player extends JFrame implements Runnable {
	
    private String name;
    private Board board;
    private BoardCanvas boardCanvas;
    private CardPane cardPane;
    private Port port;
    private EventList events;

    public Player (String name, Port port){
        this.name = name;
        this.port = port;
        (new Thread(this)).start();
    }

    public void run(){
        // Receive the board and display board
        board = Board.read(new StringReader(port.recieve()));
        boardCanvas = new BoardCanvas(board);
        cardPane = new CardPane();
        getContentPane().add("North",boardCanvas);
        getContentPane().add("South",cardPane);
        pack();
        setResizable(false);
        setVisible(true);
        boardCanvas.start();
        do {
            board.revitalize();
            CardList hand = CardList.read(new StringReader(port.recieve()));
            port.send(cardPane.selectCards(hand).toXMLString());
            events = EventList.read(new StringReader(port.recieve()));
            events.execute(board);
        } while (!events.containsVictory());
    }
}