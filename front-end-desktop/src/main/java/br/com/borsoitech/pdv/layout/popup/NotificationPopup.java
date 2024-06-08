package br.com.borsoitech.pdv.layout.popup;

import javax.swing.*;
import java.awt.*;

public class NotificationPopup extends JWindow {

    private final Timer timer;

    public NotificationPopup(String message) {
        setLayout(new BorderLayout());
        setSize(300, 100);
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);

        JLabel messageLabel = new JLabel(message, SwingConstants.CENTER);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 16));
        messageLabel.setOpaque(true);
        messageLabel.setBackground(new Color(0, 128, 0));
        messageLabel.setForeground(Color.WHITE);

        add(messageLabel, BorderLayout.CENTER);

        timer = new Timer(4000, e -> dispose());
        timer.setRepeats(false);
    }

    public void showPopup() {
        setLocation(getScreenWidth() - getWidth() - 10, 10);
        setVisible(true);
        timer.start();
    }

    private int getScreenWidth() {
        return Toolkit.getDefaultToolkit().getScreenSize().width;
    }
}
