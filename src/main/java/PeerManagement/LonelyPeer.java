package PeerManagement;

import Auxiliary.Helpers;
import de.tum.in.www1.jReto.RemotePeer;

import de.tum.in.www1.jReto.Connection;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashMap;

public class LonelyPeer {
    private Connection incomingConnection;

    private Connection ongoingConnection; //Used to send
    private RemotePeer myPeer;


    private String username;


    public Connection getIncomingConnection() {
        return incomingConnection;
    }



    public void setIncomingConnection(Connection currentConnection) {

        this.incomingConnection = currentConnection;
        incomingConnection.setOnData((c, data) -> acceptData(data));
        incomingConnection.setOnClose(c-> System.out.println("Someone closed"));
    }

    public RemotePeer getMyPeer() {
        return myPeer;
    }
    public void acceptData(ByteBuffer input)
    {
        byte[] data=input.array();
        byte identifier =data[0];
        data= Arrays.copyOfRange(data, 1, data.length);
        switch(identifier) {
            //Do you know me?
            //A friend told me his username.
            case 0:
                changeUsername(data);

        }
    }

    private void changeUsername(byte[] data) {
        username = (String) Helpers.deserialize(data);
        Node.getInstance().saveFriend(this);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setMyPeer(RemotePeer myPeer) {
        /*
        Add some notice upon connection with someone.
         */
        this.myPeer = myPeer;
        ongoingConnection = myPeer.connect();
        ongoingConnection.setOnClose(c-> System.out.println("someone closed"));//Remove from friends.
        ongoingConnection.setOnConnect(c -> System.out.println("Someone connected"));
        ongoingConnection.setOnData((c, data) -> acceptData(data));
    }

    public Connection getOngoingConnection() {
        return ongoingConnection;
    }

    public void setOngoingConnection(Connection ongoingConnection) {
        this.ongoingConnection = ongoingConnection;

    }

}
