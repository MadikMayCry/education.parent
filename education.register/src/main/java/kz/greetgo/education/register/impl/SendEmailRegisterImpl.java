package kz.greetgo.education.register.impl;

import kz.greetgo.depinject.core.Bean;
import kz.greetgo.depinject.core.BeanGetter;
import kz.greetgo.education.controller.register.SendEmailRegister;
import kz.greetgo.email.Email;
import kz.greetgo.email.EmailSender;
import kz.greetgo.email.EmailSenderController;

@Bean
public class SendEmailRegisterImpl implements SendEmailRegister {


    public BeanGetter<EmailSender> emailSender;

    public BeanGetter<EmailSenderController> emailSenderController;

    @Override
    public void toSend(){
        emailSenderController.get().sendAllExistingEmails();
    }

    @Override
    public void prepareSendEmail(){
        Email email = new Email();
        email.setFrom("zig.zak.1996@gmail.com");
        email.setTo("140103113@stu.sdu.edu.kz");
        email.setSubject("This is subj");
        email.setBody("Hi bro");
        emailSender.get().send(email);
    }
}
