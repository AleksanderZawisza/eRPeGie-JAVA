package console;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class NormCons extends JFrame {
    public static void main(String[] args) {
        Console console = new Console();
        console.init();
        NormCons launcher = new NormCons();
        launcher.setVisible(false);
        console.getFrame().setLocation(
                launcher.getX() + launcher.getWidth() + launcher.getInsets().right,
                launcher.getY());
    }

    private NormCons() {
        super();
        setSize(600, 600);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}

class Console {
    final JFrame frame = new JFrame();
    public Console() {
        JTextArea textArea = new JTextArea(24, 80);
        textArea.setBackground(Color.BLACK);
        textArea.setForeground(Color.WHITE);
        textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        System.setOut(new PrintStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                textArea.append(String.valueOf((char) b));
            }
        }));
        frame.add(textArea);
    }
    public void init() {
        frame.pack();
        frame.setVisible(true);
    }
    public JFrame getFrame() {
        return frame;
    }
}