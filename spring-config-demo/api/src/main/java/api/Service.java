package api;

import entity.User;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *Create by yangwenfu on 2018/2/2
 */
@RequestMapping("/api-server")
public interface Service {
	@RequestMapping(value = "/say", method = RequestMethod.GET)
	void say();
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	User save(@RequestBody User user);
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	String get(@RequestParam("mobile") String mobile);

}
