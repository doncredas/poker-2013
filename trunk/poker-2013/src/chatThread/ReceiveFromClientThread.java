package chatThread;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import progettoPoker.Giocatore;
class RecieveFromClientThread implements Runnable {
	Socket clientSocket = null;
	BufferedReader brBufferedReader = null;
	String messageString=null;
	PrintWriter[] pwPrintWriter=new PrintWriter[2];
    Socket [] clientSocket1 = null;

	public RecieveFromClientThread(Socket clientSocket, Socket [] clientSocket1) {
		this.clientSocket = clientSocket;
		this.clientSocket1 = clientSocket1;

	}// end constructor

	public void run() {
		try {
			
			String messageString;
			brBufferedReader = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
			while (true) {
				messageString = grafica.GraficaPoker.ConsChat.getText();
				//grafica.GraficaPoker.setConsChat(" ");

				//while ((messageString = brBufferedReader.readLine()) != null) {// assign message from client to messageString
				//	if (messageString.equals("EXIT")) {
				//		break;// break to close socket if EXIT
				//	}
					System.out.println("From Client: " + messageString);// print the message from client
					//manda il messaggio nella chat grafica
					grafica.GraficaPoker.scriviChat(messageString);
					//Manda il messaggio a tutti quanti
					for(int i=0;i<clientSocket1.length;i++)
					pwPrintWriter[i] = new PrintWriter(this.clientSocket1[i].getOutputStream());
					for(int j=0;j<clientSocket1.length;j++){
						// client
						if(clientSocket.getInetAddress()!=clientSocket1[j].getInetAddress()){
							pwPrintWriter[j].println(messageString);// send message to client with PrintWriter
							pwPrintWriter[j].flush();// flush the PrintWriter
						}
					}
					
				//}
				//System.out.println("Please enter something to send back to client..");
				this.clientSocket.close();
				System.exit(0);
			}
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
			
				}
}// end class RecieveFromClientThread
