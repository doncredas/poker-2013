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
			System.out.println("Recieved connection from "
					+ clientSocket[i].getInetAddress() + " on port "
					+ clientSocket[i].getPort());
			// create two threads to send and recieve from client
			RecieveFromClientThread recieve = new RecieveFromClientThread(clientSocket[i], clientSocket);
			thread = new Thread(recieve);
			//thread.start();
		}		
			SendToClientThread send = new SendToClientThread(clientSocket);
		    thread2= new Thread(send);
			thread2.start();
	}//costruttore
	
		//System.out.println("Server waiting for connection on port " + port);	
}
