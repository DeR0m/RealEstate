package com.example.RealEstate.service;

import com.example.RealEstate.domain.Message;
import com.example.RealEstate.repo.MessageRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class MessageService {
    @Autowired
    private MessageRepo messageRepo;

    @Value("${upload.path}")
    private String uploadPath;

    public List<Message> getMessages() {
        return messageRepo.findAll();
    }


    public Message getOneMessage(Long id) {
        Message message = messageRepo.findById(id).get();
        return message;
    }


    public Iterable<Message> filter(String filter){
        Iterable<Message> messages = messageRepo.findAll();

        if(filter != null && !filter.isEmpty()){
            messages = messageRepo.findByTag(filter);
        }else {
            messages = messageRepo.findAll();
        }

        return messages;
    }

    public Message create(
            Message message,
            List<MultipartFile> multiPartFileList
    ) throws IOException {

        Set<String> resultFilename = new HashSet<>();

        for(MultipartFile file : multiPartFileList) {


            if (file != null && !file.getOriginalFilename().isEmpty()) {
                File uploadDir = new File(uploadPath);

                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }

                String uuidFile = UUID.randomUUID().toString();

                resultFilename.add(uuidFile + "." + file.getOriginalFilename());

                file.transferTo(new File(uploadPath + "/" + resultFilename));

            }

        }
        message.setFilename(resultFilename);


        return  messageRepo.save(message);
    }


    public Message update(
            Message messageFromDb,
            Message message) {
        BeanUtils.copyProperties(message, messageFromDb, "id");

        return messageRepo.save(messageFromDb);
    }


    public Long delete(Long id) {
        messageRepo.deleteById(id);
        return id;
    }
}
