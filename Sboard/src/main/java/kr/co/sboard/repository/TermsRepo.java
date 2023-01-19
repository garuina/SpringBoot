package kr.co.sboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.sboard.vo.TermsVO;

public interface TermsRepo extends JpaRepository<TermsVO, String>{

}
