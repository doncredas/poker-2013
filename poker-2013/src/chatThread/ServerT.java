package chatThread;

import java.io.*;
import java.net.*;

public class ServerT {

	private ServerSocket ss;
	private Socket[] clientSocket;
	Thread thread;
	Thread thread2;
	
	public ServerT(int n, int port) throws IOException{
		
		ss = new ServerSocket(port);
		clientSocket = new Socket[n];
		for(int i=0;i<clientSocket.length;i++){
			clientSocket[i] = ss.accept();
			System.out.println("Ricevuta connessione da "
					+ clientSocket[i].getInetAddress() + " su porta "
					+ clientSocket[i].getPort());
			RecieveFromClientThread recieve = new RecieveFromClientThread(clientSocket[i], clientSocket);
			thread = new Thread(recieve);
			thread.start();
		}		
			SendToClientThread send = new SendToClientThread(clientSocket);
		    thread2= new Thread(send);
			thread2.start();
	}//costruttore
	
	public static void main(String[] args) throws IOException {
		new ServerT(1,9999);
	}//main
}//ServerT
