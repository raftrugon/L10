package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
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
		return publicationMoment;
	}
	
	public void setPublicationMoment(Date publicationMoment) {
		this.publicationMoment = publicationMoment;
	}
	
	@NotBlank
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
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
	
	@EachURL
	@ElementCollection
	@NotNull
	public Collection<String> getPicturess() {
		return picturess;
	}
	
	public void setPicturess(Collection<String> picturess) {
		this.picturess = picturess;
	}
	
	
	//Relationships----------------
	
}