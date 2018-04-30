import Data.FakeDataGenerator;
import Data.Numbers;
import PeerManagement.Node;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Node node = Node.getInstance();
        node.start("Somerandom Username");

    }
}
