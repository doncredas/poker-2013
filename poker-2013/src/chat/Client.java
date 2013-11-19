package chat;
import java.net.*;
import java.io.*;
 
public class Client implements Runnable {
    // setto le variabili ----------------->
    public Socket s = null;
    public ObjectOutputStream OOS = null;
    public ObjectInputStream OIS = null;
    public BufferedReader br = null;
    public Thread t = null;
    public String str = "";
    public String localhost="127.0.0.1";
    // ------------------------------------>
 
    public Client() throws IOException, ClassNotFoundException {
    // mi connetto all'ip localhost alla porta 9999
    try{
    	s = new Socket(localhost, 9999);
    }catch(ConnectException e){
    	System.out.println("impossibile connettresi al server "+localhost);
    	System.exit(-1);
    }
    System.out.println("** Sono connesso con il server **");
    System.out.println("IP: " + s.getInetAddress());
    System.out.println("Porta: " + s.getPort());
 
    // inizializzo gli stream che mi permetteranno di inviare e ricevere i mess ->
    OOS = new ObjectOutputStream(s.getOutputStream());
    OIS = new ObjectInputStream(s.getInputStream());
    // -------------------------------------------------------------------------->
    // questo stream rimane in ascolto della tastiera
    br = new BufferedReader(new InputStreamReader(System.in));
 
    t = new Thread(this);
    t.start();
 
    while (s.isConnected()) {
            // leggo quello che mi arriva dal server
        try{
    	while ((str = (String) OIS.readObject()) != null) {
        System.out.println("Server: " + str);
            }
        }catch(SocketException e){
        	System.out.println("** Il server potrebbe essersi disconnesso! **");break;
        }
        }
    }
    public void run() {
    while (s.isConnected()) {
        try {
                // leggo tutto quello che viene premuto sulla tastiera
        while ((str = br.readLine()) != null) {
            System.out.println("Io: " + str);
                    // invio
            OOS.writeObject(str);
            OOS.flush();
        }
        }
        catch(IOException e) {
        System.out.println("** Il server potrebbe essersi disconnesso! **");
        }
    }
    }
    public static void main(String[]Args) throws IOException, ClassNotFoundException {
    new Client();
    }
}