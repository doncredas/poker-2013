  package chatThread;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

class RecieveFromClientThread implements Runnable {
	Socket clientSocket = null;
	BufferedReader brBufferedReader = null;
	String messageString=null;
	PrintWriter[] pwPrintWriter=new PrintWriter[2];
    Socket [] clientSocket1 = null;


	public RecieveFromClientThread(Socket clientSocket, Socket [] clientSocket1) {
		this.clientSocket = clientSocket;
		this.clientSocket1 = clientSocket1;

	}

	public void run() {
		try {
			brBufferedReader = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
			while (true) {
				while ((messageString = brBufferedReader.readLine()) != null) {//assegna il messaggio dal client a messageString
					if (messageString.equals("EXIT")) {
						break;// per chiudere il socket se si digita EXIT
					}
					System.out.println("Da Client: " + messageString);// stampa il messaggio del client
					
					//Manda il messaggio a tutti i client
					
					for(int i=0;i<clientSocket1.length;i++)
					pwPrintWriter[i] = new PrintWriter(this.clientSocket1[i].getOutputStream());
					
					for(int j=0;j<clientSocket1.length;j++){
						// client
						if(clientSocket.getInetAddress()!=clientSocket1[j].getInetAddress()){
							pwPrintWriter[j].println(messageString);
							pwPrintWriter[j].flush();
						}
					}
					
				}
				System.out.println("Inserisci qualcosa da inviare al client");
				this.clientSocket.close();
				System.exit(0);
			}
		} catch (Exception ex) {System.out.println(ex.getMessage());}
	}//run
	
}//RecieveFromClientThread
