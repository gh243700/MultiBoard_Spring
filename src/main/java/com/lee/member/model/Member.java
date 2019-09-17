package com.lee.member.model;

import com.lee.board.model.BaseModel;
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
public class Member extends BaseModel {
  private long id;
  private String username;
  private String password;
  private String email;
  private String joined;
  private int postCount;
  private Timestamp lastVisit;

  private ProfileImg profileImg;
}
