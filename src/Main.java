import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("GUI");
        Container pane = frame.getContentPane();
        frame.setLayout(null);
        frame.setSize(1200, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton btnAddButton = new JButton("Buton Ekle");
        btnAddButton.setBounds(15, 50, 150, 30);
        btnAddButton.setBackground(Bean.getColor());
        btnAddButton.setForeground(Color.WHITE);
        pane.add(btnAddButton);

        JButton btnAddTextFieldBtn = new JButton("Metin Alanı Ekle");
        btnAddTextFieldBtn.setBounds(15, 90, 150, 30);
        btnAddTextFieldBtn.setBackground(Bean.getColor());
        btnAddTextFieldBtn.setForeground(Color.WHITE);
        pane.add(btnAddTextFieldBtn);

        JButton btnAddLabelBtn = new JButton("Label Ekle");
        btnAddLabelBtn.setBounds(15, 130, 150, 30);
        btnAddLabelBtn.setBackground(Bean.getColor());
        btnAddLabelBtn.setForeground(Color.WHITE);
        pane.add(btnAddLabelBtn);

        JButton btnAddMenuBar = new JButton("Menü Bar Ekle");
        btnAddMenuBar.setBounds(15, 10, 150, 30);
        btnAddMenuBar.setBackground(Bean.getColor());
        btnAddMenuBar.setForeground(Color.WHITE);
        pane.add(btnAddMenuBar);

        JButton btnAddComboBox = new JButton("ComboBox Ekle");
        btnAddComboBox.setBounds(15, 170, 150, 30);
        btnAddComboBox.setBackground(Bean.getColor());
        btnAddComboBox.setForeground(Color.WHITE);
        pane.add(btnAddComboBox);

        JButton btnAddCheckBox = new JButton("CheckBox Ekle");
        btnAddCheckBox.setBounds(15, 210, 150, 30);
        btnAddCheckBox.setBackground(Bean.getColor());
        btnAddCheckBox.setForeground(Color.WHITE);
        pane.add(btnAddCheckBox);

        JButton btnAddPasswordField = new JButton("PasswordField Ekle");
        btnAddPasswordField.setBounds(15, 250, 150, 30);
        btnAddPasswordField.setBackground(Bean.getColor());
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
        btnGenerateCode.setBounds((frame.getWidth() - 320) / 2 + 175, frame.getHeight() - 60, 150, 30);
        CopyCode codeGenerator = new CopyCode(dropArea);
        btnGenerateCode.addActionListener(codeGenerator);
        btnGenerateCode.setBackground(Bean.getColor());
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
}