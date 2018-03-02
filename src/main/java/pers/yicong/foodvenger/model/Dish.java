package pers.yicong.foodvenger.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "dishes")
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    @Length(max = 100, message = "*Dish name must have at most 100 characters")
    @NotEmpty(message = "*Dish name must not be empty")
    private String name;

    @Column(name = "cid")
//    @NotEmpty
    private Integer cid;

    @Column(name = "price")
    private float price;


    @ManyToMany(mappedBy = "dishes")
    private Set<Restaurant> restaurants;

//    @OneToMany(cascade = CascadeType.ALL,
//            fetch = FetchType.LAZY,
//            mappedBy = "dish")
//    private Set<Sale> sales;


    private Integer num = 1;

    public Dish(String name) {
        this.name = name;
    }

    public Dish() {
    }


    public Dish(Integer id, String name, Integer cid, float price, Integer num) {
        this.id = id;
        this.name = name;
        this.cid = cid;
        this.price = price;
        this.num = num;
    }

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
