package pers.yicong.foodvenger.model;


import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "restaurants")
public class Restaurant {

    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    @NotEmpty(message = "*Please provide restaurant name")
    private String name;

    @Column(name = "addr")
    @NotEmpty(message = "*Please provide restaurant address")
    private String addr;

    @Column(name = "image")
    private String image;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "cuisine_in_restau",
            joinColumns = @JoinColumn(name = "rid", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "cid", referencedColumnName = "id"))
    private Set<Cuisine> cuisines;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "dishes_in_restau",
            joinColumns = @JoinColumn(name = "rid", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "did", referencedColumnName = "id"))
    private Set<Dish> dishes;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Rating rating;

    public Set<Cuisine> getCuisines() {
        return cuisines;
    }

    public void setCuisines(Set<Cuisine> cuisines) {
        this.cuisines = cuisines;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public Set<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(Set<Dish> dishes) {
        this.dishes = dishes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}