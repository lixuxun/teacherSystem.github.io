package org.shop.service;

import java.util.List;

import org.shop.pojo.Oder;

public interface OderService {
	int  ck(Oder oder);
	int cksh(Oder oder);
	int jj(int id);
	List  cc(int sh);
	List  hws(String hw);
	Oder hwss(int id);
	List spXsHistory(Oder oder);//商品销售记录

}
