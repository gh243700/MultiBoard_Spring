package com.lee.member.service;

import com.lee.member.model.Member;

public interface MemberServiceI {

  Member getMemberById(long id);

  int register(Member member);

  int deleteMember(long id);

  int loginService(String value, String password);

  int updateLastLogin(int id);

  int updatePostCount(int id);

  int updateMemberInfo(Member member);
}
