package Farm;

import java.util.Random;

import javax.swing.ImageIcon;

public class SparrowObstruction extends Thread{
	//참새가 방해하는 클래스 - 땅이 넓을수록 (많이 씨앗을 심어놓을 수록 참새가 나올 확률이 높아진다)
	
	Random random = new Random();
	
	int randomField = 0;
	boolean running = true;
	
	@Override
	public synchronized void run() {
		while (running) {
			try {
				
				Thread.sleep(10000);
				
				randomField = random.nextInt(18);
					
				// 랜덤으로 선택한 밭이 빈 밭이 아닐때 참새가 나타난다
				if (!Farming.statusOfField.get(randomField).equals("empty Field")) {
					Farming.sparrowImage.setLocation(Farming.fieldImages[randomField].getX(),Farming.fieldImages[randomField].getY());
					Farming.statusOfField.set(randomField, "empty Field");
					Farming.fieldImages[randomField].setIcon(new ImageIcon("C:\\Users\\dayou\\OneDrive\\바탕 화면\\팀노바\\java_teamProject\\basicsFieldImage.png"));
					
					//참새가 나타난 1초 후 참새는 다시 날라간다
					Thread.sleep(1000);
					Farming.sparrowImage.setLocation(-100,-100);
				}
				
			} catch (InterruptedException e) {
				running = false;
			}
		}
	}
}
