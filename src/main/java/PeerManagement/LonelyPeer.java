package PeerManagement;

import de.tum.in.www1.jReto.RemotePeer;

import de.tum.in.www1.jReto.Connection;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class LonelyPeer {
    public Connection getCurrentConnection() {
        return incomingConnection;
    }

    public void setCurrentConnection(Connection currentConnection) {
        this.incomingConnection = currentConnection;
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
                break;

        }
    }

    public void setMyPeer(RemotePeer myPeer) {
        /*
        Add some notice upon connection with someone.
         */
        this.myPeer = myPeer;
        incomingConnection =myPeer.connect();
        incomingConnection.setOnClose(c-> System.out.println("Someone disconnected"));
        incomingConnection.setOnConnect(c-> System.out.println("Someone connected"));
        incomingConnection.setOnData((c,data)-> acceptData(data));

    }
    private Connection incomingConnection;
    private Connection ongoingConnection; //Used to send
    private RemotePeer myPeer;
}
