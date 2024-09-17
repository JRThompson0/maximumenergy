#### Basic backend with room to increase granularity of permissions. 
  Currently divided into ROLE_USER, ROLE_GUEST, ROLE_EMPLOYEE, and ROLE_ADMIN, though the role repository is not static. 
  
  
  
  
#### Current Permissions  
Very simple. Currently if a url is under /users/,** it requires user permission to view-same with employee and /employee/** as well as admin and /admin/**. 

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
    <a href="#" th:href="@{/logout}">Log Out</a>
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
