package com.lee.util;

import com.lee.board.model.BaseModel;
import com.lee.board.model.Topic;
import java.util.Iterator;
import java.util.List;

public class CommonUtil {

  public static long[] getAllObjectId(List<? extends BaseModel> list) {
    long[] idArr = new long[list.size()];

    Iterator<? extends BaseModel> iterator = list.iterator();

    int index = 0;
    while (iterator.hasNext()) {

      idArr[index] = iterator.next().getId();

      index++;
    }

    return idArr;
  }

  public static long[] getAllMemberId(List<Topic> list, int ref) {

    long[] idArr = new long[list.size()];

    Iterator<Topic> iterator = list.iterator();
    int index = 0;
    while (iterator.hasNext()) {
      if (ref == 1) {
        idArr[index] = iterator.next().getWriter();
      }
      if (ref == 2) {
        idArr[index] = iterator.next().getLastPostMemberId();
      }
      index++;
    }
    return idArr;
  }
}
