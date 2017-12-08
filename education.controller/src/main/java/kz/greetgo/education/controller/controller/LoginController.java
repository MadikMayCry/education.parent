package kz.greetgo.education.controller.controller;

import kz.greetgo.depinject.core.Bean;
import kz.greetgo.depinject.core.BeanGetter;
import kz.greetgo.education.controller.model.LoginInfo;
import kz.greetgo.education.controller.register.LoginRegister;
import kz.greetgo.education.controller.utils.Controller;
import kz.greetgo.mvc.annotations.Mapping;
import kz.greetgo.mvc.annotations.RequestInput;
import kz.greetgo.mvc.annotations.ToJson;

@Bean
@Mapping("/user")
public class LoginController implements Controller {
    public BeanGetter<LoginRegister> loginRegister;

    @ToJson
    @Mapping("/login")
    public LoginInfo getUser(@RequestInput String json){
        return loginRegister.get().getUser(json);
    }

}
