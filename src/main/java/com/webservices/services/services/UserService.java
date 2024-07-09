package com.webservices.services.services;

import com.webservices.services.entities.User;
import com.webservices.services.repositories.UserRepository;
import com.webservices.services.services.exceptions.DatabaseException;
import com.webservices.services.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(Long id) {
        Optional<User> user = repository.findById(id);
        return user.orElseThrow( () -> new ResourceNotFoundException(id) );
    }

    public User save (User user) {
        return repository.save(user);
    }

    public void delete(Long id) {
        try {
            if (repository.existsById(id)) {
                repository.deleteById(id);
            } else {
                throw new ResourceNotFoundException(id);
            }
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public User update(Long id, User user) {
        User obj = repository.getReferenceById(id);
        updateData(obj, user);
        return save(obj);
    }

    public void updateData(User obj, User user) {
        obj.setName(user.getName());
        obj.setEmail(user.getEmail());
        obj.setPhone(user.getPhone());
    }
}
