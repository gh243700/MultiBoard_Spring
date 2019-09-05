package com.lee.member.repository;

import com.lee.member.model.Member;

public interface MemberRepositoryI {

  int insertMember(Member member);

  Integer checkUserExists(String value, String password);

  boolean checkEmailExists(String email);

  boolean checkUsernameExists(String username);

  Member getMemberById(long id);

  int deleteMemberById(long id);

  int updateMemberInfo(Member member);

  Integer getMaxMemberId();
}
