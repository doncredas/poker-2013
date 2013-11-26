package chat;

import java.net.*;
import java.io.*;

public class Server implements Runnable {

	// setto le variabili ----------------->
	public ServerSocket ss = null;
	public Socket[] s = new Socket[2];
	public ObjectOutputStream[] OOS = new ObjectOutputStream[2];
	public ObjectInputStream[] OIS = new ObjectInputStream[2];
	public BufferedReader br = null;
	public Thread t = null;
	public String str = null;

	// ------------------------------------>

	public Server() throws IOException, ClassNotFoundException {
		// resto in ascolto sulla porta 9999
		ss = new ServerSocket(9999);
		System.out.println("In attesa di client...");
		for(int i=0;i<s.length;i++){
		s[i] = ss.accept();
		System.out.println("** Il client #"+i+" si è connesso **");
		System.out.println("IP: " + s[i].getInetAddress());
		System.out.println("Porta: " + s[i].getPort());
		}
		String str = "";
		// inizializzo gli stream che mi permetteranno di inviare e ricevere i
		// mess ->
		for(int i=0;i<OOS.length;i++){
		OOS[i] = new ObjectOutputStream(s[i].getOutputStream());
		OIS[i] = new ObjectInputStream(s[i].getInputStream());
		}
		// -------------------------------------------------------------------------->
		// questo stream rimane in ascolto della tastiera
		br = new BufferedReader(new InputStreamReader(System.in));

		t = new Thread(this);
		t.start();

		while (s[0].isConnected()) {
			// leggo quello che mi arriva dal client
			try {
				while ((str = (String) OIS[0].readObject()) != null) {
					System.out.println("Client: " + str);
				}
			} catch (SocketException e) {
				System.out.println("** Il client potrebbe essersi disconnesso! **");
				break;
			}
		}
	}

	private boolean isConnected(Socket[] s2) {
		for(int i=0;i<s2.length;i++)
			if(s2[i].isConnected())
		return true;
		return false;
	}
	
	public void run() {
		while (isConnected(s)) {
			try {
				// leggo tutto quello che viene premuto sulla tastiera
				while ((str = br.readLine()) != null) {
					//System.out.println("Io: " + str);
					// invio
					for(int i=0;i<OOS.length;i++){
					OOS[i].writeObject(str);
					OOS[i].flush();
					}
				}
			} catch (IOException e) {
				System.out.println("** Il client potrebbe essersi disconnesso! **");
			}
		}
	}

	public static void main(String[] Args) throws IOException,
			ClassNotFoundException {
		new Server();
	}
}