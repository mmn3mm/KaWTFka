package Storage.Types;

public class File implements Data {
    String senderUsername;

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    @Override
    public String details() {
        return "That's a file";
    }
}
