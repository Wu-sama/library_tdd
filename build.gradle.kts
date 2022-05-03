plugins {
	id("org.springframework.boot")
	id("io.spring.dependency-management")
	kotlin("jvm")
	java
	kotlin("plugin.spring")
	id("io.gitlab.arturbosch.detekt")
}

group = "com.wolt.app.test"
version = "0.0.1-SNAPSHOT"

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.2")
	implementation("org.hibernate.validator:hibernate-validator:7.0.4.Final")

	testImplementation("org.junit.jupiter:junit-jupiter-api")
	testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
	testImplementation ("io.mockk:mockk:1.12.3")
	testImplementation("com.ninja-squad:springmockk:3.1.1")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(module = "mockito-core")
	}
}

allOpen {
	annotation("javax.persistence.Entity")
	annotation("javax.persistence.Embeddable")
	annotation("javax.persistence.MappedSuperclass")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
	kotlinOptions {
		jvmTarget = JavaVersion.VERSION_11.toString()
		freeCompilerArgs = listOf("-Xjvm-default=enable")
	}
}

detekt {
	buildUponDefaultConfig = true // preconfigure defaults
	allRules = false // activate all available (even unstable) rules.
	config = files("$projectDir/config/detekt.yml") // point to your custom config defining rules to run, overwriting default behavior
	baseline = file("$projectDir/config/baseline.xml") // a way of suppressing issues before introducing detekt
}