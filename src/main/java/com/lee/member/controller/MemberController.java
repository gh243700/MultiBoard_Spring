package com.lee.member.controller;

import com.lee.member.service.MemberServiceI;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Lee97
 */
@Controller
public class MemberController {

  @Autowired
  private MemberServiceI memberService;

  @RequestMapping(value = "",method = RequestMethod.POST)
  public String login(@RequestParam String value, @RequestParam String password, Model model,
      HttpSession httpSession){
    int result = memberService.loginService(value,password);
    String message = "";
    if (result == -1){
      message = "username or password does not exist";
    }
    else if (result==0){
      message= "password does not match";
    }
    if ("".equals(message)){
      model.addAttribute("message",message);
      return "redirection:/login";
    }
    httpSession.setAttribute("member",memberService.getMemberById(result));
    return "redirection:/index";
  }
}
