package com.lee.board.repository.impl;

import com.lee.board.model.Topic;
import com.lee.board.repository.TopicRepositoryI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/** @author lee */

@Repository
public class TopicRepositoryImpl implements TopicRepositoryI {

  @Autowired private JdbcTemplate jdbcTemplate;

  @Override
  public List<Topic> getListByDiscussionId(long id, String sortby, int start, int end) {
    String sql =
        "SELECT "
            + "id,"
            + "title,"
            + "writer_id,"
            + "writer_name,"
            + "write_date,"
            + "reply_number,"
            + "view_count,"
            + "discussion_id ,"
            + "recent_post_id ,"
            + "recent_post_member_id,"
            + "recent_post_member_name,"
            + "post_count "
            + "FROM topics "
            + "WHERE discussion_id = ? "
            + "ORDER BY "
            + sortby
            + " DESC "
            + "LIMIT ? OFFSET ?";
    return jdbcTemplate.query(
        sql,
        (resultSet, i) ->
            Topic.builder()
                .id(resultSet.getLong(1))
                .title(resultSet.getString(2))
                .writer(resultSet.getLong(3))
                .writerName(resultSet.getString(4))
                .writeDate(resultSet.getTimestamp(5))
                .replyNumber(resultSet.getInt(6))
                .viewCount(resultSet.getInt(7))
                .discussionId(resultSet.getLong(8))
                .lastPostId(resultSet.getLong(9))
                .lastPostMemberId(resultSet.getLong(10))
                .lastPostMemberName(resultSet.getString(11))
                .postCount(resultSet.getInt(12))
                .build(),
        id,
        end,
        start);
  }
}
