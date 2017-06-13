package com.agh.db.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Dawid on 11.06.2017.
 */
@Entity
@Table(name = "element")
public class ElementEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "elementId")
    private int elementId;

    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="fileId")
    private FileEntity elementFileEntity;

    @Column(name = "sId")
    private int sId;

    @Column(name = "n1")
    private int n1;

    @Column(name = "n2")
    private int n2;

    @Column(name = "n3")
    private int n3;

    @Column(name = "n4")
    private int n4;

    @Column(name = "n5")
    private int n5;

    @Column(name = "n6")
    private int n6;

    @Column(name = "n7")
    private int n7;

    @Column(name = "n8")
    private int n8;

    public ElementEntity() {
    }

    public ElementEntity(int elementId, FileEntity elementFileEntity, int sId, int n1, int n2, int n3, int n4, int n5, int n6, int n7, int n8) {
        this.elementId = elementId;
        this.elementFileEntity = elementFileEntity;
        this.sId = sId;
        this.n1 = n1;
        this.n2 = n2;
        this.n3 = n3;
        this.n4 = n4;
        this.n5 = n5;
        this.n6 = n6;
        this.n7 = n7;
        this.n8 = n8;
    }

    public FileEntity getElementFileEntity() {
        return elementFileEntity;
    }

    public void setElementFileEntity(FileEntity elementFileEntity) {
        this.elementFileEntity = elementFileEntity;
    }

    public Long getId() {
        return id;
    }

    public int getElementId() {
        return elementId;
    }

    public void setElementId(int elementId) {
        this.elementId = elementId;
    }

    public int getsId() {
        return sId;
    }

    public void setsId(int sId) {
        this.sId = sId;
    }

    public int getN1() {
        return n1;
    }

    public void setN1(int n1) {
        this.n1 = n1;
    }

    public int getN2() {
        return n2;
    }

    public void setN2(int n2) {
        this.n2 = n2;
    }

    public int getN3() {
        return n3;
    }

    public void setN3(int n3) {
        this.n3 = n3;
    }

    public int getN4() {
        return n4;
    }

    public void setN4(int n4) {
        this.n4 = n4;
    }

    public int getN5() {
        return n5;
    }

    public void setN5(int n5) {
        this.n5 = n5;
    }

    public int getN6() {
        return n6;
    }

    public void setN6(int n6) {
        this.n6 = n6;
    }

    public int getN7() {
        return n7;
    }

    public void setN7(int n7) {
        this.n7 = n7;
    }

    public int getN8() {
        return n8;
    }

    public void setN8(int n8) {
        this.n8 = n8;
    }

    public List<Integer> getNodes(){
        return new ArrayList<>(Arrays.asList(n1, n2, n3, n4, n5, n6, n7, n8));
    }
}
