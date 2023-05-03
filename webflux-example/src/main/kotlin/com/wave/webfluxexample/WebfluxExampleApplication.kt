package com.wave.webfluxexample

import io.r2dbc.spi.ConnectionFactories
import io.r2dbc.spi.ConnectionFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.core.io.ClassPathResource
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator


@SpringBootApplication
class WebfluxExampleApplication{
    @Bean
    fun init(connectionFactory: ConnectionFactory) {
        var connectionFactory =
            ConnectionFactories.get("r2dbc:h2:mem:///test?options=DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE")
        ConnectionFactoryInitializer().apply {
            setConnectionFactory(connectionFactory)
            setDatabasePopulator(ResourceDatabasePopulator(ClassPathResource("schema.sql")))
        }
    }
    
}

fun main(args: Array<String>) {
    runApplication<WebfluxExampleApplication>(*args)
}
