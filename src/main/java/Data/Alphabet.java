package Data;

import Data.DataSource;

public class Alphabet implements DataSource {

    @Override
    public String generate(int size) {
        return "ABCDEFGH";
    }
}
