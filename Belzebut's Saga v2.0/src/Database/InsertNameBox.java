package Database;

import game.Game;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class InsertNameBox extends JFrame {

    private String defaultText = "Insert your name...";
    JTextField textField = new JTextField(defaultText, 20);
    JButton button = new JButton("OK");

    public InsertNameBox(Game thisGame) {
        super("Player Name");
        setLayout(new FlowLayout());

        // customizes appearance: font, foreground, background
        textField.setFont(new java.awt.Font("Arial", Font.ITALIC | Font.BOLD, 14));
        textField.setForeground(Color.BLACK);
        textField.setBackground(Color.WHITE);

        // customizes text selection
        textField.setSelectionColor(Color.WHITE);
        textField.setSelectedTextColor(Color.GRAY);

        // sets initial selection
        textField.setSelectionStart(0);
        textField.setSelectionEnd(19);

        // adds event listener which listens to Enter key event
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if(!textField.getText().equals(defaultText)) {
                    System.out.println("Your name: " + textField.getText());
                    thisGame.setName(textField.getText());
                }else
                    System.out.println("Default name: Tudisie");
                setVisible(false);
            }
        });

        // adds key event listener
        textField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent event) {
                String content = textField.getText();
                if (!content.equals("")) {
                    button.setEnabled(true);
                } else {
                    button.setEnabled(false);
                }
            }
        });

        // adds action listener for the button
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if(!textField.getText().equals(defaultText)) {
                    System.out.println("Your name: " + textField.getText());
                    thisGame.setName(textField.getText());
                }else
                    System.out.println("Default name: Tudisie");
                setVisible(false);
            }
        });

        add(textField);
        add(button);

        setSize(300, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}