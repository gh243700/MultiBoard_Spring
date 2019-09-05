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
public class Post {
  private long id;
  private String content;
  private long writer;
  private Timestamp writeDate;
  private Timestamp edited;
  private int reactLike;
  private int reactFunny;
  private int reactInformative;
  private int reactTotalCount;
  private int replyStep;
  private long topicId;
  private long discussionId;
}
