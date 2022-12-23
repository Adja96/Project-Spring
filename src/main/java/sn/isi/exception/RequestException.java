package sn.isi.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestException extends RuntimeException {
	String message;
    HttpStatus status;
	public String getMessage() {
		return message;
	}
	/* 
	public void setMessage(String message) {
		this.message = message;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	public RequestException(String message, HttpStatus status) {
		super();
		this.message = message;
		this.status = status;
	}*/
}
