package mipes;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class Persona  extends Object {
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	
	@Persistent
	private String name;
	
	@Persistent
	private String lastname;
	
	@Persistent
	private String email;
	
	@Persistent
	private String gp;

	public Persona(String name, String lastname, String email, String gp) {
		super();
		this.name = name;
		this.lastname = lastname;
		this.email = email;
		this.gp = gp;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGp() {
		return gp;
	}

	public void setGp(String gp) {
		this.gp = gp;
	}

	public Key getKey() {
		return key;
	}
	
	@Override
	public String toString() {
		String resp = name + " : " + lastname + " : " + email + " : " + gp;  
		return resp;
	}

}
