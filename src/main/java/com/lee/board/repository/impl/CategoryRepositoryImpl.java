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

  private class Mapper implements RowMapper<Category> {
    @Override
    public Category mapRow(ResultSet resultSet, int i) throws SQLException {
      return Category.builder()
          .id(resultSet.getLong("id"))
          .title(resultSet.getString("title"))
          .build();
    }
  }

  @Override
  public List<Category> getCategoryList() {
    String sql = "SELECT * FROM category ORDER BY id";
    return jdbcTemplate.query(sql, new Mapper());
  }

  @Override
  public Category getCategoryById(long id) {
    String sql = "SELECT * FROM category WHERE id = ?";
    return jdbcTemplate.queryForObject(sql, new Mapper(), id);
  }
}
