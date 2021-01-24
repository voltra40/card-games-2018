package GUI;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class CardImg {
//	public static ArrayList<JLabel> getCardImgs() {
//		CardImg img = new CardImg();
//		ArrayList<JLabel> labels = new ArrayList<JLabel>();
//		for(int i=0; i<4; i++) {
//			for(int y=1; y<=13; y++) {
//				String suit = "";
//				String rank = "";
//				switch(i){
//				case 0:
//					suit = "c";
//					break;
//				case 1:
//					suit = "d";
//					break;
//				case 2:
//					suit = "h";
//					break;
//				case 3:
//					suit = "s";
//					break;
//				}
//				
//				switch(y) {
//				case 13:
//					rank = "k";
//					break;
//				case 12:
//					rank = "q";
//					break;
//				case 11:
//					rank = "j";
//					break;
//				case 1:
//					rank = "a";
//					break;
//				default :
//					rank = String.valueOf(y);
//				}
//				
//				String filename = "Cards/" + rank + suit + ".gif";
//				labels.add(img.createDisplayImage(filename));
//			}
//		}
//		final Border UNSELECTED_BORDER = BorderFactory.createEmptyBorder(5, 5, 5, 5);
//	    final Border SELECTED_BORDER = BorderFactory.createMatteBorder(5, 5, 5, 5,Color.BLACK);
//		for(JLabel label : labels) {
//			label.addMouseListener(new MouseListener() {
//				private boolean selected;
//				
//				@Override
//				public void mouseClicked(MouseEvent arg0) {
//					JLabel label = (JLabel) arg0.getComponent();
//					// TODO Auto-generated method stub
//					if(this.selected) {
//						label.setBorder(UNSELECTED_BORDER);
//					    label.repaint();
//					    this.selected = false;
//					} else {
//						label.setBorder(SELECTED_BORDER);
//					    label.repaint();
//					    this.selected = true;
//					}
//				}
//
//				@Override
//				public void mouseEntered(MouseEvent e) {
//					// TODO Auto-generated method stub
//					
//				}
//
//				@Override
//				public void mouseExited(MouseEvent e) {
//					// TODO Auto-generated method stub
//					
//				}
//
//				@Override
//				public void mousePressed(MouseEvent e) {
//					// TODO Auto-generated method stub
//					
//				}
//
//				@Override
//				public void mouseReleased(MouseEvent e) {
//					// TODO Auto-generated method stub
//					
//				}
//				
//			});
//		}
//		return labels;
//	}

	public JLabel createDisplayImage(String fileNameRelativeToClassFile) {
	    JLabel retVal = new JLabel();
	    java.net.URL imgURL = this.getClass().getResource(fileNameRelativeToClassFile);
//	    String imgURL = fileNameRelativeToClassFile;
	    if (imgURL == null) {
	      throw new IllegalArgumentException("Couldn't find file: " + fileNameRelativeToClassFile);
	    }
	    ImageIcon cardImage = new ImageIcon(imgURL);    
	    retVal.setIcon(cardImage);
	    Dimension d = new Dimension(cardImage.getIconWidth() + 10, cardImage.getIconHeight() + 10);
	    retVal.setSize(d);
	    retVal.setPreferredSize(d);
	    retVal.setMaximumSize(d);
	    retVal.setMinimumSize(d);
	    return retVal;
	  }
}
