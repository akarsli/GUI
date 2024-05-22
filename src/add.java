import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class add {
    private static final int COMPONENT_X = 180;
    private static final int COMPONENT_Y_BUTTON = 50;
    private static final int COMPONENT_Y_TEXTFIELD = 50;
    private static int buttonCounter = 1;
    private static int textfieldCounter = 1;
    private static int labelCounter = 1;
    private static JComponent selectedComponent;
    private final Container pane;

    public add(Container pane) {
        this.pane = pane;
    }

    public void createDraggableButton() {
        JButton newBtn = new JButton("button" + buttonCounter);
        buttonCounter++;
        newBtn.setBounds(COMPONENT_X, COMPONENT_Y_BUTTON, 100, 30);
        pane.add(newBtn);
        pane.repaint();

        JPopupMenu popupMenu = createPopupMenu(newBtn);

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

    public void createDraggableTextField() {
        JTextField newTextField = new JTextField("textfield" + textfieldCounter);
        textfieldCounter++;
        newTextField.setBounds(COMPONENT_X, COMPONENT_Y_TEXTFIELD, 100, 30);
        pane.add(newTextField);
        pane.repaint();

        JPopupMenu popupMenu = createPopupMenu(newTextField);

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

    public void createDraggableLabel(){
        JLabel newLabel = new JLabel("Label" + labelCounter);
        newLabel.setBorder(BorderFactory.createLineBorder(new Color(255,0,0), 1));
        labelCounter++;
        newLabel.setBounds(COMPONENT_X, COMPONENT_Y_TEXTFIELD, 100, 30);
        pane.add(newLabel);
        pane.repaint();

        JPopupMenu popupMenu = createPopupMenu(newLabel);
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
                int thisX = newLabel.getLocation().x;
                int thisY = newLabel.getLocation().y;

                int xMoved = e.getX() - initialClick.x;
                int yMoved = e.getY() - initialClick.y;

                int X = thisX + xMoved;
                int Y = thisY + yMoved;

                if (X < 0) X = 0;
                if (Y < 0) Y = 0;
                if (X + newLabel.getWidth() > pane.getWidth()) X = pane.getWidth() - newLabel.getWidth();
                if (Y + newLabel.getHeight() > pane.getHeight()) Y = pane.getHeight() - newLabel.getHeight();

                newLabel.setLocation(X, Y);
            }

            private Component getComponent(MouseEvent e) {
                return e.getComponent();
            }
        };

        newLabel.addMouseListener(mouseAdapter);
        newLabel.addMouseMotionListener(mouseAdapter);




}
    private JPopupMenu createPopupMenu(JComponent component) {
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
                else if (component instanceof JLabel) {
                    ((JLabel) component).setText(newName);
                }
            }
        });
        popupMenu.add(renameItem);



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

        JMenuItem deleteItem = new JMenuItem("Sil");
        deleteItem.addActionListener(e -> {
            String[] options = {"Sil", "İptal"};
            int response = JOptionPane.showOptionDialog(pane, "Silmek istediğinize emin misiniz?", "Silme Onayı", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
            if (response == 0) {
                pane.remove(component);
                pane.repaint();
            }
        });
        popupMenu.add(deleteItem);


        return popupMenu;
    }
}
