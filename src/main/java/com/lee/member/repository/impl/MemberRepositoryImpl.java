package com.lee.member.repository.impl;

import com.lee.member.model.Member;
import com.lee.member.model.ProfileImg;
import com.lee.member.repository.MemberRepositoryI;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Repository
public class MemberRepositoryImpl implements MemberRepositoryI {

  @Autowired private JdbcTemplate jdbcTemplate;

  private class MemberMapper implements RowMapper<Member> {
    @Override
    public Member mapRow(ResultSet resultSet, int i) throws SQLException {
      return Member.builder()
          .id(resultSet.getLong("id"))
          .username(resultSet.getString("username"))
          .password(resultSet.getString("password"))
          .email(resultSet.getString("email"))
          .joined(resultSet.getTimestamp("joined"))
          .postCount(resultSet.getInt("post_count"))
          .lastVisit(resultSet.getTimestamp("last_visit"))
          .profileImg(resultSet.getString("profile_img"))
          .build();
    }
  }

  @Override
  public int insertMember(Member member) {
    String sql = "INSERT INTO member (id,username,password,email) VALUES(?,?,?,?)";
    return jdbcTemplate.update(
        sql,
        member.getId(),
        member.getUsername(),
        member.getPassword(),
        member.getEmail());
  }

  @Override
  public Integer checkUserExists(String value, String password) {
    Integer result = null;
    String sql = "SELECT id FROM member WHERE (username =? OR email =?) AND password = ?";
    try {
      result = jdbcTemplate.queryForObject(sql, Integer.class, value, value, password);
    } catch (EmptyResultDataAccessException e) {
      return -1;
    }
    return result;
  }

  @Override
  public boolean checkEmailExists(String email) {
    String sql = "SELECT id FROM member WHERE email = ?";
    boolean result;
    try {
      if (jdbcTemplate.queryForObject(sql, Integer.class, email) != null) {
        result = false;
      } else {
        result = true;
      }
    } catch (Exception e) {
      result = false;
    }
    return result;
  }

  @Override
  public boolean checkUsernameExists(String username) {
    String sql = "SELECT username FROM member WHERE username = ?";
    boolean result;
    try {
      if (jdbcTemplate.queryForObject(sql, String.class, username) != null) {
        result = false;
      } else {
        result = true;
      }
    } catch (Exception e) {
      result = false;
    }
    return result;
  }

  @Override
  public Member getMemberById(long id) {
    String sql = "SELECT * FROM member WHERE id=?";
    return jdbcTemplate.queryForObject(sql, new MemberMapper(), id);
  }

  @Override
  public List<Member> getMemberListById(long... ids) {
    String sql =
        "SELECT id,username,password,email,"
            + "joined,post_count AS postCount,last_visit lastVisit FROM member WHERE id IN(";
    String tmp = "";
    for (int i = 0; i < ids.length; i++) {
      tmp += "," + ids[i] + "";
    }
    tmp = tmp.replaceFirst(",", "");
    sql += tmp + ")";
    return jdbcTemplate.queryForList(sql, Member.class);
  }

  @Override
  public int deleteMemberById(long id) {
    String sql = "DELETE FROM member WHERE id = ?";
    return jdbcTemplate.update(sql, id);
  }

  @Override
  public int updateMemberInfo(Member member) {
    String sql =
        "UPDATE member set username=?, password=?,email=?,post_count=?,last_visit=? "
            + "WHERE id=?";
    return jdbcTemplate.update(
        sql,
        member.getUsername(),
        member.getPassword(),
        member.getEmail(),
        member.getPostCount(),
        member.getLastVisit(),
        member.getId());
  }

  //
  //  @Override
  //  public Integer getMaxMemberId() {
  //    String sql = "SELECT NVL(MAX(id),0) FROM member";
  //    return jdbcTemplate.queryForObject(sql, Integer.class);
  //  }
}
