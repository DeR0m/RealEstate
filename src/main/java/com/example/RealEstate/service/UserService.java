package com.example.RealEstate.service;

import com.example.RealEstate.domain.User;
import com.example.RealEstate.exceptions.UserAlreadyExistException;
import com.example.RealEstate.exceptions.UserNotFoundException;
import com.example.RealEstate.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

   public User registration(User user) throws UserAlreadyExistException {
       if(userRepo.findByUsername(user.getUsername()) != null){
           throw new UserAlreadyExistException("Пользователь с таким именем существует");
       }
       return userRepo.save(user);
   }

   public User getOne(Long id) throws UserNotFoundException {
        User user = userRepo.findById(id).get();
        if(user == null){
            throw new UserNotFoundException("Пользователь не найден");
        }
        return user;
   }
}
