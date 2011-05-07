package com.l2jserver.service.game.template;

import java.io.File;

import com.l2jserver.service.configuration.Configuration;
import com.l2jserver.service.configuration.Configuration.ConfigurationName;

@ConfigurationName("template")
public interface StaticTemplateServiceConfiguration extends Configuration {
	@ConfigurationPropertyGetter(name = "template.descriptor", defaultValue = "data/script/template/template.xml")
	File getTemplateDescriptor();

	@ConfigurationPropertySetter(name = "template.descriptor")
	void setTemplateDescriptor(File file);
}
