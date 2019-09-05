package com.lee.board.util;

import com.lee.board.model.BaseModel;
import java.util.Iterator;
import java.util.List;

public class Util {

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
}
