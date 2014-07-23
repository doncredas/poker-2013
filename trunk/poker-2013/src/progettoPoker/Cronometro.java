package progettoPoker;

public class Cronometro extends Thread {

	private int secondi = 0;
	@Override
	public void run() {
		while(!isInterrupted()){
			try{
				sleep(1000);
				secondi++;
			}catch(InterruptedException e){
				break;
			}
		}
	}
	
	public int getSecondi(){
		return secondi;
	}
	
	public void reset(){
		secondi=0;
	}

}
