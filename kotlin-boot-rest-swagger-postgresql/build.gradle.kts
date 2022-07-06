import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.7.1"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
	mavenCentral()
}

dependencies {

	implementation("org.springframework.boot:spring-boot-starter-web")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.2")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.6.2")

	implementation("org.springframework.boot:spring-boot-starter-security")
	testImplementation("org.springframework.security:spring-security-test")

	implementation("io.jsonwebtoken:jjwt-api:0.11.5")
	runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.5")
	runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.5")

	implementation("io.ktor:ktor-client-okhttp:2.0.2")
	implementation("io.ktor:ktor-client-gson:2.0.2")
	implementation("io.ktor:ktor-client-logging-jvm:2.0.2")
	implementation("ch.qos.logback:logback-classic:1.2.11")

	runtimeOnly("org.postgresql:postgresql")

	implementation("org.jetbrains.exposed:exposed-core:0.38.2")
	implementation("org.jetbrains.exposed:exposed-dao:0.38.2")
	implementation("org.jetbrains.exposed:exposed-jdbc:0.38.2")
	implementation("org.jetbrains.exposed:exposed-jodatime:0.38.2")
	implementation("org.springframework.boot:spring-boot-starter-jooq")

	implementation("io.springfox:springfox-boot-starter:3.0.0")
	compileOnly("io.springfox:springfox-swagger-ui:3.0.0")

	developmentOnly("org.springframework.boot:spring-boot-devtools")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
