package Farm;

import java.util.Random;

public class AppearanceOfAnimals extends Thread{

	Random random = new Random();
	
	int minTime = 10000;
	int maxTime = 15000;
	int randomTime = 0; //동물이 출현하기까지 남은 시간 
	boolean running = true;
	
	@Override
	public synchronized void run() {
		while (running) {
			try {

				randomTime = random.nextInt(maxTime - minTime + 1) + minTime; //랜덤시간 설정
				Thread.sleep(randomTime); //랜덤시간이 흐른 후 

				// 만약 플레이어 위치가 상점이라면
				if (Farming.playerImage.getY() >= 328 && Farming.playerImage.getY() <= 473) {
					if (Farming.playerImage.getX() >= 0 && Farming.playerImage.getX() <= 120) {
						//System.out.println("현재 위치는 상점입니다");

					}
					// 만약 플레이어의 위치가 집이라면
				} else if (Farming.playerImage.getY() >= 330 && Farming.playerImage.getY() <= 475) {
					if (Farming.playerImage.getX() >= 600 && Farming.playerImage.getX() <= 720) {
						//System.out.println("현재 위치는 집입니다");

					}
					// 만약 플레이어의 위치가 밭이라면
				} else if (Farming.playerImage.getY() >= -20 && Farming.playerImage.getY() <= 235) {
					if (Farming.playerImage.getX() >= 65 && Farming.playerImage.getX() <= 620) {
						//System.out.println("현재 위치는 밭입니다");

					}
					// 상점, 집, 밭이 아닌 그 외의 위치일 때 몬스터가 나타난다
				} else {
					// 몬스터가 나타난다.
					System.out.println("!!! 몬스터와 만났다 !!!");
				}

			} catch (InterruptedException e) {
				running = false;
			}
		}
	}
}
