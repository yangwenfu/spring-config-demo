package com.example.configclient.poi;

import java.util.ArrayList;
import java.util.List;

/**
 *Create by yangwenfu on 2018/2/2
 */
public class BB {
	public static void main(String[] args) {


		List list1 = new BB().find1().find(new ArrayList());
		System.out.println(list1);
	}



	public AA find1(){
		return new AA() {
			@Override
			public String add(String s) {
				return null;
			}

			@Override
			public List find(List a) {
				a.add("a");
				a.add("b");
				return a;
			}
		};
	}
}
