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
    
    public Player(String name, Board board, BoardCanvas boardCanvas, CardPane cardPane){
        this.name = name;
        this.board = board;
        this.boardCanvas = boardCanvas;
        this.cardPane = cardPane;
    }
    
    Board recieveBoard(Board in){
        return in;
    }
    
    CardList selectCards(CardList list){
        return cardPane.selectCards(list);
    }
    /* some code that will be needed for display

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