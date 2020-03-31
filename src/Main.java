import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class Main extends JFrame {

	private JPanel contentPane;
	static JButton btnNewButton;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	static int day = 1;
	static int energy = 50;
	/**
	 * Create the frame.
	 */
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800,600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				/*JPanel dungeonWildBear = new DungeonWildWolf();
				dungeonWildBear.setVisible(false);
				getContentPane().add(dungeonWildBear);
				Player2.hp = 100;
				//DungeonWildBear.wildBearHp = 110;
				//dungeonWildBear.wildBear.hp = 100;
				//JPanel dungeonWildBear = new DungeonWildBear();
				dungeonWildBear.setVisible(true);
				btnNewButton.setVisible(false);*/
				
				
				/*JPanel dungeonWildWolf = new DungeonWildWolf();
				dungeonWildWolf.setVisible(false);
				getContentPane().add(dungeonWildWolf);
				Player2.hp = 100;
				dungeonWildWolf.setVisible(true);
				btnNewButton.setVisible(false);*/
				
				JPanel dungeonWildSnake = new DungeonWildSnake();
				dungeonWildSnake.setVisible(false);
				getContentPane().add(dungeonWildSnake);
				Player2.hp = 100;
				dungeonWildSnake.setVisible(true);
				btnNewButton.setVisible(false);
				
				//다른 버튼 및 레이블 setVisible로 보여줄지 안보여줄지 설정 하기				
			}
		});
		btnNewButton.setBounds(12, 10, 91, 23);
		add(btnNewButton);
		
		btnNewButton.setVisible(true);

	}
}
