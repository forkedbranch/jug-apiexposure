package eu.forkedbranch.jug.apiexposure;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import io.swagger.jaxrs.config.BeanConfig;

@ApplicationPath("/api")
public class DemoApplication extends Application {
	private final BeanConfig swagger;

	public DemoApplication() {
		swagger = new BeanConfig();
		swagger.setTitle("Rest Demo");
		swagger.setVersion("v1");
		swagger.setSchemes(new String[] { "http" });
		swagger.setHost("localhost:8080");
		swagger.setBasePath("/api");
		swagger.setResourcePackage("eu.forkedbranch.jug.apiexposure");
		swagger.setScan(true);
	}
}
