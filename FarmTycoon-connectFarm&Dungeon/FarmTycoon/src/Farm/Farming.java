package Farm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JPanel;

public class Farming {

	private JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Farming window = new Farming();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Farming() {
		initialize();
	}

	Player player = new Player();
	SparrowObstruction sparrowObstruction = new SparrowObstruction(); //참새 방해 쓰레드 
	AppearanceOfAnimals appearanceOfAnimals = new AppearanceOfAnimals(); //동물 출현 쓰레드
	
	static JPanel farmingScene = new JPanel() {
		//public void paintComponent(Graphics g) {
		//	Dimension d = getSize();
		//	ImageIcon image = new ImageIcon("./images/backGround.jpg");
		//	g.drawImage(image.getImage(), 0, 0, d.width, d.height, this);
		//}
	};
	
	static JPanel dungeonScene = new JPanel() ;
	
	JPanel gameSuccess = new JPanel() {
		public void paintComponent(Graphics g) {
			Dimension d = getSize();
			ImageIcon image = new ImageIcon("./images/finalBackground.jpg");
			g.drawImage(image.getImage(), 0, 0, d.width, d.height, this);
		}
	};
	
	JPanel gameFail = new JPanel() {
		public void paintComponent(Graphics g) {
			Dimension d = getSize();
			ImageIcon image = new ImageIcon("./images/finalBackground.jpg");
			g.drawImage(image.getImage(), 0, 0, d.width, d.height, this);
		}
	};
	
	JPanel seedPlantingWindow = new JPanel();
	JPanel plantStateWindow = new JPanel();

	
	
	static JLabel[] fieldImages = new JLabel[18];
	static JLabel[] emergencyMarkingImages = new JLabel[18]; //물이 부족하다는 긴급표시 image
	static JLabel playerImage = new JLabel();
	static JLabel houseImage = new JLabel();
	JLabel storeImage = new JLabel();
	static JLabel EnergyText = new JLabel();
	static JLabel daysText = new JLabel();
	static JLabel moneyText = new JLabel();
	JLabel successText = new JLabel();
	JLabel failText = new JLabel();
	JLabel successmoneyEarned = new JLabel(); //이겼을 때 남은 돈이 얼마인지 나타내주는 text
	JLabel failmoneyEarned = new JLabel(); //졌을 때 남은 돈이 얼마인지 나타내주는 text
	
	//식물 상태 창
	static JLabel[] amountOfWater = new JLabel[18]; //밭의 물의 상태가 어떠한지 text
	JLabel[] plantsImage = new JLabel[18];
	JLabel[] plantsNametext = new JLabel[18];
	JLabel[] timeLeftText = new JLabel[18];
	
	//인벤토리
	JLabel inventoryWindow = new JLabel();
	JLabel[] inventoryCompartment = new JLabel[16]; //인벤토리 칸
	JLabel[] numberOfItemsText = new JLabel[16]; //물품이 몇개있지 text
	JLabel[] inventoryDescriptionText = new JLabel[4]; //인벤토리 설명 (어떤 칸인지)
	
	//참새
	static JLabel sparrowImage = new JLabel();
	
	//씨앗 선택 창 
	JButton[] chooseSeedImage = new JButton[4];
	JButton chooseSeedCanelButton = new JButton();
	
	//식물 상태 창 
	JButton waterThePlantsButton = new JButton(); //물주기 button
	JButton rapidGrowthButton = new JButton(); //급속성장 button
	JButton harvestingButton = new JButton(); //수확하기 button
	JButton statusCheckCanelButton = new JButton(); 

	//밭
	static ArrayList<String> statusOfField = new ArrayList<>();
	int numOfField = 0;
	int fieldWidth = 80;
	int fieldHeight = 80;
	int fieldHorizontalLength = 90;
	int fieldVerticalLength = 50;
	int fieldInterval = 100;
	
	//인벤토리 
	int inventoryHorizontalLength = 5;
	int inventoryVerticalLength = 0;
	int inventoryInterval = 126;
	int inventoryWidth = 100;
	int inventoryHeight = 60;
	int inventoryDescriptionlength = 0;
	
	//벽
	int rightWall = 725;
	int leftWall = -10;
	int bottomWall = 488;
	int upperWall = -7;
	
	//씨앗 선택
	int chooseSeedBoxLength = 10;//씨앗 선택 박스의 가로 위치 
	int[] daysRemaining = new int[18]; //농작물이 자라기까지 남은 일수 
	
