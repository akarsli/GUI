import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CodeGenerator implements ActionListener {
    private final Container dropArea;

    public CodeGenerator(Container dropArea) {
        this.dropArea = dropArea;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        StringBuilder code = new StringBuilder();
        int width = dropArea.getWidth();
        int height = dropArea.getHeight();
        code.append(String.format("""
                import javax.swing.*;
                import java.awt.*;
                import java.awt.event.*;
                public class Main {
                public static void main(String[] args) {
                JFrame frame = new JFrame("GUI");
                Container pane = frame.getContentPane();
                frame.setLayout(null);
                frame.setSize(%d ,%d);
                """, width, height));

        for (Component comp : dropArea.getComponents()) {
            if (comp instanceof JButton) {
                JButton button = (JButton) comp;
                code.append(generateButtonCode(button));
            } else if (comp instanceof JTextField) {
                JTextField textField = (JTextField) comp;
                code.append(generateTextFieldCode(textField));
            } else if (comp instanceof JLabel) {
                JLabel label = (JLabel) comp;
                code.append(generateLabelCode(label));
            } else if (comp instanceof JMenuBar) {
                JMenuBar menubar = (JMenuBar) comp;
                code.append(generateMenuBarCode(menubar));
            } else if (comp instanceof JComboBox) {
                JComboBox<?> comboBox = (JComboBox<?>) comp;
                code.append(generateComboBoxCode(comboBox));
            }
        }
        code.append("""
                frame.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                int newHeight = frame.getHeight();
                int newWidth = frame.getWidth();
                menubar.setBounds(0, 0, frame.getWidth(), 30);
                }});
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setFocusable(true);
                frame.setVisible(true);
                }}
                class CustomMenuBar extends JMenuBar {
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
                // Remove left border of the first menu
                if (i == 0) {
                g2d.clearRect(bounds.x, bounds.y, 1, bounds.height);
                }}}}}
                """);

        JTextArea textArea = new JTextArea(code.toString());
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setCaretPosition(0);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(500, 400));
        JOptionPane.showMessageDialog(null, scrollPane, "Uzun Mesaj", JOptionPane.INFORMATION_MESSAGE);

    }

    private String generateButtonCode(JButton button) {
        return String.format(
                "JButton button%d = new JButton(\"%s\");\n" +
                        "button%d.setBounds(%d, %d, %d, %d);\n" +
                        "pane.add(button%d);\n",
                button.hashCode(), button.getText(),
                button.hashCode(), button.getX(), button.getY(), button.getWidth(), button.getHeight(),
                button.hashCode()
        );
    }

    private String generateTextFieldCode(JTextField textField) {
        return String.format(
                "JTextField textField%d = new JTextField(\"%s\");\n" +
                        "textField%d.setBounds(%d, %d, %d, %d);\n" +
                        "pane.add(textField%d);\n",
                textField.hashCode(), textField.getText(),
                textField.hashCode(), textField.getX(), textField.getY(), textField.getWidth(), textField.getHeight(),
                textField.hashCode()
        );
    }

    private String generateLabelCode(JLabel label) {
        return String.format(
                "JLabel label%d = new JLabel(\"%s\");\n" +
                        "label%d.setBounds(%d, %d, %d, %d);\n" +
                        "pane.add(label%d);\n",
                label.hashCode(), label.getText(),
                label.hashCode(), label.getX(), label.getY(), label.getWidth(), label.getHeight(),
                label.hashCode()
        );
    }

    private String generateMenuBarCode(JMenuBar menubar) {
        StringBuilder menuBarCode = new StringBuilder();
        menuBarCode.append(String.format(
                "CustomMenuBar menubar = new CustomMenuBar();\n" +
                        "menubar.setBounds(%d, %d, frame.getWidth(), %d);\n",
                menubar.getX(), menubar.getY(), menubar.getHeight()
        ));

        for (int i = 0; i < menubar.getMenuCount(); i++) {
            JMenu menu = menubar.getMenu(i);
            menuBarCode.append(generateMenuCode(menu));
        }

        menuBarCode.append("pane.add(menubar);");
        return menuBarCode.toString();
    }

    private String generateMenuCode(JMenu menu) {
        StringBuilder menuCode = new StringBuilder();
        menuCode.append(String.format(
                "JMenu menu%d = new JMenu(\"%s\");\n" +
                        "menubar.add(menu%d);\n",
                menu.hashCode(), menu.getText(),
                menu.hashCode()
        ));

        for (int i = 0; i < menu.getItemCount(); i++) {
            JMenuItem menuItem = menu.getItem(i);
            if (menuItem != null) {
                menuCode.append(generateMenuItemCode(menuItem, menu.hashCode()));
            }
        }

        return menuCode.toString();
    }

    private String generateMenuItemCode(JMenuItem menuItem, int menuHashCode) {
        return String.format(
                "JMenuItem menuItem%d = new JMenuItem(\"%s\");\n" +
                        "menu%d.add(menuItem%d);\n",
                menuItem.hashCode(), menuItem.getText(),
                menuHashCode, menuItem.hashCode()
        );
    }

    private String generateComboBoxCode(JComboBox<?> comboBox) {
        StringBuilder comboBoxCode = new StringBuilder();
        comboBoxCode.append(String.format(
                "JComboBox comboBox%d = new JComboBox(new String[] {",
                comboBox.hashCode()
        ));
        for (int i = 0; i < comboBox.getItemCount(); i++) {
            comboBoxCode.append(String.format("\"%s\"", comboBox.getItemAt(i)));
            if (i < comboBox.getItemCount() - 1) {
                comboBoxCode.append(", ");
            }
        }
        comboBoxCode.append(String.format(
                "});\ncomboBox%d.setBounds(%d, %d, %d, %d);\n" +
                        "pane.add(comboBox%d);\n",
                comboBox.hashCode(), comboBox.getX(), comboBox.getY(), comboBox.getWidth(), comboBox.getHeight(),
                comboBox.hashCode()
        ));
        return comboBoxCode.toString();
    }
}
