/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RoboRace;

import java.io.*;
import java.net.Socket;

/**
 *
 * @author as12ga
 */

public class NetworkPort implements Port{
    
    private Socket sock;
    private BufferedReader read;
    private PrintWriter write;
    
    public NetworkPort (Socket sock){
        this.sock = sock;
        try {
            this.read = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            this.write = new PrintWriter(new OutputStreamWriter(sock.getOutputStream()));
        }
        catch (IOException e){
        }
    }

    @Override
    public void send(String message) {
        write.println(message);
        write.println("\0");
        write.flush();
    }

    @Override
    public String recieve() {
        String result = "";
        while (true){
            try {
                String in = read.readLine();
                if (in.contains("\0")){
                    break;
                }
                result += in;
            }
            catch (IOException e){

            }
        }
        return result;
    }

    @Override
    public void close() throws IOException {
        write.close();
        read.close();
        sock.close();
    }
}
