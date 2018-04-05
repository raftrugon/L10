package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

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
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	public Date getPublicationMoment() {
		return publicationMoment;
	}
	
	public void setPublicationMoment(Date publicationMoment) {
		this.publicationMoment = publicationMoment;
	}
	
	@NotBlank
	public String getSummary() {
		return summary;
	}
	
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	@NotBlank
	public String getBody() {
		return body;
	}
	
	public void setBody(String body) {
		this.body = body;
	}
	
	@NotNull
	@ElementCollection
	@EachURL
	public Collection<String> getPicturess() {
		return picturess;
	}
	
	public void setPicturess(Collection<String> picturess) {
		this.picturess = picturess;
	}
	
	@NotNull
	public Boolean getFinalMode() {
		return finalMode;
	}
	
	public void setFinalMode(Boolean finalMode) {
		this.finalMode = finalMode;
	}
	
	
	//Relationships----------------
	private Newspaper newspaper;
	
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Newspaper getNewspaper() {
		return newspaper;
	}
	
	public void setNewspaper(Newspaper newspaper) {
		this.newspaper = newspaper;
	}
	
	
}