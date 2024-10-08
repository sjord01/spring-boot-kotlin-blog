package org.example.blog

import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(BlogProperties::class)
class BlogApplication
// We need @EnableConfigurationProperties(BlogProperties::class) to support BlogProperties.kt's @ConfigurationProperties

fun main(args: Array<String>) {
    runApplication<BlogApplication>(*args) {
        setBannerMode(Banner.Mode.OFF)
    }
}