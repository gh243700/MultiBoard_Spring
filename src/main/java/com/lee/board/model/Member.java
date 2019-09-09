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
@Builder
@AllArgsConstructor
@ToString
public class Member extends BaseModel {
  private String username;
  private String password;
  private String email;
  private String joined;
  private int postCount;
  private Timestamp lastVisit;

  private ProfileImg profileImg;
}
