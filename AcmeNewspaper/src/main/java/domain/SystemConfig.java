package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Access(AccessType.PROPERTY)
public class SystemConfig extends DomainEntity {

	//Attributes----------------
	private Collection<String> tabooWordss;
	
	@NotEmpty
	@ElementCollection
	public Collection<String> getTabooWordss() {
		return tabooWordss;
	}
	
	public void setTabooWordss(Collection<String> tabooWordss) {
		this.tabooWordss = tabooWordss;
	}
	
	
	//Relationships----------------
	
}