package com.agh.service;

import com.agh.data.*;
import com.agh.db.entity.ElementEntity;
import com.agh.db.entity.FileEntity;
import com.agh.db.entity.NodeEntity;
import com.agh.db.repository.ElementRepository;
import com.agh.db.repository.FileRepository;
import com.agh.db.repository.NodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Dawid on 11.06.2017.
 */
@Service
public class InputService {

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private NodeRepository nodeRepository;

    @Autowired
    private ElementRepository elementRepository;

    public void validateData(String filename, String data) {
        boolean readingNodes = true;
        List<Node> nodeList = new ArrayList<>();
        List<Element> elementList = new ArrayList<>();
        String lines[] = data.split("\\r?\\n");
        lines = Arrays.copyOfRange(lines, 1, lines.length);
        for (String line : lines) {

            if (readingNodes) {
                if (line.equals("*ELEMENT_SOLID")) {
                    readingNodes = false;
                    continue;
                }
                String[] values = line.split(",");
                if(values.length !=5){
                    saveFileToDb(filename, false);
                    break;
                }
                int id = Integer.parseInt(values[0]);
                float x = Float.parseFloat(values[1]) * 100.0f;
                float y = Float.parseFloat(values[2]) * 100.0f;
                float z  = Float.parseFloat(values[3]) * 100.0f;
                float temperature = Float.parseFloat(values[4]);
                nodeList.add(new Node(id, x, y, z, temperature));
            } else {
                String[] values = line.split(",");
                if(values.length !=10){
                    if (line.equals("*END")) {
                        FileEntity fileEntity = saveFileToDb(filename, true);
                        saveNodesToDb(nodeList, fileEntity);
                        saveElementsToDb(elementList, fileEntity);
                        break;
                    } else {
                        saveFileToDb(filename, false);
                        break;
                    }
                }
                int id = Integer.parseInt(values[0]);
                int sId = Integer.parseInt(values[1].replaceAll("\\s+",""));
                List<Integer> nodes = new ArrayList<>();
                for(int i=2; i<10; i++){
                    nodes.add(Integer.parseInt(values[i].replaceAll("\\s+","")));
                }
                elementList.add(new Element(id, sId, nodes));
            }
        }
    }

    private FileEntity saveFileToDb(String filename, boolean valid){
        FileEntity fileEntity = new FileEntity(filename, new Date(), valid);
        return fileRepository.save(fileEntity);
    }

    private void saveNodesToDb(List<Node> nodes, FileEntity fileEntity) {
        Float max = nodes.stream().map(node -> node.getTemperature()).max(Comparator.comparing(t -> t)).get();
        Float min = nodes.stream().map(node -> node.getTemperature()).min(Comparator.comparing(t -> t)).get();
        float v = max - min;
        for (Node node : nodes) {
            float color = (((node.getTemperature()-min) / v));
            NodeEntity nodeEntity = new NodeEntity(fileEntity, node.getId(), node.getX(), node.getY(), node.getZ(), color);
            nodeRepository.save(nodeEntity);
        }
    }

    private void saveElementsToDb(List<Element> elements, FileEntity fileEntity) {
        for (Element element : elements) {
            List<Integer> nodeList = new ArrayList<>(element.getNodeList());
            ElementEntity elementEntity = new ElementEntity(element.getId(), fileEntity, element.getsId(),  nodeList.get(0), nodeList.get(1), nodeList.get(2), nodeList.get(3), nodeList.get(4), nodeList.get(5), nodeList.get(6), nodeList.get(7));
            elementRepository.save(elementEntity);
        }
    }

    public List<FileProxy> getLoadedFiles() {
        List<FileProxy> fileProxies = new ArrayList<>();
        List<FileEntity> files = fileRepository.findAll();
        for (FileEntity fileEntity : files) {
            fileProxies.add(new FileProxy(fileEntity.getId(), fileEntity.getName(), fileEntity.getDate(), fileEntity.isValid()));
        }
        return fileProxies;
    }

    public ChartDataProxy getNodesAndElementsForFile(long id) {
        FileEntity file = fileRepository.findOne(id);
        List<Node> nodes = file.getNodes().stream().map(this::repackNodeEntity).collect(Collectors.toList());
        List<Element> elements = file.getElements().stream().map(this::repackElement).collect(Collectors.toList());
        return new ChartDataProxy(nodes, elements);
    }

    private Node repackNodeEntity(NodeEntity nodeEntity) {
        return new Node(nodeEntity.getNodeId(), nodeEntity.getX(), nodeEntity.getY(), nodeEntity.getZ(), nodeEntity.getTemperature());
    }

    private Element repackElement(ElementEntity elementEntity) {
        return new Element(safeLongToInt(elementEntity.getId()), elementEntity.getsId(), elementEntity.getNodes());
    }

    public static int safeLongToInt(long l) {
        if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE) {
            throw new IllegalArgumentException
                    (l + " cannot be cast to int without changing its value.");
        }
        return (int) l;
    }

    public void deleteFile(long id) {
        fileRepository.delete(id);
    }
}
