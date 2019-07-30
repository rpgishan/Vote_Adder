/**
 * 
 */
package com.gish.voteadder.resources;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gish.voteadder.services.VoteRequestService;

/**
 * @author Gishan
 *
 */
@RestController
@RequestMapping("/")
@CrossOrigin
public class RestApiResource {

	@Autowired
	VoteRequestService voteRequestService;

	private Thread controllerThread;
	private boolean isRunning;
	private boolean runIt;
	private int currentCount;
	private Random random = new Random();

	@RequestMapping(value = "/count")
	public String getCurrentCount() {
		return "Current Count : "+currentCount;
	}

	@RequestMapping(value = "/start")
	public String startThread() {
		runIt = true;
		controllerThread = myThread(10000);
		controllerThread.start();
		return "Thread is started";
	}

	@RequestMapping(value = "/start", params = "delay")
	public String startThreadWithDelay(@RequestParam("delay") int delay) {
		runIt = true;
		controllerThread = myThread(delay);
		controllerThread.start();
		return "Thread is started with " + (10000 + delay) + " delay limit";
	}

	@RequestMapping(value = "/start", params = { "delay", "count" })
	public String startThreadWithDelayAndCount(@RequestParam("delay") int delay, @RequestParam("count") int count) {
		count = (count < 0) ? 0 : count;
		runIt = true;
		controllerThread = myThread(delay, count);
		controllerThread.start();
		return "Thread is started with " + (10000 + delay) + " delay limit and max " + count + " requests";
	}

	@RequestMapping(value = "/stop")
	public String stopThread() {
		runIt = false;
		if (controllerThread != null && isRunning) {
			isRunning = false;
			return "Thread is stopped";
		}
		return "Thread hasn't been started";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/post", params = "count")
	public String postDataWithACount(@RequestParam("count") int count) {
		count = (count < 0) ? 0 : count;
		for (currentCount = 0; currentCount < count; currentCount++) {
			voteRequestService.runAllMethods();
		}
		return "Done";
	}

	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET, value = "/post")
	public String postData() {
		voteRequestService.runAllMethods();
		return "Done";
	}

	private Thread myThread(int delayLimit) {
		return new Thread(new Runnable() {

			@Override
			public void run() {
				isRunning = true;
				while (runIt) {
					voteRequestService.runAllMethods();
					Random r = new Random();
					int sleetInterval = 10000 + r.nextInt(delayLimit);
					System.out.println("\nsleetInterval : " + sleetInterval + "\n");
					try {
						Thread.sleep(sleetInterval);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
	}

	private Thread myThread(int delayLimit, int count) {
		currentCount = 1;
		return new Thread(new Runnable() {

			@Override
			public void run() {
				isRunning = true;
				while (runIt && (currentCount <= count)) {
					voteRequestService.runAllMethods();
					currentCount++;
					int sleetInterval = 10000 + random.nextInt(delayLimit);
					System.out.println("\nsleetInterval : " + sleetInterval + "\n");
					try {
						Thread.sleep(sleetInterval);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
	}

}
