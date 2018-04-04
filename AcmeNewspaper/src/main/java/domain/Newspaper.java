package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Newspaper extends DomainEntity {

	//Attributes----------------
	private String title;
	private Date publicationDate;
	private String description;
	private String picture;
	private Boolean isPrivate;
	
	@NotBlank
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	@NotNull
	@Temporal(TemporalType.DATE)
	public Date getPublicationDate() {
		return publicationDate;
	}
	
	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}
	
	@NotBlank
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	@URL
	public String getPicture() {
		return picture;
	}
	
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	@NotNull
	public Boolean isPrivate() {
		return isPrivate;
	}
	
	public void setIsPrivate(Boolean isPrivate) {
		this.isPrivate = isPrivate;
	}
	
	
	//Relationships----------------
	private Collection<Subscription> subscriptionss;
	private Collection<Article> articless;
	private User user;
	
	@NotNull
	@Valid
	@OneToMany(mappedBy = "newspaper")
	public Collection<Subscription> getSubscriptionss() {
		return subscriptionss;
	}
	
	public void setSubscriptionss(Collection<Subscription> subscriptionss) {
		this.subscriptionss = subscriptionss;
	}
	
	@NotNull
	@Valid
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "newspaper")
	public Collection<Article> getArticless() {
		return articless;
	}
	
	public void setArticless(Collection<Article> articless) {
		this.articless = articless;
	}
	
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	
}