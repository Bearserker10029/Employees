# 👥 Sistema de Gestión de Empleados

> Aplicación web desarrollada con Spring Boot y Thymeleaf para gestionar empleados, departamentos, trabajos y ubicaciones como parte de un laboratorio académico.

## 📋 Tabla de Contenidos

- [Descripción](#-descripción-del-proyecto)
- [Tecnologías](#-tecnologías-usadas)
- [Estructura](#-estructura-principal)
- [Flujo Funcional](#-flujo-funcional-implementado)
- [Modelos de Datos](#-modelos-de-datos)
- [Estado](#-estado-de-implementación)
- [Ejecución](#-cómo-ejecutar)

---

## 📝 Descripción del Proyecto

Una aplicación web que permite gestionar información de empleados en una organización, facilitando a los usuarios:

✅ Listar todos los empleados registrados en el sistema  
✅ Crear nuevos registros de empleados  
✅ Editar información de empleados existentes  
✅ Asociar empleados con departamentos, trabajos y ubicaciones  
✅ Gestionar datos maestros de departamentos, trabajos y ubicaciones  

## 💻 Tecnologías Usadas

| Tecnología | Versión | Uso |
|-----------|---------|-----|
| Java | 21+ | Lenguaje base |
| Spring Boot | 3.x+ | Framework MVC |
| Spring MVC | Incluido | Controladores HTTP |
| Spring Data JPA | Incluido | Acceso a datos |
| Thymeleaf | Starter | Motor de plantillas |
| Maven | Wrapper | Gestor de dependencias |
| Base de Datos | SQL | Persistencia de datos |

## 📂 Estructura Principal

```
employees/
├── src/
│   ├── main/
│   │   ├── java/com/example/employees/
│   │   │   ├── EmployeesApplication.java
│   │   │   ├── controller/
│   │   │   │   └── EmployeeController.java
│   │   │   ├── model/
│   │   │   │   ├── Employees.java
│   │   │   │   ├── Departments.java
│   │   │   │   ├── Jobs.java
│   │   │   │   └── Locations.java
│   │   │   ├── repository/
│   │   │   │   ├── EmployeesRepository.java
│   │   │   │   ├── DepartmentsRepository.java
│   │   │   │   ├── JobsRepository.java
│   │   │   │   └── LocationsRepository.java
│   │   │   └── dto/
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── static/
│   │       └── templates/
│   │           └── Employee/
│   │               ├── ListEmployee.html
│   │               ├── NewEmployee.html
│   │               └── EditEmployee.html
│   └── test/
├── pom.xml
├── mvnw
├── mvnw.cmd
├── HELP.md
└── README.md
```

## 🔄 Flujo Funcional Implementado

```
┌──────────────────────────────────────────────────────────────┐
│              FLUJO DE GESTIÓN DE EMPLEADOS                   │
├──────────────────────────────────────────────────────────────┤
│  1. GET /employees/list                                      │
│     ↓ Muestra listado de todos los empleados                 │
│                                                              │
│  2. GET /employees/new                                       │
│     ↓ Muestra formulario para crear nuevo empleado           │
│                                                              │
│  3. POST /employees/save                                     │
│     ↓ Guarda nuevo empleado en base de datos                 │
│                                                              │
│  4. GET /employees/edit/{id}                                 │
│     ↓ Muestra formulario de edición del empleado             │
│                                                              │
│  5. POST /employees/update                                   │
│     ↓ Actualiza información del empleado                     │
│                                                              │
│  6. GET /employees/delete/{id}                               │
│     ↓ Elimina empleado del sistema                           │
└──────────────────────────────────────────────────────────────┘
```

| Endpoint | Método | Descripción |
|----------|--------|-------------|
| `/employees/list` | GET | Listar todos los empleados |
| `/employees/new` | GET | Mostrar formulario de nuevo empleado |
| `/employees/save` | POST | Guardar nuevo empleado |
| `/employees/edit/{id}` | GET | Mostrar formulario de edición |
| `/employees/update` | POST | Actualizar empleado |
| `/employees/delete/{id}` | GET | Eliminar empleado |

## 📊 Modelos de Datos

### Employees (Empleados)
- **ID:** Identificador único
- **Nombre:** Nombre del empleado
- **Departamento:** Referencia a Departments
- **Trabajo:** Referencia a Jobs
- **Ubicación:** Referencia a Locations
- **Salario:** Monto del salario

### Departments (Departamentos)
- **ID:** Identificador único
- **Nombre:** Nombre del departamento
- **Ubicación:** Referencia a Locations

### Jobs (Trabajos)
- **ID:** Identificador único
- **Título:** Título del puesto
- **Salario Mínimo:** Rango salarial mínimo
- **Salario Máximo:** Rango salarial máximo

### Locations (Ubicaciones)
- **ID:** Identificador único
- **Ciudad:** Nombre de la ciudad
- **País:** País de ubicación

## ✅ Estado de Implementación

### ✔️ Completado

- [x] Modelos de entidades (Employees, Departments, Jobs, Locations)
- [x] Repositorios para acceso a datos (JPA)
- [x] Controlador EmployeeController con CRUD básico
- [x] Vistas Thymeleaf (ListEmployee, NewEmployee, EditEmployee)
- [x] Binding de datos con formularios HTML
- [x] Integración con base de datos SQL
- [x] Relaciones entre entidades

## 🚀 Cómo Ejecutar

### Requisitos Previos

- Java 21+ (compatible con Spring Boot 3.x+)
- Maven 3.6+ (incluido como wrapper)
- Base de datos SQL configurada (ver `hr dump.sql`)

### Preparar la Base de Datos

Importa el archivo `hr dump.sql` en tu servidor de base de datos SQL:

```sql
-- Ejecutar el archivo sql en tu DBMS
source hr dump.sql;
```

### Configurar la Conexión

Actualiza el archivo `application.properties` con tus credenciales de base de datos:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/employees_db
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
```

### Ejecutar la Aplicación

**En Windows (PowerShell):**

```powershell
.\mvnw.cmd spring-boot:run
```

**En Linux/macOS:**

```bash
chmod +x mvnw
./mvnw spring-boot:run
```

### Acceder a la Aplicación

Una vez iniciada, abre tu navegador en:

```
http://localhost:8080/employees/list
```

**Puerto por defecto:** 8080

---

## 📚 Recursos Adicionales

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Thymeleaf Guide](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html)
- [Spring MVC](https://docs.spring.io/spring-framework/reference/web/webmvc.html)

---

## 📄 Licencia

Este proyecto es de uso académico y educativo como parte de un laboratorio de curso universitario.
