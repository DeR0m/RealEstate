package com.example.RealEstate.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
@ToString(of = {"id", "text"})
@EqualsAndHashCode(of = {"id"})
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String text;

    @ElementCollection
    private Set<String> filename;

    public Message() {
    }

    public Message(Long id, String text) {
        this.id = id;
        this.text = text;
    }

    public Set<String> getFilename() {
        return filename;
    }

    public Set<String> setFilename(Set<String> filename) {
        this.filename = filename;
        return filename;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
