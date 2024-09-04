package org.example.blog

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("blog")
data class BlogProperties(var title: String, val banner: Banner) {
    data class Banner(val title: String? = null, val content: String)
}

/*
Then we enable it at BlogApplication level.
src/main/kotlin/com/example/blog/BlogApplication.kt

@SpringBootApplication
@EnableConfigurationProperties(BlogProperties::class)
class BlogApplication {
  // ...
}
 */