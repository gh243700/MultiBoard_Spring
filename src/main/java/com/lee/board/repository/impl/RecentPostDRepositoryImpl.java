package com.lee.board.repository.impl;

import com.lee.board.model.ProfileImg;
import com.lee.board.model.RecentPostD;
import com.lee.board.repository.RecentPostDRepositoryI;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/** @author Lee97 */
@Repository
public class RecentPostDRepositoryImpl implements RecentPostDRepositoryI {

  @Autowired private JdbcTemplate jdbcTemplate;

  @Override
  public List<RecentPostD> getListByDiscussionId(long... id) {
    String sql =
        "SELECT D.id,D.member_id AS memberId, D.post_id AS postId,D.topic_title AS topicTitle,D.discussion_id AS discussionId,time,"
            + "        M.username AS memberUsername,"
            + "    P.id,file_name AS fileName,P.file_size AS fileSize,P.file_content_type AS fileContentType,P.file_data AS fileData,P.upload_date AS uploadDate"
            + "    FROM recent_post_D D"
            + "    INNER JOIN"
            + "    (SELECT id,username FROM member) M"
            + "    ON D.member_id = M.id"
            + "    INNER JOIN "
            + "    (SELECT id,file_name,file_size,file_content_type,file_data,upload_date,member_id FROM profile_img) "
            + "    ON D.id = P.member_id"
            + "    WHERE D.discussion_id IN (";

    String temp = " ";

    for (int i = 0; i < id.length; i++) {
      temp += ",?";
    }

    temp = temp.replaceFirst(",", "");
    temp += ")";
    sql += temp;

    return jdbcTemplate.query(
        sql,
        new RowMapper<RecentPostD>() {
          @Override
          public RecentPostD mapRow(ResultSet rs, int rowNum) throws SQLException {
            return RecentPostD.builder()
                .id(rs.getLong(1))
                .memberId(rs.getInt(2))
                .postId(rs.getInt(3))
                .topicTitle(rs.getString(4))
                .discussionId(rs.getLong(5))
                .time(rs.getTimestamp(6))
                .memberUsername(rs.getString(7))
                .profileImg(
                    ProfileImg.builder()
                        .id(rs.getLong(8))
                        .fileName(rs.getString(9))
                        .fileSize(rs.getString(10))
                        .fileContentType(rs.getString(11))
                        .fileData(rs.getBytes(12))
                        .uploadDate(rs.getTimestamp(13))
                        .memberId(rs.getLong(14))
                        .build())
                .build();
          }
        },
        (Object) id);
  }
}
