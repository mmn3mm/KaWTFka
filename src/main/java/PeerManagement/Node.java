package PeerManagement;

import Auxiliary.Helpers;
import de.tum.in.www1.jReto.Connection;
import de.tum.in.www1.jReto.LocalPeer;
import de.tum.in.www1.jReto.RemotePeer;
import de.tum.in.www1.jReto.module.wlan.WlanModule;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.*;
import java.util.concurrent.Executors;

public class Node {

    String username;

    private HashMap<UUID, LonelyPeer> newFriends = new HashMap<>();



    public void setFriends(HashMap<String, Friend> friends) {
        this.friends = friends;
    }

    private HashMap<String, Friend> friends = new HashMap<>();

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

    public HashMap<String, Friend> getFriends() {
        return friends;
    }

    private void onPeerDiscovered(RemotePeer discoveredPeer) {
        System.out.println("A new friend is here, Welcome Him!");
        UUID discoveredUUID=discoveredPeer.getUniqueIdentifier();
        LonelyPeer newFriend= new LonelyPeer();
        newFriend.setMyPeer(discoveredPeer);
        newFriends.put(discoveredUUID,newFriend);
        sendUsername(newFriend); //Tell my name to the new friend.
    }

    private void sendUsername(LonelyPeer newFriend) {
        byte [] bytes={0}; //Set identifier

        bytes = Helpers.concatenate(bytes, Helpers.serialize(username));
        newFriend.getOngoingConnection().send(ByteBuffer.wrap(bytes)); //Send the data
    }

    public void saveFriend(LonelyPeer lonelyPeer) {
        //Now our friend has a name
        newFriends.remove(lonelyPeer);
        System.out.println("Welcome " + lonelyPeer.getUsername());
        Friend friend;
        if (usernameExists(lonelyPeer.getUsername())) {
            friend = friends.get(lonelyPeer.getUsername()); //If its a new device for a friend
        } else {
            friend = new Friend(); //If its a new friend.
            friend.setUsername(lonelyPeer.getUsername());
            friends.put(lonelyPeer.getUsername(), friend);
        }
        friend.getDevices().put(lonelyPeer.getMyPeer().getUniqueIdentifier(), lonelyPeer);

    }
    private boolean usernameExists(String username)
    {
        return friends.containsKey(username);
    }
    private boolean uuidExists(UUID uuid)
    {
        for (Friend temp : friends.values())
        {

            if(temp.getDevices().containsKey(uuid))
                return true;
        }
        return false;
    }

    private void onPeerLeave(RemotePeer removedPeer) {
        for (Map.Entry friend : friends.entrySet()) {
            Friend valueOfFriend=(Friend)friend.getValue();
            if(valueOfFriend.getDevices().containsKey(removedPeer.getUniqueIdentifier()));
            {
                valueOfFriend.getDevices().get(removedPeer.getUniqueIdentifier()).getOngoingConnection().close();
                valueOfFriend.getDevices().get(removedPeer.getUniqueIdentifier()).getIncomingConnection().close();
            }
            valueOfFriend.getDevices().remove(removedPeer.getUniqueIdentifier());
            if(valueOfFriend.getDevices().size()==0)
                friends.remove(friend.getKey());
        }
    }

    private void onIncomingConnection(RemotePeer peer, Connection incomingConnection) {
        //Someone connected to us.
        if (newFriends.containsKey(peer.getUniqueIdentifier())) {
            newFriends.get(peer.getUniqueIdentifier()).setIncomingConnection(incomingConnection);
        } else {
            LonelyPeer lonelyPeer = new LonelyPeer();
            lonelyPeer.setMyPeer(peer);
            lonelyPeer.setIncomingConnection(incomingConnection);
            newFriends.put(peer.getUniqueIdentifier(), lonelyPeer);
        }
    }
}
