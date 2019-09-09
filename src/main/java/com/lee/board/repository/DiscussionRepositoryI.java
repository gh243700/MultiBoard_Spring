package com.lee.board.repository;

import com.lee.board.model.Discussion;
import java.util.List;

public interface DiscussionRepositoryI {

  List<Discussion> getDiscussionListByCategoryId(long... id);

  Discussion getDiscussionInfoById(long id);

  int updateDiscussionInfo(Discussion discussion);
}
