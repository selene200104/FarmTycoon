import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class DungeonWildBear extends JPanel {

	public static Object textArea;
	private JPanel contentPane;
	private JButton dungeonEx;
	private JButton useItemButton;
	private JButton runAwayButton;
	private JButton attackButton;

	int day = 1;
	int energy = 100;

	static int randomMushroom;
	static int randomItem;
	static int numOfRun;
	static int numOfRandomMushroom = 2;
	static int numOfBone = 1;
	static int money;

	static Random randomMushroomSelect = new Random();
	static Random randomItemSelect = new Random();
	static Random randomRun = new Random();

	// 랜덤 버섯 선택하는 랜덤함수
	public static void randomMushroomSelect() {
		randomMushroom = randomMushroomSelect.nextInt(2);
	}

	// 랜덤 아이템 선택하는 랜덤함수
	public static void randomItemSelect() {
		randomItem = randomItemSelect.nextInt(3);
	}

	// 도망을 가기를 랜덤함수
	public static void randomRun() {
		numOfRun = randomRun.nextInt(5);
	}

	public DungeonWildBear() {
		setLayout(null);
		setBounds(0, 0, 800, 600);
		setBackground(Color.black);
		
		WildAnimal wildBear = new WildAnimal("야생곰", 100, 10, true);

		JLabel wildBearImage = new JLabel();
		wildBearImage.setHorizontalAlignment(SwingConstants.CENTER);
		wildBearImage.setIcon(new ImageIcon("./images/wildbear.png"));
		wildBearImage.setBounds(263, -11, 266, 216);
		add(wildBearImage);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(68, 218, 662, 216);
		textArea.setEnabled(true);
		//textArea.setFont((new Font("굴림체", Font.BOLD, 15)));

		JScrollPane scrollBar = new JScrollPane(textArea);
		scrollBar.setBounds(68, 218, 662, 216);
		scrollBar.getVerticalScrollBar().setValue(scrollBar.getVerticalScrollBar().getMaximum());
		add(scrollBar);

		// 야생공 자동 동격 쓰레드
		new Thread(new Runnable() {
			public void run() {
				try {
					do {
						Thread.sleep(3000);
						wildBearAttack();
					} while (wildBear.hp > 0 && Player2.hp > 0);
				} catch (Exception ie) {
					ie.printStackTrace();
				}
			}

			private void wildBearAttack() {
				{
					if (wildBear.hp > 0) {
						Player2.hp = Player2.hp - wildBear.power;
						textArea.append("야생곰이 공격하였습니다! (남은 플레이어 체력: " + Player2.hp + ")\n");
						scrollBar.getVerticalScrollBar().setValue(scrollBar.getVerticalScrollBar().getMaximum());

						// 게임에 지면 다음날로 바뀌고, 플레이어의 체력이 50으로 시작한다.
						if (Player2.hp == 0) {
							day = day + 1;
							energy = 50;
							System.out.println("오늘은 " + day + "일입니다. 플레이어 체력은 " + energy + "입니다.");

							try {
								Thread.sleep(1000);
								Player2.hp = 100;
								wildBear.hp = 100;
								textArea.selectAll();
								textArea.replaceSelection("");
								setVisible(false);
								//Main.dungeonWildBear.setVisible(true);
								// dungeonEx.setVisible(true);
							} catch (Exception ie) {
								ie.printStackTrace();
							}
						}
					}
				}
			}
		}).start();

		attackButton = new JButton("공격하기");
		attackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (Player2.hp > 0) {

					if (wildBear.hp > 0) {
						Player2.attackPower = Player2.randomPower.nextInt(11) + 10;
						textArea.append("플레이어는 야생곰에게 " + Player2.attackPower + " 의 데미지를 입혔습니다! ");
						wildBear.hp = wildBear.hp - Player2.attackPower;

						if (wildBear.hp > 0) {
							textArea.append("(남은 야생곰의 체력은 " + wildBear.hp + " 입니다!) \n");
						} else if (wildBear.hp <= 0) {
							textArea.append("야생 곰이 죽었습니다! \n");

							randomItemSelect();
							// System.out.println("뼛가루 받기 전 갯수: " +numOfBone);
							// System.out.println("랜덤 버섯 받기 전 갯수: " +numOfRandomMushroom);
							// System.out.println("돈 받기 전 소유머니: " +money);
							if (randomItem == 0) {
								textArea.append("SYSTEM: 뼛가루를 받았습니다! \n");
								numOfBone++;

								try {
									// textArea.append("SYSTEM: 뼛가루를 받았습니다! \n");
									Thread.sleep(1000);
									Player2.hp = 100;
									wildBear.hp = 100;
									textArea.selectAll();
									textArea.replaceSelection("");
									setVisible(false);
									// dungeonEx.setVisible(true);
								} catch (Exception ie) {
									ie.printStackTrace();
								}
								Main.btnNewButton.setVisible(true);
								// System.out.println("뼛가루 받은 후 갯수: " +numOfBone);
							} else if (randomItem == 1) {
								textArea.append("SYSTEM: 랜덤버섯을 받았습니다! \n");
								numOfRandomMushroom++;
								// System.out.println("랜덤 버섯 받은 후 갯수: " +numOfRandomMushroom);
								Main.btnNewButton.setVisible(true);
							} else if (randomItem == 2) {
								textArea.append("SYSTEM: 돈을 받았습니다! \n");
								// System.out.println("돈 받은 후 소유 머니: " +money);
								money = money + 1000;
								// System.out.println("돈: " +money);
								/*
								 * try { Thread.sleep(1000); Player.hp = 100; wildBear.hp = 100;
								 * textArea.selectAll(); textArea.replaceSelection("");
								 * dungeonWildBear.setVisible(false); dungeonEx.setVisible(true); }catch
								 * (Exception ie) { ie.printStackTrace(); }
								 */
								Main.btnNewButton.setVisible(true);
							}
						}

					}

				}
			}
		});
		attackButton.setBounds(68, 458, 168, 47);
		add(attackButton);

		useItemButton = new JButton("아이템 사용");
		useItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				System.out.println("랜덤 버섯 사용 전 갯수: " + numOfRandomMushroom);

				if (Player2.hp > 0) {
					if (wildBear.hp > 0) {
						if (numOfRandomMushroom > 0) {
							numOfRandomMushroom--;
							// System.out.println("랜덤 버섯 사용 후 남은 갯수: " +numOfRandomMushroom);
							randomMushroomSelect();
							// System.out.println("랜덤 버섯 선택번호: "randomMushroom);

							if (randomMushroom == 0) {
								Player2.hp = Player2.hp + 20;
								if (Player2.hp > 100) {
									Player2.hp = 100;
								}
								textArea.append("랜덤버섯의 효과로 플레이어의 피 증가! 체력:" + Player2.hp + "\n");
							}

							if (randomMushroom == 1) {
								Player2.hp = Player2.hp - 20;
								if (Player2.hp <= 0) {
									Player2.hp = 0;
								}
								textArea.append("랜덤버섯의 효과로 플레이어의 피 감소! 체력: " + Player2.hp + "\n");
							}
						} else {
							textArea.append("랜덤버섯이 없어서 사용할 수 없습니다.\n");
						}
					}
				}
			}

		});
		useItemButton.setBounds(317, 458, 168, 47);
		add(useItemButton);

		runAwayButton = new JButton("도망치기");
		runAwayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (Player2.hp > 0) {
					if (wildBear.hp > 0) {
						randomRun();

						if (numOfRun == 0) {
							Player2.hp = 100;
							wildBear.hp = 100;
							textArea.selectAll();
							textArea.replaceSelection("");
							setVisible(false);
							Main.btnNewButton.setVisible(true);
							
						} else {
							textArea.append("도망가기에 실패하였습니다! \n");
						}
					}
				}
			}
		});
		runAwayButton.setBounds(562, 458, 168, 47);
		add(runAwayButton);
	}

}
