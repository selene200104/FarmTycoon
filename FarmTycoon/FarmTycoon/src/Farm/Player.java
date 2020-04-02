package Farm;

import java.util.Random;

public class Player {

	public int speed = 5;
	public static int energy = 100;
	public static int money = 0;
	
	public static int amountPumpkinSeed = 2; //가지고 있는 호박씨의 수
	public static int amountOnionSeed = 3; //가지고 있는 양파씨의 수 
	public static int amountCabbageSeed = 3; //가지고 있는 양배추씨의 수
	public static int amountCarrotSeed = 3; //가지고 있는 당근씨의 수
	public static int amountPumpkin = 0; //가지고 있는 호박의 수
	public static int amountOnion = 0; //가지고 있는 양파의 수 
	public static int amountCabbage = 0; //가지고 있는 양배추의 수
	public static int amountCarrot = 0; //가지고 있는 당근의 수
	
	public static int amountBone = 2;//가지고 있는 뼈의 수
	
	static Random randomPower = new Random();
	static int hp = 100;
	static int attackPower; 	//플레이어의 파워
	static int amountRandomMushroom = 2; //가지고 있는 복불복 버섯의 수
	static int amountPotionHp_30 = 3; //가지고 있는 체력 30 업해주는 포션 수
	static int amountPotionHp_50 = 3; //가지고 있는 체력 50 업해주는 포션 수
}