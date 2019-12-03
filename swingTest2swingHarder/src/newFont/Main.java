package newFont;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Main {

    JFrame window;
    JPanel textPanel;
    JLabel textLabel;
    Font normalFont = new Font("Times New Roman", Font.PLAIN, 28);
    Font pixelMplus;

    public static void main(String[] args) {

        new Main();
    }

    public Main(){

        try{
            pixelMplus = Font.createFont(Font.TRUETYPE_FONT, new File("PixelMplus10-Regular.ttf")).deriveFont(30f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("PixelMplus10-Regular.ttf")));

        }
        catch(IOException | FontFormatException e){

        }

        window = new JFrame();
        window.setSize(800,600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        window.setLayout(null);

        textPanel = new JPanel();
        textPanel.setBounds(100, 250, 600, 250);
        textPanel.setBackground(Color.black);

        textLabel = new JLabel("Hello traveler!");
        textLabel.setBackground(Color.black);
        textLabel.setForeground(Color.white);
        textLabel.setFont(pixelMplus);
        textPanel.add(textLabel);

        window.add(textPanel);

        window.setVisible(true);

    }

}