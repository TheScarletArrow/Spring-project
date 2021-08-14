package ru.scarletarrow.bootmap.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mail {
    private String to;
    private String subject;
    private String body;
    private List<String> attachment;
}
