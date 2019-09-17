package com.lee.board.service;

import com.lee.board.model.Post;
import com.lee.board.model.Topic;
import java.util.List;

public interface TopicServiceI {

  int insertPost(Post post, long memberId);

  List<Post> getPostListByTopicId(long topicId, int page);

  List<Topic> getListByDiscussionId(long id, String sortby, int page);
}
