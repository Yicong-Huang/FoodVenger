package pers.yicong.foodvenger.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
@Table(name = "ratings")
public class Rating {

    @Id
    @Column(name = "rid")
    private Integer rid;

    @Column(name = "rating")
    @NotEmpty
    private float rating;

    @Column(name = "num_votes")
    @NotEmpty
    private Integer numVotes;


    @OneToOne(mappedBy = "rating")
    private Restaurant restaurant;


    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public Integer getNumVotes() {
        return numVotes;
    }

    public void setNumVotes(Integer numVotes) {
        this.numVotes = numVotes;
    }
}
