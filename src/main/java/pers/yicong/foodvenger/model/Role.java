package pers.yicong.foodvenger.model;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="role_id")
    private Integer id;
	@Column(name="role")
	private String role;

    public Integer getId() {
		return id;
	}

    public void setId(Integer id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
}
