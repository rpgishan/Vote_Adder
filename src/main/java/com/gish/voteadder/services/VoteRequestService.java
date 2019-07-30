/**
 * 
 */
package com.gish.voteadder.services;

import java.util.Random;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author Gishan
 *
 */
@Service
public class VoteRequestService {
	private String[] firstGirlsNames = { "bhagya", "Tharushi", "Sachini", "Hiruni", "janani", "Fathima", "Rashmi",
			"Thilini", "Samadhi", "Amanda", "Nethmi", "chathu", "Nipuni", "Sanduni", "Upeksha", "sara", "Shehara",
			"harshani", "Ama", "Gayani", "Dinithi", "Sachintha", "Hansi", "ruwani", "senuri", "Roshani", "Hashani",
			"arunai", "Kalpani", "Ashani", "Piyumi", "Nuwani", "dewmini", "saumya", "dumindi", "shashini", "Hashani",
			"Yashodha", "chamathka", "Chathuri", "rajitha", "Isuri", "akila", "kaushi", "sumudu", "Sandali", "induwari",
			"nimesha", "Dinesha", "Hansani" };
	private String[] lastNames = { "Abeynaike", "Appuhamy", "Balasuriya", "Bandāra", "Bandaranaike", "Buddhika",
			"Chandrasiri", "Cooray", "Dahanayake", "Dassanayake", "De Abrew", "De Alwis", "Devapriya", "Dinuk",
			"Dissanayake", "Ekanayake", "Fernando", "Fonseca", "Gamage", "Gunasekera", "Gunawardena", "Hemantha",
			"Herath", "Jagath", "Jayakody", "Jayaratne", "Jayasinghe", "Jayatilleka", "Jayatissa", "Jayawickrama",
			"Jayewardene", "Karunaratne", "Madugalle", "Mendis", "Nandasiri", "Niroshan", "Pathirana", "Peiris",
			"Perera", "Prasanna", "Premaratne", "Priyantha", "Rajapaksa", "Rajasekara", "Ramanayake", "Ranaweera",
			"Ratnayake", "Samarasinghe", "Senanayake", "Senasinghe", "Seneviratne", "Shantha", "Silva", "Subasinghe",
			"Udawatte", "Vimukthi", "Warakagoda", "Weeraratne", "Weerasekara", "Weerawansa", "Wickremasinghe",
			"Wijeratne", "Wijewardene" };
	private int[] sp = { 1, 2, 5, 6, 7 };
	private int[] days = { 5, 6, 7 };
	private long restmobile = 3378678l;
	private long voteumber = 34452126632600l;
	private long fbnumber = 39443583618790l;
	private long fbid = 105385945936656l;
	private int nicy = 64;
	private long nicrest = 648168l;
	private int nameno = 0;

	private String cookie = "PHPSESSID=al6724a02ve2nnjiim630cs6o0";
	private int cookie1 = 1000, cookie2 = 10, cookie3 = 100;

	private String token = "5QH1UiZ7tf040iJ444XA5EB8977tIi96";

	private RestTemplate template = new RestTemplate();

	private Random random = new Random();

