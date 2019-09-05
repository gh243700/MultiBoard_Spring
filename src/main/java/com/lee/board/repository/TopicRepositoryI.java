package com.lee.board.repository;

import com.lee.board.model.Topic;
import java.util.List;

public interface TopicRepositoryI {
  List<Topic> getListByDiscussionId(long id,int page);
}
