package GUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Base.Card;
import Base.Deck;
import Base.FortyThieves;
import Base.Golf;
import Base.LittleSpider;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 * @version2
 * the menu and game frame
 * also is the driver
 * 
 * */
public class Menu {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		JMenuBar bar = new JMenuBar();
		Deck deck = new Deck();
		JPanel panel = new JPanel();
		for(Card card : deck.getCards()) {
			panel.add(card.getLabel());
		}
		JMenu menu = new JMenu("New Game");
		frame.setSize(1920, 1080);
		frame.add(panel);
		JMenuItem item = new JMenuItem("Golf");
		JMenuItem item2 = new JMenuItem("Little Spider");
		JMenuItem item3 = new JMenuItem("Exit");
		JMenuItem item4 = new JMenuItem("Forty Thieves");
		
		item.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Golf golf = new Golf(frame);
				golf.display();
				JPanel panel = golf.getPanel();
				frame.getContentPane().removeAll();
				frame.add(panel);
				frame.revalidate();
				frame.repaint();	
			}
			
		});
		
		
		
		item2.addActionListener(new ActionListener() {

			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				LittleSpider spider = new LittleSpider(frame);
				spider.display();
				JPanel panel = spider.getPanel();
				frame.getContentPane().removeAll();
				frame.add(panel);
				frame.revalidate();
				frame.repaint();	
				
			}
			
		});
		
		item3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.exit(0);
				
			}
			
		});
		
		item4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				FortyThieves thieves = new FortyThieves(frame);
				
				JPanel panel = thieves.display();
				frame.getContentPane().removeAll();
				frame.add(panel);
				frame.revalidate();
				frame.repaint();
			}
			
		});

		menu.add(item);
		menu.add(item2);
		menu.add(item4);
		menu.add(item3);
		bar.add(menu);
		frame.setJMenuBar(bar);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
