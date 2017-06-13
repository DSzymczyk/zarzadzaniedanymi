package com.agh.db.entity;

import javax.persistence.*;

/**
 * Created by Dawid on 11.06.2017.
 */
@Entity
@Table(name = "node")
public class NodeEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="fileId")
    private FileEntity fileEntity;

    @Column(name = "nodeId")
    private int nodeId;

    @Column(name = "x")
    private float x;

    @Column(name = "y")
    private float y;

    @Column(name = "z")
    private float z;

    @Column(name = "temperature")
    private float temperature;

    public NodeEntity() {
    }

    public NodeEntity(FileEntity fileEntity, int nodeId, float x, float y, float z, float temperature) {
        this.fileEntity = fileEntity;
        this.nodeId = nodeId;
        this.x = x;
        this.y = y;
        this.z = z;
        this.temperature = temperature;
    }

    public int getNodeId() {
        return nodeId;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public FileEntity getFileEntity() {
        return fileEntity;
    }

    public void setFileEntity(FileEntity fileEntity) {
        this.fileEntity = fileEntity;
    }
}
