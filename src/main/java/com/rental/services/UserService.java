package com.rental.services;

import com.rental.entities.User;
import com.rental.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(User user) {
        // Logique d'enregistrement de l'utilisateur
        // Assurez-vous que le mot de passe est haché avant de sauvegarder l'utilisateur
        return userRepository.save(user);
    }

    public boolean existsByEmail(String email) {
        // Vérification si l'email existe déjà dans la base de données
        return userRepository.existsByEmail(email);
    }
}
