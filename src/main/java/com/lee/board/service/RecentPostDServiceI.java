package com.lee.board.service;

import com.lee.board.model.RecentPostD;
import java.util.List;

public interface RecentPostDServiceI {

  List<RecentPostD> getListByDiscussionId(long... id);
}
