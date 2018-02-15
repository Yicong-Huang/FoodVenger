package pers.yicong.foodvenger.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
@Table(name = "ratings")
public class Rating {

    @Id
    @Column(name = "rid")
    private String rid;

    @Column(name = "rating")
    @NotEmpty
    private float rating;

    @Column(name = "num_votes")
    @NotEmpty
    private int numVotes;


    @OneToOne(mappedBy = "rating")
    private Restaurant restaurant;


    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getNumVotes() {
        return numVotes;
    }

    public void setNumVotes(int numVotes) {
        this.numVotes = numVotes;
    }
}
