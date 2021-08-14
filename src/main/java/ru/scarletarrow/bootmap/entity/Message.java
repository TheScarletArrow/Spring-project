package ru.scarletarrow.bootmap.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private MESSAGE_TYPE message_type;
    private String message;

}
