package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;

import cz.jirutka.validator.collection.constraints.EachURL;

@Entity
@Access(AccessType.PROPERTY)
public class FollowUp extends DomainEntity {

	//Attributes----------------
	private Date publicationMoment;
	private String title;
	private String summary;
	private String body;
	private Collection<String> picturess;

	@Past
	public Date getPublicationMoment() {
		return this.publicationMoment;
	}

	public void setPublicationMoment(final Date publicationMoment) {
		this.publicationMoment = publicationMoment;
	}

	@NotBlank
	@NotNull
	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
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
	@NotNull
	public String getBody() {
		return this.body;
	}

	public void setBody(final String body) {
		this.body = body;
	}

	@EachURL
	@ElementCollection
	@NotNull
	public Collection<String> getPicturess() {
		return this.picturess;
	}

	public void setPicturess(final Collection<String> picturess) {
		this.picturess = picturess;
	}


	//Relationships----------------

	private Article article;


	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Article getArticle() {
		return this.article;
	}


	public void setArticle(final Article article) {
		this.article = article;
	}



}