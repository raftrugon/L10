package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Access(AccessType.PROPERTY)
public class SystemConfig extends DomainEntity {

	//Attributes----------------
	private Collection<String> tabooWordss;

	@NotEmpty
	@ElementCollection
	@NotNull
	public Collection<String> getTabooWordss() {
		return this.tabooWordss;
	}

	public void setTabooWordss(final Collection<String> tabooWordss) {
		this.tabooWordss = tabooWordss;
	}


	//Relationships----------------

}