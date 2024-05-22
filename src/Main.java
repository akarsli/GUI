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

        MouseAdapter mouseAdapter = new MouseAdapter() {
            Point initialClick;

            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    String newName = JOptionPane.showInputDialog(pane, "Butonun yeni ismini girin:");
                    if (newName != null && !newName.trim().isEmpty()) {
                        newBtn.setText(newName);
                    }
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

        MouseAdapter mouseAdapter = new MouseAdapter() {
            Point initialClick;

            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    String newText = JOptionPane.showInputDialog(pane, "Metin alanının yeni ismini girin:");
                    if (newText != null && !newText.trim().isEmpty()) {
                        newTextField.setText(newText);
                    }
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
}
