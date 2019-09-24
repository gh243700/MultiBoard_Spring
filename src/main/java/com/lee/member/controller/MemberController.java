package com.lee.member.controller;

import com.lee.member.model.Member;
import com.lee.member.service.MemberServiceI;
import java.lang.ProcessBuilder.Redirect;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/** @author Lee97 */
@Controller
public class MemberController {

  @Autowired private MemberServiceI memberService;

  @RequestMapping(value = "/member/logout", method = RequestMethod.GET)
  public String logout(HttpSession httpSession) {
    httpSession.removeAttribute("member");
    return "redirect:/home";
  }

  @RequestMapping(value = "/member/login", method = RequestMethod.GET)
  public String login(Model model, RedirectAttributes redirectAttributes) {
    model.addAllAttributes(redirectAttributes.getFlashAttributes());
    return "member/login";
  }

  @RequestMapping(value = "/member/login", method = RequestMethod.POST)
  public String login(
      @RequestParam("emailAddressOrName") String value,
      @RequestParam("password") String password,
      RedirectAttributes redirectAttributes,
      HttpSession httpSession) {
    Map<Integer, String> message = memberService.loginService(value, password);
    if (message.containsKey(0)) {
      redirectAttributes.addFlashAttribute("value", value);
      redirectAttributes.addFlashAttribute("password", password);
      redirectAttributes.addFlashAttribute("message", message.get(0));
      return "redirect:/member/login";
    }
    httpSession.setAttribute(
        "member", memberService.getMemberById(Integer.parseInt(message.get(1))));
    return "redirect:/home";
  }

  @RequestMapping(value = "/member/register", method = RequestMethod.GET)
  public String registerMember(Model model, RedirectAttributes redirectAttributes) {
    model.addAttribute(redirectAttributes.getFlashAttributes());
    return "member/register";
  }

  @RequestMapping(value = "/member/register", method = RequestMethod.POST)
  public String registerMember(
      @RequestParam("displayName") String displayName,
      @RequestParam("emailAddress") String emailAddress,
      @RequestParam("confirmEmailAddress") String confirmEmailAddress,
      @RequestParam("password") String password,
      @RequestParam("confirmPassword") String confirmPassword,
      RedirectAttributes redirectAttributes,
      HttpSession httpSession) {
    String displayValidation = memberService.displayNameValidation(displayName);
    String emailValidation = memberService.emailValidation(emailAddress, confirmEmailAddress);
    String passwordValidation = memberService.validatePassword(password, confirmPassword);
    if (displayValidation == null && emailValidation == null && passwordValidation == null) {
      Member member =
          Member.builder().username(displayName).password(password).email(emailAddress).build();
      String message = memberService.register(member);
      redirectAttributes.addFlashAttribute("message", message);
      if (message != null) {
        httpSession.setAttribute(
            "member", memberService.loginService(member.getEmail(), member.getPassword()));
        return "redirect:/member/login";
      }
    }
    redirectAttributes.addFlashAttribute("displayNameMessage", displayValidation);
    redirectAttributes.addFlashAttribute("emailMessage", emailValidation);
    redirectAttributes.addFlashAttribute("passwordMessage", passwordValidation);
    redirectAttributes.addFlashAttribute("displayName", displayName);
    redirectAttributes.addFlashAttribute("emailAddress", emailAddress);
    redirectAttributes.addFlashAttribute("confirmEmailAddress", confirmEmailAddress);
    redirectAttributes.addFlashAttribute("password", password);
    redirectAttributes.addFlashAttribute("confirmPassword", confirmPassword);
    return "redirect:/member/register";
  }
}
