package com.lee.board.repository.impl;

import com.lee.board.model.Discussion;
import com.lee.board.repository.DiscussionRepositoryI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/** @author Lee97 */
@Repository
public class DiscussionRepositoryImpl implements DiscussionRepositoryI {

  @Autowired private JdbcTemplate jdbcTemplate;

  @Override
  public List<Discussion> getDiscussionListByCategoryId(long... id) {
    String sql =
        "SELECT id,title,description,category_id AS categoryId,total_post_count AS totalPostCount "
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
  public Discussion getDiscussionById(int id) {
    String sql =
        "SELECT id,title,description,category_id AS categoryId,post_count AS postCount FROM discussion WHERE id = ?";
    return jdbcTemplate.queryForObject(sql, Discussion.class, id);
  }

  @Override
  public int updateDiscussionInfo(Discussion discussion) {
    String sql =
        "UPDATE discussion SET title=?,description=?,category_id=?,post_count=? WHERE id = ?";
    return jdbcTemplate.update(sql, discussion);
  }
}
