package pers.yicong.foodvenger.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;


    @Column(name = "cid")
    @NotEmpty
    private int cid;


    @Column(name = "did")
    @NotEmpty
    private String did;

    @Column(name = "sale_date")
    private Date saleDate;

    @ManyToOne
    @JoinTable(name = "customer", joinColumns = @JoinColumn(name = "cid", nullable = false))
    private Customer customer;


    @ManyToOne
    @JoinTable(name = "dishes", joinColumns = @JoinColumn(name = "did", nullable = false))
    private Dish dish;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    //    @ManyToOne
//    @JoinTable(name = "dishes", joinColumns = @JoinColumn(name = "id", referencedColumnName = "did"))
    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }
}
