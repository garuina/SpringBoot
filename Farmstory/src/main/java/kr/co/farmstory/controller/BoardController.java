package kr.co.farmstory.controller;

import kr.co.farmstory.service.ArticleService;
import kr.co.farmstory.vo.ArticleVO;

import org.h2.util.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BoardController {

    @Autowired
    private ArticleService service;

    @GetMapping("board/list")
    public String list(Model model, String group, String cate,String pg){

        int currentPage = service.getCurrnetPage(pg);
        int start = service.getLimitStart(currentPage);

        int total = service.selectCountTotal(cate);
        int lastPageNum = service.getLastPageNum(total);
        int pageStartNum = service.getPageStartNum(total, start);
        int groups[] = service.getPageGroup(currentPage, lastPageNum);

        List<ArticleVO> articles = service.selectArticles(start, cate);

        model.addAttribute("articles", articles);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("lastPageNum", lastPageNum);
        model.addAttribute("pageStartNum", pageStartNum);
        model.addAttribute("groups", groups);

        model.addAttribute("group", group);
        model.addAttribute("cate", cate);
        return "board/list";
    }

    @GetMapping("board/modify")
    public String modify(Model model, String group, String cate, int no){
        
    	ArticleVO article = service.selectArticle(no);
    	
    	model.addAttribute("group", group);
        model.addAttribute("cate", cate);
        model.addAttribute("article", article);
        
        return "board/modify";
    }
    
    @PostMapping("board/modify")
    public String modify(ArticleVO vo, HttpServletRequest req, Model model,String group, String cate) {
    	String regip = req.getRemoteAddr();
        vo.setRegip(regip);
        
    	service.updateArticle(vo);
    	
    	 model.addAttribute("group", group);
         model.addAttribute("cate", cate);
    	return "redirect:/board/list?group="+group+"&cate="+cate;
    }
    
    
    @GetMapping("board/view")
    public String view(Model model, String group, String cate, int no, int parent, String pg){

    	// 글 가져오기
        ArticleVO article = service.selectArticle(no);
        service.updateArticleHit(no);
        model.addAttribute("group", group);
        model.addAttribute("cate", cate);
        model.addAttribute("pg", pg);
        model.addAttribute("article", article);
        
        // 댓글 가져오기
        List<ArticleVO> comments = service.selectComments(no);
        model.addAttribute("comments", comments);
        
        return "board/view";
    }
    
    @ResponseBody
    @PostMapping("board/commentWrite")
    public Map<String, ArticleVO> CommentWrite(ArticleVO vo, HttpServletRequest req) {
    	
    	
    	String regip = req.getRemoteAddr();
    	vo.setRegip(regip);
    	
    	ArticleVO comment = service.insertComment(vo);
    	
    	
    	Map<String, ArticleVO> resultMap = new HashMap<>();
    	resultMap.put("comment", comment);
    	
    	
    	return resultMap;
    }
    
    @ResponseBody
    @PostMapping("board/commentModify")
    public Map<String, Integer> CommentModify(int no, String content) {
    	
    	int result = service.updateComment(content, no);
    	Map<String, Integer> resultMap = new HashMap<>();
    	resultMap.put("result", result);
    	
    	return resultMap;
    	
    }
    

    @GetMapping("board/write")
    public String write(Model model, String group, String cate){
        model.addAttribute("group", group);
        model.addAttribute("cate", cate);
        return "board/write";
    }

    @PostMapping("board/write")
    public String write(ArticleVO vo, HttpServletRequest req, Model model,String group, String cate){
        String regip = req.getRemoteAddr();
        vo.setRegip(regip);

        service.insertArticle(vo);
        
        model.addAttribute("group", group);
        model.addAttribute("cate", cate);
        return "redirect:/board/list?group="+group+"&cate="+cate;

    }
    
    @GetMapping("board/delete")
    public String delete(String group, String cate,int no) {
    	
    	service.deleteArticle(no);
    	
    	return "redirect:/board/list?group="+group+"&cate="+cate;
    }
    
    @ResponseBody
    @GetMapping("board/commentDelete")
    public Map<String, Integer> CommentDelte(int no, int parent) {
    	
    	int result = service.deleteComment(no, parent);
    	Map<String, Integer> resultMap = new HashMap<>();
    	resultMap.put("result", result);
    	
    	return resultMap;
    	
    	
    }

}