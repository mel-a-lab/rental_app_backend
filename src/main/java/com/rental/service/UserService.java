package com.rental.service;

import com.rental.entities.User;
import com.rental.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(User user) {
        // Ici, vous implémenterez la logique d'enregistrement de l'utilisateur
        // Par exemple : hacher le mot de passe, enregistrer l'utilisateur dans la base de données, etc.
        return userRepository.save(user);
    }

    // D'autres méthodes peuvent être ajoutées ici pour gérer les utilisateurs
}
