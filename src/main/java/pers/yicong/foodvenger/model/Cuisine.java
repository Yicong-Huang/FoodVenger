package pers.yicong.foodvenger.model;

import org.hibernate.validator.constraints.NotEmpty;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "cuisine")
public class Cuisine {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "type")
    @NotEmpty(message = "*Please provide cuisine type")
    private String type;

    @ManyToMany(mappedBy = "cuisines")
    private Set<Restaurant> restaurants;


    public Set<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(Set<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
