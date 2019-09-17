package com.lee.board.controller;

import com.lee.board.model.Category;
import com.lee.board.model.Discussion;
import com.lee.board.service.CategoryServiceI;
import com.lee.board.service.DiscussionServiceI;
import com.lee.board.service.TopicServiceI;
import com.lee.util.CommonUtil;
import com.lee.util.Util;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ForumController {

  @Autowired private CategoryServiceI categoryService;

  @Autowired private DiscussionServiceI discussionService;

  @Autowired private TopicServiceI topicServiceI;

  @RequestMapping(
      value = {"/home"},
      method = RequestMethod.GET)
  public String readAllForums(Model model) {
    List<Category> categoryList = categoryService.getCategoryList();
    List<Discussion> discussionList =
        discussionService.getListByCategoryId(CommonUtil.getAllObjectId(categoryList));
    model.addAttribute("categoryList", categoryList);
    model.addAttribute("discussionList", discussionList);
    return "index";
  }

  @RequestMapping(
      value = {"/forum/{discussionId}/page/{page}", "/forum/{discussionId}"},
      method = RequestMethod.GET)
  public String getDiscussionListById(
      Model model,
      @PathVariable long discussionId,
      @PathVariable(required = false) int page,
      @RequestParam(value = "sortby", required = false, defaultValue = "title") String sortby,
      @RequestParam(value = "sortdirection", required = false, defaultValue = "DESC")
          String sortdirection) {
    model.addAttribute(
        "topicList", topicServiceI.getListByDiscussionId(discussionId, sortby, page));
    return null;
  }
}
