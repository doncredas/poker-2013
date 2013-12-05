package progettoPoker;

public class Cronometro extends Thread {

	int secondi = 0;
	@Override
	public void run() {
		while(!isInterrupted()){
			try{
				sleep(1000);
				System.out.println(secondi);
				secondi++;
			}catch(InterruptedException e){
				System.out.println("errore!");
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
