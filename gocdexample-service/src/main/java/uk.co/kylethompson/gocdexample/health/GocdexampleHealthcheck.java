package uk.co.kylethompson.gocdexample.health;

import com.codahale.metrics.health.HealthCheck;

public class GocdexampleHealthcheck extends HealthCheck {

    public GocdexampleHealthcheck() { }

    @Override
    protected Result check() throws Exception {
        return Result.healthy();
    }
}
