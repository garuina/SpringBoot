package kr.co.ch07.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User5VO {
	
	private String name;
	private String birth;
	private String gender;
	private String age;
	private String addr;
	private String hp;
}
