package chatThread;
import java.net.Socket;
import java.io.*;
class RecieveThread implements Runnable {
	Socket sock = null;
	BufferedReader recieve = null;

	public RecieveThread(Socket sock) {
		this.sock = sock;
	}// end constructor

	public void run() {
		try {
			recieve = new BufferedReader(new InputStreamReader(this.sock.getInputStream()));// get inputstream
			String msgRecieved = null;
			msgRecieved = grafica.GraficaPoker.ConsChat.getText();

			//grafica.GraficaPoker.setConsChat(" ");
			
			//while ((msgRecieved = recieve.readLine()) != null) {
				System.out.println("From Server: " + msgRecieved);
				//manda il messaggio nella chat grafica
				grafica.GraficaPoker.scriviChat(msgRecieved);
				//System.out.println("Please enter something to send to server..");
			//}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}// end run
}// end class recievethread