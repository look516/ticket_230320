package com.ticket.api;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ticket.show.service.TagService;
import com.ticket.showList.domain.ShowData;
import com.ticket.showList.domain.ShowList;

@SpringBootTest
class XmlParserTest {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	
	@Autowired
	private TagService tagService;
	
	String showXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n"
			+ "<dbs>\r\n"
			+ "    <db>\r\n"
			+ "        <mt20id>PF224468</mt20id>\r\n"
			+ "        <prfnm>첼리스트 이호찬 리사이틀</prfnm>\r\n"
			+ "        <prfpdfrom>2023.10.14</prfpdfrom>\r\n"
			+ "        <prfpdto>2023.10.14</prfpdto>\r\n"
			+ "        <fcltynm>예술의전당 (리사이틀홀)</fcltynm>\r\n"
			+ "        <prfcast>이호찬, 송영민</prfcast>\r\n"
			+ "        <prfcrew> </prfcrew>\r\n"
			+ "        <prfruntime>1시간 25분</prfruntime>\r\n"
			+ "        <prfage>만 6세 이상</prfage>\r\n"
			+ "        <entrpsnm> </entrpsnm>\r\n"
			+ "        <pcseguidance>R석 50,000원, S석 30,000원</pcseguidance>\r\n"
			+ "        <poster>http://www.kopis.or.kr/upload/pfmPoster/PF_PF224468_230823_142841.gif</poster>\r\n"
			+ "        <sty> </sty>\r\n"
			+ "        <genrenm>서양음악(클래식)</genrenm>\r\n"
			+ "        <prfstate>공연예정</prfstate>\r\n"
			+ "        <openrun>N</openrun>\r\n"
			+ "        <styurls>\r\n"
			+ "            <styurl>http://www.kopis.or.kr/upload/pfmIntroImage/PF_PF224468_230823_0228410.jpg</styurl>\r\n"
			+ "        </styurls>\r\n"
			+ "        <mt10id>FC000001</mt10id>\r\n"
			+ "        <dtguidance>토요일(20:00)</dtguidance>\r\n"
			+ "    </db>\r\n"
			+ "</dbs>";
	
