/**
 * 
 */
package com.gish.voteadder.resources;

import java.util.Enumeration;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author Gishan
 *
 */
@RestController
@RequestMapping("/")
@CrossOrigin
public class TestResource {

	private RestTemplate template = new RestTemplate();

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String get(HttpServletRequest httpServletRequest) {
		Enumeration<String> headers = httpServletRequest.getHeaderNames();
		while (headers.hasMoreElements()) {
			String header = headers.nextElement();
			System.out.println(header + " ---> " + httpServletRequest.getHeader(header));
		}
		return "HttpServletRequest : " + httpServletRequest.toString();
	}

	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public String post(HttpServletRequest httpServletRequest) {
		Enumeration<String> headers = httpServletRequest.getHeaderNames();
		while (headers.hasMoreElements()) {
			String header = headers.nextElement();
			System.out.println(header + " ---> " + httpServletRequest.getHeader(header));
		}
		return "HttpServletRequest : " + httpServletRequest.toString();
	}

	@RequestMapping(value = "/sendGet", method = RequestMethod.GET)
	public String sengGet() {
		template = new RestTemplate();
		String response = sendGetRequests(template);
		return "response : " + response;
	}
	
	
	
	
	private String sendGetRequests(RestTemplate template) {
		System.out.println();
		System.out.println("sendGetRequests");
		String url = "https://moodle.itfac.mrt.ac.lk/login/index.php";
//		String url = "http://192.168.1.2:8085/test";
//		String location = "";
		
		String data = "username=username&password=password";


		HttpHeaders headers = new HttpHeaders();
		headers.set("host", "moodle.itfac.mrt.ac.lk");
		headers.set("user-agent", ":P");
		headers.set("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		headers.set("accept-language", "en-US,en;q=0.5");
		headers.set("accept-encoding", "gzip, deflate, br");
		headers.set("referer", "https://moodle.itfac.mrt.ac.lk/login/index.php");
		headers.set("content-type", "application/x-www-form-urlencoded");
		headers.set("content-length", (data.length()) + "");
		headers.set("cookie", "MoodleSession=cookie"+new Random().nextLong());
		headers.set("connection", "keep-alive");
		headers.set("upgrade-insecure-requests", "1");
		headers.set("accept-charset", "big5, big5-hkscs, cesu-8, euc-jp, euc-kr, gb18030, gb2312, gbk, ibm-thai, ibm00858, ibm01140, ibm01141, ibm01142, ibm01143, ibm01144, ibm01145, ibm01146, ibm01147, ibm01148, ibm01149, ibm037, ibm1026, ibm1047, ibm273, ibm277, ibm278, ibm280, ibm284, ibm285, ibm290, ibm297, ibm420, ibm424, ibm437, ibm500, ibm775, ibm850, ibm852, ibm855, ibm857, ibm860, ibm861, ibm862, ibm863, ibm864, ibm865, ibm866, ibm868, ibm869, ibm870, ibm871, ibm918, iso-2022-cn, iso-2022-jp, iso-2022-jp-2, iso-2022-kr, iso-8859-1, iso-8859-13, iso-8859-15, iso-8859-2, iso-8859-3, iso-8859-4, iso-8859-5, iso-8859-6, iso-8859-7, iso-8859-8, iso-8859-9, jis_x0201, jis_x0212-1990, koi8-r, koi8-u, shift_jis, tis-620, us-ascii, utf-16, utf-16be, utf-16le, utf-32, utf-32be, utf-32le, utf-8, windows-1250, windows-1251, windows-1252, windows-1253, windows-1254, windows-1255, windows-1256, windows-1257, windows-1258, windows-31j, x-big5-hkscs-2001, x-big5-solaris, x-compound_text, x-euc-jp-linux, x-euc-tw, x-eucjp-open, x-ibm1006, x-ibm1025, x-ibm1046, x-ibm1097, x-ibm1098, x-ibm1112, x-ibm1122, x-ibm1123, x-ibm1124, x-ibm1166, x-ibm1364, x-ibm1381, x-ibm1383, x-ibm300, x-ibm33722, x-ibm737, x-ibm833, x-ibm834, x-ibm856, x-ibm874, x-ibm875, x-ibm921, x-ibm922, x-ibm930, x-ibm933, x-ibm935, x-ibm937, x-ibm939, x-ibm942, x-ibm942c, x-ibm943, x-ibm943c, x-ibm948, x-ibm949, x-ibm949c, x-ibm950, x-ibm964, x-ibm970, x-iscii91, x-iso-2022-cn-cns, x-iso-2022-cn-gb, x-iso-8859-11, x-jis0208, x-jisautodetect, x-johab, x-macarabic, x-maccentraleurope, x-maccroatian, x-maccyrillic, x-macdingbat, x-macgreek, x-machebrew, x-maciceland, x-macroman, x-macromania, x-macsymbol, x-macthai, x-macturkish, x-macukraine, x-ms932_0213, x-ms950-hkscs, x-ms950-hkscs-xp, x-mswin-936, x-pck, x-sjis_0213, x-utf-16le-bom, x-utf-32be-bom, x-utf-32le-bom, x-windows-50220, x-windows-50221, x-windows-874, x-windows-949, x-windows-950, x-windows-iso2022jp");
		
//		accept-charset ---> big5, big5-hkscs, cesu-8, euc-jp, euc-kr, gb18030, gb2312, gbk, ibm-thai, ibm00858, ibm01140, ibm01141, ibm01142, ibm01143, ibm01144, ibm01145, ibm01146, ibm01147, ibm01148, ibm01149, ibm037, ibm1026, ibm1047, ibm273, ibm277, ibm278, ibm280, ibm284, ibm285, ibm290, ibm297, ibm420, ibm424, ibm437, ibm500, ibm775, ibm850, ibm852, ibm855, ibm857, ibm860, ibm861, ibm862, ibm863, ibm864, ibm865, ibm866, ibm868, ibm869, ibm870, ibm871, ibm918, iso-2022-cn, iso-2022-jp, iso-2022-jp-2, iso-2022-kr, iso-8859-1, iso-8859-13, iso-8859-15, iso-8859-2, iso-8859-3, iso-8859-4, iso-8859-5, iso-8859-6, iso-8859-7, iso-8859-8, iso-8859-9, jis_x0201, jis_x0212-1990, koi8-r, koi8-u, shift_jis, tis-620, us-ascii, utf-16, utf-16be, utf-16le, utf-32, utf-32be, utf-32le, utf-8, windows-1250, windows-1251, windows-1252, windows-1253, windows-1254, windows-1255, windows-1256, windows-1257, windows-1258, windows-31j, x-big5-hkscs-2001, x-big5-solaris, x-compound_text, x-euc-jp-linux, x-euc-tw, x-eucjp-open, x-ibm1006, x-ibm1025, x-ibm1046, x-ibm1097, x-ibm1098, x-ibm1112, x-ibm1122, x-ibm1123, x-ibm1124, x-ibm1166, x-ibm1364, x-ibm1381, x-ibm1383, x-ibm300, x-ibm33722, x-ibm737, x-ibm833, x-ibm834, x-ibm856, x-ibm874, x-ibm875, x-ibm921, x-ibm922, x-ibm930, x-ibm933, x-ibm935, x-ibm937, x-ibm939, x-ibm942, x-ibm942c, x-ibm943, x-ibm943c, x-ibm948, x-ibm949, x-ibm949c, x-ibm950, x-ibm964, x-ibm970, x-iscii91, x-iso-2022-cn-cns, x-iso-2022-cn-gb, x-iso-8859-11, x-jis0208, x-jisautodetect, x-johab, x-macarabic, x-maccentraleurope, x-maccroatian, x-maccyrillic, x-macdingbat, x-macgreek, x-machebrew, x-maciceland, x-macroman, x-macromania, x-macsymbol, x-macthai, x-macturkish, x-macukraine, x-ms932_0213, x-ms950-hkscs, x-ms950-hkscs-xp, x-mswin-936, x-pck, x-sjis_0213, x-utf-16le-bom, x-utf-32be-bom, x-utf-32le-bom, x-windows-50220, x-windows-50221, x-windows-874, x-windows-949, x-windows-950, x-windows-iso2022jp

		
//		Host: moodle.itfac.mrt.ac.lk
//		User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10.12; rv:49.0) Gecko/20100101 Firefox/49.0
//		Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8
//		Accept-Language: en-US,en;q=0.5
//		Accept-Encoding: gzip, deflate, br
//		Referer: https://moodle.itfac.mrt.ac.lk/login/index.php
//		Cookie: MoodleSession=ihuu7jjebdgs1370515l8kq476
//		Connection: keep-alive
//		Upgrade-Insecure-Requests: 1

		HttpEntity<String> request = new HttpEntity<String>(data, headers);

		 System.out.println("//////////////////////// request /////////////////////////////////////////////");
		 System.out.println(request.getBody());
		 System.out.println("//////////////////////// request Headers/////////////////////////////////////////////");
		 HttpHeaders requestHeaders = request.getHeaders();
		 for (String iterable_element : request.getHeaders().keySet()) {
		 for (String iterable_element2 : requestHeaders.get(iterable_element))
		 {
		 System.out.println(iterable_element + " ---> " +iterable_element2);
		
		 }
		 }
		 System.out.println("//////////////////////// end request /////////////////////////////////////////////");
		 System.out.println();

		ResponseEntity<String> response = template.postForEntity(url, request, String.class);
//		ResponseEntity<String> response = template.exchange(url, HttpMethod.POST, request, String.class);
//		template.
		HttpHeaders responseHeaders = response.getHeaders();
		 System.out.println("//////////////////////// Response/////////////////////////////////////////////");
		 System.out.println(response.getStatusCodeValue());
		 System.out.println("//////////////////////// Headers /////////////////////////////////////////////");
		for (String iterable_element : responseHeaders.keySet()) {
			for (String iterable_element2 : responseHeaders.get(iterable_element)) {
				 System.out.println(iterable_element + " ---> " +
				 iterable_element2);
//				if (iterable_element.equalsIgnoreCase("location")) {
//					location = iterable_element2;
//					System.out.println("next page  : " + location);
//				}
			}
		}
		// System.out.println("");
		// System.out.println("/////////////////////////////////////////////////////////////////////");
		// System.out.println(response);
		// System.out.println(response.getStatusCode());
		// System.out.println(response.hasBody());
		// System.out.println(response.getBody());
		// System.out.println("/////////////////////////////////////////////////////////////////////");
		// System.out.println();

		return response.getStatusCodeValue()+"";
	}

}
