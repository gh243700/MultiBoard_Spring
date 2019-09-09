package com.lee.member.repository;

import com.lee.member.model.Member;
import com.lee.member.model.ProfileImg;

public interface MemberRepositoryI {

  int insertMember(Member member);

  ProfileImg getProfileImgById(long id);

  int insertMemberProfileImg(long memberId, ProfileImg profileImg);

  Integer checkUserExists(String value, String password);

  boolean checkEmailExists(String email);

  boolean checkUsernameExists(String username);

  Member getMemberById(long id);

  int deleteMemberById(long id);

  int updateMemberInfo(Member member);

  Integer getMaxMemberId();
}
