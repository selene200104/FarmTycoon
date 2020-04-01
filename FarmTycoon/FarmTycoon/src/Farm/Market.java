package Farm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Market extends JFrame{

	JPanel shopPanel = new JPanel() {
		public void paintComponent(Graphics g) {
			Dimension d = getSize();
			ImageIcon image = new ImageIcon("./images/shop.jpg");
			g.drawImage(image.getImage(), 0, 0, d.width, d.height, this);
		}
	};
	
	/*JPanel buyShopPanel = new JPanel() {
		public void paintComponent(Graphics g) {
			Dimension d = getSize();
			ImageIcon image = new ImageIcon("./images/intheshop.jpg");
			g.drawImage(image.getImage(), 0, 0, d.width, d.height, this);
		}
	};
	
	JPanel sellShopPanel = new JPanel() {
		public void paintComponent(Graphics g) {
			Dimension d = getSize();
			ImageIcon image = new ImageIcon("./images/intheshop.jpg");
			g.drawImage(image.getImage(), 0, 0, d.width, d.height, this);
		}
	};*/
	
	JPanel buyShopPanel = new JPanel();
	JPanel sellShopPanel = new JPanel();
	JButton buyButton = new JButton();
	JButton sellButton = new JButton();
	
	int subjectLineHorizontalPosition = 20;
	int subjectLineVerticalPosition = 10;
	int subjectLineWidth= 100;
	int subjectLineHeight= 20;
	// 판매 가격
	int sellingPrice = 0;
	int pumpkinPrice = 6000;
	int onionPrice = 2000 ;
	int cabbagePrice = 3500 ;
	int carrotPrice = 2000 ;
	// 판매할 양
	int amountSellingPumpkin = 0;
	int amountSellingOnion = 0;
	int amountSellingCabbage = 0;
	int amountSellingCarrot = 0;
	
	Market(){
		
		setTitle("상점");
		setSize(450, 300);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		setLayout(new BorderLayout());
		
		shopPanel.setLayout(null);
		shopPanel.setBounds(0, 0, 450, 400);
		shopPanel.setVisible(true);
		//shopPanel.setBackground(Color.WHITE);
		add(shopPanel);
		
		buyShopPanel.setLayout(null);
		buyShopPanel.setBounds(0, 0, 450, 400);
		buyShopPanel.setVisible(false);
		buyShopPanel.setBackground(Color.WHITE);
		add(buyShopPanel);
		
		sellShopPanel.setLayout(null);
		sellShopPanel.setBounds(0, 0, 450, 400);
		sellShopPanel.setVisible(false);
		sellShopPanel.setBackground(Color.WHITE);
		add(sellShopPanel);
		
		buyButton.setText("구매하기");
		buyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shopPanel.setVisible(false);
				buyShopPanel.setVisible(true);
			}
		});
		buyButton.setBounds(40, 50, 100, 50);
		shopPanel.add(buyButton);
		
		sellButton.setText("판매하기");
		sellButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shopPanel.setVisible(false);
				sellShopPanel.setVisible(true);
				
				JLabel productsListLabel = new JLabel();
				productsListLabel.setText("* 품목 이름 * ");
				productsListLabel.setHorizontalAlignment(JLabel.CENTER);
				productsListLabel.setBounds(20, 10, 100, 20);
				productsListLabel.setBackground(Color.white);
				productsListLabel.setOpaque(true);
				sellShopPanel.add(productsListLabel);
				
				JLabel productsPriceLabel = new JLabel();
				productsPriceLabel.setText("* 품목 가격 * ");
				productsPriceLabel.setHorizontalAlignment(JLabel.CENTER);
				productsPriceLabel.setBounds(120, 10, 100, 20);
				productsPriceLabel.setBackground(Color.white);
				productsPriceLabel.setOpaque(true);
				sellShopPanel.add(productsPriceLabel);
				
				JLabel productAmountLabel = new JLabel();
				productAmountLabel.setText("* 보유 갯수 * ");
				productAmountLabel.setHorizontalAlignment(JLabel.CENTER);
				productAmountLabel.setBounds(220, 10, 100, 20);
				sellShopPanel.add(productAmountLabel);
				
				JLabel sellItemCountLabel = new JLabel();
				sellItemCountLabel.setText("* 판매 갯수 * ");
				sellItemCountLabel.setHorizontalAlignment(JLabel.CENTER);
				sellItemCountLabel.setBounds(320, 10, 100, 20);
				sellShopPanel.add(sellItemCountLabel);
				
				final JLabel sellingPriceLabel = new JLabel();
				sellingPriceLabel.setText("총 판매금액은 : "+sellingPrice+" 원 입니다.");
				sellingPriceLabel.setHorizontalAlignment(JLabel.CENTER);
				sellingPriceLabel.setBounds(80, 180, 300, 20);
				sellShopPanel.add(sellingPriceLabel);
				
				//호박 팔 때
				JLabel pumpkinLabel = new JLabel();
				pumpkinLabel.setText(" 호박 ");
				pumpkinLabel.setHorizontalAlignment(JLabel.CENTER);
				pumpkinLabel.setBounds(20, 40, 100, 20);
				sellShopPanel.add(pumpkinLabel);
				
				JLabel pumpkinPriceLabel = new JLabel();
				pumpkinPriceLabel.setText(""+pumpkinPrice);
				pumpkinPriceLabel.setHorizontalAlignment(JLabel.CENTER);
				pumpkinPriceLabel.setBounds(120, 40, 100, 20);
				sellShopPanel.add(pumpkinPriceLabel);
				
				JLabel pumpkinAmountLabel = new JLabel();
				pumpkinAmountLabel.setText(""+Player.amountPumpkin);
				pumpkinAmountLabel.setHorizontalAlignment(JLabel.CENTER);
				pumpkinAmountLabel.setBounds(220, 40, 100, 20);
				sellShopPanel.add(pumpkinAmountLabel);
				
				JButton pumpkinCountMinus = new JButton("-");
				pumpkinCountMinus.setBounds(300, 40, 50, 20);
				pumpkinCountMinus.setBorderPainted(false);
				pumpkinCountMinus.setBackground(Color.white);
				pumpkinCountMinus.setOpaque(true);
				sellShopPanel.add(pumpkinCountMinus);
				
				final JLabel sellPumpkinAmountLabel = new JLabel(""+amountSellingPumpkin);
				sellPumpkinAmountLabel.setHorizontalAlignment(JLabel.CENTER);
				sellPumpkinAmountLabel.setBounds(340, 40, 50, 20);
				sellShopPanel.add(sellPumpkinAmountLabel);
				
				JButton pumpkinCountPlus = new JButton("+");
				pumpkinCountPlus.setBounds(380, 40, 50, 20);
				pumpkinCountPlus.setBorderPainted(false);
				pumpkinCountPlus.setBackground(Color.white);
				pumpkinCountPlus.setOpaque(true);
				sellShopPanel.add(pumpkinCountPlus);
				
				pumpkinCountMinus.addActionListener (new ActionListener(){
		            public void actionPerformed(ActionEvent ae){
		            	amountSellingPumpkin--;
		            	
		            	//값이 0보다 작아지면 더이상 작아질 수 없다.
		            	if(amountSellingPumpkin < 0 ) {
		            		amountSellingPumpkin = 0;
		            	}
		            	
		            	sumSellingPrice();
		            	sellingPriceLabel.setText("총 판매금액은 : "+sellingPrice+" 원 입니다.");
		            	
		            	sellPumpkinAmountLabel.setText(amountSellingPumpkin+"");
		                System.out.println(amountSellingPumpkin);
		            }
		        });
				
				pumpkinCountPlus.addActionListener (new ActionListener(){
		            public void actionPerformed(ActionEvent ae){
		            	amountSellingPumpkin++;
		            	
		            	//값이 소유하고 있는 값보다 커지면 더이상 커질 수 없다.
		            	if(amountSellingPumpkin > Player.amountPumpkin ) {
		            		amountSellingPumpkin = Player.amountPumpkin;
		            	}
		            	
		            	sumSellingPrice();
		            	sellingPriceLabel.setText("총 판매금액은 : "+sellingPrice+" 원 입니다.");
		            	
		            	sellPumpkinAmountLabel.setText(amountSellingPumpkin+"");
		                System.out.println(amountSellingPumpkin);
		            }
		        });
				
				//양파 팔 때
				JLabel onionLabel = new JLabel();
				onionLabel.setText(" 양파 ");
				onionLabel.setHorizontalAlignment(JLabel.CENTER);
				onionLabel.setBounds(20, 70, 100, 20);
				sellShopPanel.add(onionLabel);
				
				JLabel onionPriceLabel = new JLabel();
				onionPriceLabel.setText(""+onionPrice);
				onionPriceLabel.setHorizontalAlignment(JLabel.CENTER);
				onionPriceLabel.setBounds(120, 70, 100, 20);
				sellShopPanel.add(onionPriceLabel);
				
				JLabel onionAmountLabel = new JLabel();
				onionAmountLabel.setText(""+Player.amountOnion);
				onionAmountLabel.setHorizontalAlignment(JLabel.CENTER);
				onionAmountLabel.setBounds(220, 70, 100, 20);
				sellShopPanel.add(onionAmountLabel);
				
				JButton onionCountMinus = new JButton("-");
				onionCountMinus.setBounds(300, 70, 50, 20);
				onionCountMinus.setBorderPainted(false);
				onionCountMinus.setBackground(Color.white);
				onionCountMinus.setOpaque(true);
				sellShopPanel.add(onionCountMinus);
				
				final JLabel sellOnionAmountLabel = new JLabel(""+amountSellingOnion);
				sellOnionAmountLabel.setHorizontalAlignment(JLabel.CENTER);
				sellOnionAmountLabel.setBounds(340, 70, 50, 20);
				sellShopPanel.add(sellOnionAmountLabel);
				
				JButton onionCountPlus = new JButton("+");
				onionCountPlus.setBounds(380, 70, 50, 20);
				onionCountPlus.setBorderPainted(false);
				onionCountPlus.setBackground(Color.white);
				onionCountPlus.setOpaque(true);
				sellShopPanel.add(onionCountPlus);
				
				onionCountMinus.addActionListener (new ActionListener(){
		            public void actionPerformed(ActionEvent ae){
		            	amountSellingOnion--;
		            	
		            	//값이 0보다 작아지면 더이상 작아질 수 없다.
		            	if(amountSellingOnion < 0 ) {
		            		amountSellingOnion = 0;
		            	}
		            	
		            	sumSellingPrice();
		            	sellingPriceLabel.setText("총 판매금액은 : "+sellingPrice+" 원 입니다.");
		            	
		            	sellOnionAmountLabel.setText(amountSellingOnion+"");
		                System.out.println(amountSellingOnion);
		            }
		        });
				
				onionCountPlus.addActionListener (new ActionListener(){
		            public void actionPerformed(ActionEvent ae){
		            	amountSellingOnion++;
		            	
		            	//값이 소유하고 있는 값보다 커지면 더이상 커질 수 없다.
		            	if(amountSellingOnion > Player.amountOnion ) {
		            		amountSellingOnion = Player.amountOnion;
		            	}
		            	
		            	sumSellingPrice();
		            	sellingPriceLabel.setText("총 판매금액은 : "+sellingPrice+" 원 입니다.");
		            	
		            	sellOnionAmountLabel.setText(amountSellingOnion+"");
		                System.out.println(amountSellingOnion);
		            }
		        });
				
				//양배추 팔 때
				JLabel cabbageLabel = new JLabel();
				cabbageLabel.setText(" 양배추 ");
				cabbageLabel.setHorizontalAlignment(JLabel.CENTER);
				cabbageLabel.setBounds(20, 100, 100, 20);
				sellShopPanel.add(cabbageLabel);
				
				JLabel cabbagePriceLabel = new JLabel();
				cabbagePriceLabel.setText(""+cabbagePrice);
				cabbagePriceLabel.setHorizontalAlignment(JLabel.CENTER);
				cabbagePriceLabel.setBounds(120, 100, 100, 20);
				sellShopPanel.add(cabbagePriceLabel);
				
				JLabel cabbageAmountLabel = new JLabel();
				cabbageAmountLabel.setText(""+Player.amountCabbage);
				cabbageAmountLabel.setHorizontalAlignment(JLabel.CENTER);
				cabbageAmountLabel.setBounds(220, 100, 100, 20);
				sellShopPanel.add(cabbageAmountLabel);
				
				JButton cabbageCountMinus = new JButton("-");
				cabbageCountMinus.setBounds(300, 100, 50, 20);
				cabbageCountMinus.setBorderPainted(false);
				cabbageCountMinus.setBackground(Color.white);
				cabbageCountMinus.setOpaque(true);
				sellShopPanel.add(cabbageCountMinus);
				
				final JLabel sellCabbageAmountLabel = new JLabel(""+amountSellingCabbage);
				sellCabbageAmountLabel.setHorizontalAlignment(JLabel.CENTER);
				sellCabbageAmountLabel.setBounds(340, 100, 50, 20);
				sellShopPanel.add(sellCabbageAmountLabel);
				
				JButton cabbageCountPlus = new JButton("+");
				cabbageCountPlus.setBounds(380, 100, 50, 20);
				cabbageCountPlus.setBorderPainted(false);
				cabbageCountPlus.setBackground(Color.white);
				cabbageCountPlus.setOpaque(true);
				sellShopPanel.add(cabbageCountPlus);
				
				cabbageCountMinus.addActionListener (new ActionListener(){
		            public void actionPerformed(ActionEvent ae){
		            	amountSellingCabbage--;
		            	
		            	//값이 0보다 작아지면 더이상 작아질 수 없다.
		            	if(amountSellingCabbage < 0 ) {
		            		amountSellingCabbage = 0;
		            	}
		            	
		            	sumSellingPrice();
		            	sellingPriceLabel.setText("총 판매금액은 : "+sellingPrice+" 원 입니다.");
		            	
		            	sellCabbageAmountLabel.setText(amountSellingCabbage+"");
		                System.out.println(amountSellingCabbage);
		            }
		        });
				
				cabbageCountPlus.addActionListener (new ActionListener(){
		            public void actionPerformed(ActionEvent ae){
		            	amountSellingCabbage++;
		            	
		            	//값이 소유하고 있는 값보다 커지면 더이상 커질 수 없다.
		            	if(amountSellingCabbage > Player.amountCabbage ) {
		            		amountSellingCabbage = Player.amountCabbage;
		            	}
		            	
		            	sumSellingPrice();
		            	sellingPriceLabel.setText("총 판매금액은 : "+sellingPrice+" 원 입니다.");
		            	
		            	sellCabbageAmountLabel.setText(amountSellingCabbage+"");
		                System.out.println(amountSellingCabbage);
		            }
		        });
				
				//당근 팔 때
				JLabel carrotLabel = new JLabel();
				carrotLabel.setText(" 당근 ");
				carrotLabel.setHorizontalAlignment(JLabel.CENTER);
				carrotLabel.setBounds(20, 130, 100, 20);
				sellShopPanel.add(carrotLabel);
				
				JLabel carrotPriceLabel = new JLabel();
				carrotPriceLabel.setText(""+carrotPrice);
				carrotPriceLabel.setHorizontalAlignment(JLabel.CENTER);
				carrotPriceLabel.setBounds(120, 130, 100, 20);
				sellShopPanel.add(carrotPriceLabel);
				
				JLabel carrotAmountLabel = new JLabel();
				carrotAmountLabel.setText(""+Player.amountCarrot);
				carrotAmountLabel.setHorizontalAlignment(JLabel.CENTER);
				carrotAmountLabel.setBounds(220, 130, 100, 20);
				sellShopPanel.add(carrotAmountLabel);
				
				JButton carrotCountMinus = new JButton("-");
				carrotCountMinus.setBounds(300, 130, 50, 20);
				carrotCountMinus.setBorderPainted(false);
				carrotCountMinus.setBackground(Color.white);
				carrotCountMinus.setOpaque(true);
				sellShopPanel.add(carrotCountMinus);
				
				final JLabel sellCarrotAmountLabel = new JLabel(""+amountSellingCarrot);
				sellCarrotAmountLabel.setHorizontalAlignment(JLabel.CENTER);
				sellCarrotAmountLabel.setBounds(340, 130, 50, 20);
				sellShopPanel.add(sellCarrotAmountLabel);
				
				JButton carrotCountPlus = new JButton("+");
				carrotCountPlus.setBounds(380, 130, 50, 20);
				carrotCountPlus.setBorderPainted(false);
				carrotCountPlus.setBackground(Color.white);
				carrotCountPlus.setOpaque(true);
				sellShopPanel.add(carrotCountPlus);
				
				carrotCountMinus.addActionListener (new ActionListener(){
		            public void actionPerformed(ActionEvent ae){
		            	amountSellingCarrot--;
		            	
		            	//값이 0보다 작아지면 더이상 작아질 수 없다.
		            	if(amountSellingCarrot < 0 ) {
		            		amountSellingCarrot = 0;
		            	}
		            	
		            	sumSellingPrice();
		            	sellingPriceLabel.setText("총 판매금액은 : "+sellingPrice+" 원 입니다.");
		            	
		            	sellCarrotAmountLabel.setText(amountSellingCarrot+"");
		                System.out.println(amountSellingCarrot);
		            }
		        });
				
				carrotCountPlus.addActionListener (new ActionListener(){
		            public void actionPerformed(ActionEvent ae){
		            	amountSellingCarrot++;
		            	
		            	//값이 소유하고 있는 값보다 커지면 더이상 커질 수 없다.
		            	if(amountSellingCarrot > Player.amountCarrot ) {
		            		amountSellingCarrot = Player.amountCarrot;
		            	}
		            	
		            	sumSellingPrice();
		            	sellingPriceLabel.setText("총 판매금액은 : "+sellingPrice+" 원 입니다.");
		            	
		            	sellCarrotAmountLabel.setText(amountSellingCarrot+"");
		                System.out.println(amountSellingCarrot);
		            }
		        });
				
				//판매하기 버튼
				JButton sellButton = new JButton("판매하기");
				sellButton.setBounds(150, 220, 150, 20);
				sellButton.setBorderPainted(false);
				sellButton.setOpaque(true);
				sellShopPanel.add(sellButton);
				
				//판매하기 버튼을 눌렀을 때
				sellButton.addActionListener (new ActionListener(){
		            public void actionPerformed(ActionEvent ae){
		    			
					JLabel sellingMoneyLabel = new JLabel();
					sellingMoneyLabel.setBounds(400, 0, 200, 50);
					sellingMoneyLabel.setBackground(Color.pink);
					
					Player.money = Player.money + sellingPrice;
					Farming.moneyText.setText("돈 : " + Player.money);
					/*//판매 스레드 실행
					Selling selling = new Selling(sellingMoneyLabel);
					selling.start();
		            sellingMoneyLabel.setOpaque(true);
					FarmGame.headerPanel.add(sellingMoneyLabel);
					//System.out.println(sellingMoneyLabel);
					*/
					//각각의 농작물 값에서 판매갯수만큼 빠짐
					Player.amountPumpkin -= amountSellingPumpkin;
					Player.amountOnion -= amountSellingOnion;
					Player.amountCabbage -= amountSellingCarrot;
					Player.amountCarrot -= amountSellingCabbage;
					
					//계산버튼을 누르면 모든 판매갯수, 가격의 값이 0으로 초기화 된다.
					amountSellingPumpkin = 0;
					amountSellingOnion = 0;
					amountSellingCarrot = 0;
					amountSellingCabbage = 0;
					sellingPrice = 0;
					
					sellPumpkinAmountLabel.setText(""+amountSellingPumpkin);
					sellOnionAmountLabel.setText(""+amountSellingOnion);
					sellCarrotAmountLabel.setText(""+amountSellingCarrot);
					sellCabbageAmountLabel.setText(""+amountSellingCabbage);
					sellingPriceLabel.setText(""+sellingPrice);

					dispose();
		            }
		        });
			}
			
			void sumSellingPrice(){
				sellingPrice = amountSellingPumpkin*pumpkinPrice
						+amountSellingOnion*onionPrice
						+amountSellingCarrot*carrotPrice
						+amountSellingCabbage*cabbagePrice;
			}
						
		});
		sellButton.setBounds(40, 130, 100, 50);
		shopPanel.add(sellButton); 
	}
}
