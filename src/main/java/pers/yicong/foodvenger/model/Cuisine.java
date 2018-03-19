package pers.yicong.foodvenger.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "cuisines")
public class Cuisine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "type")
    @NotEmpty(message = "*Please provide cuisine type")
    private String type;

    @ManyToMany(mappedBy = "cuisines")
    @JsonIgnore
    private Set<Restaurant> restaurants;

    public Cuisine(String type) {
        this.type = type;

    }

    public Cuisine() {
    }

    public Cuisine(String type, Set<Restaurant> restaurants) {
        this.type = type;
        this.restaurants = restaurants;
    }

    @JsonIgnore
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}
