package RoboRace;

import COSC3P40.midi.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;

public class Player extends JFrame {

    private String name;
    private Board board;
    private BoardCanvas boardCanvas;
    private CardPane cardPane;

    public Player(String name){
        this.name = name;
    }
    
    public void receiveBoard(Board in){
        this.board = in;
        this.boardCanvas = new BoardCanvas(board);
        this.cardPane = new CardPane();
        getContentPane().add("North",boardCanvas);
        getContentPane().add("South",cardPane);
        pack();
        setResizable(false);
        setVisible(true);
        boardCanvas.start();
    }
    
    public void receiveEvents(EventList list){
        //STUFF
    }
    
    CardList selectCards(CardList list){
        return cardPane.selectCards(list);
    }
    
    public void close(){
        // STUFF
    }
    /* some code that will be needed for display...
        boardCanvas = new BoardCanvas(board);
        cardPane = new CardPane();
        getContentPane().add("North",boardCanvas);
        getContentPane().add("South",cardPane);
        pack();
        setResizable(false);
        setVisible(true);
        boardCanvas.start();
    */
}