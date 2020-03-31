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
	static int randomItem;	//�������� ���� �� �ִ� �������� ��ȣ
	static int numOfRun;	//����ĥ �� �ִ� ���� ��ȣ

	static Random randomMushroomSelect = new Random();
	static Random randomItemSelect = new Random();
	static Random randomRun = new Random();

	// ���Һ����� �����ϴ� �����Լ�
	public static void randomMushroomSelect() {
		randomMushroom = randomMushroomSelect.nextInt(2);
	}

	// ���� ������ �����ϴ� �����Լ�
	public static void randomItemSelect() {
		randomItem = randomItemSelect.nextInt(3);
	}

	// ������ �� �ִ� ��ȣ�� �����ִ� �����Լ�
	public static void randomRun() {
		numOfRun = randomRun.nextInt(5);
	}

	public DungeonWildWolf() {
		//System.out.println("������ " + Main.day + "���Դϴ�. �÷��̾� ü���� " + Main.energy + "�Դϴ�.");
		//System.out.println("���� ü��"+wildBearHp);
		setLayout(null);
		setBounds(0, 0, 800, 600);
		setBackground(Color.black);
		
		WildAnimal wildWolf = new WildAnimal("�߻�����", 100, 10, true);
		
		
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
		textArea.setFont((new Font("����ü", Font.BOLD, 15)));
		
		JPanel itemPanel = new JPanel();
		itemPanel.setBounds(273, 397, 252, 58);
		itemPanel.setLayout(null);
		itemPanel.setVisible(false);
		add(itemPanel);
		
		// �߻����� �ڵ� ���� ������
		new Thread(new Runnable() {
			public void run() {
				try {
					//boolean isStop = true;
					do {
						Thread.sleep(1000);
						wildBearAttack();
						//System.out.println("������");
					} while (Player2.hp > 0 && wildWolf.hp > 0);
				} catch (Exception ie) {
					ie.printStackTrace();
				}
			}
		private void wildBearAttack() {
			{
				if (wildWolf.hp > 0) {
					Player2.hp = Player2.hp - wildWolf.power;
					textArea.append("�߻����밡 �����Ͽ����ϴ�! (�÷��̾��� ���� ��: " + Player2.hp + ")\n");
					scrollBar.getVerticalScrollBar().setValue(scrollBar.getVerticalScrollBar().getMaximum());

					// ���ӿ� ���� �������� �ٲ��, �÷��̾��� ü���� 50���� �����Ѵ�.
					if (Player2.hp <= 0) {
						Main.day ++;
						Main.energy = 50;
						//System.out.println("������ " + Main.day + "���Դϴ�. �÷��̾� ü���� " + Main.energy + "�Դϴ�.");
						
							textArea.selectAll();
							textArea.replaceSelection("");
							setVisible(false);
							//interrupt();
							Main.btnNewButton.setVisible(true);
							JOptionPane.showMessageDialog(null, "�߻��������� ���ϰ� ���ҽ��ϴ�.", "SYSTEM", JOptionPane.INFORMATION_MESSAGE);

					}
				}
			}
		}
		}).start();

		attackButton = new JButton("�����ϱ�");
		attackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (Player2.hp > 0) {

					if (wildWolf.hp > 0) {
						Player2.attackPower = Player2.randomPower.nextInt(11) + 10;
						textArea.append("�÷��̾�� �߻����뿡�� " + Player2.attackPower + " �� �������� �������ϴ�! ");
						wildWolf.hp = wildWolf.hp - Player2.attackPower;

						if (wildWolf.hp > 0) {
							textArea.append("(���� �߻������� ü���� " + wildWolf.hp + " �Դϴ�!) \n");
						} else if (wildWolf.hp <= 0) {
							textArea.append("�߻� ���밡 �׾����ϴ�! \n");

							randomItemSelect();

							//�İ��� ����
							if (randomItem == 0) {
								//textArea.append("SYSTEM: �İ��縦 �޾ҽ��ϴ�! \n");
								Player2.numOfBone++;
								textArea.selectAll();
								textArea.replaceSelection("");
								setVisible(false);
								
								Main.btnNewButton.setVisible(true);
								JOptionPane.showMessageDialog(null, "�İ��縦 �޾ҽ��ϴ�", "SYSTEM", JOptionPane.INFORMATION_MESSAGE);

								//System.out.println("�İ��� ���� �� ����: " +numOfBone);
							} 
							//���Һ� ���� ����
							else if (randomItem == 1) {
								//textArea.append("SYSTEM: ���Һ� ������ �޾ҽ��ϴ�! \n");
								Player2.numOfRandomMushroom++;
								textArea.selectAll();
								textArea.replaceSelection("");
								setVisible(false);
								
								//System.out.println("���Һ����� ���� �� ����: " +Player2.numOfRandomMushroom);
								JOptionPane.showMessageDialog(null, "���Һ������� �޾ҽ��ϴ�", "SYSTEM", JOptionPane.INFORMATION_MESSAGE);
								Main.btnNewButton.setVisible(true);
							} 
							//������
							else if (randomItem == 2) {
								//textArea.append("SYSTEM: ���� �޾ҽ��ϴ�! \n");
								Player2.money = Player2.money + 500;
								System.out.println("�� ���� �� ���� �Ӵ�: " +Player2.money);
								
								textArea.selectAll();
								textArea.replaceSelection("");
								setVisible(false);
								
								Main.btnNewButton.setVisible(true);
								JOptionPane.showMessageDialog(null, "���� �޾ҽ��ϴ�", "SYSTEM", JOptionPane.INFORMATION_MESSAGE);
							}
						}

					}

				}
			}
		});
		attackButton.setBounds(68, 465, 168, 47);
		add(attackButton);
		
		useItemButton = new JButton("������ ���");
		useItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				itemPanel.setVisible(true);
			}
		});
		
		//JButton useRandomMushroomButton = new JButton(new ImageIcon("img/btLogin.png"));
		JButton useRandomMushroomButton = new JButton("����");
		useRandomMushroomButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("���Һ����� ��� �� ����: " + Player2.numOfRandomMushroom);

				if (Player2.hp > 0) {
					if (wildWolf.hp > 0) {
						if (Player2.numOfRandomMushroom > 0) {
							Player2.numOfRandomMushroom--;
							// System.out.println("���Һ����� ��� �� ���� ����: " +numOfRandomMushroom);
							randomMushroomSelect();
							// System.out.println("���Һ����� ���ù�ȣ: "randomMushroom);

							if (randomMushroom == 0) {
								Player2.hp = Player2.hp + 100;
								if (Player2.hp > 100) {
									Player2.hp = 100;
								}
								textArea.append("���Һ������� ȿ���� �÷��̾��� �� ����! ü��:" + Player2.hp + "\n");
								itemPanel.setVisible(false);
							}

							if (randomMushroom == 1) {
								Player2.hp = Player2.hp - 100;
								textArea.append("���Һ������� ȿ���� �÷��̾��� �� ����! ü��: " + Player2.hp + "\n");
								
								if (Player2.hp <= 0) {
									Player2.hp = 0;
								
									Main.day ++;
									Main.energy = 50;
									textArea.selectAll();
									textArea.replaceSelection("");
									setVisible(false);
									//interrupt();
									Main.btnNewButton.setVisible(true);
									JOptionPane.showMessageDialog(null, "���Һ������� ȿ���� �װ��Ҿ��.", "SYSTEM", JOptionPane.INFORMATION_MESSAGE);

									//}catch(Exception ie) {
									//	ie.printStackTrace();
									//}
								}		
							}
						} else {
							textArea.append("���Һ������� ��� ����� �� �����ϴ�.\n");
						}
					}
				}
			}
		});
		//btnNewButton.setFont(new Font("����ü", Font.PLAIN, 7));
		useRandomMushroomButton.setBounds(12, 10, 68, 37);
		itemPanel.add(useRandomMushroomButton);
		
		JButton usePotionHpOf30_Button = new JButton("ü�� 30% ����");
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
							textArea.append("ü�� 30% �߰� ������ ����߽��ϴ�!. ü�� UP+30! \n");
							itemPanel.setVisible(false);
						}else {
							textArea.append("ü�� 30% �߰� ������ ��� ����� �� �����ϴ�. \n");
							itemPanel.setVisible(false);
						}
					}
				}
			}
		});
		usePotionHpOf30_Button.setBounds(92, 10, 68, 37);
		itemPanel.add(usePotionHpOf30_Button);
		
		JButton usePotionHpOf50_Button = new JButton("ü�� 50% ����");
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
							textArea.append("ü�� 50% �߰� ������ ����߽��ϴ�!. ü�� UP+50! \n");
							itemPanel.setVisible(false);
						}else {
							textArea.append("ü�� 50% �߰� ������ ��� ����� �� �����ϴ�. \n");
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

		runAwayButton = new JButton("����ġ��");
		runAwayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (Player2.hp > 0) {
					if (wildWolf.hp > 0) {
						randomRun();

						if (numOfRun == 0) {
							//wildBear.hp = wildBear.hp-wildBear.hp ;
							wildWolf.hp = 0;						
							System.out.println("�������� ���� ü��: "+wildWolf.hp);
							textArea.selectAll();
							textArea.replaceSelection("");
							setVisible(false);
							Main.btnNewButton.setVisible(true);
							JOptionPane.showMessageDialog(null, "�����ƽ��ϴ�!", "SYSTEM", JOptionPane.INFORMATION_MESSAGE);
							
							//Player2.hp  = 100;
							//wildBear.hp = 100;
						} else {
							textArea.append("�������⿡ �����Ͽ����ϴ�! \n");
						}
					}
				}
			}
		});
		
		runAwayButton.setBounds(562, 465, 168, 47);
		add(runAwayButton);
		
	}
}
