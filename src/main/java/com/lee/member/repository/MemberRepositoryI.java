package com.lee.member.repository;

import com.lee.member.model.Member;
import com.lee.member.model.ProfileImg;
import java.util.List;

public interface MemberRepositoryI {

  int insertMember(Member member);


  Integer checkUserExists(String value, String password);

  boolean checkEmailExists(String email);

  boolean checkUsernameExists(String username);

  Member getMemberById(long id);

  List<Member> getMemberListById(long... ids);

  int deleteMemberById(long id);

  int updateMemberInfo(Member member);

}
