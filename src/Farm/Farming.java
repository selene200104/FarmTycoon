package Farm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
	ManageCrops manageCrops = new ManageCrops();
	
	public JPanel farmingScene = new JPanel() {
	/*	public void paintComponent(Graphics g) {
			Dimension d = getSize();
			ImageIcon image = new ImageIcon("./image/background.jpg");
			g.drawImage(image.getImage(), 0, 0, d.width, d.height, this);
		}*/
	};
	JButton[] fieldImages = new JButton[18];
	JButton chooseSeedCanelButton = new JButton();
	JPanel seedPlantingWindow = new JPanel();
	
	JLabel playerImage = new JLabel();
	JLabel houseImage = new JLabel();
	JLabel storeImage = new JLabel();
	JLabel[] seedImage = new JLabel[4];
	JLabel[] seedExplanationImage = new JLabel[4];


	int fieldWidth = 80;
	int fieldHeight = 80;
	int fieldHorizontalLength = 90;
	int fieldVerticalLength = 50;
	int fieldInterval = 100;
	int rightWall = 725;
	int leftWall = -10;
	int bottomWall = 488;
	int upperWall = -7;

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
		playerImage.setIcon(new ImageIcon("C:\\Users\\dayou\\OneDrive\\바탕 화면\\팀노바\\java_teamProject\\rabbit.png"));
		playerImage.setBounds(365, 338, 70, 80);
		farmingScene.add(playerImage);
		
		houseImage.setHorizontalAlignment(SwingConstants.CENTER);
		houseImage.setIcon(new ImageIcon("C:\\Users\\dayou\\OneDrive\\바탕 화면\\팀노바\\java_teamProject\\houseImage.png"));
		houseImage.setBounds(620, 400, 150, 150);
		farmingScene.add(houseImage);

		storeImage.setHorizontalAlignment(SwingConstants.CENTER);
		storeImage.setIcon(new ImageIcon("C:\\Users\\dayou\\OneDrive\\바탕 화면\\팀노바\\java_teamProject\\storeImage.png"));
		storeImage.setBounds(20, 400, 150, 150);
		farmingScene.add(storeImage);
		
		seedPlantingWindow.setBackground(Color.WHITE);
		seedPlantingWindow.setBounds(150, 100, 500, 300);
		seedPlantingWindow.setLayout(null);
		seedPlantingWindow.setVisible(false);
		farmingScene.add(seedPlantingWindow);
		
		int chooseSeedBoxLength = 10;//씨앗 선택 박스의 가로 위치 
		for (int i = 0; i < seedImage.length; i++) {
			
			seedPlantingWindow.add(seedImage[i] = new JLabel());
			seedPlantingWindow.add(seedExplanationImage[i] = new JLabel());
			
			seedImage[i].setBounds(chooseSeedBoxLength, 10, 105, 100);
			seedImage[i].setHorizontalAlignment(SwingConstants.CENTER);
			seedImage[i].setIcon(new ImageIcon("C:\\Users\\dayou\\OneDrive\\바탕 화면\\팀노바\\java_teamProject\\storeImage.png"));
			
			seedExplanationImage[i].setBounds(chooseSeedBoxLength, 110, 105, 160);
			seedExplanationImage[i].setHorizontalAlignment(SwingConstants.CENTER);
			seedExplanationImage[i].setIcon(new ImageIcon("C:\\Users\\dayou\\OneDrive\\바탕 화면\\팀노바\\java_teamProject\\storeImage.png"));
			
			chooseSeedBoxLength = chooseSeedBoxLength + 124;
		}

		chooseSeedCanelButton.setText("취소하기");
		chooseSeedCanelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				seedPlantingWindow.setVisible(false);	
				for (int i = 0; i < fieldImages.length; i++) {
					fieldImages[i].setEnabled(true);
				}
			}
		});
		chooseSeedCanelButton.setBounds(200, 270, 100, 20);
		seedPlantingWindow.add(chooseSeedCanelButton);
		
		for (int i = 0; i < fieldImages.length; i++) {

			farmingScene.add(fieldImages[i] = new JButton());
			fieldImages[i].setIcon(
					new ImageIcon("C:\\Users\\dayou\\OneDrive\\바탕 화면\\팀노바\\java_teamProject\\basicsFieldImage.png"));

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
		}
		
		

		for (int i = 0; i < fieldImages.length; i++) {
			fieldImages[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					for (int i = 0; i < fieldImages.length; i++) {
						if (e.getSource() == fieldImages[i]) {
							playerImage.setLocation(fieldImages[i].getX(), fieldImages[i].getY() + 15);
							frame.requestFocus();
						}
					}
				}
			});
		}



		manageCrops.start();
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
				if (playerImage.getY() >= upperWall) {
					playerImage.setLocation(playerImage.getX(), playerImage.getY() - player.speed);
				}
				break;

			case KeyEvent.VK_DOWN:
				if (playerImage.getY() <= bottomWall) {
					playerImage.setLocation(playerImage.getX(), playerImage.getY() + player.speed);
				}
				break;

			case KeyEvent.VK_RIGHT:
				if (playerImage.getX() <= rightWall) {
					playerImage.setLocation(playerImage.getX() + player.speed, playerImage.getY());
				}
				break;

			case KeyEvent.VK_LEFT:
				if (playerImage.getX() >= leftWall) {
					playerImage.setLocation(playerImage.getX() - player.speed, playerImage.getY());
				}
				break;
				
			case KeyEvent.VK_SPACE:
				System.out.println(playerImage.getX());
				for (int i = 0; i < fieldImages.length; i++) {
					if(playerImage.getX() == fieldImages[i].getX()
							&&playerImage.getY() == fieldImages[i].getY() + 15) {
						seedPlantingWindow.setVisible(true);
						for (int j = 0; j < fieldImages.length; j++) {
							fieldImages[j].setEnabled(false);
						}
					}
				}
			}
		}

		// 키보드의 키가 눌렸다 때었을때 실행
		@Override
		public void keyReleased(KeyEvent e) {
		}
	}
}
