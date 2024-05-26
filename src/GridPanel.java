import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class GridPanel extends JPanel {
    private int gridSize;
    private boolean showGrid;

    public GridPanel(int gridSize) {
        this.gridSize = gridSize;
        this.showGrid = false;

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e) && showGrid) {
                    showPopupMenu(e);
                }
            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (showGrid) {
            drawGrid(g);
        }
    }

    public void drawGrid(Graphics g) {
        int width = getWidth();
        int height = getHeight();

        g.setColor(Color.LIGHT_GRAY);

        for (int x = 0; x < width; x += gridSize) {
            g.drawLine(x, 0, x, height);
        }

        for (int y = 0; y < height; y += gridSize) {
            g.drawLine(0, y, width, y);
        }
    }

    public void setShowGrid(boolean showGrid) {
        this.showGrid = showGrid;
        repaint();
    }

    public void showPopupMenu(MouseEvent e) {
        JPopupMenu popupMenu = new JPopupMenu();

        JMenuItem smallGridItem = new JMenuItem("Small Grid (20 px)");
        smallGridItem.addActionListener(event -> setGridSize(20));
        popupMenu.add(smallGridItem);

        JMenuItem mediumGridItem = new JMenuItem("Medium Grid (30 px)");
        mediumGridItem.addActionListener(event -> setGridSize(30));
        popupMenu.add(mediumGridItem);

        JMenuItem largeGridItem = new JMenuItem("Large Grid (40 px)");
        largeGridItem.addActionListener(event -> setGridSize(40));
        popupMenu.add(largeGridItem);

        popupMenu.show(this, e.getX(), e.getY());
    }

    public void setGridSize(int newSize) {
        this.gridSize = newSize;
        repaint();
    }
}