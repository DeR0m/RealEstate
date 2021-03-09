package com.example.RealEstate.controller;

import com.example.RealEstate.domain.User;
import com.example.RealEstate.exceptions.UserAlreadyExistException;
import com.example.RealEstate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registration")
public class RegistrationController {
    @Autowired
    private UserService userService;

    @GetMapping()
    public String registration() {
        return "registration";
    }

    @PostMapping()
    public ResponseEntity registration(@RequestBody User user){
        try{
            userService.registration(user);
            return ResponseEntity.ok("Пользователь успешно сохранен");
        }
        catch (UserAlreadyExistException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
