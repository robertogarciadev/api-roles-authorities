# 🔐 Auth Roles API  

API RESTful que implementa autenticación y autorización con **JWT**, utilizando un control de acceso basado en **roles** y **authorities**.  

---

## 📋 Descripción  

### 🇪🇸 Español  
Esta API permite gestionar usuarios y proteger recursos mediante un sistema de autenticación con **JSON Web Tokens (JWT)**.  
El control de acceso se realiza según **roles** y **authorities**, garantizando que solo los usuarios con permisos adecuados puedan acceder a cada endpoint.  
Además, la documentación de los endpoints está generada automáticamente con **Swagger UI** y la especificación **OpenAPI 3** gracias a la librería **springdoc-openapi**.  

### 🇬🇧 English  
This API provides user management and secures resources through **JWT-based authentication**.  
Access control is handled via **roles** and **authorities**, ensuring that only users with the appropriate permissions can access each endpoint.  
API documentation is automatically generated with **Swagger UI** and **OpenAPI 3**, using **springdoc-openapi** library.  

---

📌 Características principales  

✅ Spring Boot como framework principal.  

✅ Spring Data JPA para la persistencia con Hibernate.  

✅ Spring Security + JWT para autenticación y autorización en cada petición.  

✅ Gestión de roles y permisos: control de acceso a endpoints según el rol del usuario.  

✅ PasswordEncoder + UserDetails para manejo seguro de contraseñas y usuarios.  

✅ Arquitectura por capas: model, repository, service, controller.  

✅ Manejo centralizado de excepciones con GlobalExceptionHandler.  

✅ Documentación de endpoints con **Swagger UI + OpenAPI 3 (springdoc)**.  

✅ Pruebas manuales con Postman.  

✅ Dockerfile para empaquetar y desplegar la aplicación fácilmente.  

---

🛠️ Tecnologías utilizadas  

- Java 21  
- Spring Boot 3  
- Spring Security  
- Spring Data JPA (Hibernate)  
- JWT (JSON Web Tokens)  
- PostgreSQL  
- Docker  
- Postman  
- Swagger UI + OpenAPI 3 (springdoc-openapi)  

---

✨ Próximas mejoras  

📌 Tests unitarios e integración con JUnit + Mockito.  
📌 Pipeline de CI/CD con GitHub Actions.  

