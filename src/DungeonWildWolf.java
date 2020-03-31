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
	private JPanel contentPane;
	private JButton dungeonEx;
	private JButton useItemButton;
	private JButton runAwayButton;
	private JButton attackButton;

	static int randomMushroom;	
	static int randomItem;	//랜덤으로 받을 수 있는 아이템의 번호
	static int numOfRun;	//도망칠 수 있는 랜덤 번호

	static Random randomMushroomSelect = new Random();
	static Random randomItemSelect = new Random();
	static Random randomRun = new Random();

	// 복불복버섯 선택하는 랜덤함수
	public static void randomMushroomSelect() {
		randomMushroom = randomMushroomSelect.nextInt(2);
	}

	// 랜덤 아이템 선택하는 랜덤함수
	public static void randomItemSelect() {
		randomItem = randomItemSelect.nextInt(3);
	}

	// 도망갈 수 있는 번호를 정해주는 랜덤함수
	public static void randomRun() {
		numOfRun = randomRun.nextInt(5);
	}

	public DungeonWildWolf() {
		//System.out.println("오늘은 " + Main.day + "일입니다. 플레이어 체력은 " + Main.energy + "입니다.");
		//System.out.println("늑대 체력"+wildBearHp);
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
					//boolean isStop = true;
					do {
						Thread.sleep(1000);
						wildBearAttack();
						//System.out.println("공격중");
					} while (Player2.hp > 0 && wildWolf.hp > 0);
				} catch (Exception ie) {
					ie.printStackTrace();
				}
			}
		private void wildBearAttack() {
			{
				if (wildWolf.hp > 0) {
					Player2.hp = Player2.hp - wildWolf.power;
					textArea.append("야생늑대가 공격하였습니다! (플레이어의 남은 피: " + Player2.hp + ")\n");
					scrollBar.getVerticalScrollBar().setValue(scrollBar.getVerticalScrollBar().getMaximum());

					// 게임에 지면 다음날로 바뀌고, 플레이어의 체력이 50으로 시작한다.
					if (Player2.hp <= 0) {
						Main.day ++;
						Main.energy = 50;
						//System.out.println("오늘은 " + Main.day + "일입니다. 플레이어 체력은 " + Main.energy + "입니다.");
						
							textArea.selectAll();
							textArea.replaceSelection("");
							setVisible(false);
							//interrupt();
							Main.btnNewButton.setVisible(true);
							JOptionPane.showMessageDialog(null, "야생늑대한테 당하고 말았습니다.", "SYSTEM", JOptionPane.INFORMATION_MESSAGE);

					}
				}
			}
		}
		}).start();

		attackButton = new JButton("공격하기");
		attackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (Player2.hp > 0) {

					if (wildWolf.hp > 0) {
						Player2.attackPower = Player2.randomPower.nextInt(11) + 10;
						textArea.append("플레이어는 야생늑대에게 " + Player2.attackPower + " 의 데미지를 입혔습니다! ");
						wildWolf.hp = wildWolf.hp - Player2.attackPower;

						if (wildWolf.hp > 0) {
							textArea.append("(남은 야생늑대의 체력은 " + wildWolf.hp + " 입니다!) \n");
						} else if (wildWolf.hp <= 0) {
							textArea.append("야생 늑대가 죽었습니다! \n");

							randomItemSelect();

							//뼛가루 받음
							if (randomItem == 0) {
								//textArea.append("SYSTEM: 뼛가루를 받았습니다! \n");
								Player2.numOfBone++;
								textArea.selectAll();
								textArea.replaceSelection("");
								setVisible(false);
								
								Main.btnNewButton.setVisible(true);
								JOptionPane.showMessageDialog(null, "뼛가루를 받았습니다", "SYSTEM", JOptionPane.INFORMATION_MESSAGE);

								//System.out.println("뼛가루 받은 후 갯수: " +numOfBone);
							} 
							//복불복 버섯 받음
							else if (randomItem == 1) {
								//textArea.append("SYSTEM: 복불복 버섯을 받았습니다! \n");
								Player2.numOfRandomMushroom++;
								textArea.selectAll();
								textArea.replaceSelection("");
								setVisible(false);
								
								//System.out.println("복불복버섯 받은 후 갯수: " +Player2.numOfRandomMushroom);
								JOptionPane.showMessageDialog(null, "복불복버섯을 받았습니다", "SYSTEM", JOptionPane.INFORMATION_MESSAGE);
								Main.btnNewButton.setVisible(true);
							} 
							//돈받음
							else if (randomItem == 2) {
								//textArea.append("SYSTEM: 돈을 받았습니다! \n");
								Player2.money = Player2.money + 500;
								System.out.println("돈 받은 후 소유 머니: " +Player2.money);
								
								textArea.selectAll();
								textArea.replaceSelection("");
								setVisible(false);
								
								Main.btnNewButton.setVisible(true);
								JOptionPane.showMessageDialog(null, "돈을 받았습니다", "SYSTEM", JOptionPane.INFORMATION_MESSAGE);
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
		
		//JButton useRandomMushroomButton = new JButton(new ImageIcon("img/btLogin.png"));
		JButton useRandomMushroomButton = new JButton("버섯");
		useRandomMushroomButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("복불복버섯 사용 전 갯수: " + Player2.numOfRandomMushroom);

				if (Player2.hp > 0) {
					if (wildWolf.hp > 0) {
						if (Player2.numOfRandomMushroom > 0) {
							Player2.numOfRandomMushroom--;
							// System.out.println("복불복버섯 사용 후 남은 갯수: " +numOfRandomMushroom);
							randomMushroomSelect();
							// System.out.println("복불복버섯 선택번호: "randomMushroom);

							if (randomMushroom == 0) {
								Player2.hp = Player2.hp + 100;
								if (Player2.hp > 100) {
									Player2.hp = 100;
								}
								textArea.append("복불복버섯의 효과로 플레이어의 피 증가! 체력:" + Player2.hp + "\n");
								itemPanel.setVisible(false);
							}

							if (randomMushroom == 1) {
								Player2.hp = Player2.hp - 100;
								textArea.append("복불복버섯의 효과로 플레이어의 피 감소! 체력: " + Player2.hp + "\n");
								
								if (Player2.hp <= 0) {
									Player2.hp = 0;
								
									Main.day ++;
									Main.energy = 50;
									textArea.selectAll();
									textArea.replaceSelection("");
									setVisible(false);
									//interrupt();
									Main.btnNewButton.setVisible(true);
									JOptionPane.showMessageDialog(null, "복불복버섯의 효과로 죽고말았어요.", "SYSTEM", JOptionPane.INFORMATION_MESSAGE);

									//}catch(Exception ie) {
									//	ie.printStackTrace();
									//}
								}		
							}
						} else {
							textArea.append("복불복버섯이 없어서 사용할 수 없습니다.\n");
						}
					}
				}
			}
		});
		//btnNewButton.setFont(new Font("굴림체", Font.PLAIN, 7));
		useRandomMushroomButton.setBounds(12, 10, 68, 37);
		itemPanel.add(useRandomMushroomButton);
		
		JButton usePotionHpOf30_Button = new JButton("체력 30% 포션");
		usePotionHpOf30_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Player2.hp > 0) {
					if (wildWolf.hp > 0) {
						if(Player2.numOfPotionHp_30 >0) {
							Player2.numOfPotionHp_30--;
							Player2.hp = Player2.hp +30;
							if(Player2.hp > 100) {
								Player2.hp = 100;
							}
							textArea.append("체력 30% 추가 포션을 사용했습니다!. 체력 UP+30! \n");
							itemPanel.setVisible(false);
						}else {
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
				if (Player2.hp > 0) {
					if (wildWolf.hp > 0) {
						if(Player2.numOfPotionHp_50 >0) {
							Player2.numOfPotionHp_50--;
							Player2.hp = Player2.hp +50;
							if(Player2.hp > 100) {
								Player2.hp = 100;
							}
							textArea.append("체력 50% 추가 포션을 사용했습니다!. 체력 UP+50! \n");
							itemPanel.setVisible(false);
						}else {
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

				if (Player2.hp > 0) {
					if (wildWolf.hp > 0) {
						randomRun();

						if (numOfRun == 0) {
							//wildBear.hp = wildBear.hp-wildBear.hp ;
							wildWolf.hp = 0;						
							System.out.println("도망갈때 늑대 체력: "+wildWolf.hp);
							textArea.selectAll();
							textArea.replaceSelection("");
							setVisible(false);
							Main.btnNewButton.setVisible(true);
							JOptionPane.showMessageDialog(null, "도망쳤습니다!", "SYSTEM", JOptionPane.INFORMATION_MESSAGE);
							
							//Player2.hp  = 100;
							//wildBear.hp = 100;
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
