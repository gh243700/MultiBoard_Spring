package com.lee.member.service.impl;

import com.lee.member.model.Member;
import com.lee.member.repository.MemberRepositoryI;
import com.lee.member.service.MemberServiceI;
import java.sql.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** @author Lee97 */
@Service
public class MemberServiceImpl implements MemberServiceI {

  @Autowired private MemberRepositoryI memberRepository;

  @Override
  public Member getMemberById(long id) {
    return memberRepository.getMemberById(id);
  }

  @Override
  public int register(Member member) {
    member.setId(memberRepository.getMaxMemberId() + 1);
    return memberRepository.insertMember(member);
  }

  @Override
  public int deleteMember(long id) {
    return memberRepository.deleteMemberById(id);
  }

  @Override
  public int loginService(String value, String password) {
    // return code: -1 = username or email not found, 0 = password does not match;
    int result = memberRepository.checkUserExists(value, password);
    if (result == -1) {
      if (!memberRepository.checkEmailExists(value)
          || !memberRepository.checkUsernameExists(value)) {
        return -1;
      } else {
        return 0;
      }
    }
    // returns id;
    return result;
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
