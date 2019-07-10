package org.shop.service;

import java.util.List;

import org.shop.pojo.Hw;

public interface HwService {
	List  sp(Hw hw);
	int   spxj(Hw hw);
	int  xjrk(Hw hw);
	int spxjReject(int id);
	Hw  dy(int id);
	int ckkk(Hw hw);
	Hw dys(String name);
	List spCgHistory(Hw hw);//商品采购记录

}
