package com.lee.member.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class Util {

  public static MultipartFile getDefaultImage() {
    File file  = new File("/home/lee/IdeaProjects/MultiBoard_Spring/src/main/resources/image/default-avatar.png");
    FileItem fileItem = null;
    try {
      fileItem = new DiskFileItem("defaultAvatar", Files.probeContentType(file.toPath()),false,file.getName(),(int)file.length(),file.getParentFile());
      IOUtils.copy(new FileInputStream(file),fileItem.getOutputStream());
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
    return new CommonsMultipartFile(fileItem);
  }
}
