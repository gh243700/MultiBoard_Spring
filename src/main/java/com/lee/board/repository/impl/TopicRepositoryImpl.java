package com.lee.board.repository.impl;

import com.lee.board.model.Post;
import com.lee.board.model.Topic;
import com.lee.board.repository.TopicRepositoryI;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/** @author lee */
@Repository
public class TopicRepositoryImpl implements TopicRepositoryI {

  @Autowired private JdbcTemplate jdbcTemplate;

  private class PostMapper implements RowMapper<Post> {

    @Override
    public Post mapRow(ResultSet resultSet, int i) throws SQLException {
      return Post.builder()
          .id(resultSet.getLong("id"))
          .content(resultSet.getString("content"))
          .writeDate(resultSet.getTimestamp("write_date"))
          .writer(resultSet.getLong("writer"))
          .edited(resultSet.getTimestamp("edited"))
          .reactLike(resultSet.getInt("react_like"))
          .reactAgree(resultSet.getInt("react_agree"))
          .reactInformative(resultSet.getInt("react_informative"))
          .reactTotalCount(resultSet.getInt("react_total_count"))
          .replyStep(resultSet.getInt("reply_step"))
          .topicId(resultSet.getLong("topic_id"))
          .discussionId(resultSet.getLong("discussion_id"))
          .profileImg(resultSet.getString("profile_img"))
          .username(resultSet.getString("username"))
          .postCount(resultSet.getInt("post_count"))
          .build();
    }
  }

  private class TopicMapper implements RowMapper<Topic> {
    @Override
    public Topic mapRow(ResultSet resultSet, int i) throws SQLException {
      return Topic.builder()
          .id(resultSet.getLong("id"))
          .title(resultSet.getString("title"))
          .writer(resultSet.getLong("writer"))
          .writeDate(resultSet.getTimestamp("write_date"))
          .replyNumber(resultSet.getInt("reply_number"))
          .viewCount(resultSet.getInt("view_count"))
          .discussionId(resultSet.getLong("discussion_id"))
          .lastPostId(resultSet.getLong("recent_post_id"))
          .lastPostMemberId(resultSet.getLong("recent_post_member"))
          .lastPostDate(resultSet.getTimestamp("recent_post_date"))
          .postCount(resultSet.getInt("post_count"))
          .build();
    }
  }

  @Override
  public int insertTopic(Topic topic) {
    String sql =
        "INSERT INTO topics(title,writer,discussion_id,recent_post_id,post_count,recent_post_member,recent_post_date) "
            + "VALUES (?,?,?,?,?,?,?)";
    return jdbcTemplate.update(
        sql,
        topic.getTitle(),
        topic.getWriter(),
        topic.getDiscussionId(),
        topic.getLastPostId(),
        topic.getPostCount(),
        topic.getLastPostMemberId(),
        topic.getLastPostDate());
  }

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
  public long getMaxTopicId() {
    String sql = "SELECT MAX(id) FROM topics";
    return jdbcTemplate.queryForObject(sql, long.class);
  }

  @Override
  public Topic getTopicById(long topicId) {
    String sql = "SELECT * FROM topics WHERE id = ?";
    return jdbcTemplate.queryForObject(sql, new TopicMapper(), topicId);
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
  public List<Post> getPostListByTopicId(long tId, int limit, int offset) {
    String sql =
        "SELECT A.*, B.username, B.profile_img, B.post_count FROM posts A INNER JOIN member B ON A.writer = B.id WHERE A.topic_id = ? ORDER BY A.reply_step ASC LIMIT ? OFFSET ?";
    return jdbcTemplate.query(sql, new PostMapper(), tId, limit, offset);
  }

  @Override
  public List<Topic> getListByDiscussionId(long id, String sortby, int start, int end) {
    String sql =
        "SELECT *"
            + "FROM topics "
            + "WHERE discussion_id = ? "
            + "ORDER BY "
            + sortby
            + " DESC "
            + "LIMIT ? OFFSET ?";
    return jdbcTemplate.query(sql, new TopicMapper(), id, end, start);
  }
}
