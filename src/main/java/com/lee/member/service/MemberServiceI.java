package com.lee.member.service;

import com.lee.member.model.Member;
import java.util.Map;

public interface MemberServiceI {

  Member getMemberById(long id);

  String displayNameValidation(String displayName);

  String emailValidation(String emailAddress, String confirmEmailAddress);

  String validatePassword(String password, String confirmPassword);

  String register(Member member);

  int deleteMember(long id);

  Map<Integer, String> loginService(String value, String password);

  int updateLastLogin(int id);

  int updatePostCount(int id);

  int updateMemberInfo(Member member);
}
