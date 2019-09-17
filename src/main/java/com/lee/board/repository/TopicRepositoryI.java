package com.lee.board.repository;

import com.lee.board.model.Post;
import com.lee.board.model.Topic;
import java.util.List;

public interface TopicRepositoryI {

  int insertPost(Post post);

  int getMaxReplyStep(long topicId);

  Topic getTopicById(long topicId);

  int updateTopic(Topic topic);

  List<Post> getPostListByTopicId(long tId, int start, int end);

  List<Topic> getListByDiscussionId(long id, String sortby, int start, int end);
}
