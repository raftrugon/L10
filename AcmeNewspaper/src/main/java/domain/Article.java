package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;

import services.ArticleService;

import cz.jirutka.validator.collection.constraints.EachURL;

@Entity
@Access(AccessType.PROPERTY)
public class Article extends DomainEntity {


	//Attributes----------------
	private String title;
	private Date publicationMoment;
	private String summary;
	private String body;
	private Collection<String> picturess;
	private Boolean finalMode;

	@NotBlank
	@NotNull
	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	public Date getPublicationMoment() {
		return this.publicationMoment;
	}

	public void setPublicationMoment(final Date publicationMoment) {
		this.publicationMoment = publicationMoment;
	}

	@NotBlank
	@NotNull
	public String getSummary() {
		return this.summary;
	}

	public void setSummary(final String summary) {
		this.summary = summary;
	}

	@NotBlank
	public String getBody() {
		return this.body;
	}

	public void setBody(final String body) {
		this.body = body;
	}

	@NotNull
	@ElementCollection
	@EachURL
	public Collection<String> getPicturess() {
		return this.picturess;
	}

	public void setPicturess(final Collection<String> picturess) {
		this.picturess = picturess;
	}

	@NotNull
	public Boolean getFinalMode() {
		return this.finalMode;
	}

	public void setFinalMode(final Boolean finalMode) {
		this.finalMode = finalMode;
	}


	//Relationships----------------
	private Newspaper newspaper;
	private Collection<FollowUp> followUps;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Newspaper getNewspaper() {
		return this.newspaper;
	}

	public void setNewspaper(final Newspaper newspaper) {
		this.newspaper = newspaper;
	}

	@NotNull
	@Valid
	@OneToMany(mappedBy = "article")
	public Collection<FollowUp> getFollowUps() {
		return this.followUps;
	}


	public void setFollowUps(final Collection<FollowUp> followUps) {
		this.followUps = followUps;
	}

}