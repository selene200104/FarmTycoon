package Farm;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
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

	JPanel farmingScene = new JPanel() {
		public void paintComponent(Graphics g) {
			Dimension d = getSize();
			ImageIcon image = new ImageIcon("./image/background.jpg");
			g.drawImage(image.getImage(), 0, 0, d.width, d.height, this);
		}
	};
	JLabel playerImage = new JLabel();
	JLabel houseImage = new JLabel();
	JLabel storeImage = new JLabel();
	
	JButton[] fields = new JButton[18];
	
	int rightWall = 725;
	int leftWall = -10;
	int bottomWall = 488;
	int uppelWall = -7;
	int fieldWidth = 80;
	int fieldHeight = 80; 
	int fieldHorizontalLength = 90;
	int fieldVerticalLength = 50;
	int fieldInterval = 100; // 타일 간격
	int speed = 5;
	
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

		for (int i = 0; i < fields.length; i++) {

			farmingScene.add(fields[i] = new JButton());
			fields[i].setIcon(new ImageIcon("C:\\Users\\dayou\\OneDrive\\바탕 화면\\팀노바\\java_teamProject\\basicsFieldImage.png"));
			
			if (i < 6) {
				fieldVerticalLength = 50;
				fields[i].setBounds(fieldHorizontalLength, fieldVerticalLength, fieldWidth, fieldHeight);

			} else if ((i >= 6) && (i < 12)) {
				fieldVerticalLength = 140;
				fields[i].setBounds(fieldHorizontalLength, fieldVerticalLength, fieldWidth, fieldHeight);

			} else if ((i >= 12) && (i < 18)) {
				fieldVerticalLength = 230;
				fields[i].setBounds(fieldHorizontalLength, fieldVerticalLength, fieldWidth, fieldHeight);

			}
			
			fieldHorizontalLength = fieldInterval + fieldHorizontalLength;
			fields[i].setFocusPainted(false);
			fields[i].setContentAreaFilled(false);
			fields[i].setBorderPainted(false);

			if ((i + 1) % 6 == 0) {
				fieldHorizontalLength = 90;
			}
		}
		
		frame.addKeyListener(new key());
		frame.requestFocus();
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
				playerImage.setLocation(playerImage.getX(), playerImage.getY() - speed);
				break;

			case KeyEvent.VK_DOWN:
				playerImage.setLocation(playerImage.getX(), playerImage.getY() + speed);
				break;

			case KeyEvent.VK_RIGHT:
				playerImage.setLocation(playerImage.getX() + speed, playerImage.getY());
				break;

			case KeyEvent.VK_LEFT:
				playerImage.setLocation(playerImage.getX() - speed, playerImage.getY());
				break;
			}

			// 캐릭터가 화면 밖을 나가지 않게 하기
			if (playerImage.getX() >= rightWall) {
				playerImage.setLocation(rightWall, playerImage.getY());

			} else if (playerImage.getX() <= leftWall) {
				playerImage.setLocation(leftWall, playerImage.getY());

			} else if (playerImage.getY() >= bottomWall) {
				playerImage.setLocation(playerImage.getX(), bottomWall);

			} else if (playerImage.getY() <= uppelWall) {
				playerImage.setLocation(playerImage.getX(), uppelWall);

			}
		}

		// 키보드의 키가 눌렸다 때었을때 실행
		@Override
		public void keyReleased(KeyEvent e) {
		}
	}
}
