package com.lee.board.controller;

import com.lee.board.model.Category;
import com.lee.board.model.Discussion;
import com.lee.board.service.CategoryServiceI;
import com.lee.board.service.DiscussionServiceI;
import com.lee.board.service.TopicServiceI;
import com.lee.board.util.Util;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ForumController {

  @Autowired private CategoryServiceI categoryService;

  @Autowired private DiscussionServiceI discussionService;

  @RequestMapping(
      value = {"/home"},
      method = RequestMethod.GET)
  public String readAllForums(Model model) {
    List<Category> categoryList = categoryService.getCategoryList();
    List<Discussion> discussionList =
        discussionService.getListByCategoryId(Util.getAllObjectId(categoryList));
    model.addAttribute("categoryList", categoryList);
    model.addAttribute("discussionList", discussionList);
    return "index";
  }

  @RequestMapping(value = "/forum/{discussionId}/page/{page}", method = RequestMethod.GET)
  public String getDiscussionListById(
      Model model, @PathVariable long discussionId, @PathVariable(required = false) int page) {
    //model.addAttribute("topicList", topicService.getListByDiscussionId(discussionId, page));
    return null;
  }
}
