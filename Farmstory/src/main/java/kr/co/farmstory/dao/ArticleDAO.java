package kr.co.farmstory.dao;

import kr.co.farmstory.vo.ArticleVO;
import kr.co.farmstory.vo.FileVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ArticleDAO {

    public int insertArticle(ArticleVO vo); // int는 실행 결과의 raw 갯수
    public int insertFile(FileVO vo);
    public int insertComment(ArticleVO comment);
    
    public ArticleVO selectArticle(int no);
    public List<ArticleVO> selectArticles(@Param("start") int start, @Param("cate") String cate);
    public FileVO selectFile(int fno);
    
    public List<ArticleVO> selectGrowLatests();
    public List<ArticleVO> selectSchoolLatests();
    public List<ArticleVO> selectStoryLatests();
    
    public List<ArticleVO> selectNoticeLatests();
    public List<ArticleVO> selectQnaLatests();
    public List<ArticleVO> selectFaqLatests();
    
    public List<ArticleVO> selectComments(int no);
    public ArticleVO selectCommentLatest();
    
    public int updateFileDownload(int fno);
    public void updateArticle(ArticleVO vo);
    public void updateArticleHit(int no);
    public void updateCommentCountPLS(int parent);
    public void updateCommentCountMNS(int parent);
    public int updateComment(@Param("content") String content, @Param("no") int no);
    
    public void deleteArticle(int no);
    public int deleteComment(@Param("no") int no, @Param("parent")int parent);
    
    public int selectCountTotal(String cate);
}