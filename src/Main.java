import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("GUI");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1200, 800);

            Container pane = frame.getContentPane();
            pane.setLayout(null);

            JButton btn = new JButton("Buton Ekle");
            btn.setBounds(15, 10, 150, 30);
            btn.setBackground(new Color(30, 136, 229));
            btn.setForeground(Color.WHITE);
            pane.add(btn);

            JButton addTextFieldBtn = new JButton("Metin Alanı Ekle");
            addTextFieldBtn.setBounds(15, 50, 150, 30);
            addTextFieldBtn.setBackground(new Color(30, 136, 229));
            addTextFieldBtn.setForeground(Color.WHITE);
            pane.add(addTextFieldBtn);

            JButton addLabelBtn = new JButton("Label Ekle");
            addLabelBtn.setBounds(15, 90, 150, 30);
            addLabelBtn.setBackground(new Color(30, 136, 229));
            addLabelBtn.setForeground(Color.WHITE);
            pane.add(addLabelBtn);

            JButton addMenuBar = new JButton("Menü Bar Ekle");
            addMenuBar.setBounds(15, 130, 150, 30);
            addMenuBar.setBackground(new Color(30, 136, 229));
            addMenuBar.setForeground(Color.WHITE);
            pane.add(addMenuBar);

            JButton addComboBox = new JButton("ComboBox Ekle");
            addComboBox.setBounds(15, 170, 150, 30);
            addComboBox.setBackground(new Color(30, 136, 229));
            addComboBox.setForeground(Color.WHITE);
            pane.add(addComboBox);

            GridPanel dropArea = new GridPanel(20); // Grid size of 20 pixels
            dropArea.setBounds(250, 50, frame.getWidth() - 320, frame.getHeight() - 150);
            dropArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            dropArea.setBackground(new Color(211, 211, 221));
            dropArea.setLayout(null);
            pane.add(dropArea);

            JCheckBox gridOnOff = new JCheckBox("Izgarayı aç / kapa", true);
            gridOnOff.setBounds(30, frame.getHeight() - 130, 150, 30);
            gridOnOff.addActionListener(e -> dropArea.setShowGrid(gridOnOff.isSelected()));
            pane.add(gridOnOff);

            JButton generateCodeButton = new JButton("Kod Çıktısını Oluştur");
            generateCodeButton.setBounds((frame.getWidth() - 320) / 2 + 175, frame.getHeight() - 75, 150, 30);
            generateCodeButton.setBackground(new Color(30, 136, 229));
            generateCodeButton.setForeground(Color.WHITE);
            CodeGenerator codeGenerator = new CodeGenerator(dropArea);
            generateCodeButton.addActionListener(codeGenerator);
            pane.add(generateCodeButton);

            JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
            separator.setBounds(200, 0, 1, frame.getHeight());
            separator.setBackground(Color.BLACK);
            separator.setOpaque(true);
            pane.add(separator);

            Add add = new Add(dropArea);

            frame.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                    int newHeight = frame.getHeight();
                    int newWidth = frame.getWidth();
                    generateCodeButton.setBounds((frame.getWidth() - 320) / 2 + 175, frame.getHeight() - 75, 150, 30);
                    separator.setBounds(200, 0, 1, newHeight);
                    dropArea.setBounds(250, 50, newWidth - 320, newHeight - 150);
                    gridOnOff.setBounds(30, frame.getHeight() - 130, 150, 30);
                    add.adjustMenuBarSize(dropArea.getWidth());
                }
            });

            btn.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    add.createDraggableButton();
                }
            });

            addTextFieldBtn.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    add.createDraggableTextField();
                }
            });

            addLabelBtn.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    add.createDraggableLabel();
                }
            });

            addMenuBar.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (!add.isBlnMenu()) {
                        add.createMenuBar();
                    } else {
                        JPopupMenu popupMenu = new JPopupMenu();
                        JOptionPane.showMessageDialog(pane, "MenuBar daha önce oluşturulmuş");
                        popupMenu.show(addMenuBar, e.getX(), e.getY());
                    }
                }
            });

            addComboBox.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    add.createDraggableComboBox();
                }
            });

            frame.setFocusable(true);
            frame.setVisible(true);
        });
    }

    public static class GridPanel extends JPanel {
        private int gridSize;
        private boolean showGrid;

        public GridPanel(int gridSize) {
            this.gridSize = gridSize;
            this.showGrid = true;

            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (SwingUtilities.isRightMouseButton(e)) {
                        showPopupMenu(e);
                    }
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (showGrid) {
                drawGrid(g);
            }
        }

        private void drawGrid(Graphics g) {
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

        private void showPopupMenu(MouseEvent e) {
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

        private void setGridSize(int newSize) {
            this.gridSize = newSize;
            repaint();
        }
    }

}
