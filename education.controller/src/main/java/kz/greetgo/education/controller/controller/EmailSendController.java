package kz.greetgo.education.controller.controller;

import kz.greetgo.depinject.core.Bean;
import kz.greetgo.depinject.core.BeanGetter;
import kz.greetgo.education.controller.register.ClientRegister;
import kz.greetgo.education.controller.utils.Controller;
import kz.greetgo.mvc.annotations.Mapping;
import kz.greetgo.mvc.annotations.ParPath;

@Bean
@Mapping("/email")
public class EmailSendController implements Controller {
    public BeanGetter<ClientRegister> userRegisterBeanGetter;

    @Mapping("/{generatedNumber}")
    public void sentEmail(@ParPath("generatedNumber") String num){
        System.out.println(num);
        String username = userRegisterBeanGetter.get().acceptUser(num);
        System.out.println(username);
    }
}