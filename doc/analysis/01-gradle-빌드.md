# Gradle 빌드 설정 분석

- **대상 파일**: `admin-api/build.gradle`, `build.gradle`, `settings.gradle`
- **상태**: 완료

---

## 발견 사항 및 처리 결과

| 항목 | 문제 | 심각도 | 처리 |
|------|------|--------|------|
| annotationProcessor 순서 | MapStruct가 Lombok보다 먼저 선언 → 매핑 생성 코드에서 누락 필드 가능 | High | 수정 완료 |
| `lombok-mapstruct-binding` 누락 | processor 실행 순서 보장 라이브러리 없음 | High | 추가 완료 |
| `ignoreFailures = true` | 테스트 실패해도 빌드 통과 (테스트 없는 동안 임시 유지) | Medium | 유지 (의도적) |
| `user-api/build.gradle` 없음 | settings.gradle에 include되어 있지만 파일 미존재 | Medium | 미처리 |
| `spring-boot-configuration-processor` | `@Value`만 사용하는 프로젝트에서 효과 없음 | Low | 제거 완료 |
| `junit-platform-launcher` | starter-test에 이미 포함 | Low | 제거 완료 |
| REST Docs 유지 | 미사용이었으나 Claude로 테스트 자동 생성 후 문서화 연결 예정 | - | 유지 (의도적) |
| group/version 이중 정의 | root(`com.example.mpa`, `1.0.0`) vs admin-api(`net.koast`, `0.0.1-SNAPSHOT`) | Low | 미처리 |

---

## 현재 build.gradle

```groovy
plugins {
    id 'java'
    id 'war'
    id 'org.springframework.boot' version '3.4.1'
    id 'io.spring.dependency-management' version '1.1.7'
    id 'org.asciidoctor.jvm.convert' version '3.3.2'
}

group = 'net.koast'
version = '0.0.1-SNAPSHOT'

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

configurations {
    compileOnly {
        extendsFrom annotationProcessor
    }
}

repositories {
    mavenCentral()
}

ext {
    set('snippetsDir', file("build/generated-snippets"))
}

dependencies {
    implementation (
        'org.springframework.boot:spring-boot-starter-security',
        'org.springframework.boot:spring-boot-starter-validation',
        'org.springframework.boot:spring-boot-starter-web',
        'org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.4',
        'com.github.ulisesbocchio:jasypt-spring-boot-starter:3.0.4',
        'org.mapstruct:mapstruct:1.5.5.Final'
    )
    compileOnly 'org.projectlombok:lombok'
    developmentOnly 'org.springframework.boot:spring-boot-devtools'
    runtimeOnly 'org.postgresql:postgresql'
    annotationProcessor (
        'org.projectlombok:lombok',
        'org.projectlombok:lombok-mapstruct-binding:0.2.0',
        'org.mapstruct:mapstruct-processor:1.5.5.Final'
    )
    providedRuntime 'org.springframework.boot:spring-boot-starter-tomcat'
    testImplementation (
        'org.springframework.boot:spring-boot-starter-test',
        'org.mybatis.spring.boot:mybatis-spring-boot-starter-test:3.0.4',
        'org.springframework.restdocs:spring-restdocs-mockmvc',
        'org.springframework.security:spring-security-test'
    )
    testCompileOnly 'org.projectlombok:lombok'
    testAnnotationProcessor 'org.projectlombok:lombok'
}

tasks.named('test') {
    outputs.dir snippetsDir
    useJUnitPlatform()
    ignoreFailures = true  // 테스트 코드 작성 완료 후 제거 예정
}

tasks.named('asciidoctor') {
    inputs.dir snippetsDir
    dependsOn test
}
```
