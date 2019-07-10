package org.shop.dao;

import java.util.List;

import org.shop.pojo.Hw;

public interface HwDao {
	List sp(Hw hw);//商品列表
	int spxj(Hw hw);//商品新建同意
	int spxjReject(int id);//商品新建拒绝
	int xjrk(Hw hw);//新建入库
	int ckkk(Hw hw);//出库后更新剩余数量和时间
	Hw dy(int id);
	Hw dys(String name);
	List spCgHistory(Hw hw);//商品采购记录
//	List spXsHistory(Hw hw);//商品销售记录
}
