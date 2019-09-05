package com.lee.board.service;

import com.lee.board.model.Topic;
import java.util.List;

public interface TopicServiceI {

  List<Topic> getListByDiscussionId(long id,int start);
}
