package com.lee.board.model;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Discussion extends BaseModel {
  private long id;
  private String title;
  private String description;
  private long categoryId;
  private int postCount;

  private long recentPostId;
  private long recentPostMemberId;
  private String recentPostMemberName;
  private long recentPostTopicsId;
  private String recentPostTopicsTitle;
  private Timestamp recentPostTime;
}
