package com.ticket.showList.bo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ticket.api.ShowAPIWebClient;
import com.ticket.api.XmlParser;
import com.ticket.show.domain.Show;
import com.ticket.showList.dao.ShowListMapper;
import com.ticket.showList.domain.ShowData;
import com.ticket.showList.domain.ShowList;

@SpringBootTest
class ShowListBOTest {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ShowAPIWebClient showAPIWebClient;
	
	@Autowired
	private XmlParser xmlParser;
	
	@Autowired
	private ShowListMapper showListMapper;
	
	@Test
	void DB에show넣기(int page) {
		page = 1;
		// 공연목록 api 1p 10개 가져오기
		String pageStr = Integer.toString(page);
		String xmlString = showAPIWebClient.getShowList("10", pageStr);
		List<ShowList> showListList = null;
		try {
			showListList = xmlParser.parseXmlString(xmlString);
		} catch (Exception e) {
			logger.info("######## 공연목록 xml 파싱 안 됨");
		}
		
		List<ShowData> showDataList = new ArrayList<>();
		List<Show> showList = new ArrayList<>();
		
		// 10개 하나씩 공연 상세 api 가져오기
		for (ShowList show : showListList) {
			String xmlData = showAPIWebClient.getShow(show.getMt20id());
			ShowData showData = null;
			try {
				showData = xmlParser.parseShowDataXmlString(xmlData);
			} catch (Exception e) {
				logger.info("######## 공연상세 xml 파싱 안 됨");
			}
			showDataList.add(showData);
			
			Show showToInsert = new Show();
			
			// TheaterId 처리
			Random random = new Random();
			int randomTheaterId = random.nextInt(10) + 1;
			showToInsert.setTheaterId(randomTheaterId);
			
			showToInsert.setName(showData.getPrfnm());
			showToInsert.setGenre(showData.getGenrenm());
			showToInsert.setStartDate(showData.getPrfpdfrom());
			showToInsert.setEndDate(showData.getPrfpdto());
			
			// validStartDate 처리
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
			Date sDate = null;
			try {
				sDate = dateFormat.parse(showData.getPrfpdfrom());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Date nowDate = new Date();
			Date lateDate = sDate.before(nowDate) ? nowDate : sDate;
			String validStartDate = dateFormat.format(lateDate);
			showToInsert.setValidStartDate(validStartDate);
			
			showToInsert.setValidEndDate(showData.getPrfpdto());
			
			// 타임, age 파싱
			int time = timeParsing(showData.getPrfruntime());
			showToInsert.setTime(time);
			int age = ageParsing(showData.getPrfage());
			showToInsert.setAge(age);
			
			showToInsert.setImagePath(showData.getPoster());
			showToInsert.setBannerImagePath(showData.getPoster());
			showToInsert.setInfoImagePath(showData.getPoster());
			showToInsert.setDiscountImagePath(showData.getPoster());
			
		}
		
		// DB에 insert
		showListMapper.insertShowList(showList);
		
	}
	
	@Test
	public int timeParsing(String time) {
		// 정규표현식 패턴: 숫자 다음에 "시간"이 오는 숫자 추출
		Pattern patternHours = Pattern.compile("(\\d+)시간");
        // 정규표현식 패턴: 숫자 다음에 "분"이 오는 숫자 추출
        Pattern patternMinutes = Pattern.compile("(\\d+)분");
        
        
        Matcher matcherHours = patternHours.matcher(time);
        Matcher matcherMinutes = patternMinutes.matcher(time);
        
        int[] extractedNumbers = new int[2]; // 0: 시간, 1: 분
        
        if (matcherHours.find()) {
            extractedNumbers[0] = Integer.parseInt(matcherHours.group(1));
        }
        
        if (matcherMinutes.find()) {
            extractedNumbers[1] = Integer.parseInt(matcherMinutes.group(1));
        }
        int minutes = extractedNumbers[0] * 60 + extractedNumbers[1];
        return minutes;
	}
	
	@Test
	public int ageParsing(String age) {
		Pattern pattern = Pattern.compile("만 (\\d+)세");
        Matcher matcher = pattern.matcher(age);
        
        if (matcher.find()) {
            String extractedNumberStr = matcher.group(1);
            try {
                int extractedNumber = Integer.parseInt(extractedNumberStr);
                return extractedNumber;
            } catch (NumberFormatException e) {
                logger.info("######time 변환 불가");
            }
        }
        return 0;
	}
		
	}

	
	
	
	
	
	
	


