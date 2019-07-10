package org.shop.dao;

import java.util.List;

import org.shop.pojo.Oder;

public interface OderDao {
	int  ck(Oder oder);//新建出库
	int cksh(Oder oder);//同意出库
	int jj(int id);//拒绝出库
	List  cc(int sh);//审查列表
	List  hws(String hw);
	Oder hwss(int id);
	List spXsHistory(Oder oder);//商品销售记录
}
