package com.agh.data;

import java.util.List;

/**
 * Created by Dawid on 12.06.2017.
 */
public class ChartDataProxy {
    List<Node> nodesList;
    List<Element> elementList;

    public ChartDataProxy() {
    }

    public ChartDataProxy(List<Node> nodesList, List<Element> elementList) {
        this.nodesList = nodesList;
        this.elementList = elementList;
    }

    public List<Node> getNodesList() {
        return nodesList;
    }

    public void setNodesList(List<Node> nodesList) {
        this.nodesList = nodesList;
    }

    public List<Element> getElementList() {
        return elementList;
    }

    public void setElementList(List<Element> elementList) {
        this.elementList = elementList;
    }
}
