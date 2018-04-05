package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import security.UserAccount;

@Entity
@Access(AccessType.PROPERTY)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Actor extends DomainEntity {

	//Attributes----------------
	private String name;
	private String surnames;
	private Collection<String> addressess;
	private Collection<String> phoness;
	private Collection<String> emailss;
	
	@NotBlank
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@NotNull
	public String getSurnames() {
		return surnames;
	}
	
	public void setSurnames(String surnames) {
		this.surnames = surnames;
	}
	
	@NotNull
	@ElementCollection
	public Collection<String> getAddressess() {
		return addressess;
	}
	
	public void setAddressess(Collection<String> addressess) {
		this.addressess = addressess;
	}
	
	@NotNull
	@ElementCollection
	public Collection<String> getPhoness() {
		return phoness;
	}
	
	public void setPhoness(Collection<String> phoness) {
		this.phoness = phoness;
	}
	
	@NotEmpty
	@ElementCollection
	public Collection<String> getEmailss() {
		return emailss;
	}
	
	public void setEmailss(Collection<String> emailss) {
		this.emailss = emailss;
	}
	
	
	//Relationships----------------
	private UserAccount userAccount;
	
	@NotNull
	@Valid
	@OneToOne(cascade = CascadeType.ALL, optional = false)
	public UserAccount getUserAccount() {
		return userAccount;
	}
	
	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}
	
	
}