	String showListXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n"
			+ "<dbs>\r\n"
			+ "    <db>\r\n"
			+ "        <mt20id>PF224564</mt20id>\r\n"
			+ "        <prfnm>홍주, 흥에 취하다</prfnm>\r\n"
			+ "        <prfpdfrom>2023.09.01</prfpdfrom>\r\n"
			+ "        <prfpdto>2023.09.01</prfpdto>\r\n"
			+ "        <fcltynm>홍주문화회관</fcltynm>\r\n"
			+ "        <poster>http://www.kopis.or.kr/upload/pfmPoster/PF_PF224564_230824_144847.jpg</poster>\r\n"
			+ "        <genrenm>한국음악(국악)</genrenm>\r\n"
			+ "        <prfstate>공연예정</prfstate>\r\n"
			+ "        <openrun>N</openrun>\r\n"
			+ "    </db>\r\n"
			+ "    <db>\r\n"
			+ "        <mt20id>PF224563</mt20id>\r\n"
			+ "        <prfnm>디깅라이브세종, EP6. 장필순 [세종]</prfnm>\r\n"
			+ "        <prfpdfrom>2023.11.25</prfpdfrom>\r\n"
			+ "        <prfpdto>2023.11.25</prfpdto>\r\n"
			+ "        <fcltynm>세종 음악창작소 누리락</fcltynm>\r\n"
			+ "        <poster>http://www.kopis.or.kr/upload/pfmPoster/PF_PF224563_230824_144643.gif</poster>\r\n"
			+ "        <genrenm>대중음악</genrenm>\r\n"
			+ "        <prfstate>공연예정</prfstate>\r\n"
			+ "        <openrun>N</openrun>\r\n"
			+ "    </db>\r\n"
			+ "    <db>\r\n"
			+ "        <mt20id>PF224562</mt20id>\r\n"
			+ "        <prfnm>디깅라이브세종, EP5. O3ohn(오존) [세종]</prfnm>\r\n"
			+ "        <prfpdfrom>2023.10.28</prfpdfrom>\r\n"
			+ "        <prfpdto>2023.10.28</prfpdto>\r\n"
			+ "        <fcltynm>세종 음악창작소 누리락</fcltynm>\r\n"
			+ "        <poster>http://www.kopis.or.kr/upload/pfmPoster/PF_PF224562_230824_144321.gif</poster>\r\n"
			+ "        <genrenm>대중음악</genrenm>\r\n"
			+ "        <prfstate>공연예정</prfstate>\r\n"
			+ "        <openrun>N</openrun>\r\n"
			+ "    </db>\r\n"
			+ "    <db>\r\n"
			+ "        <mt20id>PF224561</mt20id>\r\n"
			+ "        <prfnm>해금협회 20주년 기념공연</prfnm>\r\n"
			+ "        <prfpdfrom>2023.09.23</prfpdfrom>\r\n"
			+ "        <prfpdto>2023.09.23</prfpdto>\r\n"
			+ "        <fcltynm>국립국악원</fcltynm>\r\n"
			+ "        <poster>http://www.kopis.or.kr/upload/pfmPoster/PF_PF224561_230824_144314.jpg</poster>\r\n"
			+ "        <genrenm>한국음악(국악)</genrenm>\r\n"
			+ "        <prfstate>공연예정</prfstate>\r\n"
			+ "        <openrun>N</openrun>\r\n"
			+ "    </db>\r\n"
			+ "    <db>\r\n"
			+ "        <mt20id>PF224560</mt20id>\r\n"
			+ "        <prfnm>하우스콘서트 공연장 마실, The Cellists of KNUA</prfnm>\r\n"
			+ "        <prfpdfrom>2023.09.13</prfpdfrom>\r\n"
			+ "        <prfpdto>2023.09.13</prfpdto>\r\n"
			+ "        <fcltynm>거제문화예술회관</fcltynm>\r\n"
			+ "        <poster>http://www.kopis.or.kr/upload/pfmPoster/PF_PF224560_230824_143925.png</poster>\r\n"
			+ "        <genrenm>서양음악(클래식)</genrenm>\r\n"
			+ "        <prfstate>공연예정</prfstate>\r\n"
			+ "        <openrun>N</openrun>\r\n"
			+ "    </db>\r\n"
			+ "    <db>\r\n"
			+ "        <mt20id>PF224559</mt20id>\r\n"
			+ "        <prfnm>디깅라이브세종, EP4. 해서웨이 [세종]</prfnm>\r\n"
			+ "        <prfpdfrom>2023.09.23</prfpdfrom>\r\n"
			+ "        <prfpdto>2023.09.23</prfpdto>\r\n"
			+ "        <fcltynm>세종 음악창작소 누리락</fcltynm>\r\n"
			+ "        <poster>http://www.kopis.or.kr/upload/pfmPoster/PF_PF224559_230824_143920.gif</poster>\r\n"
			+ "        <genrenm>대중음악</genrenm>\r\n"
			+ "        <prfstate>공연예정</prfstate>\r\n"
			+ "        <openrun>N</openrun>\r\n"
			+ "    </db>\r\n"
			+ "    <db>\r\n"
			+ "        <mt20id>PF224558</mt20id>\r\n"
			+ "        <prfnm>하녀들</prfnm>\r\n"
			+ "        <prfpdfrom>2023.09.08</prfpdfrom>\r\n"
			+ "        <prfpdto>2023.09.10</prfpdto>\r\n"
			+ "        <fcltynm>삼동소극장</fcltynm>\r\n"
			+ "        <poster>http://www.kopis.or.kr/upload/pfmPoster/PF_PF224558_230824_143703.jpg</poster>\r\n"
			+ "        <genrenm>연극</genrenm>\r\n"
			+ "        <prfstate>공연예정</prfstate>\r\n"
			+ "        <openrun>N</openrun>\r\n"
			+ "    </db>\r\n"
			+ "    <db>\r\n"
			+ "        <mt20id>PF224557</mt20id>\r\n"
			+ "        <prfnm>포크 트리오, 동창회콘서트 [제주]</prfnm>\r\n"
			+ "        <prfpdfrom>2023.10.09</prfpdfrom>\r\n"
			+ "        <prfpdto>2023.10.09</prfpdto>\r\n"
			+ "        <fcltynm>제주관광대학교컨벤션홀</fcltynm>\r\n"
			+ "        <poster>http://www.kopis.or.kr/upload/pfmPoster/PF_PF224557_230824_143233.gif</poster>\r\n"
			+ "        <genrenm>대중음악</genrenm>\r\n"
			+ "        <prfstate>공연예정</prfstate>\r\n"
			+ "        <openrun>N</openrun>\r\n"
			+ "    </db>\r\n"
			+ "    <db>\r\n"
			+ "        <mt20id>PF224556</mt20id>\r\n"
			+ "        <prfnm>데이비드 내한공연</prfnm>\r\n"
			+ "        <prfpdfrom>2023.12.09</prfpdfrom>\r\n"
			+ "        <prfpdto>2023.12.09</prfpdto>\r\n"
			+ "        <fcltynm>무신사 개러지(구. 왓챠홀)</fcltynm>\r\n"
			+ "        <poster>http://www.kopis.or.kr/upload/pfmPoster/PF_PF224556_230824_143037.gif</poster>\r\n"
			+ "        <genrenm>대중음악</genrenm>\r\n"
			+ "        <prfstate>공연예정</prfstate>\r\n"
			+ "        <openrun>N</openrun>\r\n"
			+ "    </db>\r\n"
			+ "    <db>\r\n"
			+ "        <mt20id>PF224555</mt20id>\r\n"
			+ "        <prfnm>포레무지카와 함께하는 인상주의를 아세요?</prfnm>\r\n"
			+ "        <prfpdfrom>2023.09.10</prfpdfrom>\r\n"
			+ "        <prfpdto>2023.09.10</prfpdto>\r\n"
			+ "        <fcltynm>예술의전당</fcltynm>\r\n"
			+ "        <poster>http://www.kopis.or.kr/upload/pfmPoster/PF_PF224555_230824_143022.gif</poster>\r\n"
			+ "        <genrenm>서양음악(클래식)</genrenm>\r\n"
			+ "        <prfstate>공연예정</prfstate>\r\n"
			+ "        <openrun>N</openrun>\r\n"
			+ "    </db>\r\n"
			+ "</dbs>\r\n"
			+ "";
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//@Test
	void parseXmlString() throws Exception {
		String xmlString = showListXml;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new ByteArrayInputStream(xmlString.getBytes()));

