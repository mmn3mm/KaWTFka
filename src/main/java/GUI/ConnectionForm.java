package GUI;

import PeerManagement.Friend;
import PeerManagement.LonelyPeer;
import PeerManagement.Node;
import Storage.DataStorage;
import Storage.Types.Data;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class ConnectionForm extends JFrame{
    private JPanel panel;
    private JTextArea textArea1;
    private JButton viewDataButton;
    private JButton viewFriendsButton;
    private JButton startSendingButton;

    public ConnectionForm(){
        setContentPane(panel);
        textArea1.setEditable(false);
        viewDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea1.setText("");
                ArrayList<Data> storage= DataStorage.getInstance().getStorage();
                for (Data data: storage) {
                    textArea1.append(data.details()+" sent by " + data.getSenderUsername()+"\n");
                }
            }
        });

        viewFriendsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea1.setText("");
                HashMap<String, Friend> friends= Node.getInstance().getFriends();
                if(friends.isEmpty())
                    textArea1.append("There's no friends.");
                for (Map.Entry friend : friends.entrySet()) {
                    textArea1.append(friend.getKey().toString()+"\n");
                    Friend valueOfFriend=(Friend)friend.getValue();
                    HashMap<UUID, LonelyPeer> devices=valueOfFriend.getDevices();
                    for(Map.Entry Device :devices.entrySet()) {
                        textArea1.append("    " + Device.getKey().toString()+"\n");
                    }
                }
            }
        });
        startSendingButton.addActionListener(new ActionListener() {
            int delay = 1000; //milliseconds
            ActionListener taskPerformer = new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    Node.getInstance().randomData();
                }
            };

            @Override
            public void actionPerformed(ActionEvent e) {
                Timer timer=new javax.swing.Timer(delay, taskPerformer);
                timer.start();

            }
        });

    }
}
