package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import security.UserAccount;

@Entity
@Access(AccessType.PROPERTY)
public class User extends Actor {

	//Attributes----------------
	
	//Relationships
	
	//Relationships----------------
	private Collection<Newspaper> newspapers;
	private Collection<Chirp> chirps;
	@NotNull
	@Valid
	@OneToMany(mappedBy="user")
	public Collection<Newspaper> getNewspapers() {
		return newspapers;
	}
	
	public void setNewspapers(Collection<Newspaper> newspapers) {
		this.newspapers = newspapers;
	}
	
	@NotNull
	@Valid
	@OneToMany(mappedBy="user")
	public Collection<Chirp> getChirps() {
		return chirps;
	}
	
	public void setChirps(Collection<Chirp> chirps) {
		this.chirps = chirps;
	}
	
	
}