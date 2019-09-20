package com.lee.board.service;

import com.lee.board.model.Category;
import java.util.List;

public interface CategoryServiceI {

  List<Category> getCategoryList();

  Category getCategoryById(long id);
}
