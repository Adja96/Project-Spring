package sn.isi.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {
	
	private int id;
	@NotNull(message = "le prenom ne doit pas etre nul")
	private String firstname;
	@NotNull(message = "le nom ne doit pas etre nul")
	private String lastname;
	@NotNull(message = "l em ne doit pas etre nul")
	private String em;
	@NotNull(message = "le pc ne doit pas etre nul")
	private String pc;
	
	

}
