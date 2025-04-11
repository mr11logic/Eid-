package example;
import db.Entity;
import db.Trackable;
import java.util.Date;

public class Document extends Entity implements Trackable {
    private String content;
    private Date creationDate;
    private Date lastModificationDate;

    public Document(String content) {
        super();
        this.content = content;
        this.creationDate = new Date();
        this.lastModificationDate = new Date();
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
        this.lastModificationDate = new Date();
    }
    @Override
    public Date getCreationDate() {
        return creationDate;
    }
    @Override
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
    @Override
    public Date getLastModificationDate() {
        return lastModificationDate;
    }
    @Override
    public void setLastModificationDate(Date lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
    }
    @Override
    public Entity copy() {
        Document copy = new Document(this.content);
        copy.id = this.id;
        if (this.creationDate != null) {
            copy.creationDate = new Date(this.creationDate.getTime());
        } else {
            copy.creationDate = null;
        }
        if (this.lastModificationDate != null) {
            copy.lastModificationDate = new Date(this.lastModificationDate.getTime());
        } else {
            copy.lastModificationDate = null;
        }

        return copy;
    }
    @Override
    public int getEntityCode() {
        return 0;
    }
}