import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main {
    private static final int COMPONENT_X = 180; // Bileşenlerin sabit X konumu
    private static final int COMPONENT_Y_BUTTON = 10; // Butonların sabit Y konumu
    private static final int COMPONENT_Y_TEXTFIELD = 50; // Metin alanlarının sabit Y konumu
    private static int textfieldCounter = 1;
    private static int buttonCounter = 1;
    private static JComponent selectedComponent;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Null Layout Örneği");
        Container pane = frame.getContentPane();
        frame.setLayout(null);
        frame.setSize(1200, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton btn = new JButton("Buton Ekle");
        btn.setBounds(15, 10, 150, 30);
        pane.add(btn);

        JButton addTextFieldBtn = new JButton("Metin Alanı Ekle");
        addTextFieldBtn.setBounds(15, 50, 150, 30);
        pane.add(addTextFieldBtn);

        JPanel dropArea = new JPanel();
        dropArea.setBounds(250, 50, 700, 650);
        dropArea.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
        dropArea.setLayout(null);
        pane.add(dropArea);

        JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
        separator.setBounds(200, 0, 1, frame.getHeight());
        separator.setBackground(Color.BLACK);
        separator.setOpaque(true);
        pane.add(separator);

        Timer timer = new Timer(100, e -> separator.setBounds(200, 0, 1, frame.getHeight()));
        timer.start();

        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                createDraggableButton(dropArea);
            }
        });

        addTextFieldBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                createDraggableTextField(dropArea);
            }
        });

        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DELETE && selectedComponent != null) {
                    dropArea.remove(selectedComponent);
                    dropArea.repaint();
                    selectedComponent = null;
                }
            }
        });

        frame.setFocusable(true);
        frame.setVisible(true);
    }

    private static void createDraggableButton(Container pane) {
        JButton newBtn = new JButton("button" + buttonCounter);
        buttonCounter++;
        newBtn.setBounds(COMPONENT_X, COMPONENT_Y_BUTTON, 100, 30);
        pane.add(newBtn);
        pane.repaint();

        JPopupMenu popupMenu = createPopupMenu(newBtn, pane);

        MouseAdapter mouseAdapter = new MouseAdapter() {
            Point initialClick;

            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                } else if (SwingUtilities.isLeftMouseButton(e)) {
                    initialClick = e.getPoint();
                    getComponent(e).setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
                    selectedComponent = (JComponent) e.getComponent();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                getComponent(e).setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                int thisX = newBtn.getLocation().x;
                int thisY = newBtn.getLocation().y;

                int xMoved = e.getX() - initialClick.x;
                int yMoved = e.getY() - initialClick.y;

                int X = thisX + xMoved;
                int Y = thisY + yMoved;

                if (X < 0) X = 0;
                if (Y < 0) Y = 0;
                if (X + newBtn.getWidth() > pane.getWidth()) X = pane.getWidth() - newBtn.getWidth();
                if (Y + newBtn.getHeight() > pane.getHeight()) Y = pane.getHeight() - newBtn.getHeight();

                newBtn.setLocation(X, Y);
            }

            private Component getComponent(MouseEvent e) {
                return e.getComponent();
            }
        };

        newBtn.addMouseListener(mouseAdapter);
        newBtn.addMouseMotionListener(mouseAdapter);
    }

    private static void createDraggableTextField(Container pane) {
        JTextField newTextField = new JTextField("textfield" + textfieldCounter);
        textfieldCounter++;
        newTextField.setBounds(COMPONENT_X, COMPONENT_Y_TEXTFIELD, 100, 30);
        pane.add(newTextField);
        pane.repaint();

        JPopupMenu popupMenu = createPopupMenu(newTextField, pane);

        MouseAdapter mouseAdapter = new MouseAdapter() {
            Point initialClick;

            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                } else if (SwingUtilities.isLeftMouseButton(e)) {
                    initialClick = e.getPoint();
                    getComponent(e).setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
                    selectedComponent = (JComponent) e.getComponent();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                getComponent(e).setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                int thisX = newTextField.getLocation().x;
                int thisY = newTextField.getLocation().y;

                int xMoved = e.getX() - initialClick.x;
                int yMoved = e.getY() - initialClick.y;

                int X = thisX + xMoved;
                int Y = thisY + yMoved;

                if (X < 0) X = 0;
                if (Y < 0) Y = 0;
                if (X + newTextField.getWidth() > pane.getWidth()) X = pane.getWidth() - newTextField.getWidth();
                if (Y + newTextField.getHeight() > pane.getHeight()) Y = pane.getHeight() - newTextField.getHeight();

                newTextField.setLocation(X, Y);
            }

            private Component getComponent(MouseEvent e) {
                return e.getComponent();
            }
        };

        newTextField.addMouseListener(mouseAdapter);
        newTextField.addMouseMotionListener(mouseAdapter);
    }

    private static JPopupMenu createPopupMenu(JComponent component, Container pane) {
        JPopupMenu popupMenu = new JPopupMenu();

        JMenuItem renameItem = new JMenuItem("Yeniden İsimlendir");
        renameItem.addActionListener(e -> {
            String newName = JOptionPane.showInputDialog(pane, "Yeni isim girin:");
            if (newName != null && !newName.trim().isEmpty()) {
                if (component instanceof JButton) {
                    ((JButton) component).setText(newName);
                } else if (component instanceof JTextField) {
                    ((JTextField) component).setText(newName);
                }
            }
        });
        popupMenu.add(renameItem);

        JMenuItem deleteItem = new JMenuItem("Sil");
        deleteItem.addActionListener(e -> {
            pane.remove(component);
            pane.repaint();
        });
        popupMenu.add(deleteItem);

        JMenuItem resizeItem = new JMenuItem("Yeniden Boyutlandır");
        resizeItem.addActionListener(e -> {
            String newSize = JOptionPane.showInputDialog(pane, "Yeni boyutları girin (genişlik,yükseklik):");
            if (newSize != null && !newSize.trim().isEmpty()) {
                String[] dimensions = newSize.split(",");
                if (dimensions.length == 2) {
                    try {
                        int width = Integer.parseInt(dimensions[0].trim());
                        int height = Integer.parseInt(dimensions[1].trim());
                        component.setSize(width, height);
                        pane.repaint();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(pane, "Geçersiz boyut formatı.", "Hata", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        popupMenu.add(resizeItem);

        return popupMenu;
    }
}
