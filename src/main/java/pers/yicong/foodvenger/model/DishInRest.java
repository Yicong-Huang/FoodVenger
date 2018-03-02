package pers.yicong.foodvenger.model;

//@Entity
//@Table(name = "dishes_in_restau")
public class DishInRest {

    //    @Column(name = "did")
    private Integer did;

    //    @Column(name = "rid")
    private Integer rid;


    public DishInRest(int rid, int did) {
        this.did = did;
        this.rid = rid;

    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }
}
