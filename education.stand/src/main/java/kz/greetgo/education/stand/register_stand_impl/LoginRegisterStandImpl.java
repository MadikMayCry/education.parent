package kz.greetgo.education.stand.register_stand_impl;

import kz.greetgo.depinject.core.Bean;
import kz.greetgo.depinject.core.BeanGetter;
import kz.greetgo.education.controller.model.LoginInfo;
import kz.greetgo.education.controller.register.LoginRegister;
import kz.greetgo.education.stand.register_stand_impl.db.Db;
import kz.greetgo.education.stand.register_stand_impl.model.ClientDot;
import org.json.JSONObject;

@Bean
public class LoginRegisterStandImpl implements LoginRegister{
    public BeanGetter<Db> db;

    @Override
    public LoginInfo getUser(String json){
        JSONObject obj = new JSONObject(json);
        String email = obj.getString("email");
        String password = obj.getString("password");

        for (ClientDot clientDot : db.get().clientStorage.values()){
            if (email.equals(clientDot.getEmail()) && password.equals(clientDot.getPassword()) && clientDot.accepted.equals("1")){
                LoginInfo loginInfo = new LoginInfo(clientDot.getId(),clientDot.getType_id());
                return loginInfo;
            }
        }
        return null;
    }

}
