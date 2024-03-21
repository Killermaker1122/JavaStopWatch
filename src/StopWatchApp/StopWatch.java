package StopWatchApp;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class StopWatch implements ActionListener {

    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JButton start_B = new JButton("Start");
    JButton reset_B = new JButton("Reset");
    JLabel timeLabel = new JLabel();
    int elapsedTime = 0;
    int seconds = 0;
    int minutes = 0;
    int hours = 0;
    boolean started = false;
    String seconds_string = String.format("%02d", seconds);
    String minutes_string = String.format("%02d", minutes);
    String hours_string = String.format("%02d", hours);

    Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            elapsedTime = elapsedTime + 1000;
            hours = (elapsedTime / 3600000);
            minutes = (elapsedTime / 60000) % 60;
            seconds = (elapsedTime / 1000) % 60;
            seconds_string = String.format("%02d", seconds);
            minutes_string = String.format("%02d", minutes);
            hours_string = String.format("%02d", hours);
            timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);
        }
    });

    StopWatch() {
        panel.setLayout(null);
        panel.setBackground(new Color(128, 128, 128)); // Dark Gray
        panel.setBounds(0, 0, 420, 420);

        timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);
        timeLabel.setBounds(100, 100, 200, 100);
        timeLabel.setFont(new Font("Arial", Font.BOLD, 35));
        timeLabel.setBorder(BorderFactory.createBevelBorder(1));
        timeLabel.setOpaque(true);
        timeLabel.setHorizontalAlignment(JTextField.CENTER);
        timeLabel.setForeground(Color.black);

        start_B.setBounds(100, 200, 100, 50);
        start_B.setFont(new Font("Arial", Font.PLAIN, 20));
        start_B.setFocusable(false);
        start_B.setBackground(new Color(0, 153, 0)); // Green
        start_B.setForeground(Color.WHITE);
        start_B.addActionListener(this);

        reset_B.setBounds(200, 200, 100, 50);
        reset_B.setFont(new Font("Arial", Font.PLAIN, 20));
        reset_B.setFocusable(false);
        reset_B.setBackground(new Color(255, 51, 51)); // Red
        reset_B.setForeground(Color.WHITE);
        reset_B.addActionListener(this);

        panel.add(timeLabel);
        panel.add(start_B);
        panel.add(reset_B);

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 420);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == start_B) {
            start();
            if (started == false) {
                started = true;
                start_B.setText("Stop");
                start();
            } else {
                started = false;
                start_B.setText("Start");
                stop();
            }
        }
        if (e.getSource() == reset_B) {
            started = false;
            start_B.setText("Start");
            reset();
        }
    }

    void start() {
        timer.start();
    }

    void stop() {
        timer.stop();
    }

    void reset() {
        timer.stop();
        elapsedTime = 0;
        seconds = 0;
        minutes = 0;
        hours = 0;
        seconds_string = String.format("%02d", seconds);
        minutes_string = String.format("%02d", minutes);
        hours_string = String.format("%02d", hours);
        timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);
    }

    public static void main(String[] args) {
        new StopWatch();
    }
}
