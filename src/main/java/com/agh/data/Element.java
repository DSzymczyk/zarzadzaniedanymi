package com.agh.data;

import java.util.List;

/**
 * Created by Dawid on 11.06.2017.
 */
public class Element {
    int id;
    int sId;
    List<Integer> nodeList;

    public Element(int id, int sId, List<Integer> nodeList) {
        this.id = id;
        this.sId = sId;
        this.nodeList = nodeList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getsId() {
        return sId;
    }

    public void setsId(int sId) {
        this.sId = sId;
    }

    public List<Integer> getNodeList() {
        return nodeList;
    }

    public void setNodeList(List<Integer> nodeList) {
        this.nodeList = nodeList;
    }
}
