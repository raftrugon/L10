package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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
	@ManyToOne(optional = false)
	public Newspaper getNewspaper() {
		return newspaper;
	}
	
	public void setNewspaper(Newspaper newspaper) {
		this.newspaper = newspaper;
	}
	
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Customer getCustomer() {
		return customer;
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
}