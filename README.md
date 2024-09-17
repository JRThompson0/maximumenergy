#### Basic backend with room to increase granularity of permissions. 
  Currently divided into ROLE_USER, ROLE_GUEST, ROLE_EMPLOYEE, and ROLE_ADMIN, though the role repository is not static, which allows relatively simple creation of new roles.
  
#### Current Permissions  
Very simple. Currently if a url is under /users/,** it requires user permission to view-same with employee and /employee/** as well as admin and /admin/**. This can be pretty extensively customized through a combination of namespace and the securityFilterChain, but there's not enough web pages to hide them all just yet.

#### Per-page permissions.

To make part of a page only viewable for a certain role using namespaces, one can use index.html as an example:

```
<div sec:authorize="isAuthenticated()">
    This content is only shown to authenticated users.
    Welcome, <span th:text="${#authentication.principal.username}">User</span>!
    <div sec:authorize="hasRole('ROLE_ADMIN')">
        This content is only shown to administrators.
    </div>
    <div sec:authorize="hasRole('ROLE_USER')">
        This content is only shown to people with the user role
    </div>
    <div sec:authorize="hasRole('ROLE_ADMIN')">
        This content is only shown to people with the admin role
    </div>
    <div sec:authorize="hasRole('ROLE_EMPLOYEE')">
        This content is only shown to people with the admin role
    </div>
</div>
```
  
#### Role Creation
New roles can be created by adding them to data.sql in the resources folder of this project as such:
  
```
  -- Insert 'ROLE_ADMIN' if it does not already exist
INSERT INTO maxxenergy.roles (role_Name)
SELECT 'ROLE_ADMIN'
WHERE NOT EXISTS (
    SELECT 1 FROM maxxenergy.roles WHERE role_Name = 'ROLE_ADMIN'
);
```
This will create a role that can be associated with certain filepaths in the SpringSecurityConfig using the security filter chain, or certain parts of a webpage using html namespaces.

#### Adding the Role to the securityChain
On the security filter chain, you can associate certain roles with certain paths and path patterns such that only certain roles can view things under certain paths. 

```
.requestMatchers("/admin/**").hasRole("ADMIN")
```
the hasRole() and hasAnyRole() methods both prepend "ROLE_" from the roles in the role repository.


#### Dependency List:

- Spring Boot Starters
  - spring-boot-starter-data-jpa: Adds support for JPA and integrates Hibernate ORM.
    - spring-boot-starter-thymeleaf: Provides support for Thymeleaf templates (though it’s listed twice with different versions, which might be redundant).
    - spring-boot-starter-web: Brings in Spring MVC and other web-related components.
    - spring-boot-starter-test: Includes testing libraries like JUnit, Hamcrest, and Mockito.
    - spring-boot-starter-security: Adds support for Spring Security.
- Jakarta Dependencies
  - jakarta.servlet-api: Provides the Jakarta Servlet API, marked as provided which means it’s expected to be provided by the runtime environment.
  - jakarta.validation-api: API for Jakarta Bean Validation, used for validating bean constraints.
- Hibernate Validator
  - hibernate-validator: Implements the Jakarta Bean Validation API, necessary for runtime validation.
- Database Connector
   - mysql-connector-j: Connector for MySQL databases, marked as runtime which means it’s needed at runtime but not for compilation.
- Lombok
  - lombok: Provides annotations to reduce boilerplate code. Marked as optional, meaning it’s not required at runtime or for compilation.
- Thymeleaf
  - thymeleaf: Core Thymeleaf template engine.
  - thymeleaf-spring6: Integration library for Thymeleaf with Spring 6.
- Spring Security
  - spring-security-test: Testing support for Spring Security.
    - spring-security-core: Core Spring Security components.
- DevTools
  - spring-boot-devtools: Provides additional development-time features such as hot swapping. Marked as optional.
- Thymeleaf Extras
  - thymeleaf-extras-springsecurity6: Adds Spring Security dialect for Thymeleaf.
