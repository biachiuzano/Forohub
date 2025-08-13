-- Tabla PERFIL
CREATE TABLE perfil (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);

-- Tabla USUARIO
CREATE TABLE usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    correo_electronico VARCHAR(150) NOT NULL UNIQUE,
    contrasena VARCHAR(255) NOT NULL
);

-- Tabla intermedia USUARIO_PERFIL (relación M:N)
CREATE TABLE usuario_perfil (
    usuario_id BIGINT NOT NULL,
    perfil_id BIGINT NOT NULL,
    PRIMARY KEY (usuario_id, perfil_id),
    CONSTRAINT fk_usuario_perfil_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id),
    CONSTRAINT fk_usuario_perfil_perfil FOREIGN KEY (perfil_id) REFERENCES perfil(id)
);

-- Tabla CURSO
CREATE TABLE curso (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    categoria VARCHAR(100)
);

-- Tabla TOPICO
CREATE TABLE topico (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    mensaje TEXT NOT NULL,
    fecha_creacion DATETIME NOT NULL,
    status VARCHAR(50) NOT NULL,
    autor_id BIGINT NOT NULL,
    curso_id BIGINT NOT NULL,
    CONSTRAINT fk_topico_autor FOREIGN KEY (autor_id) REFERENCES usuario(id),
    CONSTRAINT fk_topico_curso FOREIGN KEY (curso_id) REFERENCES curso(id)
);

-- Tabla RESPUESTA
CREATE TABLE respuesta (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    mensaje TEXT NOT NULL,
    fecha_creacion DATETIME NOT NULL,
    solucion BOOLEAN DEFAULT FALSE,
    topico_id BIGINT NOT NULL,
    autor_id BIGINT NOT NULL,
    CONSTRAINT fk_respuesta_topico FOREIGN KEY (topico_id) REFERENCES topico(id),
    CONSTRAINT fk_respuesta_autor FOREIGN KEY (autor_id) REFERENCES usuario(id)
);
