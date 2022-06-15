package viewmodels;

import java.util.Date;

public class contact {
    private String id;
    private String name;
    private String server;
    private String last;
    private Date lastDate;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public void setLastDate(Date lastDate) {
        this.lastDate = lastDate;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getServer() {
        return server;
    }

    public String getLast() {
        return last;
    }

    public Date getLastDate() {
        return lastDate;
    }
}
