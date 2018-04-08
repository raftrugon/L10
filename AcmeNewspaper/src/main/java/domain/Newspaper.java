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
	private Boolean inappropriate;
	

	@NotBlank
	@NotNull
	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	@NotNull
	@Temporal(TemporalType.DATE)
	public Date getPublicationDate() {
		return this.publicationDate;
	}

	public void setPublicationDate(final Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	@NotBlank
	@NotNull
	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	@URL
	public String getPicture() {
		return this.picture;
	}

	public void setPicture(final String picture) {
		this.picture = picture;
	}

	@NotNull
	public Boolean getIsPrivate() {
		return this.isPrivate;
	}

	public void setIsPrivate(final Boolean isPrivate) {
		this.isPrivate = isPrivate;
	}
	@NotNull
	public Boolean getInappropriate() {
		return this.inappropriate;
	}

	public void setInappropriate(Boolean inappropriate) {
		this.inappropriate = inappropriate;
	}


	//Relationships----------------
	private Collection<Subscription> subscriptionss;
	private Collection<Article> articless;
	private User user;

	@NotNull
	@Valid
	@OneToMany(mappedBy = "newspaper")
	public Collection<Subscription> getSubscriptionss() {
		return this.subscriptionss;
	}

	public void setSubscriptionss(final Collection<Subscription> subscriptionss) {
		this.subscriptionss = subscriptionss;
	}

	@NotNull
	@Valid
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "newspaper")
	public Collection<Article> getArticless() {
		return this.articless;
	}

	public void setArticless(final Collection<Article> articless) {
		this.articless = articless;
	}

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(final User user) {
		this.user = user;
	}


}