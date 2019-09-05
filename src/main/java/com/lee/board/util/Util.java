package com.lee.board.util;

import com.lee.board.model.Category;
import java.util.Iterator;
import java.util.List;

/**
 * @author Lee97
 */

public class Util {

  public static long[] getAllCategoryId(List<Category> categoryList) {
    long[] idArr = new long[categoryList.size()];
    Iterator<Category> iterator = categoryList.iterator();
    int index = 0;
    while (iterator.hasNext()) {
      idArr[index] = iterator.next().getId();
      index++;
    }
    return idArr;
  }
}
