package chatThread;

import java.io.*;
import java.net.*;
public class ClientT {
public static void main(String[] args)
{
try {
Socket sock = new Socket("localhost",444);
SendThread sendThread = new SendThread(sock);
Thread thread = new Thread(sendThread);thread.start();
RecieveThread recieveThread = new RecieveThread(sock);
Thread thread2 =new Thread(recieveThread);thread2.start();
} catch (Exception e) {System.out.println(e.getMessage());}
}
}

