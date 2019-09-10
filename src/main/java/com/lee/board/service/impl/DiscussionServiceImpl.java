package com.lee.board.service.impl;

import com.lee.board.model.Discussion;
import com.lee.board.repository.DiscussionRepositoryI;
import com.lee.board.service.DiscussionServiceI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscussionServiceImpl implements DiscussionServiceI {

  @Autowired private DiscussionRepositoryI discussionRepository;

  @Override
  public List<Discussion> getListByCategoryId(long... id) {
    for (long i : id){
      System.out.println(i+"-----------------------------------------------------");
    }
    return discussionRepository.getDiscussionListByCategoryId(id);
  }

  @Override
  public int updatePostCount(int id) {
    Discussion discussion = discussionRepository.getDiscussionInfoById(id);
    discussion.setPostCount(discussion.getPostCount() + 1);
    return discussionRepository.updateDiscussionInfo(discussion);
  }

}
