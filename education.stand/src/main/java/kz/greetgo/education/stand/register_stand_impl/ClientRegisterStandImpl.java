package kz.greetgo.education.stand.register_stand_impl;

import kz.greetgo.depinject.core.Bean;
import kz.greetgo.depinject.core.BeanGetter;
import kz.greetgo.education.controller.errors.RestError;
import kz.greetgo.education.controller.model.ClientInfo;
import kz.greetgo.education.controller.register.ClientRegister;
import kz.greetgo.education.stand.register_stand_impl.db.Db;
import kz.greetgo.education.stand.register_stand_impl.model.ClientDot;
import kz.greetgo.email.Email;
import kz.greetgo.email.EmailSender;
import kz.greetgo.email.EmailSenderController;
import org.json.JSONObject;

import java.util.*;

@Bean
public class ClientRegisterStandImpl implements ClientRegister {
    public BeanGetter<Db> db;

    public BeanGetter<EmailSender> emailSenderBeanGetter;
    public BeanGetter<EmailSenderController> emailSenderControllerBeanGetter;

    @Override
    public List<ClientInfo> getClientList() {
        List<ClientInfo> clientInfo = new ArrayList<ClientInfo>();
        for(ClientDot clientDot : db.get().clientStorage.values()){
            ClientInfo ci = new ClientInfo(clientDot.id,clientDot.name,clientDot.surname,clientDot.birthDate,clientDot.telephone,clientDot.email,clientDot.address,clientDot.password);
            clientInfo.add(ci);
        }
        return clientInfo;
    }


    @Override
    public String getClientDelete(String json) {
        JSONObject obj = new JSONObject(json);
        String id = obj.getString("id");
        System.out.println(id);
        db.get().clientStorage.remove(id);
        return "Ok delete";
    }

    @Override
    public String getClientAdd(String json) {
        System.out.println(json);
        JSONObject obj = new JSONObject(json);
        String id = obj.getString("id");
        String name = obj.getString("name");
        String surname = obj.getString("surname");
        String birthDate = obj.getString("birthDate");
        String telephone = obj.getString("telephone");
        String email = obj.getString("email");
        String address = obj.getString("address");
        String password = obj.getString("password");

        System.out.print(id);
        for (String myId : db.get().clientStorage.keySet()) {
            ClientDot cl = db.get().clientStorage.get(myId);
            if(cl.email.equals(email) && cl.email.equals(telephone)){
                return "Not saved";
            }
        }
        if (id.length() == 0) {
            Random ran = new Random();
            int a = ran.nextInt();
            id = a + "";
            ClientDot x = new ClientDot(id, name, surname, birthDate, telephone, email, address, "2",password);
            db.get().clientStorage.put(id, x);


            String body = "This is your link for activated account:\n http://localhost:1314/education/api/email/"+urlGenerator(email);

            Email emailSend = new Email();
            emailSend.setFrom("madikasd4@gmail.com");
            emailSend.setTo(email);
            emailSend.setSubject("Registration Finish");
            emailSend.setBody(body);


            emailSenderBeanGetter.get().send(emailSend);
            emailSenderControllerBeanGetter.get().sendAllExistingEmails();

            return "Ok, saved and sent email";
        } else {
            ClientDot x = db.get().clientStorage.get(id);
            x.setName(name);
            x.setSurname(surname);
            x.setEmail(email);
            x.setBirthDate(birthDate);
            x.setTelephone(telephone);
            x.setAddress(address);
            x.setPassword(password);

            db.get().clientStorage.put(id,x);
            return "Ok, updated";
        }


    }
    private String urlGenerator(String email){
        Random r = new Random();
        long number = r.nextLong();
        if(number<0)number*=-1;
        db.get().linkStorage.put(number, email);
        String strLong = Long.toString(number);
        return strLong;
    }

    @Override
    public String acceptUser(String number){
        String email = db.get().linkStorage.get(Long.valueOf(number));
        for (ClientDot cl : db.get().clientStorage.values()) {
            if(cl.email.equals(email)){
                cl.setAccepted("1");
            }
        }
        return email;

    }
}
