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

        JButton btn = new JButton("Buton Ekle");
        btn.setBounds(15, 10, 150, 30);
        pane.add(btn);

        JButton addTextFieldBtn = new JButton("Metin Alanı Ekle");
        addTextFieldBtn.setBounds(15, 50, 150, 30);
        pane.add(addTextFieldBtn);

        JButton addLabelBtn = new JButton("Label Ekle");
        addLabelBtn.setBounds(15, 90, 150, 30);
        pane.add(addLabelBtn);

        JButton addMenuBar=new JButton("Menü Bar Ekle");
        addMenuBar.setBounds(15,130,150,30);
        pane.add(addMenuBar);

        JButton addComboBox=new JButton("ComboBox Ekle");
        addComboBox.setBounds(15, 170,150,30);
        pane.add(addComboBox);

        JPanel dropArea = new JPanel();
        dropArea.setBounds(250, 50, frame.getWidth() - 320, frame.getHeight() - 150);
        dropArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        dropArea.setBackground(new Color(211, 211, 221));
        dropArea.setLayout(null);
        pane.add(dropArea);

        JButton generateCodeButton = new JButton("Kod Çıktısını Oluştur");
        generateCodeButton.setBounds((frame.getWidth() - 320)/2+175, frame.getHeight()-75,200, 30);
        CodeGenerator codeGenerator = new CodeGenerator(dropArea);
        generateCodeButton.addActionListener(codeGenerator);
        pane.add(generateCodeButton);

        JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
        separator.setBounds(200, 0, 1, frame.getHeight());
        separator.setBackground(Color.BLACK);
        separator.setOpaque(true);
        pane.add(separator);

        add add = new add(dropArea);

        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int newHeight = frame.getHeight();
                int newWidth = frame.getWidth();
                generateCodeButton.setBounds((frame.getWidth() - 320)/2+175, frame.getHeight()-75,200, 30);
                separator.setBounds(200, 0, 1, newHeight);
                dropArea.setBounds(250, 50, newWidth - 320, newHeight - 150);
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
                    JOptionPane.showMessageDialog(pane,"MenuBar daha önce oluşturulmuş");
                    popupMenu.show(addMenuBar, e.getX(), e.getY());
                }
            }
        });

        addComboBox.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                add.createComboBox();
            }
        });

        frame.setFocusable(true);
        frame.setVisible(true);
    }
}