package com.lee.board.repository.impl;

import com.lee.board.model.Discussion;
import com.lee.board.repository.DiscussionRepositoryI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DiscussionRepositoryImpl implements DiscussionRepositoryI {

  @Autowired private JdbcTemplate jdbcTemplate;

  @Override
  public List<Discussion> getDiscussionListByCategoryId(long... id) {
    String sql =
        "SELECT id,title,description,category_id AS categoryId,total_post_count AS totalPostCount,"
            + "recent_post_id AS recentPostId ,"
            + " recent_post_member_id AS recentPostMemberId,"
            + "recent_post_topics_id AS recentPostTopicsId,"
            + "recent_post_topics_title AS recentPostTopicsTitle,"
            + "recent_post_time AS recentPostTime "
            + "FROM discussion WHERE category_id (IN ";
    String temp = "";
    for (int i = 0; i < id.length; i++) {
      temp += ",?";
    }
    temp = temp.replaceFirst(",", "");
    temp += ")";
    sql += temp;
    return jdbcTemplate.queryForList(sql, Discussion.class, (Object) id);
  }

  @Override
  public Discussion getDiscussionInfoById(long id) {
    String sql =
        "id,title,description,category_id AS categoryId,total_post_count AS totalPostCount,"
            + "recent_post_id AS recentPostId ,"
            + " recent_post_member_id AS recentPostMemberId,"
            + "recent_post_topics_id AS recentPostTopicsId,"
            + "recent_post_topics_title AS recentPostTopicsTitle,"
            + "recent_post_time AS recentPostTime "
            + "FROM discussion "
            + "WHERE id = ?";
    return jdbcTemplate.queryForObject(sql, Discussion.class, id);
  }

  @Override
  public int updateDiscussionInfo(Discussion discussion) {
    String sql =
        "UPDATE discussion SET title=?,description = ?,category_id=?,total_post_count=?,"
            + "recent_post_id = ? , recent_post_member_id = ?,recent_post_topics_id = ?,recent_post_topics_title = ?,recent_post_time = ? WHERE id = ?";
    return jdbcTemplate.update(
        sql,
        discussion.getTitle(),
        discussion.getDescription(),
        discussion.getCategoryId(),
        discussion.getPostCount(),
        discussion.getRecentPostId(),
        discussion.getRecentPostMemberId(),
        discussion.getRecentPostTopicsId(),
        discussion.getRecentPostTopicsTitle(),
        discussion.getRecentPostTime(),
        discussion.getId());
  }
}
