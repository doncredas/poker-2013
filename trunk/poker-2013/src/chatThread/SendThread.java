package chatThread;
import java.net.Socket;
import java.io.*;
class SendThread implements Runnable {
	Socket sock = null;
	PrintWriter print = null;
	BufferedReader brinput = null;
	String msgtoServerString = null;

	
	public SendThread(Socket sock) {
		this.sock = sock;
	}

	public void run() {
		try {
			if (sock.isConnected()) {
				System.out.println("Client connesso a "+ sock.getInetAddress() + " su porta " + sock.getPort());
				this.print = new PrintWriter(sock.getOutputStream(), true);
				while (true) {
					System.out.println("Manda il tuo messaggio al server. Digita 'EXIT' per terminare");
					brinput = new BufferedReader(new InputStreamReader(System.in));
					msgtoServerString = brinput.readLine();
					this.print.println(msgtoServerString);
					this.print.flush();
					if (msgtoServerString.equals("EXIT"))
						break;
				}//while
				sock.close();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}//run
}//SendThread