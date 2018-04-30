package Data;

import Auxiliary.Helpers;

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
        byte [] username= "PLACEHOLDER".getBytes();
        byte [] output= Helpers.concatenate(username,data);

        //Send data here
        return;
    }

}
