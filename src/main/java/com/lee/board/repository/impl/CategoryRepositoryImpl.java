package com.lee.board.repository.impl;

import com.lee.board.model.Category;
import com.lee.board.repository.CategoryRepositoryI;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/** @author Lee97 */
@Repository
public class CategoryRepositoryImpl implements CategoryRepositoryI {

  @Autowired private JdbcTemplate jdbcTemplate;

  @Override
  public List<Category> getCategoryList() {
    String sql = "SELECT id,title FROM category ORDER BY id";
    return jdbcTemplate.query(sql, new RowMapper<Category>() {
      @Override
      public Category mapRow(ResultSet resultSet, int i) throws SQLException {
        return Category.builder().id(resultSet.getLong(1)).title(resultSet.getString(2)).build();
      }
    });
  }
}
