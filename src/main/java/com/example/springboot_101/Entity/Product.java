package com.example.springboot_101.Entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Product {
    @Id
    private Long id;
    @Column(name = "designation")
    private String designation;
    @Column(name = "prix")
    private int prix;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public Product(Long id, String designation, int prix) {
        this.id = id;
        this.designation = designation;
        this.prix = prix;
    }
    public Product (){}
}
