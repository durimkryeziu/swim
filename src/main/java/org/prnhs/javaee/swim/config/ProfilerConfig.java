package org.prnhs.javaee.swim.config;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import java.util.concurrent.TimeUnit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProfilerConfig {

    static final MetricRegistry metrics = new MetricRegistry();
    
    @Bean
    public MetricRegistry metrics(){
        return metrics;
    }
    
    @Bean
    public ConsoleReporter reporter(){
        ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build();
        return reporter;
    }
}
