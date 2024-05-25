import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class add {
    private static final int COMPONENT_X = 10;
    private static final int COMPONENT_Y_BUTTON = 50;
    private static final int COMPONENT_Y_TEXTFIELD = 50;
    private static JComponent selectedComponent;
    private final Container pane;
    CustomMenuBar newMenuBar;
    public boolean blnMenu;
    JMenu newMenu;
    String[] newItem={};

    public add(Container pane) {
        this.pane = pane;
    }

    public void createDraggableButton() {
        JButton newBtn = new JButton("Button");
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
        JTextField newTextField = new JTextField("TextField");
        newTextField.setBounds(COMPONENT_X, COMPONENT_Y_TEXTFIELD+40, 100, 30);
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

    public void createDraggableLabel() {
        JLabel newLabel = new JLabel("Label");
        newLabel.setBorder(BorderFactory.createLineBorder(new Color(255, 0, 0), 1));
        newLabel.setBounds(COMPONENT_X, COMPONENT_Y_TEXTFIELD+80, 100, 30);
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

    public boolean isBlnMenu() {
        return blnMenu;
    }

    public void createMenuBar() {
        newMenuBar = new CustomMenuBar();
        newMenuBar.setBounds(0, 0, pane.getWidth(), 30);
        blnMenu = true;
        pane.add(newMenuBar);
        pane.repaint();

        JPopupMenu popupMenuBar = createPopupMenuBar(newMenuBar);
        MouseAdapter mouseAdapter = new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    popupMenuBar.show(e.getComponent(), e.getX(), e.getY());
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                getComponent(e).setCursor(Cursor.getDefaultCursor());
            }

            private Component getComponent(MouseEvent e) {
                return e.getComponent();
            }
        };

        newMenuBar.addMouseListener(mouseAdapter);
        newMenuBar.addMouseMotionListener(mouseAdapter);
    }

    public void adjustMenuBarSize(int newWidth) {
        if (newMenuBar != null) {
            newMenuBar.setSize(newWidth, 30);
            pane.repaint();
        }
    }

    public void createMenu() {
        String menuName = JOptionPane.showInputDialog(pane, "Menu ismini girin:");
        if (menuName != null && !menuName.trim().isEmpty()) {
            newMenu = new JMenu(menuName);
            newMenuBar.add(newMenu);
            pane.revalidate();
            pane.repaint();

            JPopupMenu popupMenu = createPopupMenuItem(newMenu);
            newMenu.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (SwingUtilities.isRightMouseButton(e)) {
                        popupMenu.show(e.getComponent(), e.getX(), e.getY());
                        pane.repaint();
                        pane.revalidate();
                    }
                }
            });
        }
    }

    public void createMenuItem(JMenu menu) {
        String menuItemName = JOptionPane.showInputDialog(pane, "Item ismi girin:");
        if (menuItemName != null && !menuItemName.trim().isEmpty()) {
            JMenuItem newItem = new JMenuItem(menuItemName);
            menu.add(newItem);
            pane.revalidate();
            pane.repaint();
        }
    }

    public void createDraggableComboBox(){
        JComboBox newComboBox=new JComboBox(newItem);
        newComboBox.setBounds(COMPONENT_X, COMPONENT_Y_TEXTFIELD+120,100,30);
        pane.add(newComboBox);
        pane.repaint();
        pane.revalidate();


        JPopupMenu PopupComboBox = createPopupComboBox(newComboBox);
        MouseAdapter mouseAdapter = new MouseAdapter() {
            Point initialClick;

            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    PopupComboBox.show(e.getComponent(), e.getX(), e.getY());
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
                int thisX = newComboBox.getLocation().x;
                int thisY = newComboBox.getLocation().y;

                int xMoved = e.getX() - initialClick.x;
                int yMoved = e.getY() - initialClick.y;

                int X = thisX + xMoved;
                int Y = thisY + yMoved;


                if (X < 0) X = 0;
                if (Y < 0) Y = 0;
                if (X + newComboBox.getWidth() > pane.getWidth()) X = pane.getWidth() - newComboBox.getWidth();
                if (Y + newComboBox.getHeight() > pane.getHeight()) Y = pane.getHeight() - newComboBox.getHeight();

                newComboBox.setLocation(X, Y);
            }

            private Component getComponent(MouseEvent e) {
                return e.getComponent();
            }
        };

        newComboBox.addMouseListener(mouseAdapter);
        newComboBox.addMouseMotionListener(mouseAdapter);
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
                } else if (component instanceof JLabel) {
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

    private JPopupMenu createPopupMenuBar(JMenuBar menuBar) {
        JPopupMenu popupMenuBar = new JPopupMenu();

        JMenuItem addMenu = new JMenuItem("Menu Ekle");
        addMenu.addActionListener(e -> createMenu());
        popupMenuBar.add(addMenu);

        JMenuItem deleteMenuBar = new JMenuItem("Menu Bar Sil");
        deleteMenuBar.addActionListener(e -> {
            String[] options = {"Sil", "İptal"};
            int response = JOptionPane.showOptionDialog(pane, "Menu Bar'ı silmek istediğinize emin misiniz?", "Silme Onayı", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
            if (response == JOptionPane.YES_OPTION) {
                pane.remove(menuBar);
                blnMenu = false;
                pane.repaint();
            }
        });
        popupMenuBar.add(deleteMenuBar);

        return popupMenuBar;
    }

    public JPopupMenu createPopupMenuItem(JMenu menu) {
        JPopupMenu popupMenu = new JPopupMenu();

        JMenuItem addItem = new JMenuItem("Item Ekle");
        addItem.addActionListener(e -> {
            createMenuItem(menu);
            popupMenu.setVisible(false);
        });
        popupMenu.add(addItem);

        JMenuItem renameMenu = new JMenuItem("Menüyü Yeniden İsimlendir");
        renameMenu.addActionListener(e -> {
            String newName = JOptionPane.showInputDialog(pane, "Yeni menü ismini girin:");
            if (newName != null && !newName.trim().isEmpty()) {
                menu.setText(newName);
                pane.revalidate();
                pane.repaint();
                popupMenu.setVisible(false);
            }
        });
        popupMenu.add(renameMenu);

        JMenuItem deleteMenu = new JMenuItem("Menüyü Sil");
        deleteMenu.addActionListener(e -> {
            String[] options = {"Sil", "İptal"};
            int response = JOptionPane.showOptionDialog(pane, "Menüyü silmek istediğinize emin misiniz?", "Silme Onayı", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
            if (response == JOptionPane.YES_OPTION) {
                newMenuBar.remove(menu);
                pane.revalidate();
                pane.repaint();
                popupMenu.setVisible(false);
            }
        });
        popupMenu.add(deleteMenu);

        menu.setComponentPopupMenu(popupMenu);
        pane.repaint();
        pane.revalidate();
        return popupMenu;
    }

    public JPopupMenu createPopupComboBox(JComboBox comboBox){

        JPopupMenu PopupComboBox=new JPopupMenu();

        JMenuItem addItem = new JMenuItem("Item Ekle");
        addItem.addActionListener(e -> {
            String itemName = JOptionPane.showInputDialog(pane, "Item ismi girin:");
            if (itemName != null && !itemName.trim().isEmpty()) {

                DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) comboBox.getModel();
                model.addElement(itemName);

                pane.repaint();
                pane.revalidate();
            }
        });
        PopupComboBox.add(addItem);

        JMenuItem removeItem=new JMenuItem("Item sil");
        removeItem.addActionListener(e ->{
            // Get the existing items in the ComboBox model
            DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) comboBox.getModel();
            int itemCount = model.getSize();

            // If there are no items, show a message
            if (itemCount == 0) {
                JOptionPane.showMessageDialog(pane, "ComboBox'ta silinecek öğe yok.", "Bilgi", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // Create an array to store the existing item names
            String[] comboBoxItems = new String[itemCount];
            for (int i = 0; i < itemCount; i++) {
                comboBoxItems[i] = model.getElementAt(i);
            }

            // Display the list of items in a JOptionPane showOptionDialog
            int selectedIndex = JOptionPane.showOptionDialog(pane, "Silmek istediğiniz öğeyi seçin:", "Öğe Seçimi", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, comboBoxItems, comboBoxItems[0]);

            // If an item is selected, remove it from the model
            if (selectedIndex != JOptionPane.CLOSED_OPTION) {
                model.removeElementAt(selectedIndex);
                pane.repaint();
                pane.revalidate();
            }
        });
        PopupComboBox.add(removeItem);

        // ... rest of the code for resizeItem and removeComboBox

        return PopupComboBox;
    }

    static class CustomMenuBar extends JMenuBar {
        @Override
        protected void paintComponent(Graphics g) {
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
}