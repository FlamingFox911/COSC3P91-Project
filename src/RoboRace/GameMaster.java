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
    
    public static void run(String[] args){
        System.out.print("Hello, World! I am the GameMaster.");
        System.out.println("Are you you the PlayerKeeper?");
    }
}		