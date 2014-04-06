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
	}
	
	//public void start(){
		
	//}
	
	public void restart(){
		this.i=60;
		setIcona(this.i);
		run();
	}
	public void stop(){
		setIcona(this.i);
	}
	
	public void setIcona(int numero){
		switch(numero){
		case 60:time.setIcon(Icone.Tsessanta);break;
		case 59:time.setIcon(Icone.Tcinquantanove);break;
		case 58:time.setIcon(Icone.Tcinquantotto);break;
		case 57:time.setIcon(Icone.Tcinquantasette);break;
		case 56:time.setIcon(Icone.Tcinquantasei);break;
		case 55:time.setIcon(Icone.Tcinquantacinque);break;
		case 54:time.setIcon(Icone.Tcinquantaquattro);break;
		case 53:time.setIcon(Icone.Tcinquantatre);break;
		case 52:time.setIcon(Icone.Tcinquantadue);break;
		case 51:time.setIcon(Icone.Tcinquantuno);break;
		case 50:time.setIcon(Icone.Tcinquanta);break;
		case 49:time.setIcon(Icone.Tquarantanove);break;
		case 48:time.setIcon(Icone.Tquarantotto);break;
		case 47:time.setIcon(Icone.Tquarantasette);break;
		case 46:time.setIcon(Icone.Tquarantasei);break;
		case 45:time.setIcon(Icone.Tquarantacinque);break;
		case 44:time.setIcon(Icone.Tquarantaquattro);break;
		case 43:time.setIcon(Icone.Tquarantatre);break;
		case 42:time.setIcon(Icone.Tquarantadue);break;
		case 41:time.setIcon(Icone.Tquarantuno);break;
		case 40:time.setIcon(Icone.Tquaranta);break;
		case 39:time.setIcon(Icone.Ttrentanove);break;
		case 38:time.setIcon(Icone.Ttrentotto);break;
		case 37:time.setIcon(Icone.Ttrentasette);break;
		case 36:time.setIcon(Icone.Ttrentasei);break;
		case 35:time.setIcon(Icone.Ttrentacinque);break;
		case 34:time.setIcon(Icone.Ttrentaquattro);break;
		case 33:time.setIcon(Icone.Ttrentatre);break;
		case 32:time.setIcon(Icone.Ttrentadue);break;
		case 31:time.setIcon(Icone.Ttrentuno);break;
		case 30:time.setIcon(Icone.Ttrenta);break;
		
		}
	}
	public static JLabel getLabel(){
		return time;
	}

	@Override
	public void run() {
		while(this.i<0)
		{
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			setIcona(this.i);
			this.i--;
			time.repaint();
		}//while
	}

}
