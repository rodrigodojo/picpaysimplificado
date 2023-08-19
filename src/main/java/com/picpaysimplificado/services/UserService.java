package com.picpaysimplificado.services;

import com.picpaysimplificado.repositories.UserRepository;
import com.picpaysimplificado.domains.user.User;
import com.picpaysimplificado.domains.user.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void validateTransaction(User sender, BigDecimal amount) throws Exception {
        if (sender.getUserType() == UserType.MERCHANT) {
            throw new Exception("Usuario não pode realizar essa operação");
        }
        if (sender.getBalance().compareTo(amount) < 0) {
            throw new Exception("Usuario não tem saldo suficiente");
        }
    }

    public User findUserById(Long id) throws Exception {
        return this.userRepository.findUserById(id).orElseThrow(() -> new Exception("Usuário nao encontrado"));
    }

    public void saveUser(User user){
        this.userRepository.save(user);
    }
}
