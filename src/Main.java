import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main {
    private static final Color COLOR = new Color(30, 136, 229);
    public static void main(String[] args) {
        JFrame frame = new JFrame("GUI");
        Container pane = frame.getContentPane();
        frame.setLayout(null);
        frame.setSize(1200, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton btnAddButton = new JButton("Buton Ekle");
        btnAddButton.setBounds(15, 50, 150, 30);
        btnAddButton.setBackground(COLOR);
        btnAddButton.setForeground(Color.WHITE);
        pane.add(btnAddButton);

        JButton btnAddTextFieldBtn = new JButton("Metin Alanı Ekle");
        btnAddTextFieldBtn.setBounds(15, 90, 150, 30);
        btnAddTextFieldBtn.setBackground(COLOR);
        btnAddTextFieldBtn.setForeground(Color.WHITE);
        pane.add(btnAddTextFieldBtn);

        JButton btnAddLabelBtn = new JButton("Label Ekle");
        btnAddLabelBtn.setBounds(15, 130, 150, 30);
        btnAddLabelBtn.setBackground(COLOR);
        btnAddLabelBtn.setForeground(Color.WHITE);
        pane.add(btnAddLabelBtn);

        JButton btnAddMenuBar = new JButton("Menü Bar Ekle");
        btnAddMenuBar.setBounds(15, 10, 150, 30);
        btnAddMenuBar.setBackground(COLOR);
        btnAddMenuBar.setForeground(Color.WHITE);
        pane.add(btnAddMenuBar);

        JButton btnAddComboBox = new JButton("ComboBox Ekle");
        btnAddComboBox.setBounds(15, 170, 150, 30);
        btnAddComboBox.setBackground(COLOR);
        btnAddComboBox.setForeground(Color.WHITE);
        pane.add(btnAddComboBox);

        JButton btnAddCheckBox = new JButton("CheckBox Ekle");
        btnAddCheckBox.setBounds(15,210,150,30);
        btnAddCheckBox.setBackground(COLOR);
        btnAddCheckBox.setForeground(Color.WHITE);
        pane.add(btnAddCheckBox);

        JButton btnAddPasswordField = new JButton("PasswordField Ekle");
        btnAddPasswordField.setBounds(15, 250, 150, 30);
        btnAddPasswordField.setBackground(COLOR);
        btnAddPasswordField.setForeground(Color.WHITE);
        pane.add(btnAddPasswordField);

        GridPanel dropArea = new GridPanel(20);
        dropArea.setBounds(250, 50, frame.getWidth() - 320, frame.getHeight() - 150);
        dropArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        dropArea.setBackground(new Color(211, 211, 221));
        dropArea.setLayout(null);
        pane.add(dropArea);

        JCheckBox gridOnOff = new JCheckBox("Izgarayı aç / kapa", false);
        gridOnOff.setBounds(30, frame.getHeight() - 130, 150, 30);
        gridOnOff.addActionListener(e -> dropArea.setShowGrid(gridOnOff.isSelected()));
        pane.add(gridOnOff);

        JButton btnGenerateCode = new JButton("Kod Çıktısını Kopyala");
        btnGenerateCode.setBounds((frame.getWidth() - 320)/2+175, frame.getHeight()-60,150, 30);
        CopyCode codeGenerator = new CopyCode(dropArea);
        btnGenerateCode.addActionListener(codeGenerator);
        btnGenerateCode.setBackground(COLOR);
        btnGenerateCode.setForeground(Color.WHITE);
        pane.add(btnGenerateCode);

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
                btnGenerateCode.setBounds((newWidth - 320) / 2 + 175, newHeight - 83, 170, 30);
                separator.setBounds(200, 0, 1, newHeight);
                dropArea.setBounds(250, 50, newWidth - 320, newHeight - 150);
                gridOnOff.setBounds(30, newHeight - 130, 150, 30);
                add.adjustMenuBarSize(dropArea.getWidth());
            }
        });

        btnAddButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                add.createDraggableButton();
            }
        });

        btnAddTextFieldBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                add.createDraggableTextField();
            }
        });

        btnAddLabelBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                add.createDraggableLabel();
            }
        });

        btnAddMenuBar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!add.isBlnMenu()) {
                    add.createMenuBar();
                } else {
                    JPopupMenu popupMenu = new JPopupMenu();
                    JOptionPane.showMessageDialog(pane, "MenuBar daha önce oluşturulmuş");
                    popupMenu.show(btnAddMenuBar, e.getX(), e.getY());
                }
            }
        });

        btnAddComboBox.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                add.createDraggableComboBox();
            }
        });

        btnAddCheckBox.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                add.createDraggableCheckBox();
            }
        });

        btnAddPasswordField.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                add.createDraggablePasswordField();
            }
        });

        frame.setFocusable(true);
        frame.setVisible(true);

    }

    public static class GridPanel extends JPanel {
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