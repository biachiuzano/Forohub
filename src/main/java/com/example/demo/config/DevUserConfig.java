package com.example.demo.config;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DevUserConfig {

    @Bean
    CommandLineRunner initUser(UsuarioRepository repo, PasswordEncoder encoder) {
        return args -> {
            // Cambiá estos dos si querés otro usuario de prueba
            String correo = "test@correo.com";
            String raw = "123456"; // esta es la contraseña que mandarás desde Insomnia

            var existente = repo.findByCorreoElectronico(correo).orElse(null);
            if (existente == null) {
                var u = new Usuario("Usuario Test", correo, encoder.encode(raw));
                repo.save(u);
                System.out.println(">>> Usuario creado: " + correo + " / " + raw);
            } else {
                // Si querés forzar que el usuario de prueba tenga esta contraseña:
                // existente.setContrasena(encoder.encode(raw));
                // repo.save(existente);
                System.out.println(">>> Usuario ya existe: " + correo);
            }
        };
    }
}
