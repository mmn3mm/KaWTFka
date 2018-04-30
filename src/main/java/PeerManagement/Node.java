package PeerManagement;

import de.tum.in.www1.jReto.Connection;
import de.tum.in.www1.jReto.LocalPeer;
import de.tum.in.www1.jReto.RemotePeer;
import de.tum.in.www1.jReto.module.wlan.WlanModule;

import java.io.IOException;
import java.util.Collections;
import java.util.concurrent.Executors;

public class Node {

    String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private static Node myNode = new Node();

    public static Node getInstance() {
        return myNode;
    }

    private WlanModule wlanModule;
    private LocalPeer localPeer;
    private Connection connection;

    public void start(String username) {
        this.username = username;
        try {
            wlanModule = new WlanModule("mmn3mm");
        } catch (IOException e) {
            e.printStackTrace();
        }

        localPeer = new LocalPeer(Collections.singletonList(wlanModule), Executors.newSingleThreadExecutor());
        localPeer.start(
                this::onPeerDiscovered,
                this::onPeerLeave,
                this::onIncomingConnection
        );
    }

    private void onPeerDiscovered(RemotePeer discoveredPeer) {
        System.out.println("LOLOLOLOLOL");
    }

    private void onPeerLeave(RemotePeer removedPeer) {
        //A peer left
    }

    private void onIncomingConnection(RemotePeer peer, Connection incomingConnection) {
        //Someone connected to us.
    }
}
