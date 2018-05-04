package Data;

public class Numbers implements DataSource {

    @Override
    public String generate(int size) {
        return "123456";
    }
}
