package com.lee.member.service.impl;

import com.lee.member.model.Member;
import com.lee.member.repository.MemberRepositoryI;
import com.lee.member.service.MemberServiceI;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** @author Lee97 */
@Service
public class MemberServiceImpl implements MemberServiceI {

  @Autowired private MemberRepositoryI memberRepository;

  @Override
  public Member getMemberById(long id) {
    return memberRepository.getMemberById(id);
  }

  @Override
  public String displayNameValidation(String displayName) {
    String message = null;
    if (memberRepository.checkUsernameExists(displayName)) {
      message = "user already exists";
    }
    return message;
  }

  @Override
  public String emailValidation(String emailAddress, String confirmEmailAddress) {
    String message = null;
    if (!emailAddress.equals(confirmEmailAddress)) {
      message = "email does not match";
    }
    if (memberRepository.checkEmailExists(emailAddress)) {
      message = "email exists";
    }
    return message;
  }

  @Override
  public String validatePassword(String password, String confirmPassword) {
    String message = null;
    if (!password.equals(confirmPassword)) {
      message = "password does not match";
    }
    return message;
  }

  @Override
  @Transactional(rollbackFor = {DataAccessException.class, IOException.class})
  public String register(Member member) {
    String message = null;
    if (memberRepository.insertMember(member) != 1) {
      message = "register failed";
    }
    return message;
  }

  @Override
  public int deleteMember(long id) {
    return memberRepository.deleteMemberById(id);
  }

  @Override
  public Map<Integer, String> loginService(String value, String password) {
    String message = null;
    int val = memberRepository.checkUserExists(value, password);
    Map<Integer, String> map = new HashMap<>();
    if (val == -1) {
      boolean emailExists = memberRepository.checkEmailExists(value);
      boolean usernameExists = memberRepository.checkUsernameExists(value);
      if (!emailExists && !usernameExists) {
        message = "username or email does not exist";
      } else {
        message = "password does not match";
      }
      map.put(0, message);
    } else {
      map.put(1, Integer.toString(val));
    }
    // returns id;
    return map;
  }

  @Override
  public int updateLastLogin(int id) {
    Member member = memberRepository.getMemberById(id);
    member.setLastVisit(new Timestamp(System.currentTimeMillis()));
    return memberRepository.updateMemberInfo(member);
  }

  @Override
  public int updatePostCount(int id) {
    Member member = memberRepository.getMemberById(id);
    member.setPostCount(member.getPostCount() + 1);
    return memberRepository.updateMemberInfo(member);
  }

  @Override
  public int updateMemberInfo(Member member) {
    return memberRepository.updateMemberInfo(member);
  }
}
