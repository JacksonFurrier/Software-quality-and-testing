package ucoach.auth.model;
import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="token")
public class RestToken {
	private String id;
	private String token;
	
	public RestToken(){
		
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public RestToken(String id, String token) {
		super();
		this.id = id;
		this.token = token;
	}
	
}

