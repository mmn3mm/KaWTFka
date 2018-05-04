package GUI;

import PeerManagement.Node;
import sun.applet.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends JFrame{
    private JTextField textField1;
    private JButton button1;
    private JPanel panel;

    public LoginForm()
    {
        setContentPane(panel);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username= textField1.getText();
                Node node = Node.getInstance();
                node.start(username);
                JFrame frame = new ConnectionForm();
                frame.setTitle("Welcome " +Node.getInstance().getUsername() +".. ");
                frame.setPreferredSize(new Dimension(500,300));
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setResizable(true);
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
                dispose();
            }
        });
    }
}
