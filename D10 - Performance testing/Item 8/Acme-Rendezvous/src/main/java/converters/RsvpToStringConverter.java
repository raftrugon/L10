
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Rsvp;

@Component
@Transactional
public class RsvpToStringConverter implements Converter<Rsvp, String> {

	@Override
	public String convert(final Rsvp rsvp) {
		String result;

		if (rsvp == null) {
			result = null;
		} else {
			result = String.valueOf(rsvp.getId());
		}

		return result;
	}

}
