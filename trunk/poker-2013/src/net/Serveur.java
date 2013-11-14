package net;

import java.io.IOException;
import java.net.ServerSocket;


public class Serveur {

	public static void main(String[] doncredas){
		ServerSocket socketserver;
		
		try{
			socketserver = new ServerSocket(1993);
			Thread t = new Thread(new accepter_Client(socketserver));
			t.start();
//			socketserver.close();
		}catch (IOException e){
			e.printStackTrace();
		}
//		Socket permission;
//		BufferedReader in;
//		PrintWriter out;
		
/*		try {
			socketserver = new ServerSocket(1993);
			System.out.println("Le serveur est à l'écoute du port: " +socketserver.getLocalPort());
			permission = socketserver.accept();
			System.out.println("Doncredas s'est connecté");
			
			out = new PrintWriter(permission.getOutputStream());
			out.println("Vous ètes connecté Doncredas");
			out.flush();
			
			socketserver.close();
			permission.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
*/
	}
}
