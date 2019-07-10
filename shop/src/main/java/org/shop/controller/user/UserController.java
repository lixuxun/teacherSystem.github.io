package org.shop.controller.user;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.shop.pojo.Hw;
import org.shop.pojo.Oder;
import org.shop.pojo.User;

import org.shop.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class UserController {

	@Autowired
	private UserService u;

	@Autowired
	private HwService h;

	@Autowired
	private OderService s;

	// login
	@RequestMapping("tzlogin")
	public String tzlogin() {
		return "login";
	}

	// 登录验证
	@ResponseBody
	@RequestMapping("login")
	public String login(User user, HttpSession session, String requestDate) {
		System.out.println("requestDate："+requestDate +" "+requestDate.getClass());
		Map map = new HashMap();
		JSONObject requestJson = JSONObject.fromObject(requestDate);
		map.put("name", requestJson.getString("name"));
		map.put("password", requestJson.getString("password"));
		user.setName(requestJson.getString("name"));
		user.setPassword(requestJson.getString("password"));
		User user2 = u.login(user);
		session.setAttribute("idPass", user2.getIdPass());
		session.setAttribute("id", user2.getId());
		if (user2 == null) {
			Map reMap = new HashMap();
			reMap.put("succ", "false");
			JSONObject jsonObject = JSONObject.fromObject(reMap);
			return jsonObject.toString();
		} else {
			System.out.println("user2" + user2);
			session.setAttribute("name", user.getName());
			session.setAttribute("id", user2.getId());
			session.setAttribute("position", user2.getPosition());
			Map reMap = new HashMap();
			reMap.put("succ", "true");
			JSONObject jsonObject = JSONObject.fromObject(reMap);
			return jsonObject.toString();
		}

	}

	//登陆成功进入用户页面
	@RequestMapping("sy")
	public String denglu(HttpSession session, String idPass, Model model, String name, User user) {
		int qx = (int) session.getAttribute("position");
		// model.addAttribute("list", u.findall());
		System.out.println(qx);
		System.out.println(idPass);
		System.out.println(user);
		if (qx == 0) {
			model.addAttribute("list", u.findall(user));
			return "gly/yh";
		} else if (qx == 1) {
			model.addAttribute("list", u.findall(user));
			return "gly/yh";
		} else if (qx == 2) {
			return "redirect:tzckxs";
		} else if (qx == 3) {
			return "redirect:tzkccg";
		}
		return "login";
	}


	//登陆时注册界面，默认职位为未分配和身份未验证
	@RequestMapping("touser")
	public String touser(User user) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH");
		String time = df.format(System.currentTimeMillis());
		user.setDate(df.parse(time));
		user.setPosition(4);
		user.setIdPass(0);
		u.touser(user);
		System.out.println(user);
		return "redirect:tzlogin";
	}

	//在所有用户界面根据id删除用户
	@RequestMapping("delete")
	public String delete(int id) {
		System.out.println(id);
		u.delete(id);
		return "redirect:sy";
	}

	//进入添加用户界面
	@RequestMapping("tjyh")
	public String tzyh() {
		return "gly/upyh";
	}

	//进行用户插入
	@RequestMapping("insert")
	public String insert(User user) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH");
		String time = df.format(System.currentTimeMillis());
		user.setDate(df.parse(time));
		u.touser(user);
		return "redirect:sy";
	}

	//在所有用户界面根据id进行用户更新,进入user更新界面
	@RequestMapping("tzup")
	public String tzup(int id, Model mode) {
		System.out.println(id);
		User user = u.findid(id);
		mode.addAttribute("user", user);
		return "gly/user";
	}

	//根据登陆时的id进行账号设置，进入user更新界面
	@RequestMapping("tzzup")
	public String tzzup(Model model,HttpSession session) {
		int  id=(int) session.getAttribute("id");
		User user = u.findid(id);
		model.addAttribute("user", user);
		return "gly/user";
	}

	//提交用户更新信息
	@RequestMapping("upyh")
	public String upyh(User user) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH");
		String time = df.format(System.currentTimeMillis());
		u.userup(user);
		return "redirect:sy";
	}

	//跳转货物
	@RequestMapping("tzhw")
	public String tzhw(Model model, Hw hw) {
		hw.setSj(1);
		hw.setSh(1);
		model.addAttribute("list", h.sp(hw));
		System.out.println(h.sp(hw).getClass());
		System.out.println(h.sp(hw));
		return "gly/hw";
	}


    //历史采购记录
    @RequestMapping("tzCgHistory")
    public String tzCgHistory(Model model, Hw hw) {
        hw.setSh(1);
        model.addAttribute("list", h.spCgHistory(hw));
        System.out.println(h.spCgHistory(hw));
        return "gly/cghistory";
    }


    //历史销售记录
    @RequestMapping("tzXsHistory")
    public String tzXsHistory(Model model, Oder oder) {
		oder.setSh(1);
        List list = s.spXsHistory(oder);
        model.addAttribute("list", list);
        return "gly/xshistory";
    }

	//货物下架
	@RequestMapping("xj")
	public String spxj(int id, Hw hw) throws ParseException {
		hw.setSh(1);
		hw.setSj(0);
		hw.setId(id);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH");
		String time = df.format(System.currentTimeMillis());
		hw.setDate(df.parse(time));
		h.spxj(hw);
		return "redirect:tzhw";
	}

	//跳转库存
	@RequestMapping("tzkc")
	public String tzkc(Model model, Hw hw) {
		hw.setSj(0);
		hw.setSh(1);
		model.addAttribute("list", h.sp(hw));
		return "gly/kc";
	}

	//货物上架
	@RequestMapping("sj")
	public String spsj(int id, Hw hw,HttpSession session) throws ParseException {
		hw.setSh(1);
		hw.setSj(1);
		hw.setId(id);
        String zrr = (String) session.getAttribute("name");
		hw.setName(zrr);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH");
		String time = df.format(System.currentTimeMillis());
		hw.setDate(df.parse(time));
		h.spxj(hw);
		return "redirect:tzkc";
	}

	//跳转新建入库单
	@RequestMapping("tzrk")
	public String tzrk() {
		return "gly/rkd";
	}


	//提交新建入库单数据
	@RequestMapping("rk")
	public String zjrk(Hw hw, HttpSession session) throws ParseException {
		System.out.println("rk"+hw);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH");
		String time = df.format(System.currentTimeMillis());
		hw.setDate(df.parse(time));
		hw.setSj(0);
		hw.setSh(0);
		String zrr = (String) session.getAttribute("name");
		hw.setZrr(zrr);
		System.out.println("rk"+hw);
		h.xjrk(hw);
		return "redirect:tzrk";
	}

	//跳转入库审核
	@RequestMapping("tzrksh")
	public String tzrush(Model model, Hw hw) {
		//通过Sh和Sj为0标记查找所有待审核入单货物
		hw.setSh(0);
		hw.setSj(0);
		model.addAttribute("list", h.sp(hw));
		return "gly/rksh";
	}

	//入库审核通过
	@RequestMapping("rks")
	public String rk(int id, Hw hw) throws ParseException {
		hw.setSj(0);
		hw.setSh(1);
		hw.setId(id);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH");
		String time = df.format(System.currentTimeMillis());
		hw.setDate(df.parse(time));
		h.spxj(hw);//通过调用spxj
		return "redirect:tzrksh";
	}

	//入库审核拒绝
	@RequestMapping("rkjj")
	public String rkReject(int id){
		System.out.println(id);
		h.spxjReject(id);
		return "redirect:tzrksh";
	}

	//跳转新建出库页面
	@RequestMapping("tzck")
	public String tzck(Model model, Hw hw) {
		//通过Sh和Sj为1标记查找所有待审核入单货
		hw.setSh(1);
		hw.setSj(1);
		List list = h.sp(hw);
		model.addAttribute("list", list);
		return "gly/ckd";
	}

	//根据商品id出库，跳转出单页面
	@RequestMapping("ckid")
	public String ckid(int id, Model model, HttpSession session) {
		Hw list = h.dy(id);
		int jj = list.getNumber();
		session.setAttribute("jj", jj);
		session.setAttribute("gg", list.getMoney());
		model.addAttribute("list", list);
		return "gly/ckl";
	}

	//提交出单页面数据
	@RequestMapping("ckl")
	public String ckl(Oder oder, HttpSession session, Hw hw) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH");
		String time = df.format(System.currentTimeMillis());
		oder.setDate(df.parse(time));
		oder.setSh(0);//默认为审核状态
		int g = oder.getJg() - oder.getMoney();
		System.out.println(g);
		oder.setLr(oder.getSl() * g);
		String name = (String) session.getAttribute("name");
		oder.setZrr(name);
		s.ck(oder);//在sh表添加待审核出库单
		return "redirect:tzck";
	}

	@RequestMapping("tzcksh")
	public String cksss(Model model) {
		List list = s.cc(0);
		model.addAttribute("list", list);
		return "gly/cksh";

	}

	//出库审核通过
	@RequestMapping("cksh")
	public String cksh(int id, Oder oder, Hw hw, HttpSession session) throws ParseException {
		System.out.println(id);
		System.out.println(oder);
		System.out.println(hw);
		oder.setSh(1);
		//sh.setHw(s.hwss(id).getHw());
		s.cksh(oder);//更新审核通过状态
		//Sh ssSh = new Sh();
		String name = s.hwss(id).getHw();
		//List list = s.hws(name);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH");
		String time = df.format(System.currentTimeMillis());
		hw.setDate(df.parse(time));
		Hw hsHw = new Hw();
		hsHw = h.dys(name);
		int ye = hsHw.getNumber();
		int l = ye - s.hwss(id).getSl();
		hw.setNumber(l);
		hw.setName(name);
		h.ckkk(hw);//更新货物数量
		return "redirect:tzcksh";
	}

	//出库审核拒绝
	@RequestMapping("sb")
	public String sb(int id, HttpSession session) {
		s.jj(id);
		return "redirect:tzcksh";
	}


	//********************************采购******************************************
	@RequestMapping("tzkccg")
	public String tzkccg(Model model, Hw hw) {
		hw.setSj(0);
		hw.setSh(1);
		model.addAttribute("list", h.sp(hw));
		return "cg/kc";
	}
	@RequestMapping("tzrkcg")
	public String tzrkcg() {
		return "cg/rkd";
	}

	@RequestMapping("rkcg")
	public String zjrkcg(Hw hw, HttpSession session) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH");
		String time = df.format(System.currentTimeMillis());
		hw.setDate(df.parse(time));
		hw.setSj(0);
		hw.setSh(0);
		String zrr = (String) session.getAttribute("name");
		hw.setZrr(zrr);
		h.xjrk(hw);
		return "redirect:tzrkcg";
	}

	@RequestMapping("tzzupss")
	public String tzzupss(Model model,HttpSession session) {
		int  id=(int) session.getAttribute("id");
		User user = u.findid(id);
		model.addAttribute("user", user);
		return "cg/user";
	}

	//*************************************销售**************************************
	@RequestMapping("tzckxs")
	public String tzckxs(Model model, Hw hw) {
		System.out.println("tzckxs");
		hw.setSh(1);
		hw.setSj(1);
		List list = h.sp(hw);
		model.addAttribute("list", list);
		return "xs/ckd";
	}
	@RequestMapping("ckidxs")
	public String ckidxs(int id, Model model, HttpSession session) {
		Hw list = h.dy(id);
		int jj = list.getNumber();
		session.setAttribute("jj", jj);
		session.setAttribute("gg", list.getMoney());
		model.addAttribute("list", list);
		return "xs/ckl";
	}

	@RequestMapping("cklxs")
	public String cklxs(Oder oder, HttpSession session, Hw hw) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH");
		String time = df.format(System.currentTimeMillis());
		oder.setDate(df.parse(time));
		oder.setSh(0);
		int g = oder.getJg() - oder.getMoney();
		System.out.println(g);
		oder.setLr(oder.getSl() * g);
		String name = (String) session.getAttribute("name");
		oder.setZrr(name);
		s.ck(oder);
		return "redirect:tzckxs";
	}


	@RequestMapping("tzzups")
	public String tzzups(Model model,HttpSession session) {
		int  id=(int) session.getAttribute("id");
		User user = u.findid(id);
		model.addAttribute("user", user);
		return "xs/user";
	}



	@RequestMapping("upyhs")
	public String upyhs(User user) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH");
		String time = df.format(System.currentTimeMillis());
		user.setPosition(0);
		user.setIdPass(0);
		user.setDate(df.parse(time));
		u.userup(user);
		return "redirect:tzlogin";
	}
}
