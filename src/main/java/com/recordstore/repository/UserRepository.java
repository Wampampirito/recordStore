package com.recordstore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recordstore.model.User;

/**
 * Repositorio para la entidad {@link User}, proporcionando acceso a las operaciones de base de datos.
 * 
 * La interfaz {@link UserRepository} extiende {@link JpaRepository}, lo que permite realizar operaciones CRUD 
 * sobre la entidad {@link User} sin necesidad de escribir implementaciones manuales.
 * 
 * Métodos adicionales:
 * <ul>
 *   <li><b>existsByEmail(String email)</b>: Verifica si ya existe un usuario con un correo electrónico específico.</li>
 * </ul>
 * 
 * Ejemplo de uso:
 * <pre>
 * Boolean exists = userRepository.existsByEmail("correo@ejemplo.com");
 * </pre>
 */
public interface UserRepository extends JpaRepository<User, Double> {

    /**
     * Verifica si existe un usuario con el correo electrónico proporcionado.
     * 
     * Este método se utiliza para evitar la duplicación de correos electrónicos en la base de datos al 
     * registrar nuevos usuarios.
     * 
     * @param email El correo electrónico que se desea verificar.
     * @return {@code true} si el correo ya está registrado, {@code false} en caso contrario.
     */
    Boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
}