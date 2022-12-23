package sn.isi.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ia {
	
	private int id;
	@NotNull(message = "le nom ne doit pas etre nul")
	private String name;
	
    
}
