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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.Icon;
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
	SparrowObstruction sparrowObstruction = new SparrowObstruction();
	
	static JPanel farmingScene = new JPanel() {
		//public void paintComponent(Graphics g) {
		//	Dimension d = getSize();
		//	ImageIcon image = new ImageIcon("./images/backGround.jpg");
		//	g.drawImage(image.getImage(), 0, 0, d.width, d.height, this);
		//}
	};

	static JLabel[] fieldImages = new JLabel[18];
	static JLabel[] emergencyMarkingImages = new JLabel[18];//물이 필요하다는 긴급표시
	JButton chooseSeedCanelButton = new JButton();
	JPanel seedPlantingWindow = new JPanel();
	JPanel plantStateWindow = new JPanel();
	
	static JLabel playerImage = new JLabel();
	JLabel playerEnergy = new JLabel();
	JLabel daysText = new JLabel();
	static JLabel houseImage = new JLabel();
	JLabel storeImage = new JLabel();
	JButton[] seedImage = new JButton[4];
	JLabel[] seedExplanationImage = new JLabel[4];

	//식물 상태창 
	JLabel[] plantsImage = new JLabel[18];
	JLabel[] plantsNametext = new JLabel[18];
	JLabel[] timeLeftText = new JLabel[18];
	static JLabel[] amountOfWater = new JLabel[18];
	JButton waterThePlantsButton = new JButton();
	JButton rapidGrowthButton = new JButton();
	JButton harvestingButton = new JButton();
	JButton statusCheckCanelButton = new JButton();
	
	//인벤토리
	JLabel inventoryWindow = new JLabel();
	JLabel[] inventoryCompartment = new JLabel[16]; //인벤토리 칸
	JLabel[] numberOfItemsText = new JLabel[16]; //물품이 몇개있지 text
	JLabel[] inventoryDescriptionText = new JLabel[4]; //인벤토리 설명 (어떤 칸인지)
	int inventoryHorizontalLength = 5;
	int inventoryVerticalLength = 0;
	int inventoryInterval = 100;
	int inventoryWidth = 90;
	int inventoryHeight = 60;
	int inventoryDescriptionlength = 0;
	
	//참새
	static JLabel sparrowImage = new JLabel();
	
	//밭
	static ArrayList<String> statusOfField = new ArrayList<>();
	int numOfField = 0;
	int fieldWidth = 80;
	int fieldHeight = 80;
	int fieldHorizontalLength = 90;
	int fieldVerticalLength = 50;
	int fieldInterval = 100;
	
	//벽
	int rightWall = 725;
	int leftWall = -10;
	int bottomWall = 488;
	int upperWall = -7;
	
	//씨앗 선택
	int chooseSeedBoxLength = 10;//씨앗 선택 박스의 가로 위치 
	int[] daysRemaining = new int[18]; //농작물이 자라기까지 남은 일수 
	
	//날짜
	int day = 0;
	int finalday = 7; // 마지막날
	
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("farmTycoon");
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);

		farmingScene.setBounds(0, 0, 800, 600);
		frame.getContentPane().add(farmingScene);
		farmingScene.setLayout(null);

		playerImage.setHorizontalAlignment(SwingConstants.CENTER);
		playerImage.setIcon(new ImageIcon("./images/rabbit.png"));
		playerImage.setBounds(365, 338, 70, 80);
		farmingScene.add(playerImage);
		
		playerEnergy.setText("남은 에너지 : 100");
		playerEnergy.setFont(new Font("굴림", Font.BOLD, 15));
		playerEnergy.setBounds(650, -35, 150, 150);
		farmingScene.add(playerEnergy);
		
		daysText.setText("0일차");
		daysText.setFont(new Font("굴림", Font.BOLD, 15));
		daysText.setBounds(650, -35, 100, 100);
		farmingScene.add(daysText);
		
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
		inventoryWindow.setBounds(180, 80, 400, 350);
		inventoryWindow.setLayout(null);
		inventoryWindow.setVisible(false);
		farmingScene.add(inventoryWindow);
		
		for (int i = 0; i < inventoryCompartment.length; i++) {
			inventoryWindow.add(numberOfItemsText[i] = new JLabel());
			inventoryWindow.add(inventoryCompartment[i] = new JLabel());
		
			if (i < 4) {
				inventoryVerticalLength = 30;
				inventoryCompartment[i].setBounds(inventoryHorizontalLength, inventoryVerticalLength, inventoryWidth , inventoryHeight);
				numberOfItemsText[i].setBounds(inventoryHorizontalLength + 40, inventoryVerticalLength + 20, 50 , 50);

			} else if ((i >= 4) && (i < 8)) {
				inventoryVerticalLength = 120;
				inventoryCompartment[i].setBounds(inventoryHorizontalLength, inventoryVerticalLength, inventoryWidth, inventoryHeight);
				numberOfItemsText[i].setBounds(inventoryHorizontalLength + 40, inventoryVerticalLength + 20, 50 , 50);
				
			} else if ((i >= 8) && (i < 12)) {
				inventoryVerticalLength = 210;
				inventoryCompartment[i].setBounds(inventoryHorizontalLength, inventoryVerticalLength, inventoryWidth, inventoryHeight);
				numberOfItemsText[i].setBounds(inventoryHorizontalLength + 40, inventoryVerticalLength + 20, 50 , 50);
				
			} else if ((i >= 12) && (i < 16)) {
				inventoryVerticalLength = 295;
				inventoryCompartment[i].setBounds(inventoryHorizontalLength, inventoryVerticalLength, inventoryWidth, inventoryHeight);
				numberOfItemsText[i].setBounds(inventoryHorizontalLength + 40, inventoryVerticalLength + 20, 50 , 50);
			}
			inventoryHorizontalLength = inventoryInterval + inventoryHorizontalLength;
			
			if ((i + 1) % 4 == 0) {
				inventoryHorizontalLength = 5;
			}

			inventoryCompartment[i].setHorizontalAlignment(SwingConstants.CENTER);
			//inventoryCompartment[i].setIcon(new ImageIcon("./images/storeImage.png"));
			
			numberOfItemsText[i].setHorizontalAlignment(SwingConstants.CENTER);
			numberOfItemsText[i].setText("X 0");
		}
		inventoryCompartment[0].setIcon(new ImageIcon("./images/PumkinFieldImage.png"));
		inventoryCompartment[1].setIcon(new ImageIcon("./images/OnionFieldImage.png"));
		inventoryCompartment[2].setIcon(new ImageIcon("./images/CabbageFieldImage.png"));
		inventoryCompartment[3].setIcon(new ImageIcon("./images/CarrotFieldImage.png"));
		inventoryCompartment[4].setIcon(new ImageIcon("./images/PumkinFieldImage.png"));
		inventoryCompartment[5].setIcon(new ImageIcon("./images/OnionFieldImage.png"));
		inventoryCompartment[6].setIcon(new ImageIcon("./images/CabbageFieldImage.png"));
		inventoryCompartment[7].setIcon(new ImageIcon("./images/CarrotFieldImage.png"));
		inventoryCompartment[8].setIcon(new ImageIcon("./images/PumkinFieldImage.png"));
		inventoryCompartment[9].setIcon(new ImageIcon("./images/OnionFieldImage.png"));
		inventoryCompartment[10].setIcon(new ImageIcon("./images/CabbageFieldImage.png"));
		inventoryCompartment[11].setIcon(new ImageIcon("./images/CarrotFieldImage.png"));
		inventoryCompartment[12].setIcon(new ImageIcon("./images/PumkinFieldImage.png"));
		inventoryCompartment[13].setIcon(new ImageIcon("./images/OnionFieldImage.png"));
		inventoryCompartment[14].setIcon(new ImageIcon("./images/CabbageFieldImage.png"));
		inventoryCompartment[15].setIcon(new ImageIcon("./images/CarrotFieldImage.png"));
		
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
		for (int i = 0; i < seedImage.length; i++) {
			
			seedPlantingWindow.add(seedImage[i] = new JButton());
			seedPlantingWindow.add(seedExplanationImage[i] = new JLabel());
			
			seedImage[i].setBounds(chooseSeedBoxLength, 10, 105, 100);
			seedImage[i].setHorizontalAlignment(SwingConstants.CENTER);
			
			seedExplanationImage[i].setBounds(chooseSeedBoxLength, 110, 105, 160);
			seedExplanationImage[i].setHorizontalAlignment(SwingConstants.CENTER);
			seedExplanationImage[i].setIcon(new ImageIcon("./images/storeImage.png"));
			
			chooseSeedBoxLength = chooseSeedBoxLength + 124;
			
			seedImage[i].setFocusPainted(false);
			seedImage[i].setContentAreaFilled(false);
		}
		
		seedImage[0].setIcon(new ImageIcon("./images/PumkinFieldImage.png"));
		seedImage[1].setIcon(new ImageIcon("./images/OnionFieldImage.png"));
		seedImage[2].setIcon(new ImageIcon("./images/CabbageFieldImage.png"));
		seedImage[3].setIcon(new ImageIcon("./images/CarrotFieldImage.png"));
		
		seedImage[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (player.energy <= 5) {
					JOptionPane.showMessageDialog(null, "에너지가 모자랍니다", "!!!!", JOptionPane.INFORMATION_MESSAGE);
					
				} if(player.numOfPumpkinSeed == 0){
					JOptionPane.showMessageDialog(null, "호박씨가 모자랍니다", "!!!!", JOptionPane.INFORMATION_MESSAGE);
					
				}else {
				
					plantsImage[numOfField].setIcon(new ImageIcon("./images/PumkinFieldImage.png"));
					plantsNametext[numOfField].setText("이름 : 호박");
					timeLeftText[numOfField].setText("남은 일 수 : 4일");

					player.numOfPumpkinSeed--;
					daysRemaining[numOfField] = 4;
					player.energy = player.energy - 5;
					playerEnergy.setText("남은 에너지 : " + player.energy);
				}
			}
		});
		
		seedImage[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (player.energy <= 5) {
					JOptionPane.showMessageDialog(null, "에너지가 모자랍니다", "!!!!", JOptionPane.INFORMATION_MESSAGE);
					
				} if(player.numOfOnionSeed == 0){
					JOptionPane.showMessageDialog(null, "양파씨가 모자랍니다", "!!!!", JOptionPane.INFORMATION_MESSAGE);
					
				} else {
					plantsImage[numOfField].setIcon(new ImageIcon("./images/OnionFieldImage.png"));
					plantsNametext[numOfField].setText("이름 : 양파");
					timeLeftText[numOfField].setText("남은 일 수 : 2일");

					player.numOfOnionSeed--;
					daysRemaining[numOfField] = 2;
					player.energy = player.energy - 5;
					playerEnergy.setText("남은 에너지 : " + player.energy);
				}
			}
		});
		
		seedImage[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (player.energy <= 5) {
					JOptionPane.showMessageDialog(null, "에너지가 모자랍니다", "!!!!", JOptionPane.INFORMATION_MESSAGE);
					
				}if(player.numOfCabbageSeed == 0){
					JOptionPane.showMessageDialog(null, "양배추씨가 모자랍니다", "!!!!", JOptionPane.INFORMATION_MESSAGE);
					
				} else {
					plantsImage[numOfField].setIcon(new ImageIcon("./images/CabbageFieldImage.png"));
					plantsNametext[numOfField].setText("이름 : 양배추");
					timeLeftText[numOfField].setText("남은 일 수 : 3일");

					player.numOfCabbageSeed--;
					daysRemaining[numOfField] = 3;
					player.energy = player.energy - 5;
					playerEnergy.setText("남은 에너지 : " + player.energy);
				}
			}
		});
		
		seedImage[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (player.energy <= 5) {
					JOptionPane.showMessageDialog(null, "에너지가 모자랍니다", "!!!!", JOptionPane.INFORMATION_MESSAGE);
					
				}if(player.numOfCarrotSeed == 0){
					JOptionPane.showMessageDialog(null, "당근씨가 모자랍니다", "!!!!", JOptionPane.INFORMATION_MESSAGE);
					
				}  else {
					plantsImage[numOfField].setIcon(new ImageIcon("./images/CarrotFieldImage.png"));
					plantsNametext[numOfField].setText("이름 : 당근");
					timeLeftText[numOfField].setText("남은 일 수 : 2일");

					player.numOfCarrotSeed--;
					daysRemaining[numOfField] = 2;
					player.energy = player.energy - 5;
					playerEnergy.setText("남은 에너지 : " + player.energy);
				}
			}
		});
		
		
		for (int i = 0; i < seedImage.length; i++) {
			seedImage[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					for (int i = 0; i < seedImage.length; i++) {
						if (e.getSource() == seedImage[i]) {
							
							if (statusOfField.get(numOfField).equals("empty Field")) {
								fieldImages[numOfField].setIcon(new ImageIcon("./images/seedFieldImage.png"));
								statusOfField.set(numOfField, "seeded field");
							}
						}
					}
					
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
					playerEnergy.setText("남은 에너지 : " + player.energy);
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
		waterThePlantsButton.setFont(new Font("굴림", Font.BOLD, 15));
		waterThePlantsButton.setBounds(15, 180, 120, 80);
		plantStateWindow.add(waterThePlantsButton);
		
		rapidGrowthButton.setText("급속성장");
		rapidGrowthButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(player.numOfBone >= 1) {
					player.numOfBone--;
					
					if(statusOfField.get(numOfField).equals("seeded field")
							|| statusOfField.get(numOfField).equals("need Water field")
							|| statusOfField.get(numOfField).equals("Proper field")) {
						//성장한다
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
					
					//다 자란 농작물을 수확한다면
					if(statusOfField.get(numOfField).equals("fullGrown field")) {
						
						if(plantsNametext[numOfField].getText().equals("이름 : 호박")) {
							player.numOfPumpkin++;
							
						}else if(plantsNametext[numOfField].getText().equals("이름 : 양파")) {
							player.numOfOnion++;
							
						}else if(plantsNametext[numOfField].getText().equals("이름 : 양배추")) {
							player.numOfCabbage++;
							
						}else if(plantsNametext[numOfField].getText().equals("이름 : 당근")) {
							player.numOfCarrot++;
						}
					}
					//다 자라지 않은 농작물을 수확한다면 농작물은 얻을 수 없으며 빈땅으로 되돌아간다
					statusOfField.set(numOfField, "empty Field");
					fieldImages[numOfField].setIcon(new ImageIcon("./images/basicsFieldImage.png"));

					player.energy  = player.energy - 7;
					playerEnergy.setText("남은 에너지 : " + player.energy);
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
		sparrowObstruction.start();
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
						playerEnergy.setText("남은 에너지 : " + player.energy);
						day++;
						daysText.setText(day + "일차");

						//마지막 날이라면 게임을 결과를 보여준다
						if (day == finalday) {

							if(player.money > 70000) {
								System.out.println("게임 승리");
								
							}else {
								System.out.println("게임 패배");
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
						
					}
				}
				break;
				
			case KeyEvent.VK_E:
				
				if (inventoryWindow.isVisible() == true) {
					inventoryWindow.setVisible(false);
					playerImage.setVisible(true);

				} else {
					numberOfItemsText[0].setText("X " + player.numOfPumpkinSeed);
					numberOfItemsText[1].setText("X " + player.numOfOnionSeed);
					numberOfItemsText[2].setText("X " + player.numOfCabbageSeed);
					numberOfItemsText[3].setText("X " + player.numOfCarrotSeed);
					numberOfItemsText[4].setText("X " + player.numOfPumpkin);
					numberOfItemsText[5].setText("X " + player.numOfOnion);
					numberOfItemsText[6].setText("X " + player.numOfCabbage);
					numberOfItemsText[7].setText("X " + player.numOfCarrot);

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
