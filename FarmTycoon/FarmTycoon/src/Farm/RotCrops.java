package Farm;

import java.util.Random;

import javax.swing.ImageIcon;

public class RotCrops extends Thread{

	//썩은 농장물을 만드는 쓰레드
	
	Random random = new Random();
	
	int numOfField = 0;
	int minTime = 10000;
	int maxTime = 15000;
	int randomShortageTime = 0;//물이 부족하기 까지 남은 시간
	int randomRotTime = 0; //농작물이 썩기까지 남은 시간
	boolean running = true;
	
	RotCrops(int numOfField){
		this.numOfField = numOfField;
	}
	
	@Override
	public synchronized void run() {
		while (running) {
			try {
				// 씨앗이 심겨졌거나 하루가 지났을 때  -> 물을 주지 않았다면 -> 랜덤시간 후 밭의 물양이 부족 -> 그후 또 물을 주지 않았다면 랜덤시간후 농장물 썩음

				if (Farming.statusOfField.get(numOfField).equals("seeded field")) {
					randomShortageTime = random.nextInt(maxTime - minTime + 1) + minTime;
					
					Thread.sleep(randomShortageTime);
					
					if(Farming.statusOfField.get(numOfField).equals("seeded field")) {
						
						Farming.emergencyMarkingImages[numOfField].setVisible(true);
						Farming.statusOfField.set(numOfField, "need Water field");
						Farming.amountOfWater[numOfField].setText("물의 양 : 매우부족");
					}

				}else if(Farming.statusOfField.get(numOfField).equals("need Water field")) {
					randomRotTime = random.nextInt(maxTime - minTime + 1) + minTime;
					Thread.sleep(randomRotTime);
					
					if(Farming.statusOfField.get(numOfField).equals("need Water field")) {
						Farming.emergencyMarkingImages[numOfField].setVisible(false);
						Farming.fieldImages[numOfField].setIcon(new ImageIcon("./images/rottenFieldImage.png"));
						Farming.statusOfField.set(numOfField, "rotten field");
					}
					
				}
				
				Thread.sleep(5000);

			} catch (InterruptedException e) {
				running = false;
			}
		}
	}

}