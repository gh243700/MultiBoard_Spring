package com.lee.board.repository;

import com.lee.board.model.RecentPostD;
import java.util.List;

public interface RecentPostDRepositoryI {

  List<RecentPostD> getListByDiscussionId(long... id);
}
