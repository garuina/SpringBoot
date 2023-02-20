package kr.co.farmstory.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.farmstory.service.ArticleService;
import kr.co.farmstory.vo.ArticleVO;

@Controller
public class MainController {
	
	@Autowired
	private ArticleService service;
	
    @GetMapping(value = {"/", "index"})
    public String index(Model model, String cate){
    	
    	// 최신 글 가져오기
    	List<ArticleVO> grows = service.selectGrowLatests();
    	
    	if(grows.size() <5){
			ArticleVO article = new ArticleVO();
			article.setNo(0);
			article.setTitle("최신글이 없습니다.");
			article.setRdate("0");
			
			for(int i=0; i<15; i++){
				grows.add(article);
			}
		}
		
    	model.addAttribute("grows",grows);

    	
    	List<ArticleVO> schools = service.selectSchoolLatests();
    	
    	if(schools.size() <5){
			ArticleVO article = new ArticleVO();
			article.setNo(0);
			article.setTitle("최신글이 없습니다.");
			article.setRdate("0");
			
			for(int i=0; i<15; i++){
				schools.add(article);
			}
		}
		
    	model.addAttribute("schools",schools);

    	
    	List<ArticleVO> stories = service.selectStoryLatests();
    	
    	if(stories.size() <5){
			ArticleVO article = new ArticleVO();
			article.setNo(0);
			article.setTitle("최신글이 없습니다.");
			article.setRdate("0");
			
			for(int i=0; i<15; i++){
				stories.add(article);
			}
		}
		
    	model.addAttribute("stories",stories);
    	
    	
    	// 공지사항 글 5개
    	List<ArticleVO> notices = service.selectNoticeLatests();
    	
    	if(notices.size() <5){
			ArticleVO article = new ArticleVO();
			article.setNo(0);
			article.setTitle("최신글이 없습니다.");
			article.setRdate("0");
			
			for(int i=0; i<15; i++){
				notices.add(article);
			}
		}
		
    	model.addAttribute("notices",notices);
    	
    	
    	// qna 질문 글 5개
    	List<ArticleVO> qnas = service.selectQnaLatests();
    	
    	if(qnas.size() <5){
			ArticleVO article = new ArticleVO();
			article.setNo(0);
			article.setTitle("최신글이 없습니다.");
			article.setRdate("0");
			
			for(int i=0; i<15; i++){
				qnas.add(article);
			}
		}
		
    	model.addAttribute("qnas",qnas);
    	
    	// faq 자주묻는 글 5개
    	List<ArticleVO> faqs = service.selectFaqLatests();
    	
    	if(faqs.size() <5){
			ArticleVO article = new ArticleVO();
			article.setNo(0);
			article.setTitle("최신글이 없습니다.");
			article.setRdate("0");
			
			for(int i=0; i<15; i++){
				faqs.add(article);
			}
		}
		
    	model.addAttribute("faqs",faqs);
    	
    	
    	
        return "index";
    }
    
    

}
