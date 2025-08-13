<p align="center">
  <img src="assets/banner.png" width="100%" alt="Forohub – API de gestión de tópicos">
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Status-En%20desarrollo-blueviolet" />
  <img src="https://img.shields.io/badge/Java-17-blue" />
  <img src="https://img.shields.io/badge/Spring_Boot-3.5.3-brightgreen" />
  <img src="https://img.shields.io/badge/MySQL-8-blue?logo=mysql&logoColor=white" />
  <img src="https://img.shields.io/badge/JWT-Seguridad-yellow" />
  <img src="https://img.shields.io/badge/Spring_Security-Implementado-orange" />
</p>

# 📘 Descripción del proyecto

**Forohub** es una API REST desarrollada en **Java + Spring Boot** que permite gestionar tópicos de un foro.  
A través de endpoints seguros y autenticados, se pueden:

- Registrar nuevos tópicos con validaciones y reglas de negocio  
- Listar, filtrar, paginar y ordenar tópicos  
- Consultar detalles de un tópico específico  
- Actualizar y eliminar tópicos existentes  
- Proteger la API con **JWT** y **Spring Security** para permitir acceso solo a usuarios autenticados  

Toda la información se almacena de forma persistente en **MySQL** y se accede mediante **Spring Data JPA** para una gestión eficiente de datos.

---

# 🛠️ Tecnologías utilizadas

| Herramienta | Versión | Uso |
|-------------|---------|-----|
| ☕ **Java** | 17 | Core del proyecto |
| 🌱 **Spring Boot** | 3.5.3 | Framework, inyección de dependencias y configuración REST |
| 🐬 **MySQL** | 8 | Base de datos persistente |
| 📦 **Spring Data JPA / Hibernate** | 6.2 | ORM y manejo de queries |
| 🔐 **Spring Security** | 6.3 | Autenticación y autorización |
| 🔑 **JWT (JSON Web Tokens)** | - | Gestión de sesiones y seguridad |
| 🛠️ **Maven Wrapper** | 3.9.10 | Build y gestión de dependencias |
| 💻 **VS Code / IntelliJ IDEA** | (opcional) | Editor/IDE |

---

# ✨ Funcionalidades

- API REST con endpoints para CRUD de tópicos  
- **Registrar tópico** → guarda título, mensaje, autor y curso, evitando duplicados  
- **Listar** tópicos con orden, paginación y filtros por curso/año  
- **Consultar** detalles de un tópico específico  
- **Actualizar** tópicos existentes  
- **Eliminar** tópicos  
- **Autenticación JWT** y control de acceso con **Spring Security**  
- **Validaciones** con `@Valid` y manejo de errores personalizados

---

# ▶️ Cómo ejecutar

> Requisitos: Java 17 y MySQL corriendo en `localhost:3306`.

1. **Clona el repo**  
   `git clone https://github.com/biachiuzano/forohub.git
   cd forohub`

2. Crea la base de datos (una vez)
 `sql
Copiar
Editar
CREATE DATABASE forohub; `

3. Configura las credenciales
Crea el archivo src/main/resources/application.properties con:
 `properties
Copiar
Editar
spring.datasource.url=jdbc:mysql://localhost:3306/forohub
spring.datasource.username=<tu_usuario>
spring.datasource.password=<tu_password>
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
api.security.secret=1234 `
O bien exporta estas variables de entorno.

4. Compila y corre
 `Copiar
Editar
./mvnw clean install -DskipTests
./mvnw spring-boot:run `

5. Prueba la API con herramientas como Insomnia o Postman usando el endpoint /login para obtener tu token JWT.

# 📁 Estructura del proyecto
forohub/
 ├─ .mvn/                       # Maven Wrapper
 
 ├─ src/
 
 │  ├─ main/
 
 │  │   ├─ java/com/example/demo/
 
 │  │   │   ├─ controller/      # Controladores REST
 
 │  │   │   ├─ dto/             # DTOs de entrada/salida
 
 │  │   │   ├─ model/           # Entidades JPA (Usuario, Topico, Curso)
 
 │  │   │   ├─ repository/      # Repositorios Spring Data JPA
 
 │  │   │   ├─ security/        # Configuración JWT y filtros
 
 │  │   │   ├─ service/         # Lógica de negocio
 
 │  │   │   └─ ForohubApplication.java
 
 │  │   └─ resources/
 
 │  │       └─ application.properties
 
 │  └─ test/                    # Pruebas unitarias e integración
 
 ├─ assets/                     # Banner e imágenes para el README
 
 ├─ pom.xml
 
 └─ README.md

 # 👩🏻‍💻 Desarrolladora

<p align="center">
  <a href="https://github.com/biachiuzano" target="_blank">
    <img src="https://github.com/user-attachments/assets/9f0e476a-1eb0-4884-aa5a-68e2768bb232" width="120" alt="Bianka Chiuzano" style="border-radius: 50%;" />
  </a>
  <br>
  <b>Bianka Chiuzano</b><br>
  <sub>Desarrolladora de software | Apasionada por la tecnología</sub>
</p>

# 📄 Licencia

Este proyecto fue desarrollado con fines educativos y es de libre uso.  
¡Podés compartirlo, modificarlo y seguir aprendiendo! ✨


