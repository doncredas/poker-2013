package chat;

import java.net.*;
import java.io.*;

public class Server implements Runnable {

	// setto le variabili ----------------->
	public ServerSocket ss = null;
	public Socket s = null;
	public ObjectOutputStream OOS = null;
	public ObjectInputStream OIS = null;
	public BufferedReader br = null;
	public Thread t = null;
	public String str = null;

	// ------------------------------------>

	public Server() throws IOException, ClassNotFoundException {
		// resto in ascolto sulla porta 9999
		ss = new ServerSocket(9999);
		System.out.println("In attesa di client...");
		s = ss.accept();
		System.out.println("** Un client si è connesso **");
		System.out.println("IP: " + s.getInetAddress());
		System.out.println("Porta: " + s.getPort());
		String str = "";
		// inizializzo gli stream che mi permetteranno di inviare e ricevere i
		// mess ->
		OOS = new ObjectOutputStream(s.getOutputStream());
		OIS = new ObjectInputStream(s.getInputStream());
		// -------------------------------------------------------------------------->
		// questo stream rimane in ascolto della tastiera
		br = new BufferedReader(new InputStreamReader(System.in));

		t = new Thread(this);
		t.start();

		while (s.isConnected()) {
			// leggo quello che mi arriva dal client
			try {
				while ((str = (String) OIS.readObject()) != null) {
					System.out.println("Client: " + str);
				}
			} catch (SocketException e) {
				System.out.println("** Il client potrebbe essersi disconnesso! **");
				break;
			}
		}
	}

	public void run() {
		while (s.isConnected()) {
			try {
				// leggo tutto quello che viene premuto sulla tastiera
				while ((str = br.readLine()) != null) {
					System.out.println("Io: " + str);
					// invio
					OOS.writeObject(str);
					OOS.flush();
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