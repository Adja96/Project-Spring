package sn.isi.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column( nullable = false , length = 200)
	private String firstname;
	@Column(nullable = false , length = 200)
	private String lastname;
	@Column( nullable = false , length = 150)
	private String em;
	@Column( nullable = false , length = 150)
	
	private String pc;
	@ManyToMany
	private List<AppRolesEntity> appRoles;
	
	@OneToMany(mappedBy = "appUser")
	private List<IaEntity> ias ;
	

}
