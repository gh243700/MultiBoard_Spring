package com.lee.board.repository.impl;

import com.lee.board.model.Discussion;
import com.lee.board.repository.DiscussionRepositoryI;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class DiscussionRepositoryImpl implements DiscussionRepositoryI {

  @Autowired private JdbcTemplate jdbcTemplate;

  private class Mapper implements RowMapper<Discussion> {
    @Override
    public Discussion mapRow(ResultSet resultSet, int i) throws SQLException {
      return Discussion.builder()
          .id(resultSet.getLong("id"))
          .title(resultSet.getString("title"))
          .description(resultSet.getString("description"))
          .categoryId(resultSet.getLong("category_Id"))
          .postCount(resultSet.getInt("total_post_count"))
          .recentPostId(resultSet.getLong("recent_post_id"))
          .recentPostMemberId(resultSet.getLong("recent_post_member_id"))
          .recentPostTopicsTitle(resultSet.getString("recent_post_topics_title"))
          .recentPostTime(resultSet.getTimestamp("recent_post_time"))
          .build();
    }
  }

  @Override
  public List<Discussion> getDiscussionListByCategoryId(long... id) {
    String sql = "SELECT *" + "FROM discussion WHERE " + "category_id IN(";
    String temp = "";
    for (int i = 0; i < id.length; i++) {
      temp += "," + id[i] + "";
    }
    temp = temp.replaceFirst(",", "");
    temp += ")";
    sql += temp;

    return jdbcTemplate.query(sql, new Mapper());
  }

  @Override
  public Discussion getDiscussionInfoById(long id) {
    String sql = "SELECT *" + "FROM discussion " + "WHERE id = ?";
    return jdbcTemplate.queryForObject(sql, new Mapper(), id);
  }

  @Override
  public int updateDiscussionInfo(Discussion discussion) {
    String sql =
        "UPDATE discussion "
            + "SET title=?,"
            + "description = ?,"
            + "category_id=?,"
            + "total_post_count=?,"
            + "recent_post_id = ? ,"
            + "recent_post_member_id = ?,"
            + "recent_post_topics_title = ?,"
            + "recent_post_time = ? "
            + "WHERE id=?";
    return jdbcTemplate.update(
        sql,
        discussion.getTitle(),
        discussion.getDescription(),
        discussion.getCategoryId(),
        discussion.getPostCount(),
        discussion.getRecentPostId(),
        discussion.getRecentPostMemberId(),
        discussion.getRecentPostTopicsTitle(),
        discussion.getRecentPostTime(),
        discussion.getId());
  }
}