	//날짜
	static int day = 0;
	int finalday = 7; // 마지막날
	
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("farmTycoon");
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);

		//농사 화면
		farmingScene.setBounds(0, 0, 800, 600);
		frame.getContentPane().add(farmingScene);
		farmingScene.setLayout(null);
		
		playerImage.setHorizontalAlignment(SwingConstants.CENTER);
		playerImage.setIcon(new ImageIcon("./images/rabbit.png"));
		playerImage.setBounds(365, 338, 70, 80);
		farmingScene.add(playerImage);
		
		EnergyText.setText("남은 에너지 : 100");
		EnergyText.setFont(new Font("굴림", Font.BOLD, 15));
		EnergyText.setBounds(650, -35, 150, 150);
		farmingScene.add(EnergyText);
		
		daysText.setText("0일차");
		daysText.setFont(new Font("굴림", Font.BOLD, 15));
		daysText.setBounds(650, -35, 100, 100);
		farmingScene.add(daysText);
		
		moneyText.setText("돈 : 0");
		moneyText.setFont(new Font("굴림", Font.BOLD, 15));
		moneyText.setBounds(10, -35, 100, 100);
		farmingScene.add(moneyText);
		
		houseImage.setHorizontalAlignment(SwingConstants.CENTER);
		houseImage.setIcon(new ImageIcon("./images/houseImage.png"));
		houseImage.setBounds(620, 400, 150, 150);
		farmingScene.add(houseImage);

		storeImage.setHorizontalAlignment(SwingConstants.CENTER);
		storeImage.setIcon(new ImageIcon("./images/storeImage.png"));
		storeImage.setBounds(20, 400, 150, 150);
		farmingScene.add(storeImage);
		
		sparrowImage.setHorizontalAlignment(SwingConstants.CENTER);
		sparrowImage.setIcon(new ImageIcon("./images/sparrow.png"));
		sparrowImage.setBounds(-100, -100, 100, 50);
		farmingScene.add(sparrowImage);
		
		//인벤토리 창
		inventoryWindow.setIcon(new ImageIcon("./images/inventoryBackGround.png"));
		inventoryWindow.setBounds(130, 50, 500, 360);
		inventoryWindow.setLayout(null);
		inventoryWindow.setVisible(false);
		farmingScene.add(inventoryWindow);
		
		//인벤토리 칸, 개수text 위치 초기화
		for (int i = 0; i < inventoryCompartment.length; i++) {
			inventoryWindow.add(numberOfItemsText[i] = new JLabel());
			inventoryWindow.add(inventoryCompartment[i] = new JLabel());
		
			if (i < 4) {
				inventoryVerticalLength = 30;
				inventoryCompartment[i].setBounds(inventoryHorizontalLength, inventoryVerticalLength, inventoryWidth , inventoryHeight);
				numberOfItemsText[i].setBounds(inventoryHorizontalLength + 60, inventoryVerticalLength + 20,  50, 50);

			} else if ((i >= 4) && (i < 8)) {
				inventoryVerticalLength = 120;
				inventoryCompartment[i].setBounds(inventoryHorizontalLength, inventoryVerticalLength, inventoryWidth, inventoryHeight);
				numberOfItemsText[i].setBounds(inventoryHorizontalLength + 60, inventoryVerticalLength + 20, 50 , 50);
				
			} else if ((i >= 8) && (i < 12)) {
				inventoryVerticalLength = 210;
				inventoryCompartment[i].setBounds(inventoryHorizontalLength, inventoryVerticalLength, inventoryWidth, inventoryHeight);
				numberOfItemsText[i].setBounds(inventoryHorizontalLength + 60, inventoryVerticalLength + 20, 50 , 50);
				
			} else if ((i >= 12) && (i < 16)) {
				inventoryVerticalLength = 295;
				inventoryCompartment[i].setBounds(inventoryHorizontalLength, inventoryVerticalLength, inventoryWidth, inventoryHeight);
				numberOfItemsText[i].setBounds(inventoryHorizontalLength + 60, inventoryVerticalLength + 20, 50 , 50);
			}
			inventoryHorizontalLength = inventoryInterval + inventoryHorizontalLength;
			
			if ((i + 1) % 4 == 0) {
				inventoryHorizontalLength = 5;
			}

			inventoryCompartment[i].setHorizontalAlignment(SwingConstants.CENTER);
			
			numberOfItemsText[i].setHorizontalAlignment(SwingConstants.CENTER);
			numberOfItemsText[i].setText("X 0");
		}
		
		//인벤토리 칸에 들어가는 이미지와 개수 초기화
		inventoryCompartment[0].setIcon(new ImageIcon("./images/PumkinSeed_inventory.png"));
		inventoryCompartment[1].setIcon(new ImageIcon("./images/OnionSeed_inventory.png"));
		inventoryCompartment[2].setIcon(new ImageIcon("./images/CabbageSeed_inventory.png"));
		inventoryCompartment[3].setIcon(new ImageIcon("./images/CarrotSeed_inventory.png"));
		inventoryCompartment[4].setIcon(new ImageIcon("./images/Pumkin_inventory.png"));
		inventoryCompartment[5].setIcon(new ImageIcon("./images/Onion_inventory.png"));
		inventoryCompartment[6].setIcon(new ImageIcon("./images/Cabbage_inventory.png"));
		inventoryCompartment[7].setIcon(new ImageIcon("./images/Carrot_inventory.png"));
		inventoryCompartment[8].setIcon(new ImageIcon("./images/portion+30.png"));
		inventoryCompartment[9].setIcon(new ImageIcon("./images/portion+50.png"));
		numberOfItemsText[10].setText("");
		numberOfItemsText[11].setText("");
		inventoryCompartment[12].setIcon(new ImageIcon("./images/randomMushroom.png"));
		inventoryCompartment[13].setIcon(new ImageIcon("./images/bone_invertory.png"));
		numberOfItemsText[14].setText("");
		numberOfItemsText[15].setText("");
		
		//인벤토리 설명 초기화
		for (int i = 0; i < inventoryDescriptionText.length; i++) {
			inventoryWindow.add(inventoryDescriptionText[i] = new JLabel());
			
			inventoryDescriptionText[i].setBounds(10, inventoryDescriptionlength, 50, 50);
			inventoryDescriptionlength = inventoryDescriptionlength + 85;
		}
		inventoryDescriptionText[0].setText("씨앗");
		inventoryDescriptionText[1].setText("농작물");
		inventoryDescriptionText[2].setText("포션");
		inventoryDescriptionText[3].setText("그 외");
		
		//씨앗 심기 창
		seedPlantingWindow.setBackground(Color.WHITE);
		seedPlantingWindow.setBounds(150, 100, 500, 300);
		seedPlantingWindow.setLayout(null);
		seedPlantingWindow.setVisible(false);
		farmingScene.add(seedPlantingWindow);
		
		//씨앗 심기 창 안에있는 씨앗의 이미지와 씨앗의 설명을 초기화
		for (int i = 0; i < chooseSeedImage.length; i++) {
			
			seedPlantingWindow.add(chooseSeedImage[i] = new JButton());
			
			chooseSeedImage[i].setBounds(chooseSeedBoxLength, 10, 105, 250);
			chooseSeedImage[i].setHorizontalAlignment(SwingConstants.CENTER);

			chooseSeedBoxLength = chooseSeedBoxLength + 124;
			
			chooseSeedImage[i].setFocusPainted(false);
			chooseSeedImage[i].setContentAreaFilled(false);
		}
		
		chooseSeedImage[0].setIcon(new ImageIcon("./images/choosePumkinSeedExplanation.png"));
		chooseSeedImage[1].setIcon(new ImageIcon("./images/chooseOnionSeedExplanation.png"));
		chooseSeedImage[2].setIcon(new ImageIcon("./images/chooseCabbageSeedExplanation.png"));
		chooseSeedImage[3].setIcon(new ImageIcon("./images/chooseCarrotSeedExplanation.png"));
		
		//호박을 선택했을 때 
		chooseSeedImage[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (player.energy <= 5) {
					JOptionPane.showMessageDialog(null, "에너지가 모자랍니다", "!!!!", JOptionPane.INFORMATION_MESSAGE);
					
				} if(player.amountPumpkinSeed == 0){
					JOptionPane.showMessageDialog(null, "호박씨가 모자랍니다", "!!!!", JOptionPane.INFORMATION_MESSAGE);
					
				}else {
					//선택한 밭의 정보가 호박으로 바뀐다
					plantsImage[numOfField].setIcon(new ImageIcon("./images/PumkinFieldImage.png"));
					plantsNametext[numOfField].setText("이름 : 호박");
					timeLeftText[numOfField].setText("남은 일 수 : 4일");

					player.amountPumpkinSeed--;
					daysRemaining[numOfField] = 4;
					player.energy = player.energy - 5;
					EnergyText.setText("남은 에너지 : " + player.energy);
				}
			}
		});
		
		chooseSeedImage[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (player.energy <= 5) {
					JOptionPane.showMessageDialog(null, "에너지가 모자랍니다", "!!!!", JOptionPane.INFORMATION_MESSAGE);
					
				} if(player.amountOnionSeed == 0){
					JOptionPane.showMessageDialog(null, "양파씨가 모자랍니다", "!!!!", JOptionPane.INFORMATION_MESSAGE);
					
				} else {
					plantsImage[numOfField].setIcon(new ImageIcon("./images/OnionFieldImage.png"));
					plantsNametext[numOfField].setText("이름 : 양파");
					timeLeftText[numOfField].setText("남은 일 수 : 2일");

					player.amountOnionSeed--;
					daysRemaining[numOfField] = 2;
					player.energy = player.energy - 5;
					EnergyText.setText("남은 에너지 : " + player.energy);
				}
			}
		});
		
		chooseSeedImage[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (player.energy <= 5) {
					JOptionPane.showMessageDialog(null, "에너지가 모자랍니다", "!!!!", JOptionPane.INFORMATION_MESSAGE);
					
				}if(player.amountCabbageSeed == 0){
					JOptionPane.showMessageDialog(null, "양배추씨가 모자랍니다", "!!!!", JOptionPane.INFORMATION_MESSAGE);
					
				} else {
					plantsImage[numOfField].setIcon(new ImageIcon("./images/CabbageFieldImage.png"));
					plantsNametext[numOfField].setText("이름 : 양배추");
					timeLeftText[numOfField].setText("남은 일 수 : 3일");

					player.amountCabbageSeed--;
					daysRemaining[numOfField] = 3;
					player.energy = player.energy - 5;
					EnergyText.setText("남은 에너지 : " + player.energy);
				}
			}
		});
		
		chooseSeedImage[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (player.energy <= 5) {
					JOptionPane.showMessageDialog(null, "에너지가 모자랍니다", "!!!!", JOptionPane.INFORMATION_MESSAGE);
					
				}if(player.amountCarrotSeed == 0){
					JOptionPane.showMessageDialog(null, "당근씨가 모자랍니다", "!!!!", JOptionPane.INFORMATION_MESSAGE);
					
				}  else {
					plantsImage[numOfField].setIcon(new ImageIcon("./images/CarrotFieldImage.png"));
					plantsNametext[numOfField].setText("이름 : 당근");
					timeLeftText[numOfField].setText("남은 일 수 : 2일");

					player.amountCarrotSeed--;
					daysRemaining[numOfField] = 2;
					player.energy = player.energy - 5;
					EnergyText.setText("남은 에너지 : " + player.energy);
				}
			}
		});
		
		//선택한 밭이 빈 밭이라면 씨앗이 심겨진 밭으로 바꾼다.
		for (int i = 0; i < chooseSeedImage.length; i++) {
			chooseSeedImage[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					for (int i = 0; i < chooseSeedImage.length; i++) {
						if (e.getSource() == chooseSeedImage[i]) {
							
							if (statusOfField.get(numOfField).equals("empty Field")) {
								fieldImages[numOfField].setIcon(new ImageIcon("./images/seedFieldImage.png"));
								statusOfField.set(numOfField, "seeded field");
							}
						}
					}
					
					//씨앗을 선택할때 보이지 않게 해 놓았던 것을 다시 보이게 했다
					for (int j = 0; j < fieldImages.length; j++) {
						fieldImages[j].setEnabled(true);
					}
					playerImage.setVisible(true);
					seedPlantingWindow.setVisible(false);
				}
			});
		}
		
		chooseSeedCanelButton.setText("취소하기");
		chooseSeedCanelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				playerImage.setVisible(true);
				seedPlantingWindow.setVisible(false);	
				for (int i = 0; i < fieldImages.length; i++) {
					fieldImages[i].setEnabled(true);
				}
			}
		});
		chooseSeedCanelButton.setBounds(200, 270, 120, 20);
		seedPlantingWindow.add(chooseSeedCanelButton);
		
		
		//씨앗 상태 창
		plantStateWindow.setBackground(Color.WHITE);
		plantStateWindow.setBounds(150, 100, 500, 300);
		plantStateWindow.setLayout(null);
		plantStateWindow.setVisible(false);
		farmingScene.add(plantStateWindow);

		//농작물의 이름, 남은 일 수, 자라는데 필요한 물의 양 text를 초기화
		for (int i = 0; i < fieldImages.length; i++) {
			
			plantStateWindow.add(plantsImage[i] = new JLabel());
			plantStateWindow.add(plantsNametext[i] = new JLabel());
			plantStateWindow.add(timeLeftText[i] = new JLabel());
			plantStateWindow.add(amountOfWater[i] = new JLabel());
			
			plantsImage[i].setBounds(30,60, 80, 80);
			
			plantsNametext[i].setText("이름 : ");
			plantsNametext[i].setFont(new Font("굴림", Font.BOLD, 15));
			plantsNametext[i].setBounds(150, 0, 150, 150);
			
			timeLeftText[i].setText("남은 일 수 : ");
			timeLeftText[i].setFont(new Font("굴림", Font.BOLD, 15));
			timeLeftText[i].setBounds(150, 30, 150, 150);

			amountOfWater[i].setText("물의 양 : 부족");
			amountOfWater[i].setFont(new Font("굴림", Font.BOLD, 15));
			amountOfWater[i].setBounds(150, 60, 150, 150);
			
			plantsImage[i].setVisible(false);
			plantsNametext[i].setVisible(false);
			timeLeftText[i].setVisible(false);
			amountOfWater[i].setVisible(false);
		}
		
		waterThePlantsButton.setText("물주기");
		waterThePlantsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(player.energy <= 3) {
					JOptionPane.showMessageDialog(null, "에너지가 모자랍니다", "!!!!", JOptionPane.INFORMATION_MESSAGE);
				} else {

					// 씨앗만 뿌려진 상태라면
					if (statusOfField.get(numOfField).equals("seeded field")) {
						statusOfField.set(numOfField, "Proper field");
						amountOfWater[numOfField].setText("물의 양 : 적당");
					}
					// 물부족상태라면
					if (statusOfField.get(numOfField).equals("need Water field")) {
						emergencyMarkingImages[numOfField].setVisible(false);
						statusOfField.set(numOfField, "seeded field");
						amountOfWater[numOfField].setText("물의 양 : 부족");
					}

					player.energy = player.energy - 3;
					EnergyText.setText("남은 에너지 : " + player.energy);
				}
				
				//씨앗 상태창을 선택할때 보이지 않게 해 놓았던 것을 다시 보이게 했다
				plantStateWindow.setVisible(false);	
				playerImage.setVisible(true);
				
				for (int i = 0; i < fieldImages.length; i++) {
					fieldImages[i].setEnabled(true);
					
					plantsImage[numOfField].setVisible(false);
					plantsNametext[numOfField].setVisible(false);
					timeLeftText[numOfField].setVisible(false);
					amountOfWater[numOfField].setVisible(false);
				}
			}
		});
		waterThePlantsButton.setFont(new Font("굴림", Font.BOLD, 15));
		waterThePlantsButton.setBounds(15, 180, 120, 80);
		plantStateWindow.add(waterThePlantsButton);
		
		rapidGrowthButton.setText("급속성장");
		rapidGrowthButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(player.amountBone >= 1) {
					player.amountBone--;
					
					if(statusOfField.get(numOfField).equals("seeded field")
							|| statusOfField.get(numOfField).equals("need Water field")
							|| statusOfField.get(numOfField).equals("Proper field")) {
						
						//급속성할 밭이 썩은 밭이나 빈 밭이 아니랄면 해당 밭을 성장시킨다.
						statusOfField.set(numOfField, "fullGrown field");
						
						if (plantsNametext[numOfField].getText().equals("이름 : 호박")) {
							fieldImages[numOfField].setIcon(new ImageIcon("./images/PumKinFieldImage.png"));

						} else if (plantsNametext[numOfField].getText().equals("이름 : 양파")) {
							fieldImages[numOfField].setIcon(new ImageIcon("./images/OnionFieldImage.png"));

						} else if (plantsNametext[numOfField].getText().equals("이름 : 당근")) {
							fieldImages[numOfField].setIcon(new ImageIcon("./images/CarrotFieldImage.png"));

						} else if (plantsNametext[numOfField].getText().equals("이름 : 양배추")) {
							fieldImages[numOfField].setIcon(new ImageIcon("./images/CabbageFieldImage.png"));

						}
					}
				}else {
					JOptionPane.showMessageDialog(null, "뼈의 개수가 모자랍니다", "!!!!", JOptionPane.INFORMATION_MESSAGE);
				}
				plantStateWindow.setVisible(false);	
				playerImage.setVisible(true);
				
				for (int i = 0; i < fieldImages.length; i++) {
					fieldImages[i].setEnabled(true);
					
					plantsImage[numOfField].setVisible(false);
					plantsNametext[numOfField].setVisible(false);
					timeLeftText[numOfField].setVisible(false);
					amountOfWater[numOfField].setVisible(false);
				}
			}
		});
		rapidGrowthButton.setFont(new Font("굴림", Font.BOLD, 15));
		rapidGrowthButton.setBounds(190, 180, 120, 80);
		plantStateWindow.add(rapidGrowthButton);
		
		harvestingButton.setText("수확하기");
		harvestingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(player.energy <= 7) {
					JOptionPane.showMessageDialog(null, "에너지가 모자랍니다", "!!!!", JOptionPane.INFORMATION_MESSAGE);
				}else {
					
					//다 자란 농작물을 수확한다면 해당 농작물을 얻는다.
					if(statusOfField.get(numOfField).equals("fullGrown field")) {
						
						if(plantsNametext[numOfField].getText().equals("이름 : 호박")) {
							player.amountPumpkin++;
							
						}else if(plantsNametext[numOfField].getText().equals("이름 : 양파")) {
							player.amountOnion++;
							
						}else if(plantsNametext[numOfField].getText().equals("이름 : 양배추")) {
							player.amountCabbage++;
							
						}else if(plantsNametext[numOfField].getText().equals("이름 : 당근")) {
							player.amountCarrot++;
						}
					}
					//다 자라지 않은 농작물을 수확한다면 농작물은 얻을 수 없으며 빈땅으로 되돌아간다
					statusOfField.set(numOfField, "empty Field");
					fieldImages[numOfField].setIcon(new ImageIcon("./images/basicsFieldImage.png"));

					player.energy  = player.energy - 7;
					EnergyText.setText("남은 에너지 : " + player.energy);
				}
				
				plantStateWindow.setVisible(false);	
				playerImage.setVisible(true);
				for (int i = 0; i < fieldImages.length; i++) {
					fieldImages[i].setEnabled(true);
					
					plantsImage[numOfField].setVisible(false);
					plantsNametext[numOfField].setVisible(false);
					timeLeftText[numOfField].setVisible(false);
					amountOfWater[numOfField].setVisible(false);
				}
			}
		});
		harvestingButton.setFont(new Font("굴림", Font.BOLD, 15));
		harvestingButton.setBounds(370, 180, 120, 80);
		plantStateWindow.add(harvestingButton);
		
		statusCheckCanelButton.setText("취소하기");
		statusCheckCanelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				plantStateWindow.setVisible(false);	
				playerImage.setVisible(true);
				for (int i = 0; i < fieldImages.length; i++) {
					fieldImages[i].setEnabled(true);
					
					plantsImage[numOfField].setVisible(false);
					plantsNametext[numOfField].setVisible(false);
					timeLeftText[numOfField].setVisible(false);
					amountOfWater[numOfField].setVisible(false);
				}
			}
		});
		statusCheckCanelButton.setBounds(190, 270, 120, 20);
		plantStateWindow.add(statusCheckCanelButton);
		
		//밭
		for (int i = 0; i < fieldImages.length; i++) {
			
			farmingScene.add(emergencyMarkingImages[i] = new JLabel());
			farmingScene.add(fieldImages[i] = new JLabel());
			
			fieldImages[i].setIcon(new ImageIcon("./images/basicsFieldImage.png"));

			if (i < 6) {
				fieldVerticalLength = 50;
				fieldImages[i].setBounds(fieldHorizontalLength, fieldVerticalLength, fieldWidth, fieldHeight);

			} else if ((i >= 6) && (i < 12)) {
				fieldVerticalLength = 140;
				fieldImages[i].setBounds(fieldHorizontalLength, fieldVerticalLength, fieldWidth, fieldHeight);

			} else if ((i >= 12) && (i < 18)) {
				fieldVerticalLength = 230;
				fieldImages[i].setBounds(fieldHorizontalLength, fieldVerticalLength, fieldWidth, fieldHeight);

			}

			fieldHorizontalLength = fieldInterval + fieldHorizontalLength;

			if ((i + 1) % 6 == 0) {
				fieldHorizontalLength = 90;
			}
			
			emergencyMarkingImages[i].setBounds(fieldImages[i].getX() + 25, fieldImages[i].getY() + 25, 20, 20);
			emergencyMarkingImages[i].setIcon(new ImageIcon("./images/EmergencyMarking.png"));
			emergencyMarkingImages[i].setVisible(false);
			
			statusOfField.add("empty Field");
			RotCrops rotCrops = new RotCrops(i);
			rotCrops.start();
		
		}
		
		gameSuccess.setBounds(0, 0, 800, 600);
		frame.getContentPane().add(gameSuccess);
		gameSuccess.setLayout(null);
		gameSuccess.setVisible(false);
		
		successText.setText("당신은 프로 농부가 되었습니다");
		successText.setFont(new Font("굴림", Font.BOLD, 50));
		successText.setHorizontalAlignment(SwingConstants.CENTER);
		successText.setBounds(0, 0, 800, 300);
		gameSuccess.add(successText);
		
		successmoneyEarned.setText("당신이 번 돈 : ");
		successmoneyEarned.setFont(new Font("굴림", Font.BOLD, 30));
		successmoneyEarned.setHorizontalAlignment(SwingConstants.CENTER);
		successmoneyEarned.setBounds(0, 200, 800, 300);
		gameSuccess.add(successmoneyEarned);
		
		gameFail.setBounds(0, 0, 800, 600);
		frame.getContentPane().add(gameFail);
		gameFail.setLayout(null);
		gameFail.setVisible(false);
		
		failText.setText("당신은 농부에 소질이 없군요..");
		failText.setFont(new Font("굴림", Font.BOLD, 50));
		failText.setHorizontalAlignment(SwingConstants.CENTER);
		failText.setBounds(0, 0, 800, 300);
		gameFail.add(failText);
		
		failmoneyEarned.setText("당신이 번 돈 : ");
		failmoneyEarned.setFont(new Font("굴림", Font.BOLD, 30));
		failmoneyEarned.setHorizontalAlignment(SwingConstants.CENTER);
		failmoneyEarned.setBounds(0, 200, 800, 300);
		gameFail.add(failmoneyEarned);
		
		//마우스로 집이나 밭을 클릭했을 때 플레이어가 자동으로 집이나 밭으로 이동한다.
		//오류가 있어 보류했다.
		/*
		for (int i = 0; i < fieldImages.length; i++) {
			fieldImages[i].addMouseListener(new MouseAdapter() {
		            @Override
		            public void mouseClicked(MouseEvent e) {
		            	for (int i = 0; i < fieldImages.length; i++) {
							if (e.getSource() == fieldImages[i]) {
								numOfField = i;
								
								PlayerOutoMove playerOutoMove = new PlayerOutoMove(numOfField, "fields");
								playerOutoMove.start();
								frame.requestFocus();
							}
						}
		            }          
		        });
		}
		
		houseImage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	PlayerOutoMove playerOutoMove = new PlayerOutoMove(0, "house");
				playerOutoMove.start();
            }          
        });
*/
		//던전 화면
		dungeonScene.setBounds(0,0,800,600);
		frame.getContentPane().add(dungeonScene);
		dungeonScene.setLayout(null);
		
		sparrowObstruction.start();
		appearanceOfAnimals.start();
		frame.addKeyListener(new key());
		frame.setFocusable(true);
	}

	class key implements KeyListener {

		// 좌판의 어떤 키로 문자가 눌렸을때 실행
		@Override
		public void keyTyped(KeyEvent e) {
		}

		// 키보드의 키가 눌렸을때 실행
		@Override
		public void keyPressed(KeyEvent e) {

			int keyCode = e.getKeyCode();

			switch (keyCode) {

			//플레이어 이동
			case KeyEvent.VK_UP:
			case KeyEvent.VK_W:
				if (playerImage.getY() >= upperWall) {
					playerImage.setLocation(playerImage.getX(), playerImage.getY() - player.speed);
				}
				break;

			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_S:
				if (playerImage.getY() <= bottomWall) {
					playerImage.setLocation(playerImage.getX(), playerImage.getY() + player.speed);
				}
				break;

			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_D:
				if (playerImage.getX() <= rightWall) {
					playerImage.setLocation(playerImage.getX() + player.speed, playerImage.getY());
				}
				break;

			case KeyEvent.VK_LEFT:
			case KeyEvent.VK_A:
				if (playerImage.getX() >= leftWall) {
					playerImage.setLocation(playerImage.getX() - player.speed, playerImage.getY());
				}
				break;
			
				
			case KeyEvent.VK_SPACE:
				
				System.out.println(playerImage.getX());
				System.out.println(playerImage.getY());
				
				//밭안(밭 앞)에서 스페이스바를 할 경우 씨앗 선택창또는  식물상태창 볼 수 있다.
				//상점, 집안(앞)에서 스페이스 바를 할 경우 상점에 들어가거나 하루를 지나가게 할 수 있다.

				//밭 안에서 스페이스바를 했을 때
					// 밭의 첫번째 줄
					if (playerImage.getY() <= 54) {

						if (playerImage.getX() >= 70 && playerImage.getX() <= 120) {
							numOfField = 0;
							information();

						} else if (playerImage.getX() >= 170 && playerImage.getX() <= 220) {
							numOfField = 1;
							information();

						} else if (playerImage.getX() >= 270 && playerImage.getX() <= 320) {
							numOfField = 2;
							information();

						} else if (playerImage.getX() >= 370 && playerImage.getX() <= 420) {
							numOfField = 3;
							information();

						} else if (playerImage.getX() >= 470 && playerImage.getX() <= 520) {
							numOfField = 4;
							information();

						} else if (playerImage.getX() >= 570 && playerImage.getX() <= 620) {
							numOfField = 5;
							information();

						}
						// 밭의 두번째 줄
					} else if (playerImage.getY() >= 68 && playerImage.getY() <= 143) {

						// 첫번째 칸
						if (playerImage.getX() >= 70 && playerImage.getX() <= 120) {
							numOfField = 6;
							information();

						} else if (playerImage.getX() >= 170 && playerImage.getX() <= 220) {
							numOfField = 7;
							information();

						} else if (playerImage.getX() >= 270 && playerImage.getX() <= 320) {
							numOfField = 8;
							information();

						} else if (playerImage.getX() >= 370 && playerImage.getX() <= 420) {
							numOfField = 9;
							information();

						} else if (playerImage.getX() >= 470 && playerImage.getX() <= 520) {
							numOfField = 10;
							information();

						} else if (playerImage.getX() >= 570 && playerImage.getX() <= 620) {
							numOfField = 11;
							information();

						}
						//3번째 줄
					} else if (playerImage.getY() >= 160 && playerImage.getY() <= 235) {

						if (playerImage.getX() >= 70 && playerImage.getX() <= 120) {
							numOfField = 12;
							information();

						} else if (playerImage.getX() >= 170 && playerImage.getX() <= 220) {
							numOfField = 13;
							information();

						} else if (playerImage.getX() >= 270 && playerImage.getX() <= 320) {
							numOfField = 14;
							information();

						} else if (playerImage.getX() >= 370 && playerImage.getX() <= 420) {
							numOfField = 15;
							information();

						} else if (playerImage.getX() >= 470 && playerImage.getX() <= 520) {
							numOfField = 16;
							information();

						} else if (playerImage.getX() >= 570 && playerImage.getX() <= 620) {
							numOfField = 17;
							information();
							
						}

					}
					
					//밭의 앞에서 스페이스바를 눌렀을 때
					for (int i = 0; i < fieldImages.length; i++) {
					if(playerImage.getX() == fieldImages[i].getX() && playerImage.getY() == fieldImages[i].getY() + 10) {
						information();
					}
				}
				
				
					//집안에서 스페이스바를 눌렀을 때
				if (playerImage.getY() >= 330 && playerImage.getY() <= 475) {
					if (playerImage.getX() >= 600 && playerImage.getX() <= 720) {

						//하루가 지나간다
						JOptionPane.showMessageDialog(null, "하루가 지나갑니다", " ", JOptionPane.INFORMATION_MESSAGE);
						player.energy = 100;
						EnergyText.setText("남은 에너지 : " + player.energy);
						day++;
						daysText.setText(day + "일차");

						//마지막 날이라면 게임을 결과를 보여준다
						if (day == finalday) {

							if(player.money > 70000) {
								//System.out.println("게임 승리");
								farmingScene.setVisible(false);
								gameSuccess.setVisible(true);
								successmoneyEarned.setText("당신이 번 돈 : " + player.money);
								
							}else {
								//System.out.println("게임 패배");
								farmingScene.setVisible(false);
								gameFail.setVisible(true);
								failmoneyEarned.setText("당신이 번 돈 : " + player.money);
							}
							
						} else {
							for (int i = 0; i < fieldImages.length; i++) {

								// 밭의 상태가 적당한 상태라면
								if (statusOfField.get(i).equals("Proper field")) {
									daysRemaining[i]--;
									timeLeftText[i].setText("남은 일 수 : " + daysRemaining[i]);

									// 남은 일수에 따라 이미지 교체
									if (daysRemaining[i] == 0) {
										statusOfField.set(i, "fullGrown field");

										if (plantsNametext[i].getText().equals("이름 : 호박")) {
											fieldImages[i].setIcon(new ImageIcon("./images/PumKinFieldImage.png"));

										} else if (plantsNametext[i].getText().equals("이름 : 양파")) {
											fieldImages[i].setIcon(new ImageIcon("./images/OnionFieldImage.png"));

										} else if (plantsNametext[i].getText().equals("이름 : 당근")) {
											fieldImages[i].setIcon(new ImageIcon("./images/CarrotFieldImage.png"));

										} else if (plantsNametext[i].getText().equals("이름 : 양배추")) {
											fieldImages[i].setIcon(new ImageIcon("./images/CabbageFieldImage.png"));

										}
									} else if (daysRemaining[i] == 1) {
										fieldImages[i].setIcon(new ImageIcon("./images/Grown1FieldImage.png"));

									} else if (daysRemaining[i] == 2) {
										fieldImages[i].setIcon(new ImageIcon("./images/Grown2FieldImage.png"));

									} else if (daysRemaining[i] == 3) {
										fieldImages[i].setIcon(new ImageIcon("./images/Grown3FieldImage.png"));

									}

									// 하루가 지난 후 일수가 남은 식물은 물을 추가로 줘야하도록 농작물의 상태를 바꿔주었다
									if (daysRemaining[i] >= 1) {

										statusOfField.set(i, "seeded field");
										amountOfWater[i].setText("물의 양 : 부족");
									}

									// 밭이 물이 부족한 상태로 하루가 지나게 되면
								} else if (statusOfField.get(i).equals("seeded field")
										|| statusOfField.get(i).equals("need Water field")) {

									// 썩은 밭이 된다
									statusOfField.set(i, "rotten field");
									fieldImages[i].setIcon(new ImageIcon("./images/rottenFieldImage.png"));
									emergencyMarkingImages[i].setVisible(false);
								}
							}
						}
					}
				}
				
				//상점 안에서 스페이스 바를 눌렀을 때
				if (playerImage.getY() >= 328 && playerImage.getY() <= 473) {
					if (playerImage.getX() >= 0 && playerImage.getX() <= 120) {
						System.out.println("상점으로 들어갑니다");
						moneyText.setText("돈 : " + player.money);
					}
				}
				break;
				
			case KeyEvent.VK_E:
				
				//인벤토리 보기
				
				if (inventoryWindow.isVisible() == true) {
					inventoryWindow.setVisible(false);
					playerImage.setVisible(true);

				} else {
					numberOfItemsText[0].setText("X " + player.amountPumpkinSeed);
					numberOfItemsText[1].setText("X " + player.amountOnionSeed);
					numberOfItemsText[2].setText("X " + player.amountCabbageSeed);
					numberOfItemsText[3].setText("X " + player.amountCarrotSeed);
					numberOfItemsText[4].setText("X " + player.amountPumpkin);
					numberOfItemsText[5].setText("X " + player.amountOnion);
					numberOfItemsText[6].setText("X " + player.amountCabbage);
					numberOfItemsText[7].setText("X " + player.amountCarrot);
					
					
					inventoryWindow.setVisible(true);
					playerImage.setVisible(false);
				}

				break;
			}
		}

		// 키보드의 키가 눌렸다 때었을때 실행
		@Override
		public void keyReleased(KeyEvent e) {
		}
	}
	
	public void information(){
		
		//비어있는 땅이라면 씨앗심기창을 보여준다
		if (statusOfField.get(numOfField).equals("empty Field")) {
			seedPlantingWindow.setVisible(true);
			
			for (int j = 0; j < fieldImages.length; j++) {
				fieldImages[j].setEnabled(false);
			}
			playerImage.setVisible(false);
			
			//씨앗이 심겨있는 밭이라면 농작물상태 창을 보여준다
		}else {
			plantStateWindow.setVisible(true);
			
			plantsImage[numOfField].setVisible(true);
			plantsNametext[numOfField].setVisible(true);
			timeLeftText[numOfField].setVisible(true);
			amountOfWater[numOfField].setVisible(true);
			
			//만약 썩은 땅이라면 
			if(statusOfField.get(numOfField).equals("rotten field")){
				//물주기버튼과, 급속성장버튼을 누르지 못하게 한다
				waterThePlantsButton.setEnabled(false);
				rapidGrowthButton.setEnabled(false);
			}else {
				waterThePlantsButton.setEnabled(true);
				rapidGrowthButton.setEnabled(true);
			}
			
			for (int j = 0; j < fieldImages.length; j++) {
				fieldImages[j].setEnabled(false);
			}
			playerImage.setVisible(false);
		}
	}
}
