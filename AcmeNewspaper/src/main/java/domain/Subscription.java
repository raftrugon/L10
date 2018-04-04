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
import org.springframework.format.annotation.DateTimeFormat;
import security.UserAccount;

@Entity
@Access(AccessType.PROPERTY)
public class Subscription extends DomainEntity {

	//Attributes----------------
	private CreditCard creditCard;
	
	@Valid
	public CreditCard getCreditCard() {
		return creditCard;
	}
	
	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}
	
	//Relationships----------------
	private Newspaper newspaper;
	private Customer customer;
	
	
	@NotNull
	@Valid
	@OneToOne(optional = false)
	public Newspaper getNewspaper() {
		return newspaper;
	}
	
	public void setNewspaper(Newspaper newspaper) {
		this.newspaper = newspaper;
	}
	
	@NotNull
	@Valid
	@OneToOne(optional = false)
	public Customer getCustomer() {
		return customer;
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
}