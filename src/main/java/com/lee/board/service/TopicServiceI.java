package com.lee.board.service;

import com.lee.board.model.Post;
import com.lee.board.model.Topic;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

public interface TopicServiceI {

  @Transactional(rollbackFor = Exception.class)
  Map<Integer, Object> insertTopic(Topic topic, Post post);

  int insertPost(Post post, long memberId);

  Topic getTopicById(long id);

  List<Post> getPostListByTopicId(long topicId, int page);

  void uploadImg(
      MultipartFile upload, HttpServletResponse response, HttpServletRequest request);

  List<Topic> getListByDiscussionId(long id, String sortby, String page);
}
