package com.lee.member.repository.impl;

import com.lee.member.model.Member;
import com.lee.member.model.ProfileImg;
import com.lee.member.repository.MemberRepositoryI;
import java.sql.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepositoryImpl implements MemberRepositoryI {

  @Autowired private JdbcTemplate jdbcTemplate;

  @Override
  public int insertMember(Member member) {
    String sql = "INSERT INTO member (id,username,password,email) VALUES(?,?,?,?)";
    return jdbcTemplate.update(
        sql, member.getId(), member.getUsername(), member.getPassword(), member.getEmail());
  }

  @Override
  public Integer checkUserExists(String value, String password) {
    String sql = "SELECT id FROM member WHERE (username =? OR email =?) AND password = ?";
    Integer result = jdbcTemplate.queryForObject(sql, Integer.class, value, value, password);
    if (result == null) {
      return -1;
    }
    return result;
  }

  @Override
  public boolean checkEmailExists(String email) {
    String sql = "SELECT id FROM member WHERE email = ?";
    return jdbcTemplate.queryForObject(sql, Boolean.class, email) == null;
  }

  @Override
  public boolean checkUsernameExists(String username) {
    String sql = "SELECT id FROM member WHERE username = ?";
    return jdbcTemplate.queryForObject(sql, Boolean.class, username) == null;
  }

  @Override
  public Member getMemberById(long id) {
    String sql =
        "SELECT id,username,password,email,joined,post_count,last_visit FROM member WHERE id=?";
    return jdbcTemplate.queryForObject(sql, Member.class, id);
  }

  @Override
  public int deleteMemberById(long id) {
    String sql = "DELETE FROM member WHERE id = ?";
    return jdbcTemplate.update(sql, id);
  }

  @Override
  public int updateMemberInfo(Member member) {
    String sql = "UPDATE member set username=?, password=?,email=?,post_count=?,last_visit=?,"
        + "profile_img_name = ?,"
        + "profile_img_size = ?,"
        + "profile_img_type = ?,"
        + "profile_img_data = ?,"
        + "profile_img_upload_date =? WHERE id=?";
    ProfileImg profileImg = member.getProfileImg();
    return jdbcTemplate.update(
        sql,
        member.getUsername(),
        member.getPassword(),
        member.getEmail(),
        member.getPostCount(),
        member.getLastVisit(),
        profileImg.getFileName(),
        profileImg.getFileSize(),
        profileImg.getType(),
        profileImg.getFileData(),
        System.currentTimeMillis(),
        member.getId());
  }


  @Override
  public Integer getMaxMemberId() {
    String sql = "SELECT NVL(MAX(id),0) FROM member";
    return jdbcTemplate.queryForObject(sql, Integer.class);
  }
}
