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
                        private static JComponent selectedComponent;
                        public static void main(String[] args) {
                            JFrame frame = new JFrame("GUI");
                            Container pane = frame.getContentPane();
                            frame.setLayout(null);
                            frame.setSize(%d ,%d);
                """, width, height));

        for (Component comp : dropArea.getComponents()) {
            if (comp instanceof JButton) {
                JButton button = (JButton) comp;
                code.append(generateButtonCode(button)).append("\n");
            } else if (comp instanceof JTextField) {
                JTextField textField = (JTextField) comp;
                code.append(generateTextFieldCode(textField)).append("\n");
            } else if (comp instanceof JLabel) {
                JLabel label = (JLabel) comp;
                code.append(generateLabelCode(label)).append("\n");
            } else if (comp instanceof JMenuBar) {
                JMenuBar menubar = (JMenuBar) comp;
                code.append(generateMenuBarCode(menubar)).append("\n");
            }
        }

        code.append("frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);\n");
        code.append("frame.setFocusable(true);\n");
        code.append("frame.setVisible(true);\n");
        code.append("}\n");
        code.append("}\n");

        System.out.println(code.toString());
    }

    private String generateButtonCode(JButton button) {
        return String.format(
                "JButton button%d = new JButton(\"%s\");\n" +
                        "button%d.setBounds(%d, %d, %d, %d);\n" +
                        "pane.add(button%d);",
                button.hashCode(), button.getText(),
                button.hashCode(), button.getX(), button.getY(), button.getWidth(), button.getHeight(),
                button.hashCode()
        );
    }

    private String generateTextFieldCode(JTextField textField) {
        return String.format(
                "JTextField textField%d = new JTextField(\"%s\");\n" +
                        "textField%d.setBounds(%d, %d, %d, %d);\n" +
                        "pane.add(textField%d);",
                textField.hashCode(), textField.getText(),
                textField.hashCode(), textField.getX(), textField.getY(), textField.getWidth(), textField.getHeight(),
                textField.hashCode()
        );
    }

    private String generateLabelCode(JLabel label) {
        return String.format(
                "JLabel label%d = new JLabel(\"%s\");\n" +
                        "label%d.setBounds(%d, %d, %d, %d);\n" +
                        "pane.add(label%d);",
                label.hashCode(), label.getText(),
                label.hashCode(), label.getX(), label.getY(), label.getWidth(), label.getHeight(),
                label.hashCode()
        );
    }

    private String generateMenuBarCode(JMenuBar menubar) {
        StringBuilder menuBarCode = new StringBuilder();
        menuBarCode.append(String.format(
                "JMenuBar menubar%d = new JMenuBar();\n" +
                        "menubar%d.setBounds(%d, %d, %d, %d);\n",
                menubar.hashCode(),
                menubar.hashCode(), menubar.getX(), menubar.getY(), menubar.getWidth(), menubar.getHeight()
        ));

        for (int i = 0; i < menubar.getMenuCount(); i++) {
            JMenu menu = menubar.getMenu(i);
            menuBarCode.append(generateMenuCode(menu, menubar.hashCode())).append("\n");
        }

        menuBarCode.append(String.format("pane.add(menubar%d);", menubar.hashCode()));
        return menuBarCode.toString();
    }

    private String generateMenuCode(JMenu menu, int menuBarHashCode) {
        StringBuilder menuCode = new StringBuilder();
        menuCode.append(String.format(
                "JMenu menu%d = new JMenu(\"%s\");\n" +
                        "menubar%d.add(menu%d);\n",
                menu.hashCode(), menu.getText(),
                menuBarHashCode, menu.hashCode()
        ));

        for (int i = 0; i < menu.getItemCount(); i++) {
            JMenuItem menuItem = menu.getItem(i);
            if (menuItem != null) {
                menuCode.append(generateMenuItemCode(menuItem, menu.hashCode())).append("\n");
            }
        }

        return menuCode.toString();
    }

    private String generateMenuItemCode(JMenuItem menuItem, int menuHashCode) {
        return String.format(
                "JMenuItem menuItem%d = new JMenuItem(\"%s\");\n" +
                        "menu%d.add(menuItem%d);",
                menuItem.hashCode(), menuItem.getText(),
                menuHashCode, menuItem.hashCode()
        );
    }
}
