package org.prnhs.javaee.swim.config;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class ProfilerConfig {

  @Bean
  public MetricRegistry metrics() {
    return new MetricRegistry();
  }

  @Bean
  public ConsoleReporter reporter(MetricRegistry metrics) {
    ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics)
        .convertRatesTo(TimeUnit.SECONDS)
        .convertDurationsTo(TimeUnit.MILLISECONDS)
        .build();
    return reporter;
  }
}
