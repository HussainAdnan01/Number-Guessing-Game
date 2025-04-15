import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessingGame {
    private int randomNumber;
    private int attempts;
    private JFrame frame;
    private JTextField guessField;
    private JLabel messageLabel, attemptsLabel;
    private JButton guessButton, restartButton;

    public NumberGuessingGame() {
        randomNumber = new Random().nextInt(100) + 1;
        attempts = 0;
        
        frame = new JFrame("Number Guessing Game");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(240, 248, 255));
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));
        panel.setBackground(new Color(240, 248, 255));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        messageLabel = new JLabel("Guess a number between 1 and 100", SwingConstants.CENTER);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(messageLabel);
        
        guessField = new JTextField(5);
        guessField.setHorizontalAlignment(JTextField.CENTER);
        guessField.setFont(new Font("Arial", Font.BOLD, 14));
        guessField.addActionListener(new GuessHandler()); 
        panel.add(guessField);
        
        attemptsLabel = new JLabel("Attempts: 0", SwingConstants.CENTER);
        attemptsLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(attemptsLabel);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(240, 248, 255));
        
        guessButton = new JButton("Guess");
        guessButton.setBackground(new Color(60, 179, 113));
        guessButton.setForeground(Color.WHITE);
        guessButton.setFont(new Font("Arial", Font.BOLD, 14));
        guessButton.addActionListener(new GuessHandler());
        buttonPanel.add(guessButton);
        
        restartButton = new JButton("Restart");
        restartButton.setBackground(new Color(255, 69, 0));
        restartButton.setForeground(Color.WHITE);
        restartButton.setFont(new Font("Arial", Font.BOLD, 14));
        restartButton.addActionListener(e -> restartGame());
        restartButton.setVisible(false);
        buttonPanel.add(restartButton);
        
        frame.add(panel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        
        frame.setVisible(true);
    }

    private class GuessHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                int guess = Integer.parseInt(guessField.getText());
                attempts++;
                attemptsLabel.setText("Attempts: " + attempts);
                guessField.setText(""); 
                
                if (guess < 1 || guess > 100) {
                    messageLabel.setText("Please Enter A Number Between 1 and 100");
                } else if (guess < randomNumber) {
                    messageLabel.setText("Too Low! Try again.");
                } else if (guess > randomNumber) {
                    messageLabel.setText("Too High! Try again.");
                } else {
                    messageLabel.setText("Correct! You guessed it in " + attempts + " attempts.");
                    guessButton.setEnabled(false);
                    restartButton.setVisible(true);
                }
            } catch (NumberFormatException ex) {
                messageLabel.setText("Invalid input! Enter a number.");
            }
        }
    }

    private void restartGame() {
        randomNumber = new Random().nextInt(100) + 1;
        attempts = 0;
        guessField.setText("");
        messageLabel.setText("Guess a number between 1 and 100");
        attemptsLabel.setText("Attempts: 0");
        guessButton.setEnabled(true);
        restartButton.setVisible(false);
    }
    
    public static void main(String[] args) {
        new NumberGuessingGame();
    }
}
