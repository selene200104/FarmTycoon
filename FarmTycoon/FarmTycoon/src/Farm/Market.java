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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Market extends JFrame {

	JPanel shopPanel = new JPanel() {
		public void paintComponent(Graphics g) {
			Dimension d = getSize();
			ImageIcon image = new ImageIcon("./images/shop.jpg");
			g.drawImage(image.getImage(), 0, 0, d.width, d.height, this);
		}
	};

	JPanel buyShopPanel = new JPanel();
	JPanel sellShopPanel = new JPanel();
	JButton buyButton = new JButton();
	JButton sellButton = new JButton();

	int subjectLineHorizontalPosition = 20;
	int subjectLineVerticalPosition = 10;
	int subjectLineWidth = 100;
	int subjectLineHeight = 20;

	// 구입 가격
	int buyingPrice = 0;
	int pumpkinSeedPrice = 500;
	int onionSeedPrice = 100;
	int cabbageSeedPrice = 300;
	int carrotSeedPrice = 100;
	int potionHp_30Price = 500;
	int potionHp_50Price = 700;
	int fieldUpgrade_firstPrice = 10000;
	int fieldUpgrade_secondPrice = 30000;

	// 구입할 양
	int amountBuyingPumpkinSeed = 0;
	int amountBuyingOnionSeed = 0;
	int amountBuyingCabbageSeed = 0;
	int amountBuyingCarrotSeed = 0;
	int amountBuyingPotionHp_30 = 0;
	int amountBuyingPotionHp_50 = 0;
	int countFieldUpgrade_first = 0;
	int countFieldUpgrade_second = 0;

	// 구입된 업그레이드권
	static int amountFieldUpgrade_first = 0;
	static int amountFieldUpgrade_second = 0;

	// 판매 가격
	int sellingPrice = 0;
	int pumpkinPrice = 6000;
	int onionPrice = 2000;
	int cabbagePrice = 3500;
	int carrotPrice = 2000;

	// 판매할 양
	int amountSellingPumpkin = 0;
	int amountSellingOnion = 0;
	int amountSellingCabbage = 0;
	int amountSellingCarrot = 0;

	Market() {

		setTitle("상점");
		setSize(450, 450);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		setLayout(new BorderLayout());

		shopPanel.setLayout(null);
		shopPanel.setBounds(0, 0, 450, 450);
		shopPanel.setVisible(true);
		// shopPanel.setBackground(Color.WHITE);
		add(shopPanel);

		buyShopPanel.setLayout(null);
		buyShopPanel.setBounds(0, 0, 450, 450);
		buyShopPanel.setVisible(false);
		buyShopPanel.setBackground(Color.WHITE);
		add(buyShopPanel);

		sellShopPanel.setLayout(null);
		sellShopPanel.setBounds(0, 0, 450, 450);
		sellShopPanel.setVisible(false);
		sellShopPanel.setBackground(Color.WHITE);
		add(sellShopPanel);

		buyButton.setText("구매하기");
		buyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shopPanel.setVisible(false);
				buyShopPanel.setVisible(true);
				System.out.println("구입전 1단계 업그레이드권 갯수 "+amountFieldUpgrade_first);

				JLabel productsListLabel = new JLabel();
				productsListLabel.setText("* 품목 이름 * ");
				productsListLabel.setHorizontalAlignment(JLabel.CENTER);
				productsListLabel.setBounds(20, 20, 100, 20);
				productsListLabel.setBackground(Color.white);
				productsListLabel.setOpaque(true);
				buyShopPanel.add(productsListLabel);

				JLabel productsPriceLabel = new JLabel();
				productsPriceLabel.setText("* 품목 가격 * ");
				productsPriceLabel.setHorizontalAlignment(JLabel.CENTER);
				productsPriceLabel.setBounds(120, 20, 100, 20);
				productsPriceLabel.setBackground(Color.white);
				productsPriceLabel.setOpaque(true);
				buyShopPanel.add(productsPriceLabel);

				JLabel productAmountLabel = new JLabel();
				productAmountLabel.setText("* 보유 갯수 * ");
				productAmountLabel.setHorizontalAlignment(JLabel.CENTER);
				productAmountLabel.setBounds(220, 20, 100, 20);
				buyShopPanel.add(productAmountLabel);

				JLabel buyItemCountLabel = new JLabel();
				buyItemCountLabel.setText("* 구매 갯수 * ");
				buyItemCountLabel.setHorizontalAlignment(JLabel.CENTER);
				buyItemCountLabel.setBounds(320, 20, 100, 20);
				buyShopPanel.add(buyItemCountLabel);

				final JLabel buyingPriceLabel = new JLabel();
				buyingPriceLabel.setText("총 구입금액은 : " + buyingPrice + " 원 입니다.");
				buyingPriceLabel.setHorizontalAlignment(JLabel.CENTER);
				buyingPriceLabel.setBounds(80, 330, 300, 20);
				buyShopPanel.add(buyingPriceLabel);

				JLabel seedsLabel = new JLabel();
				seedsLabel.setText("- 씨앗 -");
				seedsLabel.setHorizontalAlignment(JLabel.CENTER);
				seedsLabel.setBounds(20, 40, 100, 20);
				seedsLabel.setBackground(Color.white);
				seedsLabel.setOpaque(true);
				buyShopPanel.add(seedsLabel);

				// 호박씨앗 살 때
				JLabel pumpkinSeedLabel = new JLabel();
				pumpkinSeedLabel.setText(" 호박씨앗 ");
				pumpkinSeedLabel.setHorizontalAlignment(JLabel.CENTER);
				pumpkinSeedLabel.setBounds(20, 60, 100, 20);
				buyShopPanel.add(pumpkinSeedLabel);

				JLabel pumpkinSeedPriceLabel = new JLabel();
				pumpkinSeedPriceLabel.setText("" + pumpkinSeedPrice);
				pumpkinSeedPriceLabel.setHorizontalAlignment(JLabel.CENTER);
				pumpkinSeedPriceLabel.setBounds(120, 60, 100, 20);
				buyShopPanel.add(pumpkinSeedPriceLabel);

				JLabel pumpkinSeedAmountLabel = new JLabel();
				pumpkinSeedAmountLabel.setText("" + Player.amountPumpkinSeed);
				pumpkinSeedAmountLabel.setHorizontalAlignment(JLabel.CENTER);
				pumpkinSeedAmountLabel.setBounds(220, 60, 100, 20);
				buyShopPanel.add(pumpkinSeedAmountLabel);

				JButton pumpkinSeedCountMinus = new JButton("-");
				pumpkinSeedCountMinus.setBounds(300, 60, 50, 20);
				pumpkinSeedCountMinus.setBorderPainted(false);
				pumpkinSeedCountMinus.setBackground(Color.white);
				pumpkinSeedCountMinus.setOpaque(true);
				buyShopPanel.add(pumpkinSeedCountMinus);

				// 호박씨앗 사려는 수
				final JLabel buyPumpkinSeedAmountLabel = new JLabel("" + amountBuyingPumpkinSeed);
				buyPumpkinSeedAmountLabel.setHorizontalAlignment(JLabel.CENTER);
				buyPumpkinSeedAmountLabel.setBounds(340, 60, 50, 20);
				buyShopPanel.add(buyPumpkinSeedAmountLabel);

				JButton pumpkinSeedCountPlus = new JButton("+");
				pumpkinSeedCountPlus.setBounds(380, 60, 50, 20);
				pumpkinSeedCountPlus.setBorderPainted(false);
				pumpkinSeedCountPlus.setBackground(Color.white);
				pumpkinSeedCountPlus.setOpaque(true);
				buyShopPanel.add(pumpkinSeedCountPlus);

				pumpkinSeedCountMinus.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						amountBuyingPumpkinSeed--;

						// 값이 0보다 작아지면 더이상 작아질 수 없다.
						if (amountBuyingPumpkinSeed < 0) {
							amountBuyingPumpkinSeed = 0;
						}

						sumBuyingPrice();
						buyingPriceLabel.setText("총 구입금액은 : " + buyingPrice + " 원 입니다.");

						buyPumpkinSeedAmountLabel.setText(amountBuyingPumpkinSeed + "");
						System.out.println(amountBuyingPumpkinSeed);
					}
				});

				pumpkinSeedCountPlus.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						amountBuyingPumpkinSeed++;

						// 값이 10보다 커지면 더이상 커질 수 없다.
						if (amountBuyingPumpkinSeed > 10) {
							amountBuyingPumpkinSeed = 10;
						}

						// 쇼핑한 값을 계산해주는 메소드
						sumBuyingPrice();
						buyingPriceLabel.setText("총 구입금액은 : " + buyingPrice + " 원 입니다.");

						buyPumpkinSeedAmountLabel.setText(amountBuyingPumpkinSeed + "");
						System.out.println(amountBuyingPumpkinSeed);
					}
				});

				// 양파씨앗 살 때
				JLabel onionSeedLabel = new JLabel();
				onionSeedLabel.setText(" 양파씨앗 ");
				onionSeedLabel.setHorizontalAlignment(JLabel.CENTER);
				onionSeedLabel.setBounds(20, 80, 100, 20);
				buyShopPanel.add(onionSeedLabel);

				JLabel onionSeedPriceLabel = new JLabel();
				onionSeedPriceLabel.setText("" + onionSeedPrice);
				onionSeedPriceLabel.setHorizontalAlignment(JLabel.CENTER);
				onionSeedPriceLabel.setBounds(120, 80, 100, 20);
				buyShopPanel.add(onionSeedPriceLabel);

				JLabel onionSeedAmountLabel = new JLabel();
				onionSeedAmountLabel.setText("" + Player.amountOnionSeed);
				onionSeedAmountLabel.setHorizontalAlignment(JLabel.CENTER);
				onionSeedAmountLabel.setBounds(220, 80, 100, 20);
				buyShopPanel.add(onionSeedAmountLabel);

				JButton onionSeedCountMinus = new JButton("-");
				onionSeedCountMinus.setBounds(300, 80, 50, 20);
				onionSeedCountMinus.setBorderPainted(false);
				onionSeedCountMinus.setBackground(Color.white);
				onionSeedCountMinus.setOpaque(true);
				buyShopPanel.add(onionSeedCountMinus);
				
				// 양파씨앗 사려는 수
				final JLabel buyOnionSeedAmountLabel = new JLabel("" + amountBuyingOnionSeed);
				buyOnionSeedAmountLabel.setHorizontalAlignment(JLabel.CENTER);
				buyOnionSeedAmountLabel.setBounds(340, 80, 50, 20);
				buyShopPanel.add(buyOnionSeedAmountLabel);

				JButton onionSeedCountPlus = new JButton("+");
				onionSeedCountPlus.setBounds(380, 80, 50, 20);
				onionSeedCountPlus.setBorderPainted(false);
				onionSeedCountPlus.setBackground(Color.white);
				onionSeedCountPlus.setOpaque(true);
				buyShopPanel.add(onionSeedCountPlus);

				onionSeedCountMinus.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						amountBuyingOnionSeed--;

						// 값이 0보다 작아지면 더이상 작아질 수 없다.
						if (amountBuyingOnionSeed < 0) {
							amountBuyingOnionSeed = 0;
						}

						sumBuyingPrice();
						buyingPriceLabel.setText("총 구입금액은 : " + buyingPrice + " 원 입니다.");

						buyOnionSeedAmountLabel.setText(amountBuyingOnionSeed + "");
						System.out.println(amountBuyingOnionSeed);
					}
				});

				onionSeedCountPlus.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						amountBuyingOnionSeed++;

						// 값이 10보다 커지면 더이상 커질 수 없다.
						if (amountBuyingOnionSeed > 10) {
							amountBuyingOnionSeed = 10;
						}

						// 쇼핑한 값을 계산해주는 메소드
						sumBuyingPrice();
						buyingPriceLabel.setText("총 구입금액은 : " + buyingPrice + " 원 입니다.");

						buyOnionSeedAmountLabel.setText(amountBuyingOnionSeed + "");
						System.out.println(amountBuyingOnionSeed);
					}
				});

				// 양배추씨앗 살 때
				JLabel cabbageSeedLabel = new JLabel();
				cabbageSeedLabel.setText(" 양배추씨앗 ");
				cabbageSeedLabel.setHorizontalAlignment(JLabel.CENTER);
				cabbageSeedLabel.setBounds(20, 100, 100, 20);
				buyShopPanel.add(cabbageSeedLabel);

				JLabel cabbageSeedPriceLabel = new JLabel();
				cabbageSeedPriceLabel.setText("" + cabbageSeedPrice);
				cabbageSeedPriceLabel.setHorizontalAlignment(JLabel.CENTER);
				cabbageSeedPriceLabel.setBounds(120, 100, 100, 20);
				buyShopPanel.add(cabbageSeedPriceLabel);

				JLabel cabbageSeedAmountLabel = new JLabel();
				cabbageSeedAmountLabel.setText("" + Player.amountCabbageSeed);
				cabbageSeedAmountLabel.setHorizontalAlignment(JLabel.CENTER);
				cabbageSeedAmountLabel.setBounds(220, 100, 100, 20);
				buyShopPanel.add(cabbageSeedAmountLabel);

				JButton cabbageSeedCountMinus = new JButton("-");
				cabbageSeedCountMinus.setBounds(300, 100, 50, 20);
				cabbageSeedCountMinus.setBorderPainted(false);
				cabbageSeedCountMinus.setBackground(Color.white);
				cabbageSeedCountMinus.setOpaque(true);
				buyShopPanel.add(cabbageSeedCountMinus);

				// 양배추씨앗 사려는 수
				final JLabel buyCabbageSeedAmountLabel = new JLabel("" + amountBuyingCabbageSeed);
				buyCabbageSeedAmountLabel.setHorizontalAlignment(JLabel.CENTER);
				buyCabbageSeedAmountLabel.setBounds(340, 100, 50, 20);
				buyShopPanel.add(buyCabbageSeedAmountLabel);

				JButton cabbageSeedCountPlus = new JButton("+");
				cabbageSeedCountPlus.setBounds(380, 100, 50, 20);
				cabbageSeedCountPlus.setBorderPainted(false);
				cabbageSeedCountPlus.setBackground(Color.white);
				cabbageSeedCountPlus.setOpaque(true);
				buyShopPanel.add(cabbageSeedCountPlus);

				cabbageSeedCountMinus.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						amountBuyingCabbageSeed--;

						// 값이 0보다 작아지면 더이상 작아질 수 없다.
						if (amountBuyingCabbageSeed < 0) {
							amountBuyingCabbageSeed = 0;
						}

						sumBuyingPrice();
						buyingPriceLabel.setText("총 구입금액은 : " + buyingPrice + " 원 입니다.");

						buyCabbageSeedAmountLabel.setText(amountBuyingCabbageSeed + "");
						System.out.println(amountBuyingCabbageSeed);
					}
				});

				cabbageSeedCountPlus.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						amountBuyingCabbageSeed++;

						// 값이 10보다 커지면 더이상 커질 수 없다.
						if (amountBuyingCabbageSeed > 10) {
							amountBuyingCabbageSeed = 10;
						}

						// 쇼핑한 값을 계산해주는 메소드
						sumBuyingPrice();
						buyingPriceLabel.setText("총 구입금액은 : " + buyingPrice + " 원 입니다.");

						buyCabbageSeedAmountLabel.setText(amountBuyingCabbageSeed + "");
						System.out.println(amountBuyingCabbageSeed);
					}
				});

				// 당근씨앗 살 때
				JLabel carrotSeedLabel = new JLabel();
				carrotSeedLabel.setText(" 당근씨앗 ");
				carrotSeedLabel.setHorizontalAlignment(JLabel.CENTER);
				carrotSeedLabel.setBounds(20, 120, 100, 20);
				buyShopPanel.add(carrotSeedLabel);

				JLabel carrotSeedPriceLabel = new JLabel();
				carrotSeedPriceLabel.setText("" + carrotSeedPrice);
				carrotSeedPriceLabel.setHorizontalAlignment(JLabel.CENTER);
				carrotSeedPriceLabel.setBounds(120, 120, 100, 20);
				buyShopPanel.add(carrotSeedPriceLabel);

				JLabel carrotSeedAmountLabel = new JLabel();
				carrotSeedAmountLabel.setText("" + Player.amountCarrotSeed);
				carrotSeedAmountLabel.setHorizontalAlignment(JLabel.CENTER);
				carrotSeedAmountLabel.setBounds(220, 120, 100, 20);
				buyShopPanel.add(carrotSeedAmountLabel);

				JButton carrotSeedCountMinus = new JButton("-");
				carrotSeedCountMinus.setBounds(300, 120, 50, 20);
				carrotSeedCountMinus.setBorderPainted(false);
				carrotSeedCountMinus.setBackground(Color.white);
				carrotSeedCountMinus.setOpaque(true);
				buyShopPanel.add(carrotSeedCountMinus);

				// 당근씨앗 사려는 수
				final JLabel buyCarrotSeedAmountLabel = new JLabel("" + amountBuyingCarrotSeed);
				buyCarrotSeedAmountLabel.setHorizontalAlignment(JLabel.CENTER);
				buyCarrotSeedAmountLabel.setBounds(340, 120, 50, 20);
				buyShopPanel.add(buyCarrotSeedAmountLabel);

				JButton carrotSeedCountPlus = new JButton("+");
				carrotSeedCountPlus.setBounds(380, 120, 50, 20);
				carrotSeedCountPlus.setBorderPainted(false);
				carrotSeedCountPlus.setBackground(Color.white);
				carrotSeedCountPlus.setOpaque(true);
				buyShopPanel.add(carrotSeedCountPlus);

				carrotSeedCountMinus.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						amountBuyingCarrotSeed--;

						// 값이 0보다 작아지면 더이상 작아질 수 없다.
						if (amountBuyingCarrotSeed < 0) {
							amountBuyingCarrotSeed = 0;
						}

						sumBuyingPrice();
						buyingPriceLabel.setText("총 구입금액은 : " + buyingPrice + " 원 입니다.");

						buyCarrotSeedAmountLabel.setText(amountBuyingCarrotSeed + "");
						System.out.println(amountBuyingCarrotSeed);
					}
				});

				carrotSeedCountPlus.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						amountBuyingCarrotSeed++;

						// 값이 10보다 커지면 더이상 커질 수 없다.
						if (amountBuyingCarrotSeed > 10) {
							amountBuyingCarrotSeed = 10;
						}

						// 쇼핑한 값을 계산해주는 메소드
						sumBuyingPrice();
						buyingPriceLabel.setText("총 구입금액은 : " + buyingPrice + " 원 입니다.");

						buyCarrotSeedAmountLabel.setText(amountBuyingCarrotSeed + "");
						System.out.println(amountBuyingCarrotSeed);
					}
				});

				JLabel potionLabel = new JLabel();
				potionLabel.setText("- 포션 -");
				potionLabel.setHorizontalAlignment(JLabel.CENTER);
				potionLabel.setBounds(20, 160, 100, 20);
				potionLabel.setBackground(Color.white);
				potionLabel.setOpaque(true);
				buyShopPanel.add(potionLabel);

				// 체력포션(30) 살 때
				JLabel potionHp_30Label = new JLabel();
				potionHp_30Label.setText(" 체력포션(+30) ");
				potionHp_30Label.setHorizontalAlignment(JLabel.CENTER);
				potionHp_30Label.setBounds(20, 180, 100, 20);
				buyShopPanel.add(potionHp_30Label);

				JLabel potionHp_30PriceLabel = new JLabel();
				potionHp_30PriceLabel.setText("" + potionHp_30Price);
				potionHp_30PriceLabel.setHorizontalAlignment(JLabel.CENTER);
				potionHp_30PriceLabel.setBounds(120, 180, 100, 20);
				buyShopPanel.add(potionHp_30PriceLabel);

				JLabel potionHp_30AmountLabel = new JLabel();
				potionHp_30AmountLabel.setText("" + Player.amountPotionHp_30);
				potionHp_30AmountLabel.setHorizontalAlignment(JLabel.CENTER);
				potionHp_30AmountLabel.setBounds(220, 180, 100, 20);
				buyShopPanel.add(potionHp_30AmountLabel);

				JButton potionHp_30CountMinus = new JButton("-");
				potionHp_30CountMinus.setBounds(300, 180, 50, 20);
				potionHp_30CountMinus.setBorderPainted(false);
				potionHp_30CountMinus.setBackground(Color.white);
				potionHp_30CountMinus.setOpaque(true);
				buyShopPanel.add(potionHp_30CountMinus);

				// 체력포션(30) 사려는 수
				final JLabel buypotionHp_30AmountLabel = new JLabel("" + amountBuyingPotionHp_30);
				buypotionHp_30AmountLabel.setHorizontalAlignment(JLabel.CENTER);
				buypotionHp_30AmountLabel.setBounds(340, 180, 50, 20);
				buyShopPanel.add(buypotionHp_30AmountLabel);

				JButton potionHp_30CountPlus = new JButton("+");
				potionHp_30CountPlus.setBounds(380, 180, 50, 20);
				potionHp_30CountPlus.setBorderPainted(false);
				potionHp_30CountPlus.setBackground(Color.white);
				potionHp_30CountPlus.setOpaque(true);
				buyShopPanel.add(potionHp_30CountPlus);

				potionHp_30CountMinus.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						amountBuyingPotionHp_30--;

						// 값이 0보다 작아지면 더이상 작아질 수 없다.
						if (amountBuyingPotionHp_30 < 0) {
							amountBuyingPotionHp_30 = 0;
						}

						sumBuyingPrice();
						buyingPriceLabel.setText("총 구입금액은 : " + buyingPrice + " 원 입니다.");

						buypotionHp_30AmountLabel.setText(amountBuyingPotionHp_30 + "");
						System.out.println(amountBuyingPotionHp_30);
					}
				});

				potionHp_30CountPlus.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						amountBuyingPotionHp_30++;

						// 값이 10보다 커지면 더이상 커질 수 없다.
						if (amountBuyingPotionHp_30 > 10) {
							amountBuyingPotionHp_30 = 10;
						}

						// 쇼핑한 값을 계산해주는 메소드
						sumBuyingPrice();
						buyingPriceLabel.setText("총 구입금액은 : " + buyingPrice + " 원 입니다.");

						buypotionHp_30AmountLabel.setText(amountBuyingPotionHp_30 + "");
						System.out.println(amountBuyingPotionHp_30);
					}
				});

				// 체력포션(50) 살 때
				JLabel potionHp_50Label = new JLabel();
				potionHp_50Label.setText(" 체력포션(+50) ");
				potionHp_50Label.setHorizontalAlignment(JLabel.CENTER);
				potionHp_50Label.setBounds(20, 200, 100, 20);
				buyShopPanel.add(potionHp_50Label);

				JLabel potionHp_50PriceLabel = new JLabel();
				potionHp_50PriceLabel.setText("" + potionHp_50Price);
				potionHp_50PriceLabel.setHorizontalAlignment(JLabel.CENTER);
				potionHp_50PriceLabel.setBounds(120, 200, 100, 20);
				buyShopPanel.add(potionHp_50PriceLabel);

				JLabel potionHp_50AmountLabel = new JLabel();
				potionHp_50AmountLabel.setText("" + Player.amountPotionHp_50);
				potionHp_50AmountLabel.setHorizontalAlignment(JLabel.CENTER);
				potionHp_50AmountLabel.setBounds(220, 200, 100, 20);
				buyShopPanel.add(potionHp_50AmountLabel);

				JButton potionHp_50CountMinus = new JButton("-");
				potionHp_50CountMinus.setBounds(300, 200, 50, 20);
				potionHp_50CountMinus.setBorderPainted(false);
				potionHp_50CountMinus.setBackground(Color.white);
				potionHp_50CountMinus.setOpaque(true);
				buyShopPanel.add(potionHp_50CountMinus);

				// 체력포션(50) 사려는 수
				final JLabel buypotionHp_50AmountLabel = new JLabel("" + amountBuyingPotionHp_50);
				buypotionHp_50AmountLabel.setHorizontalAlignment(JLabel.CENTER);
				buypotionHp_50AmountLabel.setBounds(340, 200, 50, 20);
				buyShopPanel.add(buypotionHp_50AmountLabel);

				JButton potionHp_50CountPlus = new JButton("+");
				potionHp_50CountPlus.setBounds(380, 200, 50, 20);
				potionHp_50CountPlus.setBorderPainted(false);
				potionHp_50CountPlus.setBackground(Color.white);
				potionHp_50CountPlus.setOpaque(true);
				buyShopPanel.add(potionHp_50CountPlus);

				potionHp_50CountMinus.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						amountBuyingPotionHp_50--;

						// 값이 0보다 작아지면 더이상 작아질 수 없다.
						if (amountBuyingPotionHp_50 < 0) {
							amountBuyingPotionHp_50 = 0;
						}

						sumBuyingPrice();
						buyingPriceLabel.setText("총 구입금액은 : " + buyingPrice + " 원 입니다.");

						buypotionHp_50AmountLabel.setText(amountBuyingPotionHp_50 + "");
						System.out.println(amountBuyingPotionHp_50);
					}
				});

				potionHp_50CountPlus.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						amountBuyingPotionHp_50++;

						// 값이 10보다 커지면 더이상 커질 수 없다.
						if (amountBuyingPotionHp_50 > 10) {
							amountBuyingPotionHp_50 = 10;
						}

						// 쇼핑한 값을 계산해주는 메소드
						sumBuyingPrice();
						buyingPriceLabel.setText("총 구입금액은 : " + buyingPrice + " 원 입니다.");

						buypotionHp_50AmountLabel.setText(amountBuyingPotionHp_50 + "");
						System.out.println(amountBuyingPotionHp_50);
					}
				});

				JLabel fieldUpgrade = new JLabel();
				fieldUpgrade.setText("- 업그레이드권 -");
				fieldUpgrade.setHorizontalAlignment(JLabel.CENTER);
				fieldUpgrade.setBounds(20, 240, 100, 20);
				fieldUpgrade.setBackground(Color.white);
				fieldUpgrade.setOpaque(true);
				buyShopPanel.add(fieldUpgrade);

				// 밭 업그레이권(1단계) 살 때
				JLabel fieldUpgrade_firstLabel = new JLabel();
				fieldUpgrade_firstLabel.setText(" 1단계 업그레이드 ");
				fieldUpgrade_firstLabel.setHorizontalAlignment(JLabel.CENTER);
				fieldUpgrade_firstLabel.setBounds(20, 260, 100, 20);
				buyShopPanel.add(fieldUpgrade_firstLabel);

				JLabel fieldUpgrade_firstPriceLabel = new JLabel();
				fieldUpgrade_firstPriceLabel.setText("" + fieldUpgrade_firstPrice);
				fieldUpgrade_firstPriceLabel.setHorizontalAlignment(JLabel.CENTER);
				fieldUpgrade_firstPriceLabel.setBounds(120, 260, 100, 20);
				buyShopPanel.add(fieldUpgrade_firstPriceLabel);

				JLabel fieldUpgrade_first = new JLabel();
				fieldUpgrade_first.setText("" + amountFieldUpgrade_first);
				fieldUpgrade_first.setHorizontalAlignment(JLabel.CENTER);
				fieldUpgrade_first.setBounds(220, 260, 100, 20);
				buyShopPanel.add(fieldUpgrade_first);

				JButton FieldUpgrade_firstCountMinus = new JButton("-");
				FieldUpgrade_firstCountMinus.setBounds(300, 260, 50, 20);
				FieldUpgrade_firstCountMinus.setBorderPainted(false);
				FieldUpgrade_firstCountMinus.setBackground(Color.white);
				FieldUpgrade_firstCountMinus.setOpaque(true);
				buyShopPanel.add(FieldUpgrade_firstCountMinus);

				// 1단계 업그레이드권 사려는 수
				final JLabel countFieldUpgrade_firstLabel = new JLabel("" + countFieldUpgrade_first);
				countFieldUpgrade_firstLabel.setHorizontalAlignment(JLabel.CENTER);
				countFieldUpgrade_firstLabel.setBounds(340, 260, 50, 20);
				buyShopPanel.add(countFieldUpgrade_firstLabel);

				JButton FieldUpgrade_firstCountPlus = new JButton("+");
				FieldUpgrade_firstCountPlus.setBounds(380, 260, 50, 20);
				FieldUpgrade_firstCountPlus.setBorderPainted(false);
				FieldUpgrade_firstCountPlus.setBackground(Color.white);
				FieldUpgrade_firstCountPlus.setOpaque(true);
				buyShopPanel.add(FieldUpgrade_firstCountPlus);

				//1단계 업그레이드권을 소유하고 있지 않은 경우에만 살 수 있다.
				if(amountFieldUpgrade_first == 0) {
					
				FieldUpgrade_firstCountMinus.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						countFieldUpgrade_first--;

						// 값이 0보다 작아지면 더이상 작아질 수 없다.
						if (countFieldUpgrade_first < 0) {
							countFieldUpgrade_first = 0;
						}

						sumBuyingPrice();
						buyingPriceLabel.setText("총 구입금액은 : " + buyingPrice + " 원 입니다.");

						countFieldUpgrade_firstLabel.setText(countFieldUpgrade_first + "");
						System.out.println(countFieldUpgrade_first);
					}
				});

				FieldUpgrade_firstCountPlus.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						countFieldUpgrade_first++;

						// 값이 1보다 커지면 더이상 커질 수 없다.
						if (countFieldUpgrade_first > 1) {
							countFieldUpgrade_first = 1;
						}

						// 쇼핑한 값을 계산해주는 메소드
						sumBuyingPrice();
						buyingPriceLabel.setText("총 구입금액은 : " + buyingPrice + " 원 입니다.");

						countFieldUpgrade_firstLabel.setText(countFieldUpgrade_first + "");
						//System.out.println(countFieldUpgrade_first);
					}
				});
				}
				// 밭 업그레이권(2단계) 살 때
				JLabel fieldUpgrade_secondLabel = new JLabel();
				fieldUpgrade_secondLabel.setText(" 2단계 업그레이드 ");
				fieldUpgrade_secondLabel.setHorizontalAlignment(JLabel.CENTER);
				fieldUpgrade_secondLabel.setBounds(20, 280, 100, 20);
				buyShopPanel.add(fieldUpgrade_secondLabel);

				JLabel fieldUpgrade_secondPriceLabel = new JLabel();
				fieldUpgrade_secondPriceLabel.setText("" + fieldUpgrade_secondPrice);
				fieldUpgrade_secondPriceLabel.setHorizontalAlignment(JLabel.CENTER);
				fieldUpgrade_secondPriceLabel.setBounds(120, 280, 100, 20);
				buyShopPanel.add(fieldUpgrade_secondPriceLabel);

				JLabel fieldUpgrade_second = new JLabel();
				fieldUpgrade_second.setText("" + amountFieldUpgrade_second);
				fieldUpgrade_second.setHorizontalAlignment(JLabel.CENTER);
				fieldUpgrade_second.setBounds(220, 280, 100, 20);
				buyShopPanel.add(fieldUpgrade_second);

				JButton FieldUpgrade_secondCountMinus = new JButton("-");
				FieldUpgrade_secondCountMinus.setBounds(300, 280, 50, 20);
				FieldUpgrade_secondCountMinus.setBorderPainted(false);
				FieldUpgrade_secondCountMinus.setBackground(Color.white);
				FieldUpgrade_secondCountMinus.setOpaque(true);
				buyShopPanel.add(FieldUpgrade_secondCountMinus);

				// 2단계 업그레이드권 사려는 수
				final JLabel countFieldUpgrade_secondLabel = new JLabel("" + countFieldUpgrade_second);
				countFieldUpgrade_secondLabel.setHorizontalAlignment(JLabel.CENTER);
				countFieldUpgrade_secondLabel.setBounds(340, 280, 50, 20);
				buyShopPanel.add(countFieldUpgrade_secondLabel);

				JButton FieldUpgrade_secondCountPlus = new JButton("+");
				FieldUpgrade_secondCountPlus.setBounds(380, 280, 50, 20);
				FieldUpgrade_secondCountPlus.setBorderPainted(false);
				FieldUpgrade_secondCountPlus.setBackground(Color.white);
				FieldUpgrade_secondCountPlus.setOpaque(true);
				buyShopPanel.add(FieldUpgrade_secondCountPlus);

				// 1단계 업그레이드권을 가지고 있는 경우에만 살 수있다.
				if (amountFieldUpgrade_first == 1) {
					FieldUpgrade_secondCountMinus.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent ae) {
							countFieldUpgrade_second--;

							// 값이 0보다 작아지면 더이상 작아질 수 없다.
							if (countFieldUpgrade_second < 0) {
								countFieldUpgrade_second = 0;
							}

							sumBuyingPrice();
							buyingPriceLabel.setText("총 구입금액은 : " + buyingPrice + " 원 입니다.");

							countFieldUpgrade_secondLabel.setText(countFieldUpgrade_second + "");
							System.out.println(countFieldUpgrade_second);
						}
					});

					FieldUpgrade_secondCountPlus.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent ae) {
							countFieldUpgrade_second++;

							// 값이 1보다 커지면 더이상 커질 수 없다.
							if (countFieldUpgrade_second > 1) {
								countFieldUpgrade_second = 1;
							}

							// 쇼핑한 값을 계산해주는 메소드
							sumBuyingPrice();
							buyingPriceLabel.setText("총 구입금액은 : " + buyingPrice + " 원 입니다.");

							countFieldUpgrade_secondLabel.setText(countFieldUpgrade_second + "");
							System.out.println(countFieldUpgrade_second);
						}
					});
				}

				// 구입하기 버튼
				JButton buyButton = new JButton("구입하기");
				buyButton.setBounds(150, 370, 150, 20);
				buyButton.setBorderPainted(false);
				buyButton.setOpaque(true);
				buyShopPanel.add(buyButton);

				// 구입하기 버튼을 눌렀을 때
				buyButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						//System.out.println("버튼 누름");
						if (Player.money >= buyingPrice) {
							Player.money = Player.money - buyingPrice;
							Farming.moneyText.setText("돈 : " + Player.money);

							// 구입 갯수만큼 더해짐
							Player.amountPumpkinSeed += amountBuyingPumpkinSeed;
							Player.amountOnionSeed += amountBuyingOnionSeed;
							Player.amountCabbageSeed += amountBuyingCarrotSeed;
							Player.amountCarrotSeed += amountBuyingCabbageSeed;
							Player.amountPotionHp_30 += amountBuyingPotionHp_30;
							Player.amountPotionHp_50 += amountBuyingPotionHp_50;
							amountFieldUpgrade_first += countFieldUpgrade_first;
							amountFieldUpgrade_second += countFieldUpgrade_second;

							System.out.println("구입후 1단계 업그레이드권 갯수 "+amountFieldUpgrade_first);
							// 계산버튼을 누르면 필드 업그레이드권을 제외한 모든 판매갯수, 가격의 값이 0으로 초기화 된다.
							amountBuyingPumpkinSeed = 0;
							amountBuyingOnionSeed = 0;
							amountBuyingCarrotSeed = 0;
							amountBuyingCabbageSeed = 0;
							amountBuyingPotionHp_30 = 0;
							amountBuyingPotionHp_50 = 0;
							sellingPrice = 0;

							buyPumpkinSeedAmountLabel.setText("" + amountBuyingPumpkinSeed);
							buyOnionSeedAmountLabel.setText("" + amountBuyingOnionSeed);
							buyCarrotSeedAmountLabel.setText("" + amountBuyingCarrotSeed);
							buyCabbageSeedAmountLabel.setText("" + amountBuyingCabbageSeed);
							buyCarrotSeedAmountLabel.setText("" + amountBuyingPotionHp_30);
							buyCabbageSeedAmountLabel.setText("" + amountBuyingPotionHp_50);
							System.out.println("셋팅전에 업그레이드권 갯수"+amountFieldUpgrade_first);
							fieldUpgrade_first.setText("" + amountFieldUpgrade_first);
							System.out.println("셋팅후에 업그레이드권 갯수"+amountFieldUpgrade_first);

							// buyingPriceLabel.setText(""+buyingPrice);
							if(amountFieldUpgrade_first == 1) {
								//밭의 단계 - first upgraded farm 
								Farming.fieldPhase = "first upgraded farm";
								for(int i = 6; i < 12; i++) {
									Farming.fieldImages[i].setEnabled(true);
								}
							}
							if(amountFieldUpgrade_second == 1) {
								//밭의 단계 - second upgraded farm 
								Farming.fieldPhase = "second upgraded farm";
								for(int i = 0; i < 6; i++) {
									Farming.fieldImages[i].setEnabled(true);
								}
							}
							dispose();
						} else {
							dispose();
							JOptionPane.showMessageDialog(null, "돈이 부족합니다.", "SYSTEM", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
			}

			void sumBuyingPrice() {
				buyingPrice = amountBuyingPumpkinSeed * pumpkinSeedPrice + amountBuyingOnionSeed * onionSeedPrice
						+ amountBuyingCarrotSeed * carrotSeedPrice + amountBuyingCabbageSeed * cabbageSeedPrice
						+ amountBuyingPotionHp_30 * potionHp_30Price + amountBuyingPotionHp_50 * potionHp_50Price
						+ countFieldUpgrade_first * fieldUpgrade_firstPrice
						+ countFieldUpgrade_second * fieldUpgrade_secondPrice;
			}
		});
		buyButton.setBounds(40, 50, 100, 50);
		shopPanel.add(buyButton);

		/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ판매하기ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */

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
				sellingPriceLabel.setText("총 판매금액은 : " + sellingPrice + " 원 입니다.");
				sellingPriceLabel.setHorizontalAlignment(JLabel.CENTER);
				sellingPriceLabel.setBounds(80, 180, 300, 20);
				sellShopPanel.add(sellingPriceLabel);

				// 호박 팔 때
				JLabel pumpkinLabel = new JLabel();
				pumpkinLabel.setText(" 호박 ");
				pumpkinLabel.setHorizontalAlignment(JLabel.CENTER);
				pumpkinLabel.setBounds(20, 40, 100, 20);
				sellShopPanel.add(pumpkinLabel);

				JLabel pumpkinPriceLabel = new JLabel();
				pumpkinPriceLabel.setText("" + pumpkinPrice);
				pumpkinPriceLabel.setHorizontalAlignment(JLabel.CENTER);
				pumpkinPriceLabel.setBounds(120, 40, 100, 20);
				sellShopPanel.add(pumpkinPriceLabel);

				JLabel pumpkinAmountLabel = new JLabel();
				pumpkinAmountLabel.setText("" + Player.amountPumpkin);
				pumpkinAmountLabel.setHorizontalAlignment(JLabel.CENTER);
				pumpkinAmountLabel.setBounds(220, 40, 100, 20);
				sellShopPanel.add(pumpkinAmountLabel);

				JButton pumpkinCountMinus = new JButton("-");
				pumpkinCountMinus.setBounds(300, 40, 50, 20);
				pumpkinCountMinus.setBorderPainted(false);
				pumpkinCountMinus.setBackground(Color.white);
				pumpkinCountMinus.setOpaque(true);
				sellShopPanel.add(pumpkinCountMinus);

				// 호박 파려는 수
				final JLabel sellPumpkinAmountLabel = new JLabel("" + amountSellingPumpkin);
				sellPumpkinAmountLabel.setHorizontalAlignment(JLabel.CENTER);
				sellPumpkinAmountLabel.setBounds(340, 40, 50, 20);
				sellShopPanel.add(sellPumpkinAmountLabel);

				JButton pumpkinCountPlus = new JButton("+");
				pumpkinCountPlus.setBounds(380, 40, 50, 20);
				pumpkinCountPlus.setBorderPainted(false);
				pumpkinCountPlus.setBackground(Color.white);
				pumpkinCountPlus.setOpaque(true);
				sellShopPanel.add(pumpkinCountPlus);

				pumpkinCountMinus.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						amountSellingPumpkin--;

						// 값이 0보다 작아지면 더이상 작아질 수 없다.
						if (amountSellingPumpkin < 0) {
							amountSellingPumpkin = 0;
						}

						sumSellingPrice();
						sellingPriceLabel.setText("총 판매금액은 : " + sellingPrice + " 원 입니다.");

						sellPumpkinAmountLabel.setText(amountSellingPumpkin + "");
						System.out.println(amountSellingPumpkin);
					}
				});

				pumpkinCountPlus.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						amountSellingPumpkin++;

						// 값이 소유하고 있는 값보다 커지면 더이상 커질 수 없다.
						if (amountSellingPumpkin > Player.amountPumpkin) {
							amountSellingPumpkin = Player.amountPumpkin;
						}

						sumSellingPrice();
						sellingPriceLabel.setText("총 판매금액은 : " + sellingPrice + " 원 입니다.");

						sellPumpkinAmountLabel.setText(amountSellingPumpkin + "");
						System.out.println(amountSellingPumpkin);
					}
				});

				// 양파 팔 때
				JLabel onionLabel = new JLabel();
				onionLabel.setText(" 양파 ");
				onionLabel.setHorizontalAlignment(JLabel.CENTER);
				onionLabel.setBounds(20, 70, 100, 20);
				sellShopPanel.add(onionLabel);

				JLabel onionPriceLabel = new JLabel();
				onionPriceLabel.setText("" + onionPrice);
				onionPriceLabel.setHorizontalAlignment(JLabel.CENTER);
				onionPriceLabel.setBounds(120, 70, 100, 20);
				sellShopPanel.add(onionPriceLabel);

				JLabel onionAmountLabel = new JLabel();
				onionAmountLabel.setText("" + Player.amountOnion);
				onionAmountLabel.setHorizontalAlignment(JLabel.CENTER);
				onionAmountLabel.setBounds(220, 70, 100, 20);
				sellShopPanel.add(onionAmountLabel);

				JButton onionCountMinus = new JButton("-");
				onionCountMinus.setBounds(300, 70, 50, 20);
				onionCountMinus.setBorderPainted(false);
				onionCountMinus.setBackground(Color.white);
				onionCountMinus.setOpaque(true);
				sellShopPanel.add(onionCountMinus);

				// 양파 파려는 수
				final JLabel sellOnionAmountLabel = new JLabel("" + amountSellingOnion);
				sellOnionAmountLabel.setHorizontalAlignment(JLabel.CENTER);
				sellOnionAmountLabel.setBounds(340, 70, 50, 20);
				sellShopPanel.add(sellOnionAmountLabel);

				JButton onionCountPlus = new JButton("+");
				onionCountPlus.setBounds(380, 70, 50, 20);
				onionCountPlus.setBorderPainted(false);
				onionCountPlus.setBackground(Color.white);
				onionCountPlus.setOpaque(true);
				sellShopPanel.add(onionCountPlus);

				onionCountMinus.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						amountSellingOnion--;

						// 값이 0보다 작아지면 더이상 작아질 수 없다.
						if (amountSellingOnion < 0) {
							amountSellingOnion = 0;
						}

						sumSellingPrice();
						sellingPriceLabel.setText("총 판매금액은 : " + sellingPrice + " 원 입니다.");

						sellOnionAmountLabel.setText(amountSellingOnion + "");
						System.out.println(amountSellingOnion);
					}
				});

				onionCountPlus.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						amountSellingOnion++;

						// 값이 소유하고 있는 값보다 커지면 더이상 커질 수 없다.
						if (amountSellingOnion > Player.amountOnion) {
							amountSellingOnion = Player.amountOnion;
						}

						sumSellingPrice();
						sellingPriceLabel.setText("총 판매금액은 : " + sellingPrice + " 원 입니다.");

						sellOnionAmountLabel.setText(amountSellingOnion + "");
						System.out.println(amountSellingOnion);
					}
				});

				// 양배추 팔 때
				JLabel cabbageLabel = new JLabel();
				cabbageLabel.setText(" 양배추 ");
				cabbageLabel.setHorizontalAlignment(JLabel.CENTER);
				cabbageLabel.setBounds(20, 100, 100, 20);
				sellShopPanel.add(cabbageLabel);

				JLabel cabbagePriceLabel = new JLabel();
				cabbagePriceLabel.setText("" + cabbagePrice);
				cabbagePriceLabel.setHorizontalAlignment(JLabel.CENTER);
				cabbagePriceLabel.setBounds(120, 100, 100, 20);
				sellShopPanel.add(cabbagePriceLabel);

				JLabel cabbageAmountLabel = new JLabel();
				cabbageAmountLabel.setText("" + Player.amountCabbage);
				cabbageAmountLabel.setHorizontalAlignment(JLabel.CENTER);
				cabbageAmountLabel.setBounds(220, 100, 100, 20);
				sellShopPanel.add(cabbageAmountLabel);

				JButton cabbageCountMinus = new JButton("-");
				cabbageCountMinus.setBounds(300, 100, 50, 20);
				cabbageCountMinus.setBorderPainted(false);
				cabbageCountMinus.setBackground(Color.white);
				cabbageCountMinus.setOpaque(true);
				sellShopPanel.add(cabbageCountMinus);

				// 양배추 파려는 수
				final JLabel sellCabbageAmountLabel = new JLabel("" + amountSellingCabbage);
				sellCabbageAmountLabel.setHorizontalAlignment(JLabel.CENTER);
				sellCabbageAmountLabel.setBounds(340, 100, 50, 20);
				sellShopPanel.add(sellCabbageAmountLabel);

				JButton cabbageCountPlus = new JButton("+");
				cabbageCountPlus.setBounds(380, 100, 50, 20);
				cabbageCountPlus.setBorderPainted(false);
				cabbageCountPlus.setBackground(Color.white);
				cabbageCountPlus.setOpaque(true);
				sellShopPanel.add(cabbageCountPlus);

				cabbageCountMinus.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						amountSellingCabbage--;

						// 값이 0보다 작아지면 더이상 작아질 수 없다.
						if (amountSellingCabbage < 0) {
							amountSellingCabbage = 0;
						}

						sumSellingPrice();
						sellingPriceLabel.setText("총 판매금액은 : " + sellingPrice + " 원 입니다.");

						sellCabbageAmountLabel.setText(amountSellingCabbage + "");
						System.out.println(amountSellingCabbage);
					}
				});

				cabbageCountPlus.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						amountSellingCabbage++;

						// 값이 소유하고 있는 값보다 커지면 더이상 커질 수 없다.
						if (amountSellingCabbage > Player.amountCabbage) {
							amountSellingCabbage = Player.amountCabbage;
						}

						sumSellingPrice();
						sellingPriceLabel.setText("총 판매금액은 : " + sellingPrice + " 원 입니다.");

						sellCabbageAmountLabel.setText(amountSellingCabbage + "");
						System.out.println(amountSellingCabbage);
					}
				});

				// 당근 팔 때
				JLabel carrotLabel = new JLabel();
				carrotLabel.setText(" 당근 ");
				carrotLabel.setHorizontalAlignment(JLabel.CENTER);
				carrotLabel.setBounds(20, 130, 100, 20);
				sellShopPanel.add(carrotLabel);

				JLabel carrotPriceLabel = new JLabel();
				carrotPriceLabel.setText("" + carrotPrice);
				carrotPriceLabel.setHorizontalAlignment(JLabel.CENTER);
				carrotPriceLabel.setBounds(120, 130, 100, 20);
				sellShopPanel.add(carrotPriceLabel);

				JLabel carrotAmountLabel = new JLabel();
				carrotAmountLabel.setText("" + Player.amountCarrot);
				carrotAmountLabel.setHorizontalAlignment(JLabel.CENTER);
				carrotAmountLabel.setBounds(220, 130, 100, 20);
				sellShopPanel.add(carrotAmountLabel);

				JButton carrotCountMinus = new JButton("-");
				carrotCountMinus.setBounds(300, 130, 50, 20);
				carrotCountMinus.setBorderPainted(false);
				carrotCountMinus.setBackground(Color.white);
				carrotCountMinus.setOpaque(true);
				sellShopPanel.add(carrotCountMinus);

				// 당근 파려는 수
				final JLabel sellCarrotAmountLabel = new JLabel("" + amountSellingCarrot);
				sellCarrotAmountLabel.setHorizontalAlignment(JLabel.CENTER);
				sellCarrotAmountLabel.setBounds(340, 130, 50, 20);
				sellShopPanel.add(sellCarrotAmountLabel);

				JButton carrotCountPlus = new JButton("+");
				carrotCountPlus.setBounds(380, 130, 50, 20);
				carrotCountPlus.setBorderPainted(false);
				carrotCountPlus.setBackground(Color.white);
				carrotCountPlus.setOpaque(true);
				sellShopPanel.add(carrotCountPlus);

				carrotCountMinus.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						amountSellingCarrot--;

						// 값이 0보다 작아지면 더이상 작아질 수 없다.
						if (amountSellingCarrot < 0) {
							amountSellingCarrot = 0;
						}

						sumSellingPrice();
						sellingPriceLabel.setText("총 판매금액은 : " + sellingPrice + " 원 입니다.");

						sellCarrotAmountLabel.setText(amountSellingCarrot + "");
						System.out.println(amountSellingCarrot);
					}
				});

				carrotCountPlus.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						amountSellingCarrot++;

						// 값이 소유하고 있는 값보다 커지면 더이상 커질 수 없다.
						if (amountSellingCarrot > Player.amountCarrot) {
							amountSellingCarrot = Player.amountCarrot;
						}

						sumSellingPrice();
						sellingPriceLabel.setText("총 판매금액은 : " + sellingPrice + " 원 입니다.");

						sellCarrotAmountLabel.setText(amountSellingCarrot + "");
						System.out.println(amountSellingCarrot);
					}
				});

				// 판매하기 버튼
				JButton sellButton = new JButton("판매하기");
				sellButton.setBounds(150, 220, 150, 20);
				sellButton.setBorderPainted(false);
				sellButton.setOpaque(true);
				sellShopPanel.add(sellButton);

				// 판매하기 버튼을 눌렀을 때
				sellButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {

						Player.money = Player.money + sellingPrice;
						Farming.moneyText.setText("돈 : " + Player.money);

						// 각각의 농작물 값에서 판매갯수만큼 빠짐
						Player.amountPumpkin -= amountSellingPumpkin;
						Player.amountOnion -= amountSellingOnion;
						Player.amountCabbage -= amountSellingCarrot;
						Player.amountCarrot -= amountSellingCabbage;

						// 계산버튼을 누르면 모든 판매갯수, 가격의 값이 0으로 초기화 된다.
						amountSellingPumpkin = 0;
						amountSellingOnion = 0;
						amountSellingCarrot = 0;
						amountSellingCabbage = 0;
						sellingPrice = 0;

						sellPumpkinAmountLabel.setText("" + amountSellingPumpkin);
						sellOnionAmountLabel.setText("" + amountSellingOnion);
						sellCarrotAmountLabel.setText("" + amountSellingCarrot);
						sellCabbageAmountLabel.setText("" + amountSellingCabbage);
						sellingPriceLabel.setText("" + sellingPrice);

						dispose();
					}
				});
			}

			void sumSellingPrice() {
				sellingPrice = amountSellingPumpkin * pumpkinPrice + amountSellingOnion * onionPrice
						+ amountSellingCarrot * carrotPrice + amountSellingCabbage * cabbagePrice;
			}

		});
		sellButton.setBounds(40, 130, 100, 50);
		shopPanel.add(sellButton);
	}
}
