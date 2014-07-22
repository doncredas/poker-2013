package chatThread;
import java.net.Socket;
import java.io.*;
class RecieveThread implements Runnable {
	Socket sock = null;
	BufferedReader recieve = null;
	String msgRecieved = null;


	public RecieveThread(Socket sock) {
		this.sock = sock;
	}

	public void run() {
		try {
			recieve = new BufferedReader(new InputStreamReader(this.sock.getInputStream()));
			
			while ((msgRecieved = recieve.readLine()) != null) {
				System.out.println("Dal Server: " + msgRecieved);
				System.out.println("Inserisci un messaggio per il server");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}//run
}//RecieveThread
