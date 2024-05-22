import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main {
    private static JComponent selectedComponent;

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

        JButton addLabelBtn = new JButton("Label");
        addLabelBtn.setBounds(15, 90, 150, 30);
        pane.add(addLabelBtn);

        JPanel dropArea = new JPanel();
        dropArea.setBounds(250, 50, 700, 650);
        dropArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        dropArea.setBackground(new Color(211,211,221)); // Arka plan rengini açık gri yap
        dropArea.setLayout(null);
        pane.add(dropArea);

        JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
        separator.setBounds(200, 0, 1, frame.getHeight());
        separator.setBackground(Color.BLACK);
        separator.setOpaque(true);
        pane.add(separator);

        Timer timer = new Timer(100, e -> separator.setBounds(200, 0, 1, frame.getHeight()));
        timer.start();

        add add = new add(dropArea);

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


        frame.setFocusable(true);
        frame.setVisible(true);
    }
}
