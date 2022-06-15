package viewmodels;

import java.util.Date;

public class message {
    private int id;
    private String content;
    private Date created;
    private boolean sent;

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreated() {
        return created;
    }

    public boolean isSent() {
        return sent;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getContent() {
        return content;
    }
}
