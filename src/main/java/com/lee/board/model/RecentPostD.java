package com.lee.board.model;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Lee97
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class RecentPostD {
  private long id;
  private long memberId;
  private long postId;
  private String topicTitle;
  private long discussionId;
  private Timestamp time;

  private String memberUsername;
  private ProfileImg profileImg;

}
