package domain;

import java.util.Collection;
import java.util.Date;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
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
	public Collection<String> getAddressess() {
		return addressess;
	}
	
	public void setAddressess(Collection<String> addressess) {
		this.addressess = addressess;
	}
	
	@NotNull
	public Collection<String> getPhoness() {
		return phoness;
	}
	
	public void setPhoness(Collection<String> phoness) {
		this.phoness = phoness;
	}
	
	@NotEmpty
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