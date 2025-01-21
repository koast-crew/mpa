# Map Platform Application (MPA) 솔루션

**Map Platform Application(MPA)**는 지도 서비스 제공을 위한 플랫폼입니다.  
하위 프로젝트는 각각 관리자 및 사용자 대상의 백엔드 API와 프론트엔드 애플리케이션을 포함하고 있습니다.   
백엔드 프로젝트는 spring boot, 프론트엔드 프로젝트는 react를 사용하고 있으며, docker-compose 기반으로 개발 되었습니다.

---

## 1. 하위 프로젝트

| 프로젝트 이름   | 설명                                         | 운영환경                     |
|----------------|--------------------------------------------|--------------------------|
| `admin`   | 관리자 Spring Boot 프로젝트                       | tomcat                   |
| `user-api`    | 일반 사용자 Spring Boot 프로젝트                    | tomcat                   |
| `admin-web`   | 관리자 React 프론트엔드 프로젝트                       | admin 밑에 static 에 배포 |
| `user-web`    | 일반 사용자 React 프론트엔드 프로젝트                   | nginx 에 배포               |
| `doc`         | 데이터베이스, geoserver, docker 설정 등 다양한 문서들을 관리 |                          |

## 📌 개발 원칙 및 가이드
MPA 프로젝트의 개발 원칙과 코드 설계 가이드는 [개발 원칙 문서](doc/개발가이드/README.md)에서 확인할 수 있습니다.

---

## 2. 생성 및 설정 방법

아래 코드를 복사하여 PowerShell 또는 터미널에서 실행하면, 전체 프로젝트 구조를 자동으로 생성하고 설정할 수 있습니다.

```bash
# 1. 최상위 디렉토리 생성 및 이동
mkdir mpa
cd mpa

# 2. Gradle 초기화
gradle init --type basic

# 3. settings.gradle 파일 생성
Set-Content -Path ".\settings.gradle" -Value """
rootProject.name = 'mpa'

include 'admin'
include 'user-api'
include 'admin-web'
include 'user-web'
include 'doc'

project(':admin-web').projectDir = file('admin-web')
project(':user-web').projectDir = file('user-web')
project(':doc').projectDir = file('doc')
"""

# 4. build.gradle 파일 생성
Set-Content -Path ".\build.gradle" -Value """
allprojects {
    group = 'com.example.mpa'
    version = '1.0.0'

    repositories {
        mavenCentral()
    }
}

configure(subprojects.findAll { it.name in ['admin-web', 'user-web', 'doc'] }) {
    tasks.configureEach {
        enabled = false
    }
}
"""

# 5. 하위 프로젝트 디렉토리 생성
mkdir admin user-api admin-web user-web doc
mkdir doc\ddl doc\dml

# 6. Gradle Wrapper 생성
gradle wrapper

# 7. 프로젝트 구조 확인
Get-ChildItem -Recurse
```

---

## 3. 최종 디렉토리 구조

```plaintext
mpa/
├── build.gradle
├── settings.gradle
├── gradlew
├── gradlew.bat
├── gradle/
│   └── wrapper/
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── admin/           # Spring Initializer로 생성
├── user-api/            # Spring Initializer로 생성
├── admin-web/           # React 프로젝트
├── user-web/            # React 프로젝트
└── doc/                 # SQL 스크립트 관리
    ├── ddl/
    ├── dml/
    └── README.md
```

---

## 4. 빌드 및 실행

1) **전체 빌드**
   ```bash
   ./gradlew build
   ```

2) **특정 서브 프로젝트 빌드**
   예: `admin` 빌드
   ```bash
   ./gradlew :admin:build
   ```

3) **Spring Boot 실행**
   `admin-api` 또는 `user-api` 디렉토리에서 실행:
   ```bash
   ./gradlew bootRun
   ```

---

### **참고**
- **`admin-web`과 `user-web`**: React 프로젝트로 구성하며, Node.js 및 npm/yarn으로 관리합니다.
- **`doc`**: SQL 스크립트를 저장하고 관리하기 위한 디렉토리로, Gradle 빌드 대상에서 제외됩니다.


## 5. 시작

1) **컨테이너 시작**  
   docker-compose up -d
   
2) **컨테이너 중지**  
   docker-compose down  
   docker-compose down -v # 데이터까지 삭제

3) **컨테이너 재 시작**  
   docker-compose restart  
   docker-compose restart db  
   docker-compose restart geoserver  

4) **컨테이너 로그 확인**  
   docker-compose logs -f   
   docker-compose logs -f db  
   docker-compose logs -f geoserver  