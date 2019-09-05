package com.lee.board.service;

import com.lee.board.model.Discussion;
import java.util.List;

public interface DiscussionServiceI {

  List<Discussion> getListByCategoryId(long... id);

  int getPostCountById(int id);

  int updatePostCount(int id);
}
