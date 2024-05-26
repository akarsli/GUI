import javax.swing.*;
import java.awt.*;

class CustomMenuBar extends JMenuBar {
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        for (int i = 0; i < getMenuCount(); i++) {
            JMenu menu = getMenu(i);
            if (menu != null) {
                Rectangle bounds = menu.getBounds();
                g2d.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);
            }
        }
    }
}