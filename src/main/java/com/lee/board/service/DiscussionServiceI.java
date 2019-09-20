package com.lee.board.service;

import com.lee.board.model.Discussion;
import java.util.List;
import java.util.Map;

public interface DiscussionServiceI {

  List<Discussion> getListByCategoryId(long... id);

  Discussion getDiscussionById(long id);

  int updatePostCount(int id);
}
