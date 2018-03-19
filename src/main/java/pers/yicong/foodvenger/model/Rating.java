package pers.yicong.foodvenger.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "ratings")
public class Rating {

    @Id
    @Column(name = "rid")
    private Integer rid;

    @Column(name = "rating")
    private float rating;

    @Column(name = "num_votes")
    private Integer numVotes;


    @OneToOne(mappedBy = "rating")
    @JsonIgnore
    private Restaurant restaurant;

    public Rating(Integer rid, float rating, Integer numVotes) {
        this.rid = rid;
        this.rating = rating;
        this.numVotes = numVotes;
    }

    public Rating() {
    }

    @JsonIgnore
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
