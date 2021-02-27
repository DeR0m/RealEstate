package com.example.RealEstate.controller;

import com.example.RealEstate.domain.User;
import com.example.RealEstate.exceptions.UserAlreadyExistException;
import com.example.RealEstate.exceptions.UserNotFoundException;
import com.example.RealEstate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
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

    @GetMapping
    public ResponseEntity getOneUser(@RequestParam Long id){
        try{
            return ResponseEntity.ok(userService.getOne(id));
        }
        catch (UserNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
