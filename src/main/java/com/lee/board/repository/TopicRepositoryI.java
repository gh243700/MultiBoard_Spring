package com.lee.board.repository;

import com.lee.board.model.Post;
import com.lee.board.model.Topic;
import java.util.List;

public interface TopicRepositoryI {

  int insertTopic(Topic topic);

  int insertPost(Post post);

  int getMaxReplyStep(long topicId);

  long getMaxTopicId();

  Topic getTopicById(long topicId);

  int updateTopic(Topic topic);

  List<Post> getPostListByTopicId(long tId, int limit, int offset);

  List<Topic> getListByDiscussionId(long id, String sortby, int start, int end);
}
