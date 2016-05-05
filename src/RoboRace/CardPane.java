package RoboRace;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import COSC3P40.graphics.*;

public class CardPane extends JPanel implements MouseListener {
	
    private Image noCard;
    private Image[] selectImages;
    private CardList cards = null;
    private CardList result = null;
    private int[] selected = new int[7];
    private int counter = 0;
    private int x, y = 0;
    private boolean select = false;

    public CardPane() {
        setPreferredSize(new Dimension(644,120));
        ImageManager manager = ImageManager.getInstance();
        noCard = manager.loadImage("Cards/NoCard.png");
        selectImages = new Image[5];
        for(int i=0; i<5; i++)
            selectImages[i] = manager.loadImage("Cards/Select" + (i+1) + ".png");
        addMouseListener(this);
    }

    public synchronized CardList selectCards(CardList list) {
        cards = list;
        result = new CardList();
        repaint();
        select = true;
        try{
            wait();
        }
        catch (InterruptedException e){
            // Hi
        }
        select = false;
        repaint();
        notify();
        return result;
    }

    public void paintComponent(Graphics g) {
        if (cards != null){
            for (int i = 0; i < 7; i++){
                Card drawCard = cards.get(i);
                if (drawCard != null){
                    g.drawImage(drawCard.getImage(), i*92, 0, this);
                    if (selected[i] != 0){
                        g.drawImage(selectImages[selected[i] - 1], i*92 + 30, 48, this);
                    }
                }
                else {
                    g.drawImage(noCard, i*92, 0, this);
                }
            }
        }
        g.fillRect(7*92, 0, this.getWidth() - 7*92, this.getHeight());
    }

    public synchronized void mouseClicked(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        if (select == true && counter < 5 && e.getButton() == MouseEvent.BUTTON1) {
            int select = x/92;
            if (select < 7 && selected[select] == 0){
                result.add(cards.get(x/92));
                selected[select] = counter + 1;
                counter++;
                if (counter >= 5){
                    selected = new int[7];
                    counter = 0;
                    cards = new CardList();
                    for (int i = 0; i < 7; i++){
                        if (i < 5){
                            cards.add(result.get(i));
                        }
                        else {
                            cards.add(null);
                        }
                    }
                    // Transfer Values
                    notify();
                }
            }
        }
        if (select == true && counter > 0 && e.getButton() == MouseEvent.BUTTON3) {
            for (int i = 0; i < selected.length; i++){
                if (selected[i] == counter){
                    selected[i] = 0;
                    break;
                }
            }
            result.remove();
            counter--;
        }
        repaint();
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