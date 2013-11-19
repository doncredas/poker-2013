package chatMultiThread;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class JMrClient extends JPanel implements Runnable {

  private JTextField address;
  private JTextField port;
  private JButton connect;
  private JTextArea textArea;
  private JTextField textMessage;
  private Socket socket = null;

  Thread thread = null;

  public JMrClient() {
    super(new BorderLayout());
    createPanel();
  }

  protected void createPanel() {
    JPanel north = new JPanel();
    address = new JTextField(10);
    port = new JTextField(7);
    connect = new JButton("Connetti");
    textMessage = new JTextField("");
    textArea = new JTextArea("");
    textArea.setForeground(Color.WHITE);
    textArea.setBackground(Color.BLACK);
    north.add(new JLabel("Host:"));
    north.add(address);
    north.add(new JLabel("Porta:"));
    north.add(port);
    north.add(connect);
    super.add(north, BorderLayout.NORTH);
    super.add(new JScrollPane(textArea), BorderLayout.CENTER);
    super.add(textMessage, BorderLayout.SOUTH);
    connect.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (connect.getText().equals("Connetti")) {
          stateConnect();
        } else {
          stateDisconnect();
        }
      }
    });

    KeyAdapter adapter = new KeyAdapter() {
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_ENTER) {
            String str = textMessage.getText();
            if (!str.equals("")) send(str);
            textMessage.setText("");
        }
     }
    };
    textMessage.addKeyListener(adapter);
    stateDisconnect();
  }

  protected void stateConnect() {
    try {
      socket = new Socket(address.getText(), Integer.parseInt(port.getText()));
      thread = new Thread(this);
      thread.start();
    } catch(Exception ex) {}
    textArea.setEnabled(true);
    textMessage.setEnabled(true);
    address.setEnabled(false);
    connect.setText("Disconnetti");
  }

  protected void stateDisconnect() {
    try {
      if(socket!=null && socket.isConnected()) socket.close();
    }catch(Exception ex) {}
    if(thread!=null) thread.interrupt();
    textArea.setEnabled(false);
    textMessage.setEnabled(false);
    address.setEditable(true);
    connect.setText("Connetti");
  }

  protected void send(String msg) {
    try {
      msg+="\r\n";
      textArea.append(msg);
      OutputStream outStream = socket.getOutputStream();
      outStream.write(msg.getBytes());
      outStream.flush();
    }
    catch(IOException ex) {
      ex.printStackTrace();
    }
  }

  public void run() {
    try {
      while(true) {
        String msg = receive();
        textArea.append(msg);
      }
    } catch(Exception ex) {
      stateDisconnect();
    }
  }

  public String receive() throws IOException {
    String line = "";
    InputStream inStream = socket.getInputStream();
    int read = inStream.read();
    while (read!=10 && read > -1) {
      line+=String.valueOf((char)read);
      read = inStream.read();
    }
    if (read==-1) return null;
    line+=String.valueOf((char)read);
    return line;
  }

  public static void main(String [] argv) {
    JFrame frame = new JFrame("");
    frame.getContentPane().add(new JMrClient());
    frame.setSize(400,200);
    frame.setVisible(true);
  }
}