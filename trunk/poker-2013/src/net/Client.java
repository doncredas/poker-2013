package net;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.Socket;

public class Client {
	public static void main(String[] doncredas){
		Socket socket;
		try{
			socket = new Socket(Inet4Address.getLocalHost(), 1993);
			socket.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		
/*		Socket permission;
		PrintWriter out;
		BufferedReader in;
		
		try{
			permission = new Socket(Inet4Address.getLocalHost(), 1993);
			System.out.println("Demande de connexion");
			in = new BufferedReader( new InputStreamReader(permission.getInputStream()));
			String message = in.readLine();
			System.out.println(message);
			
			permission.close();
		}catch (UnknownHostException e){
			e.printStackTrace();
		}catch (IOException e){
			e.printStackTrace();
		}
*/
	}

}
