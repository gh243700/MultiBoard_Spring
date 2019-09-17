package com.lee.board.repository.impl;

import com.lee.board.model.Post;
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
  public int insertPost(Post post) {
    String sql =
        "INSERT INTO posts (id,content,writer,reply_step,topic_id,discussion_id) "
            + "VALUES (?,?,?,?,?,?)";
    return jdbcTemplate.update(
        sql,
        post.getId(),
        post.getContent(),
        post.getWriter(),
        post.getReplyStep(),
        post.getTopicId(),
        post.getDiscussionId());
  }

  @Override
  public int getMaxReplyStep(long topicId) {
    String sql = "SELECT MAX(reply_step) FROM posts WHERE topic_id = ?";
    Integer result = jdbcTemplate.queryForObject(sql, Integer.class, topicId);
    return result == null ? -1 : result;
  }

  @Override
  public Topic getTopicById(long topicId) {
    String sql =
        "SELECT "
            + "id,title,"
            + "writer_id AS writer,"
            + "writer_name AS writerName,"
            + "writer_date AS writerDate,"
            + "reply_number AS replyNumber,"
            + "view_count AS viewCount,"
            + "discussion_id AS discussionId,"
            + "recent_post_id AS lastPostId,"
            + "recent_post_member_id AS lastPostMemberId,"
            + "recent_post_member_name AS lastPostMemberName,"
            + "post_count AS postCount"
            + " FROM topics WHERE id = ?";
    return jdbcTemplate.queryForObject(sql, Topic.class, topicId);
  }

  @Override
  public int updateTopic(Topic topic) {
    String sql =
        "UPDATE topics SET title=?,reply_number=?,view_count=?,"
            + "discussion_id,recent_post_id=?,post_count=? WHERE id = ?";
    return jdbcTemplate.update(
        sql,
        topic.getTitle(),
        topic.getReplyNumber(),
        topic.getViewCount(),
        topic.getDiscussionId(),
        topic.getLastPostId(),
        topic.getReplyNumber(),
        topic.getId());
  }

  @Override
  public List<Post> getPostListByTopicId(long tId, int start, int end) {
    String sql =
        "SELECT id,"
            + "content,"
            + "write_date,"
            + "writer,"
            + "edited,"
            + "react_like,"
            + "react_agree,"
            + "react_informative,"
            + "react_total_count,"
            + "reply_step,"
            + "topic_id,"
            + "discussion_id "
            + "FROM posts WHERE topic_id = ? ORDER BY reply_step ASC LIMIT ? OFFSET ?";
    return jdbcTemplate.query(
        sql,
        (resultSet, i) ->
            Post.builder()
                .id(resultSet.getLong(1))
                .writeDate(resultSet.getTimestamp(2))
                .writer(resultSet.getLong(3))
                .edited(resultSet.getTimestamp(4))
                .reactLike(resultSet.getInt(5))
                .reactAgree(resultSet.getInt(6))
                .reactInformative(resultSet.getInt(7))
                .reactTotalCount(resultSet.getInt(8))
                .replyStep(resultSet.getInt(9))
                .topicId(resultSet.getLong(10))
                .discussionId(resultSet.getLong(11))
                .build(),
        start,
        end);
  }

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
                .build(),
        id,
        end,
        start);
  }
}
