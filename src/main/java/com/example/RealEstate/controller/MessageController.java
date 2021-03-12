package com.example.RealEstate.controller;

import com.example.RealEstate.domain.Message;
import com.example.RealEstate.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("message")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @GetMapping
    public ResponseEntity getMessages() {
        try{
            return ResponseEntity.ok(messageService.getMessages());
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("{id}")
    public ResponseEntity getOne(@PathVariable Long id) {
        try{
            return ResponseEntity.ok(messageService.getOneMessage(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/filter")
    public ResponseEntity filter(@RequestParam(required = false, defaultValue = "") String filter ){
        try{
            return ResponseEntity.ok(messageService.filter(filter));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PostMapping
    public ResponseEntity create(
            @Valid Message message,
            @RequestParam("file") List<MultipartFile> multiPartFileList
    ){
        try{
            messageService.create(message, multiPartFileList);
            return ResponseEntity.ok("Пост сделан");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PutMapping("{id}")
    public ResponseEntity update(
            @PathVariable("id") Message messageFromDb,
            @RequestBody Message message) {
        try{
            messageService.update(messageFromDb, message);
            return ResponseEntity.ok("Пост обнавлен");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        try{
            return ResponseEntity.ok(messageService.delete(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
