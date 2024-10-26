package view;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */

/**
 *
 * @author leanh
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
public class GamePanel1 extends javax.swing.JPanel {
        private final int GRID_SIZE = 4; // 4x4 grid
    private final ArrayList<CardButton> cards = new ArrayList<>();
    private CardButton firstCard = null;
    private CardButton secondCard = null;
    private Timer timer;
    public int matchedPairsCount=0; // Biến đếm số cặp đã ghép
    
    
    public GamePanel1() {
        setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));
        setSize(380,380);
        initGame();
    }

    private void initGame() {
        for (int i = 0; i < (GRID_SIZE * GRID_SIZE) / 2; i++) {
            cards.add(new CardButton(i));
            cards.add(new CardButton(i));
        }

        Collections.shuffle(cards);

        for (CardButton card : cards) {
            card.addActionListener(new CardListener());
            add(card);
        }

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstCard.flip();
                secondCard.flip();
                firstCard = null;
                secondCard = null;
                timer.stop();
            }
        });
        timer.setRepeats(false);
    }

    public void restartGame() {
        removeAll();
        cards.clear();
        firstCard = null;
        secondCard = null;
        matchedPairsCount = 0; // Đặt lại số cặp đã ghép
        
        initGame();
        revalidate();
        repaint();
    }

    private class CardListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            CardButton clickedCard = (CardButton) e.getSource();
            if (firstCard == null) {
                firstCard = clickedCard;
                firstCard.flip();
            } else if (secondCard == null && clickedCard != firstCard) {
                secondCard = clickedCard;
                secondCard.flip();

                if (firstCard.getId() == secondCard.getId()) {
                    firstCard.setMatched(true);
                    secondCard.setMatched(true);
                    firstCard = null;
                    secondCard = null;
                    matchedPairsCount++;
                } else {
                    timer.start();
                }
            }
        }
    }
    public String matchedPairsCount(){
        int y = matchedPairsCount;
        String z = String.valueOf(y);
        return z;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
