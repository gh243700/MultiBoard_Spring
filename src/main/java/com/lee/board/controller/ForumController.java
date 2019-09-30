package com.lee.board.controller;

import com.lee.board.model.Category;
import com.lee.board.model.Discussion;
import com.lee.board.model.Post;
import com.lee.board.model.Topic;
import com.lee.board.service.CategoryServiceI;
import com.lee.board.service.DiscussionServiceI;
import com.lee.board.service.TopicServiceI;
import com.lee.member.model.Member;
import com.lee.util.CommonUtil;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ForumController {

  @Autowired private CategoryServiceI categoryService;

  @Autowired private DiscussionServiceI discussionService;

  @Autowired private TopicServiceI topicServiceI;

  @RequestMapping(value = "/main/forum/uploadImg", method = RequestMethod.POST)
  public void uploadImg(
      HttpServletRequest request,
      HttpServletResponse response,
      @RequestParam MultipartFile upload) {
    topicServiceI.uploadImg(upload, response, request);
  }

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
  public String topicsListByDiscussion(
      Model model,
      @PathVariable long discussionId,
      @PathVariable(required = false) String page,
      @RequestParam(value = "do", required = false) String newTopic,
      @RequestParam(value = "sortby", required = false, defaultValue = "title") String sortby,
      @RequestParam(value = "sortdirection", required = false, defaultValue = "DESC")
          String sortdirection,
      RedirectAttributes redirectAttributes) {
    Discussion discussion = discussionService.getDiscussionById(discussionId);
    model.addAttribute("discussionInfo", discussion);
    model.addAttribute("categoryInfo", categoryService.getCategoryById(discussion.getCategoryId()));
    if ((newTopic != null) && newTopic.equals("add")) {
      return "forum/submitNewTopic";
    }
    model.addAttribute(
        "topicList", topicServiceI.getListByDiscussionId(discussionId, sortby, page));
    return "forum/forum";
  }

  @RequestMapping(
      value = {"/main/topic/{topicId}", "/main/topic/{topicId}/page/{page}"},
      method = RequestMethod.GET)
  public String getPostListById(
      Model model, @PathVariable long topicId, @PathVariable(required = false) String page) {
    Topic topic = topicServiceI.getTopicById(topicId);
    Discussion discussion = discussionService.getDiscussionById(topic.getDiscussionId());
    Category category = categoryService.getCategoryById(discussion.getCategoryId());

    model.addAttribute("discussionInfo", discussion);
    model.addAttribute("categoryInfo", category);
    model.addAttribute("topicInfo", topic);
    model.addAttribute(
        "posts",
        topicServiceI.getPostListByTopicId(
            topicId, Integer.parseInt(StringUtils.isEmpty(page) ? "1" : page) - 1));
    return "forum/subForum";
  }

  @RequestMapping(
      value = {"/main/forum/{discussionId}"},
      method = RequestMethod.POST)
  public String newTopic(
      @PathVariable(value = "discussionId") long discussion,
      RedirectAttributes redirectAttributes,
      @RequestParam(value = "title") String title,
      @RequestParam(value = "tags", required = false) String tags,
      @RequestParam(value = "editor") String content,
      HttpServletRequest request) {

    HttpSession session = request.getSession();

    Member member = (Member) session.getAttribute("member");
    Topic topic =
        Topic.builder()
            .title(title)
            .writer(member.getId())
            .discussionId(discussion)
            .postCount(1)
            .lastPostMemberId(member.getId())
            .lastPostDate(new Timestamp(System.currentTimeMillis()))
            .build();
    Post post =
        Post.builder()
            .content(content)
            .writer(member.getId())
            .edited(null)
            .replyStep(1)
            .discussionId(discussion)
            .build();
    Map<Integer, Object> map = topicServiceI.insertTopic(topic, post);
    if (map.containsKey(0)) {
      String message = (String) map.get(0);
      redirectAttributes.addFlashAttribute("message", message);
      return "redirect:toerrorpage";
    }
    long topicId = (long) map.get(1);
    return "redirect:/main/topic/" + topicId;
  }
}
