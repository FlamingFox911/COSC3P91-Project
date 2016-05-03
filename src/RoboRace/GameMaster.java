package RoboRace;

import java.util.*;
import java.io.*;

public class GameMaster {
    
    private int numberPlayers;
    
    int getNumPlayers(){
        return numberPlayers;
    }
    
    public GameMaster (int numberPlayers){
        this.numberPlayers = numberPlayers;
    }
    
    public void run(){
        System.out.print("Hello, World! I am the GameMaster.\n");
        System.out.print("Are you you the PlayerKeeper?");
    }
}		