package uk.co.kylethompson.gocdexample;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import uk.co.kylethompson.gocdexample.config.GocdexampleServiceConfiguration;
import uk.co.kylethompson.gocdexample.health.GocdexampleHealthcheck;
import uk.co.kylethompson.gocdexample.resources.HelloResource;
import uk.co.kylethompson.gocdexample.resources.StatusResource;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;


public class GocdexampleApplication extends Application<GocdexampleServiceConfiguration> {

    private static final String SERVICE_NAME = "gocdexample";

    public static void main(String[] args) throws Exception {
        new GocdexampleApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<GocdexampleServiceConfiguration> bootstrap) {
    }

    @Override
    public void run(GocdexampleServiceConfiguration configuration, Environment environment) throws Exception {
        environment.jersey().register(new StatusResource());
        environment.jersey().register(new HelloResource());
        environment.healthChecks().register(getName(), new GocdexampleHealthcheck());

        FilterRegistration.Dynamic filter = environment.servlets().addFilter("CORS", CrossOriginFilter.class);
        filter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/hello");
        filter.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "GET,PUT,POST,DELETE,OPTIONS");
        filter.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
        filter.setInitParameter(CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, "*");
        filter.setInitParameter("allowedHeaders", "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin");
        filter.setInitParameter("allowCredentials", "true");
    }

    @Override
    public String getName() {
        return SERVICE_NAME;
    }
}
