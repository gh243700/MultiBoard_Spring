package com.lee.board.repository;

import com.lee.board.model.Discussion;
import java.util.List;

public interface DiscussionRepositoryI {

  List<Discussion> getDiscussionListByCategoryId(long... id);

  Discussion getDiscussionById(int id);

  int updateDiscussionInfo(Discussion discussion);

}
