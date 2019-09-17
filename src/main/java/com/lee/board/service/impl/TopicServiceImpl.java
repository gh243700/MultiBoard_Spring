package com.lee.board.service.impl;

import com.lee.board.model.Topic;
import com.lee.board.repository.TopicRepositoryI;
import com.lee.board.service.TopicServiceI;
import com.lee.member.model.ProfileImg;
import com.lee.member.repository.MemberRepositoryI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicServiceImpl implements TopicServiceI {

  @Autowired private TopicRepositoryI topicRepositoryI;

  @Autowired private MemberRepositoryI memberRepositoryI;

  @Override
  public List<Topic> getListByDiscussionId(long id, String sortby, int page) {
    String[] sortArr = {"last_post", "start_date", "views", "posts"};
    if (sortby != null) {
      for (String s : sortArr) {
        if (sortby.equals(s)) {
          sortby = s;
        }
      }
    }

    int start = 0;
    int end = start + 25;

    List<Topic> topics = topicRepositoryI.getListByDiscussionId(id, sortby, start, end);
    if (topics != null) {
      topics.forEach(
          topic -> {
            if (topic != null) {
              ProfileImg profileImg =
                  memberRepositoryI.getProfileImgById(topic.getLastPostMemberId());
              if (profileImg != null) {
                topic.setLastPostMemberProfileImg(profileImg);
              }
            }
          });
    }
    return null;
  }
}
