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
    return discussionRepository.getDiscussionListByCategoryId(id);
  }

  @Override
  public int getPostCountById(int id) {
    return discussionRepository.getDiscussionById(id).getPostCount();
  }

  @Override
  public int updatePostCount(int id) {
    Discussion discussion = discussionRepository.getDiscussionById(id);
    discussion.setPostCount(discussion.getPostCount() + 1);
    return discussionRepository.updateDiscussionInfo(discussion);
  }
}
