package chatThread;

import java.net.Socket;
import java.io.*;

class SendToClientThread implements Runnable {
	PrintWriter[] pwPrintWriter=new PrintWriter[2];
	Socket[] clientSock = null;
	BufferedReader input=null;

	public SendToClientThread(Socket[] clientSock) {
		this.clientSock = clientSock;
		input=new BufferedReader(new InputStreamReader(System.in));
	}
	
	public void run() {
		try {
			for(int i=0;i<clientSock.length;i++)
				pwPrintWriter[i] = new PrintWriter(this.clientSock[i].getOutputStream());// get outputstream
			while (true) {
				String msgToClientString = null;
				// get userinput
				msgToClientString = input.readLine();// get message to send to
				for(int i=0;i<clientSock.length;i++){// client
				pwPrintWriter[i].println(msgToClientString);// send message to client with PrintWriter
				pwPrintWriter[i].flush();// flush the PrintWriter
				}
				System.out
						.println("Please enter something to send back to client..");
			}// end while
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}// end run
}// end class SendToClientThread

