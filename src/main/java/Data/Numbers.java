package Data;

public class Numbers implements DataSource {

    @Override
    public String generate() {
        return "123456";
    }
}
