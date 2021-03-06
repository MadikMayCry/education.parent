package kz.greetgo.education.stand.bean_containers;

import com.sun.mail.smtp.SMTPTransport;
import kz.greetgo.depinject.Depinject;
import kz.greetgo.depinject.core.Bean;
import kz.greetgo.education.stand.register_stand_impl.sheduling.MyConfig;
import kz.greetgo.education.stand.util.CommonConstant;
import kz.greetgo.email.Email;
import kz.greetgo.email.EmailSaver;
import kz.greetgo.email.EmailSender;
import kz.greetgo.email.EmailSenderController;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.security.Security;
import java.util.Date;
import java.util.Properties;
@Bean
public class SendEmailServerFactory {

    StandBeanContainer container = Depinject.newInstance(StandBeanContainer.class);

    MyConfig myConfig = container.myConfig();



    @Bean
    public EmailSender createEmailSenderSaver(){
        return new EmailSaver("Example","build/toSend");
    }


    @Bean
    public EmailSenderController createEmailSenderControllerServer(){
        File sendDir = new File("build/toSend");
        File sentDir = new File("build/sended");

        return new EmailSenderController(getEmailSenderForController(),sendDir,sentDir);
    }

    private EmailSender getEmailSenderForController(){
        return new EmailSender() {
            @Override
            public void send(Email email) {
                try{
                    CommonConstant cm = new CommonConstant();

                    Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

                    final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

                    Properties props = System.getProperties();
                    props.setProperty("mail.smtps.host", myConfig.smtpHost());
                    props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
                    props.setProperty("mail.smtp.socketFactory.fallback", "false");
                    props.setProperty("mail.smtp.port", myConfig.smtpPort());
                    props.setProperty("mail.smtp.socketFactory.port", myConfig.smtpPort());
                    props.setProperty("mail.smtps.auth", "true");

                    props.put("mail.smtps.quitwait", "false");

                    Session session = Session.getInstance(props, null);

                    final MimeMessage msg = new MimeMessage(session);

                    msg.setFrom(new InternetAddress(email.getFrom()));
                    msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getTo(), false));

                    msg.setSubject(email.getSubject());

                    msg.setText(email.getBody(), "UTF-8");
                    msg.setSentDate(new Date());

                    SMTPTransport t = (SMTPTransport) session.getTransport("smtp");

                    t.connect(myConfig.smtpHost(), myConfig.loginAccount(), myConfig.accountPassword());
                    t.sendMessage(msg, msg.getAllRecipients());

                    t.close();

                    System.out.println("OK");
                }catch (Exception e){
                    throw new RuntimeException(e);
                }
            }
        };
    }

    public void generateAndSendFunction(){

    }

}