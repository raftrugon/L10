
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import services.SystemConfigService;
import domain.SystemConfig;

@Component
@Transactional
public class StringToSystemConfigConverter implements Converter<String, SystemConfig> {

	@Autowired
	SystemConfigService	systemConfigService;


	@Override
	public SystemConfig convert(final String text) {
		SystemConfig result;
		int id;
		if (text == "") {
			result = null;
		} else {
			try {
				id = Integer.valueOf(text);
				result = systemConfigService.findOne(id);
			} catch (final Throwable oops) {
				throw new IllegalArgumentException(oops);
			}
		}
		return result;
	}

}
