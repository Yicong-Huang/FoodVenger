package pers.yicong.foodvenger.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "dishes")
public class Dish {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    @NotEmpty
    private String name;

    @Column(name = "cid")
    @NotEmpty
    private Integer cid;

    @Column(name = "price")
    @NotEmpty
    private float price;


    @ManyToMany(mappedBy = "dishes")
    private Set<Restaurant> restaurants;

//    @OneToMany(cascade = CascadeType.ALL,
//            fetch = FetchType.LAZY,
//            mappedBy = "dish")
//    private Set<Sale> sales;


    private Integer num = 1;


    public Set<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(Set<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

//    public Set<Sale> getSales() {
//        return sales;
//    }

//    public void setSales(Set<Sale> sales) {
//        this.sales = sales;
//    }
}
