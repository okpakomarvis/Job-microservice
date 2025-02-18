package com.marvisx.companyms.company;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

   /*@JsonIgnore
   @OneToMany(mappedBy = "company")
    private List<Job> jobs;

    @OneToMany(mappedBy = "company")
    private List<Review> reviews;
*/
    public Company(){}

    public Company(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