	public String runAllMethods() {
		template = new RestTemplate();
		cookie1 = 1000 + random.nextInt(8990);
		cookie2 = 10 + random.nextInt(80);
		cookie3 = 100 + random.nextInt(890);
		cookie = "PHPSESSID=al" + cookie1 + "a" + cookie2 + "ve2nnjiim" + cookie3 + "cs6o0";

		String mobile = "07" + sp[nameno % sp.length] + restmobile;
		String nic = (nicy + random.nextInt(32)) + days[random.nextInt(days.length - 1)] + nicrest + "";
		String firstName = firstGirlsNames[nameno % firstGirlsNames.length];
		String lastName = lastNames[(nameno+random.nextInt(40)) % lastNames.length];
		String name = firstName + " " + lastName;
		restmobile += random.nextInt(50000);
		voteumber++;
		fbnumber++;
		fbid++;
		nicrest += random.nextLong();
		System.out.println(
				"\n////////////////////////////////////////////////////////////////////////////////////////////////////////////");
		System.out.println("runAllMethods");
		String formlocation = sendFBPostRequests(template, fbid, firstName, lastName);
		while (!formlocation.equals("../form.php")) {
			fbid++;
			formlocation = sendFBPostRequests(template, fbid, firstName, lastName);
		}
		System.out.println("current fbid : " + fbid);
		 sendFormGetRequests(template, fbid);
		String votelocation = sendDetailsRequests(template, fbnumber, name, nic, mobile);
		nameno++;
		while (!votelocation.equals("../vote.php")) {
			votelocation = sendDetailsRequests(template, fbnumber, name, nic, mobile);
			nameno++;
			fbnumber++;
			nicrest += random.nextLong();
			nic = (nicy + random.nextInt(32)) + days[random.nextInt(days.length - 1)] + nicrest + "";

			restmobile += random.nextInt(50000);
			mobile = "07" + sp[nameno % sp.length] + restmobile;
		}
		sendPostRequests(template, voteumber);
		return "Done last fbid : " + fbid;
	}

