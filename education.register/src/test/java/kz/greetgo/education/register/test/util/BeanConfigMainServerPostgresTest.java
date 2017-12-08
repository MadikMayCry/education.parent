package kz.greetgo.education.register.test.util;


import kz.greetgo.depinject.core.BeanConfig;
import kz.greetgo.depinject.core.Include;
import kz.greetgo.education.register.beans.all.any_db.BeanConfigAnyDbAll;

@BeanConfig
@Include({BeanConfigAnyDbAll.class})
public class BeanConfigMainServerPostgresTest {
}