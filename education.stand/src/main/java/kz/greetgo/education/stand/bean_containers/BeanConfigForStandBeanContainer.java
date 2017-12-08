package kz.greetgo.education.stand.bean_containers;

import kz.greetgo.depinject.core.BeanConfig;
import kz.greetgo.depinject.core.BeanScanner;
import kz.greetgo.depinject.core.Include;
import kz.greetgo.education.controller.controller.BeanConfigControllers;
import kz.greetgo.education.stand.beans.BeanConfigStand;
import kz.greetgo.education.stand.beans.CrossOriginFilterRegistration;
import kz.greetgo.education.stand.register_stand_impl.BeanConfigRegisterStandImpl;

@BeanConfig
@BeanScanner
@Include({BeanConfigStand.class, BeanConfigControllers.class, BeanConfigRegisterStandImpl.class})
public class BeanConfigForStandBeanContainer {
}
