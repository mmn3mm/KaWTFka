package Storage.Types;

import Storage.Types.Data;

public class SimpleString implements Data {
    String senderUsername;

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    String details;

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }


    @Override
    public String details() {
        return details;
    }
}
