import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.0.6" apply false
	id("io.spring.dependency-management") version "1.1.0"
	kotlin("jvm") version "1.8.21"
	kotlin("plugin.spring") version "1.8.21"
	kotlin("plugin.jpa") version "1.8.21"

	kotlin("kapt") version "1.8.21"
}

java.sourceCompatibility = JavaVersion.VERSION_17

allprojects {
	group = "com.wave"
	version = "0.0.1-SNAPSHOT"

	repositories {
		mavenCentral()
	}
}

subprojects {
	apply(plugin = "kotlin")
	apply(plugin = "kotlin-spring")
	apply(plugin = "io.spring.dependency-management")
	apply(plugin = "kotlin-kapt")

	dependencies {
		//JWT
		implementation("com.auth0:java-jwt:3.19.2")

		//Kotlin logger
		implementation("io.github.microutils:kotlin-logging:1.12.5")

		implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
		implementation("org.jetbrains.kotlin:kotlin-reflect")
		implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

		runtimeOnly("com.mysql:mysql-connector-j")
		testImplementation("org.springframework.boot:spring-boot-starter-test")

	}

	dependencyManagement {
		imports {
			mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
		}

	tasks.withType<KotlinCompile> {
		kotlinOptions {
			freeCompilerArgs = listOf("-Xjsr305=strict")
			jvmTarget = "17"
		}
	}

	tasks.withType<Test> {
		useJUnitPlatform()
	}

	}

}


