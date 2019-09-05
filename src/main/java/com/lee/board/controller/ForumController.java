package com.lee.board.controller;

import com.lee.board.model.Category;
import com.lee.board.service.CategoryServiceI;
import com.lee.board.service.DiscussionServiceI;
import com.lee.board.service.TopicServiceI;
import com.lee.board.util.Util;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ForumController {

  @Autowired
  CategoryServiceI categoryServiceI;

  @Autowired
  DiscussionServiceI discussionServiceI;

  @Autowired
  TopicServiceI topicServiceI;

  @RequestMapping(value = {"/","home"},method = RequestMethod.GET)
  public String readAllForums(Model model){
    List<Category> categoryList = categoryServiceI.getCategoryList();
    model.addAttribute("categoryList",categoryList);
    model.addAttribute("discussionList",discussionServiceI.getListByCategoryId(Util.getAllCategoryId(categoryList)));

    return null;
  }


  @RequestMapping(value = "/forum/{discussionId}/page/{page}",method = RequestMethod.GET)
  public String getDiscussionListById(Model model, @PathVariable long discussionId,@PathVariable(required = false) int page){
    model.addAttribute("topicList",topicServiceI.getListByDiscussionId(discussionId,page));
    return null;
  }

}
