package RoboRace;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import COSC3P40.graphics.*;

public class CardPane extends JPanel implements MouseListener {
	
	private Image noCard;
	private Image[] selectImages;
	private CardList cards = null;
	
	public CardPane() {
		setPreferredSize(new Dimension(644,120));
		ImageManager manager = ImageManager.getInstance();
		noCard = manager.loadImage("Cards/NoCard.png");
		selectImages = new Image[5];
		for(int i=0; i<5; i++)
			selectImages[i] = manager.loadImage("Cards/Select" + (i+1) + ".png");
		addMouseListener(this);
	}
	
	public CardList selectCards(CardList list) {
            return null;
	}
	
	public void paintComponent(Graphics g) {		
	}
	
	public void mouseClicked(MouseEvent e) {
	}
	
	public void mouseExited(MouseEvent e) {
	}
	
	public void mouseEntered(MouseEvent e) {
	}
	
	public void mousePressed(MouseEvent e) {
	}
	
	public void mouseReleased(MouseEvent e) {
	}

	
}