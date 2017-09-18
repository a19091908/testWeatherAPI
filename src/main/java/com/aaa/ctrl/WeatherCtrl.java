package com.aaa.ctrl;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.aaa.model.Weather;
import com.aaa.repository.WeatherRepository;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class WeatherCtrl {
	@Autowired
	private WeatherRepository weatherRepository;

	private String page = "./index";
	private String url = "http://opendata.cwb.gov.tw/api/v1/rest/datastore/{dataid}?Authorization={authorization}";
	private String dataId = null;
	private String authorization = "CWB-38C92FB4-ADB1-4F2B-8D9E-9AC67BE3ACF7";
	private HttpHeaders httpHeaders = new HttpHeaders();

	private static RestTemplate restTemplate = new RestTemplate();

	@RequestMapping("/")
	String index() {
		return "index";
	}

	@RequestMapping(value = "/WeatherCtrl")
	public String doSome(Model model, HttpSession session, HttpServletRequest request,
			@RequestParam(required = false, value = "action") String action,
			@RequestParam(required = false, value = "locationName") String locationName) {

		switch (action) {
		case "insert36Hours":
			page = doInsert36Hours();
			break;
		case "search36Hours":
			page = doSearch36Hours(locationName, model);
			break;
		default:
			System.out.println("error");
		}
		return page;
	}

	/*
	 * 新增一般天氣預報-今明 36 小時天氣預報
	 */
	private String doInsert36Hours() {

		dataId = "F-C0032-001";

		// 設定表頭瀏覽器使存取URL成功
		httpHeaders.set("User-Agent", "Chrome/60.0.3112.113");
		HttpEntity<String> entity = new HttpEntity<String>(httpHeaders);

		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class, dataId,
				authorization);
		String s = responseEntity.getBody();

		try {
			// 分析JSON資料
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode rootNode = objectMapper.readTree(s);
			JsonNode jsonLocationNode = rootNode.get("records").get("location");
			if (jsonLocationNode.isArray()) {
				// 將每筆資料mapping到每個Weather物件上
				for (final JsonNode objNode : jsonLocationNode) {
					Weather weather = objectMapper.readValue(objNode.toString(), Weather.class);
					weatherRepository.save(weather);
				}
			}

		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "./index";
	}

	/*
	 * 新增鄉鎮天氣預報-單一鄉鎮市區預報 (未來 2 天天氣預報)
	 */
	private String doSearch36Hours(String locationName, Model model) {
		System.out.println("搜尋的地點:" + locationName);

		List<Weather> locationWeatherList = weatherRepository.findByLocationNameContaining(locationName);

		// 將搜尋到的資料放到model
		model.addAttribute("locationWeatherList", locationWeatherList);
		return "./index";
	}

}