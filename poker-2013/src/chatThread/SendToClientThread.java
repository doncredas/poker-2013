package chatThread;

import java.net.Socket;
import java.io.*;

class SendToClientThread implements Runnable {
	PrintWriter[] pwPrintWriter=new PrintWriter[2];
	Socket[] clientSock = null;
	BufferedReader input=null;
	String msgToClientString = null;

	public SendToClientThread(Socket[] clientSock) {
		this.clientSock = clientSock;
		input=new BufferedReader(new InputStreamReader(System.in));
	}
	
	public void run(){
		try {
			for(int i=0;i<clientSock.length;i++)
				pwPrintWriter[i] = new PrintWriter(this.clientSock[i].getOutputStream());
			while (true) {
				msgToClientString = input.readLine();
				for(int i=0;i<clientSock.length;i++){
					pwPrintWriter[i].println(msgToClientString);
					pwPrintWriter[i].flush();
				}
				System.out.println("Inserisci una risposta per il client");
			}//while
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}//run
}//SendToClientThread

