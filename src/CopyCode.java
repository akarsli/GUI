import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CopyCode implements ActionListener {
    private final Container dropArea;
    public CopyCode(Container dropArea) {
        this.dropArea = dropArea;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        CodeGenerator codeGenerator = new CodeGenerator(dropArea);
        codeGenerator.actionPerformed(null);
        String generatedCode = codeGenerator.getGeneratedCode();
        StringSelection stringSelection = new StringSelection(generatedCode);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
        JOptionPane.showMessageDialog(null, "Kod panoya kopyalandı!", "Bilgilendirme", JOptionPane.INFORMATION_MESSAGE);
    }
}
