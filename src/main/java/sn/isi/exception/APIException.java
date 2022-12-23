package sn.isi.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class APIException {
    String message;
    HttpStatus status;
    LocalDateTime timestamp;
    
	/*public APIException(String message, HttpStatus status, LocalDateTime timestamp) {
		super();
		this.message = message;
		this.status = status;
		this.timestamp = timestamp;
	}*/
    
}