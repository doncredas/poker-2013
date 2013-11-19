package chatThread;

import java.net.Socket;
import java.io.*;

class SendToClientThread implements Runnable {
	PrintWriter pwPrintWriter;
	Socket clientSock = null;

	public SendToClientThread(Socket clientSock) {
		this.clientSock = clientSock;
	}

	public void run() {
		try {
			pwPrintWriter = new PrintWriter(new OutputStreamWriter(
					this.clientSock.getOutputStream()));// get outputstream
			while (true) {
				String msgToClientString = null;
				BufferedReader input = new BufferedReader(
						new InputStreamReader(System.in));// get userinput
				msgToClientString = input.readLine();// get message to send to
														// client
				pwPrintWriter.println(msgToClientString);// send message to
															// client with
															// PrintWriter
				pwPrintWriter.flush();// flush the PrintWriter
				System.out
						.println("Please enter something to send back to client..");
			}// end while
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}// end run
}// end class SendToClientThread

