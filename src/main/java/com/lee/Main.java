package com.lee;

import com.lee.landon.config.ApplicationConfig;
import com.lee.landon.config.WebConfig;
import com.lee.landon.config.WebInitializerConfig;
import com.lee.member.model.Member;
import com.lee.member.repository.MemberRepositoryI;
import com.lee.member.repository.impl.MemberRepositoryImpl;
import com.lee.member.service.MemberServiceI;
import com.lee.member.service.impl.MemberServiceImpl;
import jdk.nashorn.internal.objects.Global;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;


public class Main {

  public static void main(String[] args) {
    ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
    context.getBean(MemberRepositoryI.class);
       // MemberRepositoryI mem = context.getBean(MemberRepositoryImpl.class);
    System.out.println(context);
  }
}
