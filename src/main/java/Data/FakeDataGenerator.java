package Data;

import Auxiliary.Helpers;
import PeerManagement.Node;
import Storage.DataStorage;
import Storage.Types.SimpleString;

import java.util.Random;

public class FakeDataGenerator implements Runnable {
    DataSource dataSource;
    public FakeDataGenerator(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void run() {

            Object data = dataSource.generate(new Random().nextInt(20));
            byte[] bytes= Helpers.serialize(data);
            SimpleString simpleString=new SimpleString();
            simpleString.setSenderUsername(Node.getInstance().getUsername());
            simpleString.setDetails((String)data);
            DataStorage.getInstance().save(simpleString);
            send(bytes);
        }


    public void send(byte[] data) {
        byte[] identifier = {1, 0};
        byte[] output = Helpers.concatenate(identifier, data);
        Node currNode=Node.getInstance();
        currNode.sendData(output);
        return;
    }

}
