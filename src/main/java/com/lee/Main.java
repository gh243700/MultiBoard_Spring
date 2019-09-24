package com.lee;

import com.lee.landon.config.ApplicationConfig;
import com.lee.member.repository.MemberRepositoryI;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {

  public static void main(String[] args) {
    ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
    context.getBean(MemberRepositoryI.class);
       // MemberRepositoryI mem = context.getBean(MemberRepositoryImpl.class);
    System.out.println(context);
  }
}
