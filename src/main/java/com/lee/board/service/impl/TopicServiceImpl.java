package com.lee.board.service.impl;

import com.lee.board.model.Discussion;
import com.lee.board.model.Post;
import com.lee.board.model.Topic;
import com.lee.board.repository.DiscussionRepositoryI;
import com.lee.board.repository.TopicRepositoryI;
import com.lee.board.service.TopicServiceI;
import com.lee.member.model.Member;
import com.lee.member.repository.MemberRepositoryI;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class TopicServiceImpl implements TopicServiceI {

  @Autowired private TopicRepositoryI topicRepositoryI;

  @Autowired private MemberRepositoryI memberRepositoryI;

  @Autowired private DiscussionRepositoryI discussionRepositoryI;

  @Override
  @Transactional(rollbackFor = Exception.class)
  public Map<Integer, Object> insertTopic(Topic topic, Post post) {
    int result1 = topicRepositoryI.insertTopic(topic);
    long topicId = topicRepositoryI.getMaxTopicId();
    post.setTopicId(topicId);
    int result2 = topicRepositoryI.insertPost(post);
    Map<Integer, Object> map = new HashMap<>();
    if (result1 == 1 && result2 == 1) {
      map.put(1, topicRepositoryI.getMaxTopicId());
    } else {
      map.put(0, "something went wrong");
      throw new RuntimeException();
    }
    return map;
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public int insertPost(Post post, long memberId) {
    post.setWriter(memberId);
    post.setReplyStep(topicRepositoryI.getMaxReplyStep(post.getTopicId()) + 1);

    Discussion discussion = discussionRepositoryI.getDiscussionInfoById(post.getDiscussionId());

    Topic topic = topicRepositoryI.getTopicById(post.getTopicId());

    Member member = memberRepositoryI.getMemberById(memberId);

    if (discussion != null && topic != null && member != null) {
      discussion.setPostCount(discussion.getPostCount() + 1);
      discussion.setRecentPostId(post.getId());
      discussion.setRecentPostTime(new Timestamp(System.currentTimeMillis()));
      discussion.setRecentPostMemberId(post.getWriter());
      discussion.setRecentPostTopicsTitle(post.getTitle());

      topic.setLastPostId(post.getId());
      topic.setLastPostMemberId(memberId);
      topic.setReplyNumber(topic.getReplyNumber() + 1);

      member.setPostCount(member.getPostCount() + 1);

      if (topicRepositoryI.insertPost(post) != 0
          && discussionRepositoryI.updateDiscussionInfo(discussion) != 0
          && topicRepositoryI.updateTopic(topic) != 0
          && memberRepositoryI.updateMemberInfo(member) != 0) {
        return 1;
      }
    }
    return 0;
  }

  @Override
  public Topic getTopicById(long id) {
    return topicRepositoryI.getTopicById(id);
  }

  @Override
  public List<Post> getPostListByTopicId(long topicId, int page) {
    int offset = page * 25;
    int limit = offset + 25;
    List<Post> posts = topicRepositoryI.getPostListByTopicId(topicId, limit, offset);
    return posts;
  }

  @Override
  public void uploadImg(
      MultipartFile upload, HttpServletResponse response, HttpServletRequest request) {
    String location =
        "/home/lee/IdeaProjects/MultiBoard_Spring/src/main/webapp/WEB-INF/static/image/post/";
    OutputStream out = null;
    PrintWriter printWriter = null;
    try {
      String fileName = upload.getOriginalFilename();
      byte[] bytes = upload.getBytes();
      String uploadPath = location + fileName;

      File file = new File(uploadPath);
      if (!file.getParentFile().exists()) {
        file.getParentFile().mkdirs();
      }
      out = new FileOutputStream(file);
      out.write(bytes);

      PrintWriter writer = response.getWriter();

      String callback = request.getParameter("CKEditorFuncNum");
      String url = "/image/post/" + fileName;

      String script =
          "<script type='text/javascript'>window.parent.CKEDITOR.tools.callFunction('"
              + callback
              + "', '"
              + url
              + "', '전송완료 메시지')</script>";
      writer.println(script);
      writer.flush();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (out != null) {
          out.close();
        }
        if (printWriter != null) {
          printWriter.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public List<Topic> getListByDiscussionId(long id, String sortby, String page) {
    String[] sortArr = {"last_post", "start_date", "views", "posts"};

    int pageI;
    try {
      pageI = Integer.parseInt(page) - 1;
    } catch (NumberFormatException e) {
      pageI = 0;
    }

    int start = pageI * 25;
    int end = start + 25;

    List<Topic> topics = topicRepositoryI.getListByDiscussionId(id, sortby, start, end);
    if (topics != null) {
      topics.forEach(
          topic -> {
            if (topic != null) {
              topic.setWriterInfo(memberRepositoryI.getMemberById(topic.getWriter()));
              topic.setLastPostMemberInfo(
                  memberRepositoryI.getMemberById(topic.getLastPostMemberId()));
            }
          });
    }
    return topics;
  }
}
