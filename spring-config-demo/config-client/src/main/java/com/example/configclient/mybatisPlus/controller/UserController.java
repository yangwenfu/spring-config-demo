package com.example.configclient.mybatisPlus.controller;

import java.util.List;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.example.configclient.mybatisPlus.entity.User;
import com.example.configclient.mybatisPlus.service.IUserService;
import com.example.configclient.mybatisPlus.service.impl.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *Create by yangwenfu on 2018/1/31
 */
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private IUserService userService;

	@GetMapping("/save")
	public User save(){
		User user = new User();
		user.setName("yang");
		user.setAge(20);
		user.setMobile("15558127163");
		userService.insert(user);
		//疑问  为什么不用全字段插入就不成功  原因找到:// // 注意！这里需要标记为填充字段 否则不会起作用
		//	@TableField(value="create_time",fill = FieldFill.INSERT) 在实体类的字段上加上 默认不开启

		userService.insertAllColumn(user);
		return user;
	}
	@GetMapping("/delete")
	public void delete(){
		userService.deleteAll();
	}

	@GetMapping("/select")
	public List<User> select(){
		List<User> users = userService.selectListBySQL();
		System.out.println(users);
		userService.selectList(new EntityWrapper<User>().eq("name","zhangsan"));

		// 查询姓名为‘张三’的所有用户记录
		List<User> userList1 = userService.selectList(
				new EntityWrapper<User>().eq("name", "张三")
		);
		// 分页查询 10 条姓名为‘张三’的用户记录
		List<User> userList = userService.selectPage(
				new Page<User>(1, 10),
				new EntityWrapper<User>().eq("name", "张三")
		).getRecords();

		// 分页查询 10 条姓名为‘张三’、性别为男，且年龄在18至50之间的用户记录
		List<User> userList2 = userService.selectPage(
				new Page<User>(1, 10),
				new EntityWrapper<User>().eq("name", "张三")
						.eq("sex", 0)
						.between("age", "18", "50")
		).getRecords();

		return users;

	}
	@GetMapping("/page")
	public Page<User> page(@RequestParam("start") int start, @RequestParam("limit") int limit){
		//由于公用的分页只有分页没有筛选  所以我们自定义查询  start 是第几页 如果是0 就是第一页
		Page page=new Page(start,limit);
		Page userPage = userService.selectUserPage(page, 10);
		return userPage;

	}
}
