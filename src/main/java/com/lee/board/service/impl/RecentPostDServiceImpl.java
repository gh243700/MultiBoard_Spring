package com.lee.board.service.impl;

import com.lee.board.model.RecentPostD;
import com.lee.board.repository.RecentPostDRepositoryI;
import com.lee.board.service.RecentPostDServiceI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Lee97
 */
@Service
public class RecentPostDServiceImpl implements RecentPostDServiceI {

  @Autowired
  private RecentPostDRepositoryI postDRepositoryI;

  @Override
  public List<RecentPostD> getListByDiscussionId(long... id){
    return postDRepositoryI.getListByDiscussionId(id);
  }
}