	private String sendFBPostRequests(RestTemplate template, long fbid, String firstName, String lastName) {
		System.out.println();
		System.out.println("sendFBPostRequests");
		String url = "http://www3.technnovation.lk/functions/action_fb_login.php";
		// RestTemplate template = new RestTemplate();
		String data = "fbid=" + fbid + "&name=" + firstName + "+" + lastName
				+ "&picture=https%3A%2F%2Ffb-s-a-a.akamaihd.net%2Fh-ak-fbx%2Fv%2Ft1.0-1%2Fc15.0.50.50%2Fp50x50%2F1379841_10150004552801901_469209496895221757_n.jpg%3Foh%3Dd501be4493d45eab9adf54b6b8953587%26oe%3D59A25233%26__gda__%3D1503725912_0d86c126f7b482d08c9526eca32e0cde";

		String location = "";

		HttpHeaders headers = new HttpHeaders();
		headers.set("host", "technnovation.lk");
		headers.set("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:53.0) Gecko/20100101 Firefox/53.0");
		headers.set("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		headers.set("accept-language", "en-US,en;q=0.5");
		headers.set("accept-encoding", "gzip, deflate");
		headers.set("referer", "http://technnovation.lk/index.php");
		headers.set("content-type", "application/x-www-form-urlencoded");
		headers.set("content-length", (data.length()/* +25 */) + "");
		headers.set("cookie", cookie);
		headers.set("connection", "keep-alive");
		headers.set("upgrade-insecure-requests", "1");

		HttpEntity<String> request = new HttpEntity<String>(data, headers);

		// System.out.println("//////////////////////// request
		// /////////////////////////////////////////////");
		// System.out.println(request.getBody());
		// System.out.println("//////////////////////// request Headers
		// /////////////////////////////////////////////");
		// HttpHeaders requestHeaders = request.getHeaders();
		// for (String iterable_element : request.getHeaders().keySet()) {
		// for (String iterable_element2 : requestHeaders.get(iterable_element))
		// {
		//// System.out.println(iterable_element + " ---> " +
		// iterable_element2);
		//
		// }
		// }
		// System.out.println("//////////////////////// end request
		// /////////////////////////////////////////////");
		// System.out.println();

		ResponseEntity<String> response = template.postForEntity(url, request, String.class);
		HttpHeaders responseHeaders = response.getHeaders();
		// System.out.println("//////////////////////// Response
		// /////////////////////////////////////////////");
		// System.out.println(response.getStatusCodeValue());
		// System.out.println("//////////////////////// Headers
		// /////////////////////////////////////////////");
		for (String iterable_element : responseHeaders.keySet()) {
			for (String iterable_element2 : responseHeaders.get(iterable_element)) {
				// System.out.println(iterable_element + " ---> " +
				// iterable_element2);
				if (iterable_element.equalsIgnoreCase("location")) {
					location = iterable_element2;
					System.out.println("next page  : " + location);
				}
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

		return location;
	}

	private String sendFormGetRequests(RestTemplate template, long fbid) {
		System.out.println();
		System.out.println("sendFormGetRequests");
		String url = "http://technnovation.lk/form.php";
		// RestTemplate template = new RestTemplate();
		String data = "fbid=" + fbid
				+ "&name=Dumini+Silva&picture=https%3A%2F%2Ffb-s-a-a.akamaihd.net%2Fh-ak-fbx%2Fv%2Ft1.0-1%2Fc15.0.50.50%2Fp50x50%2F1379841_10150004552801901_469209496895221757_n.jpg%3Foh%3Dd501be4493d45eab9adf54b6b8953587%26oe%3D59A25233%26__gda__%3D1503725912_0d86c126f7b482d08c9526eca32e0cde";

		// fbid=106582979945962&name=Dumini+Silva&picture=https%3A%2F%2Ffb-s-a-a.akamaihd.net%2Fh-ak-fbx%2Fv%2Ft1.0-1%2Fc15.0.50.50%2Fp50x50%2F1379841_10150004552801901_469209496895221757_n.jpg%3Foh%3Dd501be4493d45eab9adf54b6b8953587%26oe%3D59A25233%26__gda__%3D1503725912_0d86c126f7b482d08c9526eca32e0cde

		String location = "";

		HttpHeaders headers = new HttpHeaders();
		headers.set("host", "technnovation.lk");
		headers.set("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:53.0) Gecko/20100101 Firefox/53.0");
		headers.set("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		headers.set("accept-language", "en-US,en;q=0.5");
		headers.set("accept-encoding", "gzip, deflate");
		// headers.set("referer", "http://technnovation.lk/index.php");
		// headers.set("content-type", "application/x-www-form-urlencoded");
		// headers.set("content-length", (data.length()/* +25 */) + "");
		// headers.set("cookie", cookie);
		headers.set("connection", "keep-alive");
		// headers.set("upgrade-insecure-requests", "1");
		headers.set("Pragma", "no-cache");
		headers.set("Cache-Control", "no-cache");

		// Host: technnovation.lk
		// User-Agent: Mozilla/5.0 (Windows NT 10.0; WOW64; rv:53.0)
		// Gecko/20100101 Firefox/53.0
		// Accept:
		// text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8
		// Accept-Language: en-US,en;q=0.5
		// Accept-Encoding: gzip, deflate
		// Connection: keep-alive
		// Pragma: no-cache
		// Cache-Control: no-cache

		HttpEntity<String> request = new HttpEntity<String>(data, headers);

		// System.out.println("//////////////////////// request
		// /////////////////////////////////////////////");
		// System.out.println(request.getBody());
		// System.out.println("//////////////////////// request Headers
		// /////////////////////////////////////////////");
		// HttpHeaders requestHeaders = request.getHeaders();
		// for (String iterable_element : request.getHeaders().keySet()) {
		// for (String iterable_element2 : requestHeaders.get(iterable_element))
		// {
		//// System.out.println(iterable_element + " ---> " +
		// iterable_element2);
		//
		// }
		// }
		// System.out.println("//////////////////////// end request
		// /////////////////////////////////////////////");
		// System.out.println();

		// ResponseEntity<String> response = template.postForEntity(url,
		// request, String.class);
		ResponseEntity<String> response = template.exchange(url, HttpMethod.GET, request, String.class);
		HttpHeaders responseHeaders = response.getHeaders();
		// System.out.println("//////////////////////// Response
		// /////////////////////////////////////////////");
		// System.out.println(response.getStatusCodeValue());
		// System.out.println("//////////////////////// Headers
		// /////////////////////////////////////////////");
		for (String iterable_element : responseHeaders.keySet()) {
			for (String iterable_element2 : responseHeaders.get(iterable_element)) {
				// System.out.println(iterable_element + " ---> " +
				// iterable_element2);
				if (iterable_element.equalsIgnoreCase("location")) {
					location = iterable_element2;
					System.out.println("next page  : " + location);
				}
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

		return location;
	}

	private String sendDetailsRequests(RestTemplate template, long fbnumber, String username, String nic,
			String mobile) {
		System.out.println();
		System.out.println("sendDetailsRequests");
		String url = "http://www3.technnovation.lk/functions/action_form.php";
		// RestTemplate template = new RestTemplate();

		String data = "-----------------------------" + fbnumber
				+ "\r\nContent-Disposition: form-data; name=\"name\"\r\n\r\n" + username
				+ "\r\n-----------------------------" + fbnumber
				+ "\r\nContent-Disposition: form-data; name=\"mobile\"\r\n\r\n" + mobile
				+ "\r\n-----------------------------" + fbnumber
				+ "\r\nContent-Disposition: form-data; name=\"address\"\r\n\r\nLanka\r\n-----------------------------"
				+ fbnumber + "\r\nContent-Disposition: form-data; name=\"nic\"\r\n\r\n" + nic
				+ "v\r\n-----------------------------" + fbnumber
				+ "\r\nContent-Disposition: form-data; name=\"token\"\r\n\r\n" + token
				+ "\r\n-----------------------------" + fbnumber
				+ "\r\nContent-Disposition: form-data; name=\"agree\"\r\n\r\non\r\n-----------------------------"
				+ fbnumber + "--\r\n";

		// String data2 =
		// "-----------------------------12524248873826\r\nContent-Disposition:
		// form-data;
		// name=\"name\"\r\n\r\ndumini\r\n-----------------------------12524248873826\r\nContent-Disposition:
		// form-data;
		// name=\"mobile\"\r\n\r\n0716545678\r\n-----------------------------12524248873826\r\nContent-Disposition:
		// form-data;
		// name=\"address\"\r\n\r\nLanka\r\n-----------------------------12524248873826\r\nContent-Disposition:
		// form-data;
		// name=\"nic\"\r\n\r\n915455623v\r\n-----------------------------12524248873826\r\nContent-Disposition:
		// form-data;
		// name=\"token\"\r\n\r\n"+token+"\r\n-----------------------------12524248873826\r\nContent-Disposition:
		// form-data;
		// name=\"agree\"\r\n\r\non\r\n-----------------------------12524248873826--\r\n";

		String location = "";

		HttpHeaders headers = new HttpHeaders();
		headers.set("host", "technnovation.lk");
		headers.set("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:53.0) Gecko/20100101 Firefox/53.0");
		headers.set("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		headers.set("accept-language", "en-US,en;q=0.5");
		headers.set("accept-encoding", "gzip, deflate");
		headers.set("referer", "http://technnovation.lk/form.php");
		headers.set("content-type", "multipart/form-data; boundary=---------------------------" + fbnumber);
		// headers.set("content-type", "multipart/form-data;
		// boundary=---------------------------12524248873826");
		headers.set("content-length", /* (676 + username.length()) + */ "682");
		headers.set("cookie", cookie);
		headers.set("connection", "keep-alive");
		headers.set("upgrade-insecure-requests", "1");

		HttpEntity<String> request = new HttpEntity<String>(data, headers);

		// System.out.println("//////////////////////// request
		// /////////////////////////////////////////////");
		// System.out.println(request.getBody());
		// System.out.println("//////////////////////// request Headers
		// /////////////////////////////////////////////");
		// HttpHeaders requestHeaders = request.getHeaders();
		// for (String iterable_element : request.getHeaders().keySet()) {
		// for (String iterable_element2 : requestHeaders.get(iterable_element))
		// {
		// System.out.println(iterable_element + " ---> " + iterable_element2);
		// }
		// }
		// System.out.println("//////////////////////// end request
		// /////////////////////////////////////////////");
		// System.out.println();

		ResponseEntity<String> response = template.postForEntity(url, request, String.class);
		HttpHeaders responseHeaders = response.getHeaders();
		// System.out.println("//////////////////////// Response
		// /////////////////////////////////////////////");
		// System.out.println(response.getStatusCodeValue());
		// System.out.println("//////////////////////// Headers
		// /////////////////////////////////////////////");
		for (String iterable_element : responseHeaders.keySet()) {
			for (String iterable_element2 : responseHeaders.get(iterable_element)) {
				// System.out.println(iterable_element + " ---> " +
				// iterable_element2);
				if (iterable_element.equalsIgnoreCase("location")) {
					location = iterable_element2;
					System.out.println("next page  : " + location);
				}
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

		return location;
	}

	private String sendPostRequests(RestTemplate template, long number) {
		System.out.println();
		System.out.println("sendPostRequests");
		String url = "http://www3.technnovation.lk/functions/action_vote.php";
		// String url = "http://127.0.0.1:8085/enctest";
		// RestTemplate template = new RestTemplate();
		// long number = 54901021631485l;
		String data = "-----------------------------" + number
				+ "\r\nContent-Disposition: form-data; name=\"company\"\r\n\r\n5\r\n-----------------------------"
				+ number
				+ "\r\nContent-Disposition: form-data; name=\"template_selected\"\r\n\r\n\r\n-----------------------------"
				+ number + "\r\nContent-Disposition: form-data; name=\"token\"\r\n\r\n" + token
				+ "\r\n-----------------------------" + number + "--\r\n";

		// String data2 =
		// "-----------------------------56342308517667\r\nContent-Disposition:
		// form-data;
		// name=\"company\"\r\n\r\n5\r\n-----------------------------56342308517667\r\nContent-Disposition:
		// form-data;
		// name=\"template_selected\"\r\n\r\n\r\n-----------------------------56342308517667\r\nContent-Disposition:
		// form-data;
		// name=\"token\"\r\n\r\n"+token+"\r\n-----------------------------56342308517667--\r\n";

		String location = "";

		HttpHeaders headers = new HttpHeaders();
		headers.set("host", "technnovation.lk");
		headers.set("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:53.0) Gecko/20100101 Firefox/53.0");
		headers.set("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		headers.set("accept-language", "en-US,en;q=0.5");
		headers.set("accept-encoding", "gzip, deflate");
		headers.set("referer", "http://technnovation.lk/vote.php");
		headers.set("content-type", "multipart/form-data; boundary=---------------------------" + number);
		// headers.set("content-type", "multipart/form-data;
		// boundary=-----------------------------56342308517667");
		headers.set("content-length", "379");
		headers.set("cookie", cookie);
		headers.set("connection", "keep-alive");
		headers.set("upgrade-insecure-requests", "1");
		headers.set("pragma", "no-cache");
		headers.set("cache-control", "no-cache");

		HttpEntity<String> request = new HttpEntity<String>(data, headers);

		// System.out.println("//////////////////////// request
		// /////////////////////////////////////////////");
		// System.out.println(request.getBody());
		// System.out.println("//////////////////////// request Headers
		// /////////////////////////////////////////////");
		// HttpHeaders requestHeaders = request.getHeaders();
		// for (String iterable_element : request.getHeaders().keySet()) {
		// for (String iterable_element2 : requestHeaders.get(iterable_element))
		// {
		// System.out.println(iterable_element + " ---> " + iterable_element2);
		// }
		// }
		// System.out.println("//////////////////////// end request
		// /////////////////////////////////////////////");
		// System.out.println();

		ResponseEntity<String> response = template.postForEntity(url, request, String.class);
		HttpHeaders responseHeaders = response.getHeaders();
		// System.out.println("//////////////////////// Response
		// /////////////////////////////////////////////");
		// System.out.println(response.getStatusCodeValue());
		// System.out.println("//////////////////////// Headers
		// /////////////////////////////////////////////");
		for (String iterable_element : responseHeaders.keySet()) {
			for (String iterable_element2 : responseHeaders.get(iterable_element)) {
				// System.out.println(iterable_element + " ---> " +
				// iterable_element2);
				if (iterable_element.equalsIgnoreCase("location")) {
					location = iterable_element2;
					System.out.println("next page  : " + location);
				}
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

		return location;
	}

}
