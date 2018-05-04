package Data;

import java.util.Random;

public class TotallyRandom implements DataSource {

    @Override
    public String generate(int size) {
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        String lower = "abcdefghijklmnopqrstuvwxyz";

        String digits = "0123456789";

        String alphanum = upper + lower + digits;
        Random random=new Random();
        char []buf=new char[size];
        for (int idx = 0; idx < size; ++idx)
            buf[idx] = alphanum.charAt(random.nextInt(alphanum.length()));
        return new String(buf);
    }
}
