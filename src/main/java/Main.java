import Data.FakeDataGenerator;
import Data.Numbers;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        FakeDataGenerator fkg=new FakeDataGenerator(new Numbers());
        Thread t1=new Thread(fkg);
        t1.start();
        Scanner x=new Scanner(System.in);
        x.nextLine();

    }
}
