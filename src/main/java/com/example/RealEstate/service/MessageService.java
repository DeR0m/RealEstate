package com.example.RealEstate.service;

import com.example.RealEstate.domain.Message;
import com.example.RealEstate.repo.MessageRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

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



    public Message create(
            @Valid Message message,
            @RequestParam("file") List<MultipartFile> multiPartFileList
    ) throws IOException {

        for(MultipartFile file : multiPartFileList) {

            if (file != null && !file.getOriginalFilename().isEmpty()) {
                File uploadDir = new File(uploadPath);

                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }

                String uuidFile = UUID.randomUUID().toString();
                String resultFilename = uuidFile + "." + file.getOriginalFilename();

                file.transferTo(new File(uploadPath + "/" + resultFilename));

                message.setFilename(message.setFilename(Collections.singleton(resultFilename)));

            }

        }

        return messageRepo.save(message);
    }


    public Message update(
            @PathVariable("id") Message messageFromDb,
            @RequestBody Message message) {
        BeanUtils.copyProperties(message, messageFromDb, "id");

        return messageRepo.save(messageFromDb);
    }


    public Long delete(Long id) {
        messageRepo.deleteById(id);
        return id;
    }
}
