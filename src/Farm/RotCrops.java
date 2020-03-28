package Farm;

import java.util.Random;

import javax.swing.ImageIcon;

public class RotCrops extends Thread{

	//썩은 농장물을 만드는 쓰레드
	
	Random random = new Random();
	
	int fieldNum = 0;
	int minTime = 7000;
	int maxTime = 10000;
	int randomShortageTime = 0;//물이 부족하기 까지 남은 시간
	int randomRotTime = 0; //농작물이 썩기까지 남은 시간
	boolean running = true;
	
	RotCrops(int fieldNum){
		this.fieldNum = fieldNum;
	}
	
	@Override
	public synchronized void run() {
		while (running) {
			try {
				// 씨앗이 심겨진 밭이라면 -> 일 수가 지나면 자라게하기 ->집에 도착하면 판단하기 쓰레드에서 말고 
				//여기에선 농작물썩는 거 표현하기 랜덤시간 대로 
				
				if (Farming.statusOfField.get(fieldNum).equals("seeded field")) {
					randomShortageTime = random.nextInt(maxTime - minTime + 1) + minTime;
					Thread.sleep(randomShortageTime);
					
					//느낌표 ! 표시 놓기 땅의 상태도 바꾸기
					Farming.emergencyMarkingImages[fieldNum].setVisible(true);
					Farming.statusOfField.set(fieldNum, "need Water field");
					System.out.println(fieldNum + "번 땅이 물이 부족합니다");
					System.out.println(Farming.statusOfField.get(fieldNum));
					
					//오랫동안 물을 주지 않으면 썩기 
				}else if(Farming.statusOfField.get(fieldNum).equals("need Water field")) {
					randomRotTime = random.nextInt(maxTime - minTime + 1) + minTime;
					Thread.sleep(randomRotTime);
					
					Farming.fieldImages[fieldNum].setIcon(new ImageIcon("C:\\Users\\dayou\\OneDrive\\바탕 화면\\팀노바\\java_teamProject\\rottenFieldImage.png"));
					Farming.statusOfField.set(fieldNum, "rotten field");
					System.out.println(fieldNum + "번 땅이 썩었습니다");
					System.out.println(Farming.statusOfField.get(fieldNum));
				}
				
				Thread.sleep(1000);

			} catch (InterruptedException e) {
				running = false;
			}
		}
	}

}
