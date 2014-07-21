package grafica;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

import javax.swing.JLabel;

/**
 * Implementa il timer nella finestra,e deve risettare un icona ad ogni
 * secondo che passa, per simulare i secondi che passano per un turno
 * 
 */

public class Orologio implements Runnable{
	private int i,j;
	boolean flag=true;
	private static JLabel time=new JLabel("60");
	Font orolox=null;
	
	public Orologio(int i){
		try {
			orolox=Font.createFont(Font.TRUETYPE_FONT,Icone.orol);
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.i=i;
		this.j=i;
		time.setText(Integer.toString(i));
		time.setBounds(675,557, 500, 200);
		time.setVisible(false);
		time.setForeground(Color.white);
		time.setFont(orolox.deriveFont(Font.PLAIN,14));
	}//Orologio
	
	/**
	 * resetta il timer
	 */
	public void restart(){
		this.i=j;
		flag=true;
		time.setText(Integer.toString(i));
		time.setVisible(true);
	}//restart
	
	/**
	 * ritorna il numero attuale del timer
	 * @return
	 */
	public int getVal(){
		return i;
	}
	
	/**
	 * Ritorna la label
	 * @return
	 */
	public static JLabel getLabel(){
		return time;
	}//getLabel

	@Override
	public void run() {  
		for(this.i=j;i>=0;i--)
		{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// Auto-generated catch block
				e.printStackTrace();
			}
			if(flag){
				if(this.i<=20)time.setForeground(Color.red);
				time.setText(Integer.toString(this.i));
				time.repaint();
			}
		}//while
	}//run
	public void stop(){
		flag=false;
	}


}//Orologio
