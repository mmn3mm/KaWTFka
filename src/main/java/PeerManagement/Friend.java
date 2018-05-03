package PeerManagement;

import de.tum.in.www1.jReto.RemotePeer;

import java.util.HashMap;
import java.util.UUID;

public class Friend {

    private HashMap<UUID,LonelyPeer> devices=new HashMap<>();

    public HashMap<UUID, LonelyPeer> getDevices() {
        return devices;
    }

    public void setDevices(HashMap<UUID, LonelyPeer> devices) {
        this.devices = devices;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String username;

}
