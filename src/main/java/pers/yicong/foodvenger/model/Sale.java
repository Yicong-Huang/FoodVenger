package pers.yicong.foodvenger.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;


    @Column(name = "cid")
//    @NotEmpty
    private Integer cid;


    @Column(name = "did")
//    @NotEmpty
    private Integer did;

    @Column(name = "num")

    private Integer num;


    @Column(name = "sale_date")
    @Temporal(TemporalType.DATE)
    private Date saleDate;

//    @ManyToOne
//    @JoinTable(name = "customers", joinColumns = @JoinColumn(name = "cid", nullable = false))
//    private Customer customer;
//
//
//    @ManyToOne
//    @JoinTable(name = "dishes", joinColumns = @JoinColumn(name = "did", nullable = false))
//    private Dish dish;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

//    public Customer getCustomer() {
//        return customer;
//    }

//    public void setCustomer(Customer customer) {
//        this.customer = customer;
//    }
//
//    public Dish getDish() {
//        return dish;
//    }
//
//    public void setDish(Dish dish) {
//        this.dish = dish;
//    }
}
