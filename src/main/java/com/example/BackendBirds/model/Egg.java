package com.example.BackendBirds.model;

import jakarta.persistence.*;

@Entity
@Table(name = "eggs")
public class Egg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "egg_id")
    private int id;
    private String name;
    private int weight;
    private int diameter;

    public Egg() {
    }

    public int getId() {
        return id;
    }

//    public void setId(int id) {
//        this.id = id;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getDiameter() {
        return diameter;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Egg e) {
            return e.getId() == this.id && e.getName().equals(this.name) && e.getWeight() == this.weight && e.getDiameter() == this.diameter;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Egg{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", diameter=" + diameter +
                '}';
    }
}
