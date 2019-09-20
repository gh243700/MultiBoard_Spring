package com.lee.board.service.impl;

import com.lee.board.model.Category;
import com.lee.board.repository.CategoryRepositoryI;
import com.lee.board.service.CategoryServiceI;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Lee97
 */
@Service
public class CategoryServiceImpl implements CategoryServiceI {

  @Autowired private CategoryRepositoryI categoryRepository;

  @Override
  public List<Category> getCategoryList() {
    return categoryRepository.getCategoryList();
  }

  @Override
  public Category getCategoryById(long id) {
    return categoryRepository.getCategoryById(id);
  }
}
