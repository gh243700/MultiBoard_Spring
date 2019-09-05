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
public class ProfileImg {
  private long id;
  private String fileName;
  private String fileSize;
  private String fileContentType;
  private byte[] fileData;
  private long userId;
  private Timestamp uploadDate;
  private long memberId;
}
