package com.luthfihariz.almasgocore.service;


public interface EmailSenderService {

    public void sendMail(String from, String to, String subject, String content);
}
