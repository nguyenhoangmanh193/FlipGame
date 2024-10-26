package view;
import javax.swing.*;

public class CardButton extends JButton {
    private final int id;
    private boolean isFlipped = false;
    private boolean isMatched = false;

    public CardButton(int id) {
        this.id = id;
        setText("?");
    }

    public int getId() {
        return id;
    }

    public void flip() {
        if (isMatched) return;
        if (isFlipped) {
            setText("?");
        } else {
            setText(String.valueOf(id));
        }
        isFlipped = !isFlipped;
    }

    public void setMatched(boolean matched) {
        isMatched = matched;
        if (matched) {
            setEnabled(false);
        }
    }
}
