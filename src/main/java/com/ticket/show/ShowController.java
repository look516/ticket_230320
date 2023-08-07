package com.ticket.show;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.ticket.show.bo.ShowBO;
import com.ticket.show.domain.Show;

@RequestMapping("/show")
@Controller
public class ShowController {
	
	@Autowired
	private ShowBO showBO;
	
	@GetMapping("/show_detail_view")
	public String showDetailView(
			@RequestParam("showId") int showId,
			Model model) {
		
		// show select by showId
		Show show = showBO.getShowByShowId(showId);
		model.addAttribute("show", show);
		model.addAttribute("view", "show/showDetail");
		return "template/layout";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// 보류
	// tag값 가져오는 메소드
	private static String getTagValue(String tag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
		Node nValue = (Node)nlList.item(0);
		if (nValue == null) {
			return null;
		}
		return nValue.getNodeValue();
	}
			
	@GetMapping("/show_list_view")
	public String showListView(Model model) throws ParserConfigurationException, SAXException, IOException {
		
		String url = "http://www.kopis.or.kr/openApi/restful/pblprfr";
		String service = "bd89dc80bd9f43338c9d75e7fae03669";
		String stdate = "20230101"; // 시작일
		String eddate = "20230801"; // 종료일
		String rows = "10"; // 개수
		String cpage = "1"; // 현재 페이지
		
		
		String parsingUrl = url + "?service=" + service + "&stdate=" + stdate
				+ "&eddate=" + eddate + "&rows=" + rows + "&cpage=" + cpage;
		
		
		DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
		Document doc = dBuilder.parse(parsingUrl);
		
		// root tag => 최상위 태그(dbs)
		doc.getDocumentElement().normalize();
		model.addAttribute("root", doc.getDocumentElement().getNodeName());
		
		// 파싱 tag => 가져올 태그(db)
		// 전체 공연 개수인지 아니면 한 공연의 태그 개수인지
		NodeList nList = doc.getElementsByTagName("db");
		model.addAttribute("num", nList.getLength());
		
		List<Map> shows = new ArrayList<>();
		for(int temp = 0; temp < nList.getLength(); temp++){		
			Node nNode = nList.item(temp);
			if(nNode.getNodeType() == Node.ELEMENT_NODE){
								
				Element eElement = (Element) nNode;
				Map<String, Object> show = new HashMap<>();
				show.put("prfnm", getTagValue("prfnm", eElement));
				show.put("poster", getTagValue("poster", eElement));
				show.put("fcltynm", getTagValue("fcltynm", eElement));
				show.put("prfpdfrom", getTagValue("prfpdfrom", eElement));
				show.put("prfpdto", getTagValue("prfpdto", eElement));
				
				shows.add(show);
					
				//System.out.println(eElement.getTextContent())
			}	// if end
		}	// for end
		//shows라는 list를 model에 넣어주자.
		model.addAttribute("shows", shows);
		
		model.addAttribute("view", "show/showList");
		
		return "template/layout";
	}
}
