package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Access(AccessType.PROPERTY)
public class SystemConfig extends Actor {

	//Attributes----------------
	private Collection<String> tabooWordss;
	
	@NotEmpty
	public Collection<String> getTabooWordss() {
		return tabooWordss;
	}
	
	public void setTabooWordss(Collection<String> tabooWordss) {
		this.tabooWordss = tabooWordss;
	}
	
	
	//Relationships----------------
	
}