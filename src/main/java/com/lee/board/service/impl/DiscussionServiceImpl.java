package com.lee.board.service.impl;

import com.lee.board.model.Discussion;
import com.lee.board.repository.DiscussionRepositoryI;
import com.lee.board.service.DiscussionServiceI;
import com.lee.member.model.Member;
import com.lee.member.repository.MemberRepositoryI;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscussionServiceImpl implements DiscussionServiceI {

  @Autowired private DiscussionRepositoryI discussionRepository;
  @Autowired private MemberRepositoryI memberRepositoryI;

  @Override
  public List<Discussion> getListByCategoryId(long... id) {

    List<Discussion> discussions = discussionRepository.getDiscussionListByCategoryId(id);
//    discussions.forEach(
//        discussion -> {
//          if (discussion.getRecentPostId() != 0) {
//            Member member = memberRepositoryI.getMemberById(discussion.getRecentPostMemberId());
//            discussion.setMember(member);
//          }
//        });
    return discussions;
  }

  @Override
  public Discussion getDiscussionById(long id) {
    return discussionRepository.getDiscussionInfoById(id);
  }

  @Override
  public int updatePostCount(int id) {
    Discussion discussion = discussionRepository.getDiscussionInfoById(id);
    discussion.setPostCount(discussion.getPostCount() + 1);
    return discussionRepository.updateDiscussionInfo(discussion);
  }
}
