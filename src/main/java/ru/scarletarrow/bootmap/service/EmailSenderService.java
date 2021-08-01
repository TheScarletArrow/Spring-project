package ru.scarletarrow.bootmap.service;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;
import java.util.Objects;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender javaMailSender;

    @SneakyThrows
    public void sendMailWithAttachment(String to, String body, String subject, /*String[] attachments*/ List<String> attachments){
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setFrom("speed.map.scarlet@gmail.com");
        helper.setText(body);
        helper.setSubject(subject);
        for (String attachment: attachments ) {
            FileSystemResource fileSystemResource = new FileSystemResource(new File(attachment));
            helper.addAttachment(Objects.requireNonNull(fileSystemResource.getFilename()), fileSystemResource);
        }
        javaMailSender.send(message);
        System.out.println("Mime message sent");
    }

    public void sendSimpleMail(String to, String body, String subject){
        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("obrekhov@mail.ru");
        message.setTo(to);
        message.setText(body);
        message.setSubject(subject);
        message.setFrom("speed.map.scarlet@gmail.com");

        javaMailSender.send(message);
        System.out.println("Message sent");
    }
}
