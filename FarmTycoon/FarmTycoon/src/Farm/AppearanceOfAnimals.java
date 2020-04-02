package Farm;

import java.util.Random;

import javax.swing.JPanel;

public class AppearanceOfAnimals extends Thread{

	Random random = new Random();
	static Random randomWildAnimalSelect = new Random();
	
	int minTime = 1000;
	int maxTime = 3000;
	static int randomWildAnimal;
	static boolean running = true;
	
	public static void randomMushroomSelect() {
		randomWildAnimal = randomWildAnimalSelect.nextInt(3);
	}
	@Override
	public synchronized void run() {
		while (running) {
			try {
				int randomTime = random.nextInt(maxTime - minTime + 1) + minTime; //랜덤시간 설정
				Thread.sleep(randomTime); //랜덤시간이 흐른 후 
				//System.out.println(running);
				//System.out.println(randomTime);
				
				// 만약 플레이어 위치가 상점이라면
				if (Farming.playerImage.getY() >= 328 && Farming.playerImage.getY() <= 473
						&& Farming.playerImage.getX() >= 0 && Farming.playerImage.getX() <= 120) {
						//System.out.println("현재 위치는 상점입니다");

					// 만약 플레이어의 위치가 집이라면
				} else if (Farming.playerImage.getY() >= 330 && Farming.playerImage.getY() <= 475
						&& Farming.playerImage.getX() >= 600 && Farming.playerImage.getX() <= 720) {
						//System.out.println("현재 위치는 집입니다");
						
					// 만약 플레이어의 위치가 밭이라면
				} else if (Farming.playerImage.getY() >= -20 && Farming.playerImage.getY() <= 235
						&& Farming.playerImage.getX() >= 65 && Farming.playerImage.getX() <= 620) {
						//System.out.println("현재 위치는 밭입니다");

					// 상점, 집, 밭이 아닌 그 외의 위치일 때 몬스터가 나타난다
				} else {
					//System.out.println("현재 위치는 던전갈 수 있는 곳 입니다");
					//randomMushroomSelect();
					//System.out.println(randomWildAnimal);
					//System.out.println(running);
					randomMushroomSelect();
					
					if(randomWildAnimal == 0) {
						
						JPanel dungeonWildWolf = new DungeonWildWolf();
						dungeonWildWolf.setVisible(true);
						Farming.dungeonScene.add(dungeonWildWolf);
						Farming.dungeonScene.setVisible(true);
						Farming.farmingScene.setVisible(false);
						Player.hp = 100;
						
					}else if(randomWildAnimal == 1) {
						running = false;
						JPanel dungeonWildBear = new DungeonWildBear();
						dungeonWildBear.setVisible(true);
						Farming.dungeonScene.add(dungeonWildBear);
						Farming.dungeonScene.setVisible(true);
						Farming.farmingScene.setVisible(false);
						Player.hp = 100;
						
					}else if(randomWildAnimal == 2) {
						running = false;
						JPanel dungeonWildSnake = new DungeonWildSnake();
						dungeonWildSnake.setVisible(true);
						Farming.dungeonScene.add(dungeonWildSnake);
						Farming.dungeonScene.setVisible(true);
						Farming.farmingScene.setVisible(false);
						Player.hp = 100;

					}
					running = false;
				}

			} catch (InterruptedException e) {
				running = false;
			}
		}
	}
}