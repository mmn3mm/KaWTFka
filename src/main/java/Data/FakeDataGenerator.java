package Data;

import Auxiliary.Helpers;
import PeerManagement.Node;

public class FakeDataGenerator implements Runnable {
    DataSource dataSource;
    public FakeDataGenerator(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void run() {
        while (true) {
            Object data = dataSource.generate();
            byte[] bytes= Helpers.serialize(data);
            send(bytes);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void send(byte[] data) {
        byte[] username = Node.getInstance().getUsername().getBytes();
        byte [] output= Helpers.concatenate(username,data);

        //Send data here
        return;
    }

}
