package com.lee.member.model;

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
public class Member {
  private long id;
  private String username;
  private String password;
  private String email;
  private Timestamp joined;
  private int postCount;
  private Timestamp lastVisit;

  private ProfileImg profileImg;
}
