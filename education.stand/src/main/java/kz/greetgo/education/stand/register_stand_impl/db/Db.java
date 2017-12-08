package kz.greetgo.education.stand.register_stand_impl.db;

import kz.greetgo.depinject.core.Bean;
import kz.greetgo.depinject.core.HasAfterInject;
import kz.greetgo.education.stand.register_stand_impl.model.ClientDot;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Bean
public class Db implements HasAfterInject{
  public final Map<String, ClientDot> clientStorage = new HashMap<>();
  public final Map<Long,String> linkStorage = new HashMap<>();

  @Override
  public void afterInject() throws Exception {
    prepareData();
  }

  private void prepareData() {
    clientStorage.put("1",new ClientDot("1","Mdk","Last name","32/32/3232","+7777777777","d1e.4.liberty@gmail.com","SDU","1","qwe"));
  }
}
