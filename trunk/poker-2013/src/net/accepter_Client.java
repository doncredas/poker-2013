package net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class accepter_Client implements Runnable {
	
	ServerSocket socketserver;
	Socket socket;
	int numClients = 1;
	
	accepter_Client(ServerSocket socketserver){
		this.socketserver = socketserver;
	}

	@Override
	public void run() {
		while(true){
			try{
				socket = socketserver.accept();
				System.out.println("Serveur " +numClients+ " s'est connecté.");
				numClients++;
				socket.close();
			}catch (IOException e){
				e.printStackTrace();
			}
		}
		
	}

}
