package com.lee.board.service.impl;

import com.lee.board.model.Discussion;
import com.lee.board.model.Post;
import com.lee.board.model.Topic;
import com.lee.board.repository.DiscussionRepositoryI;
import com.lee.board.repository.TopicRepositoryI;
import com.lee.board.service.TopicServiceI;
import com.lee.member.model.Member;
import com.lee.member.model.ProfileImg;
import com.lee.member.repository.MemberRepositoryI;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TopicServiceImpl implements TopicServiceI {

  @Autowired private TopicRepositoryI topicRepositoryI;

  @Autowired private MemberRepositoryI memberRepositoryI;

  @Autowired private DiscussionRepositoryI discussionRepositoryI;

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
      discussion.setRecentPostTopicsTitle(post.getTopicInfo().getTitle());

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
  public List<Post> getPostListByTopicId(long topicId, int page) {
    int start = page * 25;
    int end = start + 25;
    List<Post> posts = topicRepositoryI.getPostListByTopicId(topicId, start, end);
    posts.forEach(
        post -> {
          if (post.getReplyStep() == 0) {
            Topic topicInfo = topicRepositoryI.getTopicById(post.getTopicId());
            post.setTopicInfo(topicInfo);
          }
          post.setMember(memberRepositoryI.getMemberById(post.getWriter()));
          post.setProfileImg(memberRepositoryI.getProfileImgById(post.getWriter()));
        });
    return posts;
  }

  @Override
  public List<Topic> getListByDiscussionId(long id, String sortby, String val) {
    String[] sortArr = {"last_post", "start_date", "views", "posts"};
    if (sortby != null) {
      for (String s : sortArr) {
        if (sortby.equals(s)) {
          sortby = s;
        }
      }
    }
    int page = val == null ? 1 : Integer.parseInt(val);
    int start = page * 25;
    int end = start + 25;

    List<Topic> topics = topicRepositoryI.getListByDiscussionId(id, sortby, start, end);
    if (topics != null) {
      topics.forEach(
          topic -> {
            if (topic != null) {
              topic.setWriterInfo(memberRepositoryI.getMemberById(topic.getWriter()));
              Member recentPostMember =
                  memberRepositoryI.getMemberById(topic.getLastPostMemberId());
              ProfileImg profileImg =
                  memberRepositoryI.getProfileImgById(topic.getLastPostMemberId());
              if (profileImg != null && recentPostMember != null) {
                recentPostMember.setProfileImg(profileImg);
                topic.setLastPostMemberInfo(recentPostMember);
              }
            }
          });
    }
    return topics;
  }
}
