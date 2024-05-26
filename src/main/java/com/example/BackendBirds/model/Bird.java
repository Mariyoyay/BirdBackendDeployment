package com.example.BackendBirds.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "birds")
public class Bird {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bird_id")
    private int id;
    private String name;
    private int age;
    private String color;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_bird_id", referencedColumnName = "bird_id")
    private List<Egg> eggs;

    @ManyToOne
    @JoinColumn(name = "fk_user_id", referencedColumnName = "user_id")
    private ApplicationUser applicationUser;

    public Bird() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<Egg> getEggs() {
        return eggs;
    }

    public void setEggs(List<Egg> eggsList) {
        this.eggs = eggsList;
    }

    public ApplicationUser getApplicationUser() {
        return applicationUser;
    }

    public void setApplicationUser(ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Bird bird) {
            boolean eggsAreTheSame = true;
            if (bird.eggs == null) bird.eggs = new ArrayList<>();
            if (bird.eggs.size() != eggs.size()) {
                eggsAreTheSame = false;
            } else {
                for (int i = 0; i < bird.eggs.size(); i++) {
                    if (bird.eggs.get(i).getId() != bird.eggs.get(i).getId()) {
                        eggsAreTheSame = false;
                        break;
                    }
                }
            }

            return id == bird.getId() && name.equals(bird.getName()) && age == bird.getAge() && color.equals(bird.getColor()) && eggsAreTheSame;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Bird{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", color='" + color + '\'' +
                '}';
    }
}
