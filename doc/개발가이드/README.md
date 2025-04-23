# MPA 프로젝트 개발 원칙

MPA(Map Platform Application) 프로젝트는 유지보수성과 확장성을 고려하여 아래와 같은 개발 원칙을 따릅니다.

## 1. Jasypt와 application.properties, custom properties 등의 설정이 엮이면서 ENC('xxxx") 형태가 안되고
      그냥 암호화 값을 설정
   2. mybatis 사용할 때 두가지 주의 사항이 있는데....
      SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) 을 빼면 세션 동기화에 문제가 생길 여지가
      있으므로 꼭 추가
3. @Repository 는 @MapperScan이 Mybatis 매퍼 인터페이스를 자동 관리하므로 생략하는 것이 표준

## 1. Entity는 Table과 1:1로 매핑해야 한다.
- **Entity 클래스는 데이터베이스 테이블과 1:1로 매핑되며, 불필요한 추가 필드를 포함하지 않는다.**
- 검색 조건, 요청 파라미터 등의 데이터는 **DTO에서 관리**하며, Entity에는 포함하지 않는다.

## 2. DTO와 Entity는 분리해야 한다.
- **Entity는 DB와의 매핑을 위한 클래스로만 사용하고, 요청(Request), 응답(Response) 데이터는 DTO로 분리한다.**
- **DTO는 Controller 및 Service 계층에서 사용하며, Entity는 Repository 및 MyBatis에서만 사용한다.**

## 3. MyBatis에서는 DTO를 사용하지 않아야 하지만, 검색 기능을 고려하여 예외적으로 허용한다.
- MyBatis에서는 Entity만을 사용해야 하지만, **검색 조건을 처리하기 위해 DTO(SearchRequest 등)를 사용할 수 있다.**
- **Entity가 Table과 1:1 매핑되는 원칙이 더 중요하므로, MyBatis에서 DTO 사용 원칙은 예외적으로 깨진다.**
- **즉, 검색 기능을 위해 MyBatis에서 DTO를 사용할 수 있지만, Entity에는 검색 관련 필드를 추가하지 않는다.**

이 원칙을 기반으로 MPA 프로젝트의 각 컴포넌트를 설계 및 구현한다.
