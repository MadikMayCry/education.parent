package kz.greetgo.education.stand.bean_containers;

import kz.greetgo.depinject.core.BeanContainer;
import kz.greetgo.depinject.core.Include;
import kz.greetgo.education.stand.beans.StandServer;
import kz.greetgo.education.stand.register_stand_impl.sheduling.MainScheduler;
import kz.greetgo.education.stand.register_stand_impl.sheduling.MyConfig;
import kz.greetgo.education.stand.register_stand_impl.sheduling.MyTask;




@Include(BeanConfigForStandBeanContainer.class)
public interface StandBeanContainer extends BeanContainer {
    StandServer standServer();

    MyConfig myConfig();

    MainScheduler getMainScheduler();

    MyTask myTask();
}