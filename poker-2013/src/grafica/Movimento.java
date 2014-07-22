package grafica;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class Movimento {

	public static void muovi(JComponent jc, int x, int y) {
		int inX = jc.getX();
		int inY = jc.getY();
		int spost = (Math.abs(x - inX) + Math.abs(y - inY)) / 3;
		if (spost != 0) {
			int spostX = (x - inX) / spost;
			int spostY = (y - inY) / spost;
			int restoX = 0;
			int restoY = 0;
			int nX, nY;
			for (int i = 0; i < spost; i++) {
				restoX = (x - inX + restoX) % spost;
				restoY = (y - inY + restoY) % spost;
				nX = jc.getX() + (spostX);
				nY = jc.getY() + (spostY);
				spostX = (x - inX + restoX) / spost;
				spostY = (y - inY + restoY) / spost;
				jc.setLocation(nX, nY);
				jc.repaint();
				try {
					Thread.sleep(1+1/2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
			jc.setLocation(x, y);
		}
	}
}
