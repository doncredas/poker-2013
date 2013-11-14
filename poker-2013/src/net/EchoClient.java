package net;

import java.io.*;
import java.net.*;

public class EchoClient {
      public static void main(String[] args) throws IOException {

        Socket echoSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;
      String nomeSocket="192.168.43.59";
        try {
            echoSocket = new Socket(nomeSocket, 7777);
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: "+nomeSocket);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: "+args[0]);
            System.exit(1);
        }

        System.out.println("Server welcome is: "+in.readLine());

        out.println("Hello how are you");
        System.out.println("Server responded: "+in.readLine());

        out.println("Some other stuff");
        System.out.println("Server now responded: "+in.readLine());

        out.close();
        in.close();
        echoSocket.close();
    }
}