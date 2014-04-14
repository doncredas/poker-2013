package grafica;
import javax.swing.JLabel;

/**
 * Implementa il timer nella finestra,e deve risettare un icona ad ogni
 * secondo che passa, per simulare i secondi che passano per un turno
 * 
 *  DA SISTEMARE TODO
 */

public class Orologio implements Runnable{
	private static JLabel time=new JLabel();
	private int i;

	
	public Orologio(){
		setIcona(60);
		time.setBounds(680,615,230,70);
	}//Orologio
	
	public void restart(){
		this.i=60;
		setIcona(this.i);
	}//restart
	
	public void setIcona(int numero){
		
		switch(numero){
		case 60:time.setIcon(Icone.T60);break;
		case 59:time.setIcon(Icone.T59);break;
		case 58:time.setIcon(Icone.T58);break;
		case 57:time.setIcon(Icone.T57);break;
		case 56:time.setIcon(Icone.T56);break;
		case 55:time.setIcon(Icone.T55);break;
		case 54:time.setIcon(Icone.T54);break;
		case 53:time.setIcon(Icone.T53);break;
		case 52:time.setIcon(Icone.T52);break;
		case 51:time.setIcon(Icone.T51);break;
		case 50:time.setIcon(Icone.T50);break;
		case 49:time.setIcon(Icone.T49);break;
		case 48:time.setIcon(Icone.T48);break;
		case 47:time.setIcon(Icone.T47);break;
		case 46:time.setIcon(Icone.T46);break;
		case 45:time.setIcon(Icone.T45);break;
		case 44:time.setIcon(Icone.T44);break;
		case 43:time.setIcon(Icone.T43);break;
		case 42:time.setIcon(Icone.T42);break;
		case 41:time.setIcon(Icone.T41);break;
		case 40:time.setIcon(Icone.T40);break;
		case 39:time.setIcon(Icone.T39);break;
		case 38:time.setIcon(Icone.T38);break;
		case 37:time.setIcon(Icone.T37);break;
		case 36:time.setIcon(Icone.T36);break;
		case 35:time.setIcon(Icone.T35);break;
		case 34:time.setIcon(Icone.T34);break;
		case 33:time.setIcon(Icone.T33);break;
		case 32:time.setIcon(Icone.T32);break;
		case 31:time.setIcon(Icone.T31);break;
		case 30:time.setIcon(Icone.T30);break;
		case 29:time.setIcon(Icone.T29);break;
		case 28:time.setIcon(Icone.T28);break;
		case 27:time.setIcon(Icone.T27);break;
		case 26:time.setIcon(Icone.T26);break;
		case 25:time.setIcon(Icone.T25);break;
		case 24:time.setIcon(Icone.T24);break;
		case 23:time.setIcon(Icone.T23);break;
		case 22:time.setIcon(Icone.T22);break;
		case 21:time.setIcon(Icone.T21);break;
		case 20:time.setIcon(Icone.T20);break;
		case 19:time.setIcon(Icone.T19);break;
		case 18:time.setIcon(Icone.T18);break;
		case 17:time.setIcon(Icone.T17);break;
		case 16:time.setIcon(Icone.T16);break;
		case 15:time.setIcon(Icone.T15);break;
		case 14:time.setIcon(Icone.T14);break;
		case 13:time.setIcon(Icone.T13);break;
		case 12:time.setIcon(Icone.T12);break;
		case 11:time.setIcon(Icone.T11);break;
		case 10:time.setIcon(Icone.T10);break;
		case 9:time.setIcon(Icone.T9);break;
		case 8:time.setIcon(Icone.T8);break;
		case 7:time.setIcon(Icone.T7);break;
		case 6:time.setIcon(Icone.T6);break;
		case 5:time.setIcon(Icone.T5);break;
		case 4:time.setIcon(Icone.T4);break;
		case 3:time.setIcon(Icone.T3);break;
		case 2:time.setIcon(Icone.T2);break;
		case 1:time.setIcon(Icone.T1);break;
		default:time.setIcon(Icone.T0);break;
		
		}//switch
	}//setIcona
	public static JLabel getLabel(){
		return time;
	}//getLabel

	@Override
	public void run() {  //TODO sostituire il for
		for(this.i=60;i>=0;i--)
		{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			setIcona(this.i);
			time.repaint();
		}//while
	}

}
