package ua.com.diakin.medicalclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Admin on 03.12.2015.
 */
@Entity
@Table(name = "files")
public class File extends BaseEntity {
/*
    @Column(name = "file_path")
    protected String filePath;
*/

    @Column(name = "comment")
    protected String comment;

    @Column(name = "image")
    protected byte[] image;

 /*   public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
*/
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
