package com.lee.board.model;

import com.lee.member.model.Member;
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
  private Timestamp writeDate;
  private int replyNumber;
  private int viewCount;
  private long discussionId;
  private int postCount;

  private long lastPostId;
  private long lastPostMemberId;
  private Timestamp lastPostDate;


  private Member writerInfo;
  private Member lastPostMemberInfo;
}
