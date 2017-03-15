import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by cyrus on 3/15/2017.
 */
public class TestFrom extends JPanel {
    private JLabel label1;
    private JButton button1;
    private int buttonCount = 0;
    private int autoCount = 1;
    private Lock lock;
    public TestFrom() {
        super(new FlowLayout());
        lock = new ReentrantLock();
        label1 = new JLabel("Test Label", JLabel.LEFT);
        add(label1);
        button1 = new JButton("Test Button");
        button1.addActionListener(e -> {
            lock.lock();
            try {
                label1.setText("Text Changed By Button " + ++ buttonCount);
            } finally {
                lock.unlock();
            }
        }); //Using lambdas, same as new ActionListener...
        add(button1);
    }
    public static void main(String[] args) {
        TestFrom form = new TestFrom();
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(form, BorderLayout.NORTH);
        frame.pack();
        frame.setBounds(300, 300, 300, 300);
        frame.setVisible(true);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        UpdateButton updateButton = new UpdateButton(form);
        executorService.execute(updateButton);
    }

    public JLabel getLabel1() {
        return label1;
    }

    public void setLabel1(JLabel label1) {
        this.label1 = label1;
    }

    public JButton getButton1() {
        return button1;
    }

    public void setButton1(JButton button1) {
        this.button1 = button1;
    }

    public int getButtonCount() {
        return buttonCount;
    }

    public void setButtonCount(int buttonCount) {
        this.buttonCount = buttonCount;
    }

    public Lock getLock() {
        return lock;
    }

    public void setLock(Lock lock) {
        this.lock = lock;
    }


    public int getAutoCount() {
        return autoCount;
    }

    public void setAutoCount(int autoCount) {
        this.autoCount = autoCount;
    }
}
