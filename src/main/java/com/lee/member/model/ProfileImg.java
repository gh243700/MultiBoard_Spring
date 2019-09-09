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
public class ProfileImg {
  private String fileName;
  private String fileSize;
  private String type;
  private byte[] fileData;
  private Timestamp uploadDate;
}
