package org.shop.service.impl;

import java.util.List;

import org.shop.dao.OderDao;
import org.shop.pojo.Oder;
import org.shop.service.OderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OderServiceImpl implements OderService {

	@Autowired
	private OderDao sd;

	@Override
	public int ck(Oder oder) {
		// TODO Auto-generated method stub
		return sd.ck(oder);
	}

	@Override
	public int cksh(Oder oder) {
		// TODO Auto-generated method stub
		return sd.cksh(oder);
	}

	@Override
	public int jj(int id) {
		// TODO Auto-generated method stub
		return sd.jj(id);
	}

	@Override
	public List cc(int sh) {
		// TODO Auto-generated method stub
		return sd.cc(sh);
	}

	@Override
	public List  hws(String hw) {
		// TODO Auto-generated method stub
		return sd.hws(hw);
	}

	@Override
	public Oder hwss(int id) {
		// TODO Auto-generated method stub
		return sd.hwss(id);
	}

	@Override
	public List spXsHistory(Oder oder) {
		return sd.spXsHistory(oder);
	}


}
