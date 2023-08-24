package com.ticket;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.ToString;



public class 람다테스트 {
	// 자바클래스 형성
	// test 어노테이션 붙이기
	
	// pathValue 와 queryString
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//@Test
	// B로 시작하는 과일명 출력
	void 테스트1() {
		List<String> list = List.of("apple", "banana", "grape"); // 새 리스트 형성 (new arrayList와 같음)
		list
		.stream() // 스트림 타입
		.filter(element -> element.startsWith("b")) // 스트림 타입 중 b로 시작하는 element 반복문돌며 찾는다.
		.forEach(element -> logger.info(element)); // b로 시작하는 것 반복문
	}
	
	// 리스트의 모든 요소를 대문자로 변경
	//@Test
	void 테스트2() {
		List<String> list = List.of("apple", "banana", "grape");
		list = list // 새로운 것 저장
		.stream()
		.map(fruit -> fruit.toUpperCase()) // 대문자(toUpperCase) 변경(map)
		.collect(Collectors.toList()); // stream to list
		
		logger.info(list.toString());
	}
	
	// 메소드 레퍼런스 - 리스트의 모든 요소를 대문자로 변경
	//@Test
	void 테스트3() {
		List<String> list = List.of("apple", "banana", "grape");
		list = list
		.stream()
		.map(String::toUpperCase) // fruit -> fruit.toUpperCase()와 동일 string클래스의 메소드
		// 파라미터가 없는 함수만 이와 같이 적용 가능
		.collect(Collectors.toList());
		
		logger.info(list.toString());
	}
	
	@Test
	void 테스트4() {
		List<Person> people = List.of(
				new Person("김바다", 30),
				new Person("신포도", 5));
		
		// 객체 안에 있는 메소드 호출
		//people.forEach(p -> p.print()); // lambda
		
		//people.forEach(Person::print); // method reference
		
		// 객체를 println으로 출력하기
		people.forEach(p -> System.out.println(p)); // lambda
		people.forEach(System.out::println); // method reference
	}
	
	@ToString
	class Person {
		private String name;
		private int age;
		
		Person(String name, int age) {
			this.name = name;
			this.age = age;
		}
		
		void print() {
			System.out.println(this);
		}
	}
}
