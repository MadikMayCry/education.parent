package kz.greetgo.education.register.beans.all.any_db;

import kz.greetgo.depinject.core.BeanConfig;
import kz.greetgo.depinject.core.BeanScanner;
import kz.greetgo.depinject.core.Include;
import kz.greetgo.education.register.impl.BeanConfigRegisterServerImpl;

@BeanConfig
@BeanScanner
@Include({BeanConfigRegisterServerImpl.class})
public class BeanConfigAnyDbAll {
}
