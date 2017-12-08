package kz.greetgo.education.controller.register;

import kz.greetgo.education.controller.model.ClientInfo;

import java.util.List;
import java.util.Map;

public interface ClientRegister {
    List<ClientInfo> getClientList();
    String getClientDelete(String json);
    String getClientAdd(String json);
    String acceptUser(String genNumber);
}
