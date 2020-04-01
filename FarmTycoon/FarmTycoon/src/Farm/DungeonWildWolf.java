package Farm;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class DungeonWildWolf extends JPanel {

	public static Object textArea;
	// private JPanel contentPane;
	private JButton dungeonEx;
	private JButton useItemButton;
	private JButton runAwayButton;
	private JButton attackButton;

	static int randomMushroom;
	static int randomItem; // 랜덤으로 받을 수 있는 아이템의 번호
	static int numOfRun; // 도망칠 수 있는 랜덤 번호

	static Random randomMushroomSelect = new Random();
	static Random randomItemSelect = new Random();
	static Random randomRun = new Random();

	// 복불복버섯 선택하는 랜덤함수
	public static void randomMushroomSelect() {
		randomMushroom = randomMushroomSelect.nextInt(2);
	}

	// 랜덤 아이템 선택하는 랜덤함수
	public static void randomItemSelect() {
		randomItem = randomItemSelect.nextInt(5);
	}

	// 도망갈 수 있는 번호를 정해주는 랜덤함수
	public static void randomRun() {
		numOfRun = randomRun.nextInt(5);
	}

	public DungeonWildWolf() {
		// System.out.println("오늘은 " + Main.day + "일입니다. 플레이어 체력은 " + Main.energy +
		// "입니다.");
		// System.out.println("늑대 체력"+wildBearHp);
		setLayout(null);
		setBounds(0, 0, 800, 600);
		setBackground(Color.black);

		WildAnimal wildWolf = new WildAnimal("야생늑대", 100, 10, true);

		JLabel wildWolfImage = new JLabel();
		wildWolfImage.setHorizontalAlignment(SwingConstants.CENTER);
		wildWolfImage.setIcon(new ImageIcon("./images/wildWolf.png"));
		wildWolfImage.setBounds(292, 10, 224, 170);
		add(wildWolfImage);

		JScrollPane scrollBar = new JScrollPane();
		scrollBar.setBounds(68, 201, 662, 186);
		scrollBar.getVerticalScrollBar().setValue(scrollBar.getVerticalScrollBar().getMaximum());
		add(scrollBar);

		JTextArea textArea = new JTextArea();
		scrollBar.setViewportView(textArea);
		textArea.setEnabled(true);
		textArea.setFont((new Font("굴림체", Font.BOLD, 15)));

		JPanel itemPanel = new JPanel();
		itemPanel.setBounds(273, 397, 252, 58);
		itemPanel.setLayout(null);
		itemPanel.setVisible(false);
		add(itemPanel);

		// 야생늑대 자동 동격 쓰레드
		new Thread(new Runnable() {
			public void run() {
				try {
					// boolean isStop = true;
					do {
						Thread.sleep(1000);
						wildBearAttack();
						// System.out.println("공격중");
					} while (Player.hp > 0 && wildWolf.hp > 0);
				} catch (Exception ie) {
					ie.printStackTrace();
				}
			}

			private void wildBearAttack() {
				{
					if (wildWolf.hp > 0) {
						Player.hp = Player.hp - wildWolf.power;
						textArea.append("야생늑대가 공격하였습니다! (플레이어의 남은 피: " + Player.hp + ")\n");
						scrollBar.getVerticalScrollBar().setValue(scrollBar.getVerticalScrollBar().getMaximum());

						// 게임에 지면 다음날로 바뀌고, 플레이어의 체력이 50으로 시작한다.
						if (Player.hp <= 0) {
							Farming.day++;
							Player.energy = 50;
							// System.out.println("오늘은 " + Main.day + "일입니다. 플레이어 체력은 " + Main.energy +
							// "입니다.");
							Farming.daysText.setText(Farming.day + "일차");
							Farming.EnergyText.setText("남은 에너지 : " + Player.energy);
							textArea.selectAll();
							textArea.replaceSelection("");
							setVisible(false);
							Farming.farmingScene.setVisible(true);
							JOptionPane.showMessageDialog(null, "야생늑대한테 당하고 말았습니다.", "SYSTEM",
									JOptionPane.INFORMATION_MESSAGE);
							AppearanceOfAnimals appearanceOfAnimals = new AppearanceOfAnimals();
							appearanceOfAnimals.start();
							AppearanceOfAnimals.running = true;

						}
					}
				}
			}
		}).start();

		attackButton = new JButton("공격하기");
		attackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (Player.hp > 0) {

					if (wildWolf.hp > 0) {
						Player.attackPower = Player.randomPower.nextInt(11) + 10;
						textArea.append("플레이어는 야생늑대에게 " + Player.attackPower + " 의 데미지를 입혔습니다! ");
						wildWolf.hp = wildWolf.hp - Player.attackPower;

						if (wildWolf.hp > 0) {
							textArea.append("(남은 야생늑대의 체력은 " + wildWolf.hp + " 입니다!) \n");
						} else if (wildWolf.hp <= 0) {
							textArea.append("야생 늑대가 죽었습니다! \n");

							randomItemSelect();

							// 복불복 버섯 받음
							if (randomItem == 0) {
								// textArea.append("SYSTEM: 복불복 버섯을 받았습니다! \n");
								Player.amountRandomMushroom++;
								textArea.selectAll();
								textArea.replaceSelection("");
								setVisible(false);

								System.out.println("복불복버섯 받은 후 갯수: " + Player.amountRandomMushroom);

								Farming.farmingScene.setVisible(true);
								JOptionPane.showMessageDialog(null, "복불복버섯을 받았습니다", "SYSTEM",
										JOptionPane.INFORMATION_MESSAGE);
								AppearanceOfAnimals appearanceOfAnimals = new AppearanceOfAnimals();
								appearanceOfAnimals.start();
								AppearanceOfAnimals.running = true;
								// AppearanceOfAnimals.running = true;
							}
							// 돈 받음
							else if (randomItem == 1) {
								// textArea.append("SYSTEM: 돈을 받았습니다! \n");
								Player.money = Player.money + 2000;
								textArea.selectAll();
								textArea.replaceSelection("");
								setVisible(false);

								Farming.farmingScene.setVisible(true);
								Farming.moneyText.setText("돈 : " + Player.money);
								// AppearanceOfAnimals.running = true;
								JOptionPane.showMessageDialog(null, "돈을 받았습니다", "SYSTEM",
										JOptionPane.INFORMATION_MESSAGE);
								AppearanceOfAnimals appearanceOfAnimals = new AppearanceOfAnimals();
								appearanceOfAnimals.start();
								AppearanceOfAnimals.running = true;
							}
							// 뼛가루 받음
							else if (randomItem >= 2) {
								// textArea.append("SYSTEM: 뼛가루를 받았습니다! \n");
								Player.amountBone = Player.amountBone + 2;
								textArea.selectAll();
								textArea.replaceSelection("");
								setVisible(false);

								Farming.farmingScene.setVisible(true);
								// AppearanceOfAnimals.running = true;
								JOptionPane.showMessageDialog(null, "뼛가루를 받았습니다", "SYSTEM",
										JOptionPane.INFORMATION_MESSAGE);
								AppearanceOfAnimals appearanceOfAnimals = new AppearanceOfAnimals();
								appearanceOfAnimals.start();
								AppearanceOfAnimals.running = true;
								// System.out.println("뼛가루 받은 후 갯수: " +numOfBone);
							}
						}

					}

				}
			}
		});
		attackButton.setBounds(68, 465, 168, 47);
		add(attackButton);

		useItemButton = new JButton("아이템 사용");
		useItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				itemPanel.setVisible(true);
			}
		});

		// JButton useRandomMushroomButton = new JButton(new
		// ImageIcon("img/btLogin.png"));
		JButton useRandomMushroomButton = new JButton("버섯");
		useRandomMushroomButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				System.out.println("복불복버섯 사용 전 갯수: " + Player.amountRandomMushroom);

				if (Player.hp > 0) {
					if (wildWolf.hp > 0) {
						if (Player.amountRandomMushroom > 0) {
							Player.amountRandomMushroom--;
							// System.out.println("복불복버섯 사용 후 남은 갯수: " +numOfRandomMushroom);
							randomMushroomSelect();
							// System.out.println("복불복버섯 선택번호: "randomMushroom);

							if (randomMushroom == 0) {
								Player.hp = Player.hp + 100;
								if (Player.hp > 100) {
									Player.hp = 100;
								}
								textArea.append("복불복버섯의 효과로 플레이어의 피 증가! 체력:" + Player.hp + "\n");
								itemPanel.setVisible(false);
							}

							if (randomMushroom == 1) {
								Player.hp = Player.hp - 100;
								textArea.append("복불복버섯의 효과로 플레이어의 피 감소! 체력: " + Player.hp + "\n");

								if (Player.hp <= 0) {
									Player.hp = 0;

									Farming.day++;
									Player.energy = 50;
									textArea.selectAll();
									textArea.replaceSelection("");
									setVisible(false);
									Farming.farmingScene.setVisible(true);
									// AppearanceOfAnimals.running = true;
									JOptionPane.showMessageDialog(null, "복불복버섯의 효과로 죽고말았어요.", "SYSTEM",
											JOptionPane.INFORMATION_MESSAGE);
									AppearanceOfAnimals appearanceOfAnimals = new AppearanceOfAnimals();
									appearanceOfAnimals.start();
									AppearanceOfAnimals.running = true;
									// AppearanceOfAnimals.running = true;
									// }catch(Exception ie) {
									// ie.printStackTrace();
									// }
								}
							}
						} else {
							textArea.append("복불복버섯이 없어서 사용할 수 없습니다.\n");
						}
					}
				}
			}
		});
		// btnNewButton.setFont(new Font("굴림체", Font.PLAIN, 7));
		useRandomMushroomButton.setBounds(12, 10, 68, 37);
		itemPanel.add(useRandomMushroomButton);

		JButton usePotionHpOf30_Button = new JButton("체력 30% 포션");
		usePotionHpOf30_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Player.hp > 0) {
					if (wildWolf.hp > 0) {
						if (Player.amountPotionHp_30 > 0) {
							Player.amountPotionHp_30--;
							Player.hp = Player.hp + 30;
							if (Player.hp > 100) {
								Player.hp = 100;
							}
							textArea.append("체력 30% 추가 포션을 사용했습니다!. 체력 UP+30! \n");
							itemPanel.setVisible(false);
						} else {
							textArea.append("체력 30% 추가 포션이 없어서 사용할 수 없습니다. \n");
							itemPanel.setVisible(false);
						}
					}
				}
			}
		});
		usePotionHpOf30_Button.setBounds(92, 10, 68, 37);
		itemPanel.add(usePotionHpOf30_Button);

		JButton usePotionHpOf50_Button = new JButton("체력 50% 포션");
		usePotionHpOf50_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Player.hp > 0) {
					if (wildWolf.hp > 0) {
						if (Player.amountPotionHp_50 > 0) {
							Player.amountPotionHp_50--;
							Player.hp = Player.hp + 50;
							if (Player.hp > 100) {
								Player.hp = 100;
							}
							textArea.append("체력 50% 추가 포션을 사용했습니다!. 체력 UP+50! \n");
							itemPanel.setVisible(false);
						} else {
							textArea.append("체력 50% 추가 포션이 없어서 사용할 수 없습니다. \n");
							itemPanel.setVisible(false);
						}
					}
				}
			}
		});
		usePotionHpOf50_Button.setBounds(172, 10, 68, 37);
		itemPanel.add(usePotionHpOf50_Button);

		useItemButton.setBounds(317, 465, 168, 47);
		add(useItemButton);

		runAwayButton = new JButton("도망치기");
		runAwayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (Player.hp > 0) {
					if (wildWolf.hp > 0) {
						randomRun();

						if (numOfRun == 0) {
							wildWolf.hp = 0;
							System.out.println("도망갈때 늑대 체력: " + wildWolf.hp);
							textArea.selectAll();
							textArea.replaceSelection("");
							setVisible(false);
							Farming.farmingScene.setVisible(true);
							// AppearanceOfAnimals.running = true;
							// System.out.println(AppearanceOfAnimals.running);
							JOptionPane.showMessageDialog(null, "도망쳤습니다!", "SYSTEM", JOptionPane.INFORMATION_MESSAGE);
							AppearanceOfAnimals appearanceOfAnimals = new AppearanceOfAnimals();
							appearanceOfAnimals.start();
							AppearanceOfAnimals.running = true;
							System.out.println(AppearanceOfAnimals.running);

						} else {
							textArea.append("도망가기에 실패하였습니다! \n");
						}
					}
				}
			}
		});

		runAwayButton.setBounds(562, 465, 168, 47);
		add(runAwayButton);

	}
}