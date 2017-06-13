package com.agh.db.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * Created by Dawid on 11.06.2017.
 */
@Entity
@Table(name = "file")
public class FileEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column(name = "valid")
    private boolean valid;

    @OneToMany(mappedBy="fileEntity")
    private List<NodeEntity> nodes;

    @OneToMany(mappedBy="elementFileEntity")
    private List<ElementEntity> elements;

    public FileEntity() {
    }

    public FileEntity(String name, Date date, boolean valid) {
        this.name = name;
        this.date = date;
        this.valid = valid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<NodeEntity> getNodes() {
        return nodes;
    }

    public void setNodes(List<NodeEntity> nodes) {
        this.nodes = nodes;
    }

    public List<ElementEntity> getElements() {
        return elements;
    }

    public void setElements(List<ElementEntity> elements) {
        this.elements = elements;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public Long getId() {
        return id;
    }
}
