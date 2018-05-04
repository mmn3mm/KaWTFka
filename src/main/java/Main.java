import Data.FakeDataGenerator;
import Data.Numbers;
import GUI.LoginForm;
import PeerManagement.Node;

import javax.swing.*;
import java.awt.*;


public class Main {
    public static JFrame frame;
    public static void main(String[] args) throws InterruptedException {
         frame = new LoginForm();
        frame.setPreferredSize(new Dimension(500,300));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
