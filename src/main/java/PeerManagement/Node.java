package PeerManagement;

import Auxiliary.Helpers;
import de.tum.in.www1.jReto.Connection;
import de.tum.in.www1.jReto.LocalPeer;
import de.tum.in.www1.jReto.RemotePeer;
import de.tum.in.www1.jReto.module.wlan.WlanModule;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.Executors;

public class Node {

    String username;

    private HashMap<UUID,LonelyPeer> newFriends;
    private HashMap<String,Friend> friends;

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

    /**
     * This function sends a msg or a small object, files are
     * sent through outTransfer
     *
     * @param peer The peer to send the data to
     * @param data The data in the form of byte array
     */
    private void sendData(RemotePeer peer, byte[] data) {
        Connection curConnection = peer.connect();
        curConnection.setOnClose(a -> System.out.println("Connection closed"));
        curConnection.setOnData((a, b) -> System.out.println("DATA RECIEVED"));
        curConnection.send(ByteBuffer.wrap(data));
        peer.getUniqueIdentifier();

    }

    private void onPeerDiscovered(RemotePeer discoveredPeer) {
        System.out.println("A new friend is here, Welcome Him!");
        UUID discoveredUUID=discoveredPeer.getUniqueIdentifier();
        LonelyPeer newFriend= new LonelyPeer();
        newFriend.setMyPeer(discoveredPeer);
        newFriend.setCurrentConnection(discoveredPeer.connect());
        newFriends.put(discoveredUUID,newFriend);
        sendUsername(newFriend); //Tell my name to the new friend.
    }

    private void sendUsername(LonelyPeer newFriend)
    {
        byte [] bytes={0}; //Set identifier
/*
        bytes= Helpers.concatenate(bytes,)
*/


    }

    private boolean usernameExists(String username)
    {
        for (Friend temp:friends)
        {
            if(temp.getUsername().equals(username))
                return true;

        }
        return false;
    }
    private boolean uuidExists(UUID uuid)
    {
        for (Friend temp:friends)
        {

            if(temp.getDevices().containsKey(uuid))
                return true;
        }
        return false;
    }

    private void onPeerLeave(RemotePeer removedPeer) {
        //A peer left
    }

    private void onIncomingConnection(RemotePeer peer, Connection incomingConnection) {
        //Someone connected to us.
    }
}