        // root tag
        doc.getDocumentElement().normalize();
        logger.info("#####root tag: " + doc.getDocumentElement().getNodeName());
        
        // parshing tag
        NodeList nList = doc.getElementsByTagName("db");
        logger.info("#####tag count: " + nList.getLength());
        
        // List<showList>
        List<ShowList> showListList = new ArrayList<>();
        
        for (int i = 0; i < nList.getLength(); i++) {
            Node node = nList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                
                
                
                String showDBId = tagService.getTagValue("mt20id", element);
                String theaterName = tagService.getTagValue("fcltynm", element);
                
                String name = tagService.getTagValue("prfnm", element);
                String genre = tagService.getTagValue("genrenm", element);
                String startDate = tagService.getTagValue("prfpdfrom", element);
                String endDate = tagService.getTagValue("prfpdto", element);
                String imagePath = tagService.getTagValue("poster", element);
                
                
                ShowList showList = new ShowList();
                showList.setMt20id(showDBId);
                showList.setFcltynm(theaterName);
                showList.setPrfnm(name);
                showList.setGenrenm(genre);
                showList.setPrfpdfrom(startDate);
                showList.setPrfpdto(endDate);
                showList.setPoster(imagePath);
                
                showListList.add(showList);
            }
        }
        logger.info(showListList.toString());
        
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Test
	void parseShowDataXmlString() throws Exception {
		String xmlString = showXml;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new ByteArrayInputStream(xmlString.getBytes()));

        // root tag
        doc.getDocumentElement().normalize();
        logger.info("###root tag: " + doc.getDocumentElement().getNodeName());
        
        // parshing tag
        NodeList nList = doc.getElementsByTagName("db");
        logger.info("###tag count: " + nList.getLength());
        
        // ShowData
        ShowData showData = new ShowData();
        
        Node node = nList.item(0);
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            
            //int theaterId = ;
            //String validStartDate = ;// startDate or new Date()
            //String validEndDate = ; // 원래는 티켓팅 풀린 날짜까지 해야 하지만 지금은 endDate로 넣기
            
            String theaterName = tagService.getTagValue("fcltynm", element);
            String name = tagService.getTagValue("prfnm", element);
            String genre = tagService.getTagValue("genrenm", element);
            String startDate = tagService.getTagValue("prfpdfrom", element);
            String endDate = tagService.getTagValue("prfpdto", element);
            String imagePath = tagService.getTagValue("poster", element);
            
            String time = tagService.getTagValue("prfruntime", element); // int로 파싱
            String age = tagService.getTagValue("prfage", element); // int로 파싱
            String bannerImagePath = tagService.getTagValue("poster", element);
            String infoImagePath = tagService.getTagValue("poster", element);
            String discountImagePath = tagService.getTagValue("poster", element);
            
			/* 현재는 임의로 같은 이미지 넣어둠 추후 styurl 이미지 가져와서 enum으로 넣기
			 * NodeList styurlList = element.getElementsByTagName("styurl");
			 * 
			 * for (int j = 0; j < styurlList.getLength(); j++) { Element sElement =
			 * (Element) styurlList.item(j); tagService.getTagValue("styurl", sElement); }
			 */
            
            
            showData.setFcltynm(theaterName);
            showData.setPrfnm(name);
            showData.setGenrenm(genre);
            showData.setPrfpdfrom(startDate);
            showData.setPrfpdto(endDate);
            showData.setPoster(imagePath);
            
            showData.setPrfruntime(time);
            showData.setPrfage(age);
            if (bannerImagePath == null) {
            	showData.setStyurl1(imagePath);
            } else {
            	showData.setStyurl1(bannerImagePath);
            }
            
            if (infoImagePath == null) {
            	showData.setStyurl2(imagePath);
            } else {
            	showData.setStyurl2(infoImagePath);
            }
       
            if (discountImagePath == null) {
            	showData.setStyurl3(imagePath);
            } else {
            	showData.setStyurl3(discountImagePath);
            }
            
            
        }
        
        logger.info(showData.toString());
    }

}
