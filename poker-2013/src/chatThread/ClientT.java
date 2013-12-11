package chatThread;

import java.net.*;
public class ClientT {
	private Socket sock;
	private SendThread sendThread;
	private RecieveThread recieveThread;
	private Thread thread,thread2;
	
	public ClientT(InetAddress ip,int port){
		try {
			sock = new Socket(ip,port);
			sendThread = new SendThread(sock);
			thread = new Thread(sendThread);
			thread.start();
			recieveThread = new RecieveThread(sock);
			thread2 =new Thread(recieveThread);
			thread2.start();
		} catch (Exception e) {System.out.println(e.getMessage());}
	}
}

