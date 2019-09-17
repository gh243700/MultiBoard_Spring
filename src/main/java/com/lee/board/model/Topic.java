package com.lee.board.model;

import com.lee.member.model.ProfileImg;
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
@Builder
@ToString
public class Topic {
  private long id;
  private String title;
  private long writer;
  private String writerName;
  private Timestamp writeDate;
  private int replyNumber;
  private int viewCount;
  private long discussionId;

  private long lastPostId;
  private long lastPostMemberId;
  private String lastPostMemberName;

  ProfileImg lastPostMemberProfileImg;

}
