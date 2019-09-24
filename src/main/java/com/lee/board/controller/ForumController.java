package com.lee.board.controller;

import com.lee.board.model.Category;
import com.lee.board.model.Discussion;
import com.lee.board.model.Post;
import com.lee.board.service.CategoryServiceI;
import com.lee.board.service.DiscussionServiceI;
import com.lee.board.service.TopicServiceI;
import com.lee.util.CommonUtil;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ForumController {

  @Autowired private CategoryServiceI categoryService;

  @Autowired private DiscussionServiceI discussionService;

  @Autowired private TopicServiceI topicServiceI;


  @RequestMapping(
      value = {"/home", ""},
      method = RequestMethod.GET)
  public String readAllForums(Model model) {
    List<Category> categoryList = categoryService.getCategoryList();
    List<Discussion> discussionList =
        discussionService.getListByCategoryId(CommonUtil.getAllObjectId(categoryList));
    model.addAttribute("categoryList", categoryList);
    model.addAttribute("discussionList", discussionList);
    return "forum/home";
  }

  @RequestMapping(
      value = {"main/forum/{discussionId}/page/{page}", "main/forum/{discussionId}"},
      method = RequestMethod.GET)
  public String getDiscussionListById(
      Model model,
      @PathVariable long discussionId,
      @PathVariable(required = false) String page,
      @RequestParam(value = "sortby", required = false, defaultValue = "title") String sortby,
      @RequestParam(value = "sortdirection", required = false, defaultValue = "DESC")
          String sortdirection) {
    Discussion discussion = discussionService.getDiscussionById(discussionId);
    model.addAttribute("discussionInfo", discussion);
    model.addAttribute("categoryInfo", categoryService.getCategoryById(discussion.getCategoryId()));
    model.addAttribute(
        "topicList", topicServiceI.getListByDiscussionId(discussionId, sortby, page));
    return "forum/forum";
  }

  @RequestMapping(
      value = {"/topic/{topicId}", "/topic/{topicId}/page/{page}"},
      method = RequestMethod.GET)
  public String getPostListById(
      Model model, @PathVariable long topicId, @PathVariable(required = false) String page) {

    model.addAttribute(
        "topics",
        topicServiceI.getPostListByTopicId(
            topicId, Integer.parseInt(StringUtils.isEmpty(page) ? "1" : page) - 1));
    return null;
  }

  @RequestMapping(
      value = {"/topic/{topicId}/*/"},
      method = RequestMethod.POST)
  public String submitPostAtTopicId(
      @PathVariable long topicId, Post post, RedirectAttributes redirectAttributes) {
    int att = 0;
    if (topicServiceI.insertPost(post, topicId) != 0) {
      att = 1;
    }
    redirectAttributes.addAttribute("value", att);
    return "redirect:/topic/" + topicId;
  }
}
