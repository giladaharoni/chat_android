package viewmodels;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;
@Entity
public class message {
    @PrimaryKey(autoGenerate = true)
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

    public message(int id, String content, Date created, boolean sent) {
        this.id = id;
        this.content = content;
        this.created = created;
        this.sent = sent;
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
