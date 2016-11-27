package ru.skanerxxl.rambler.database.entity;

import javax.persistence.*;

@Entity
@Table
public class Photo {
    @Id
    @GeneratedValue
    private long id;
    private String name;

    @Column(columnDefinition = "LONGBLOB")
    @Lob
    private byte[] body;

    public Photo() {
    }

    public Photo(String name, byte[] body) {
        this.name = name;
        this.body = body;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }
}
