package com.utm.test.basic;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;


//@RunWith(SpringJUnit4ClassRunner.class)用来声明在Spring环境下进行测试
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations={……})用来声明配置文件，把项目中所有的关于ssh的配置文件都声明到里面
@ContextConfiguration(locations={"classpath:config/spring-common.xml",
        "classpath:config/spring-user.xml"
})
//这个非常关键，如果不加入这个注解配置，事务控制就会完全失效！
@Transactional
//这里的事务关联到配置文件中的事务控制器（transactionManager = "transactionManager"），同时指定自动回滚（defaultRollback = true）。这样做操作的数据才不会污染数据库！
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)

public abstract  class AbstractTestCase {

}
