package org.example.blog

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpStatus
import toSlug

/*
- We use real sentences between backticks instead of camel-case to provide expressive test function names
- JUnit 5 allows to inject constructor and method parameters, which is a good fit with Kotlin read-only
and non-nullable properties
- This code leverages getForObject and getForEntity Kotlin extensions (you need to import them)
 */

/*
In JUnit 5, you may need to run a method before or after all tests in a class.
By default, these methods must be static (using a companion object in Kotlin),
as test classes are created once per test. However, you can change this default behavior
to instantiate test classes once per class. This can be done by setting a property file for the entire project.

See src/main/resources/junit-platform.properties
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IntegrationTests(@Autowired val restTemplate: TestRestTemplate) {

    @BeforeAll
    fun setup() {
        println(">> Setup")
    }

    @Test
    fun `Assert blog page title, content and status code`() {
        println(">> Assert blog page title, content and status code")
        val entity = restTemplate.getForEntity<String>("/")
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(entity.body).contains("<h1>Blog</h1>", "Lorem")
    }

    @Test
    fun `Assert article page title, content and status code`() {
        println(">> Assert article page title, content and status code")
        val title = "Lorem"
        val entity = restTemplate.getForEntity<String>("/article/${title.toSlug()}")
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(entity.body).contains(title, "Lorem", "dolor sit amet")
    }

    @AfterAll
    fun teardown() {
        println(">> Tear down")
    }

}