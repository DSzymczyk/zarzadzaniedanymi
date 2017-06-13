package com.agh.data;

/**
 * Created by Dawid on 11.06.2017.
 */
public class Node {
    int id;
    float x;
    float y;
    float z;
    float temperature;

    public Node(int id, float x, float y, float z, float temperature) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.z = z;
        this.temperature = temperature;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
