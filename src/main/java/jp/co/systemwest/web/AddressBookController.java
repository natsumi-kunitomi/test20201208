package jp.co.systemwest.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jp.co.systemwest.domain.dao.edit.UserInsertDao;
import jp.co.systemwest.domain.dao.edit.UserSearchDao;
import jp.co.systemwest.domain.dao.edit.UserUpdateDao;
import jp.co.systemwest.domain.dao.search.UserDeleteDao;
import jp.co.systemwest.domain.dao.search.UserSelectDao;
import jp.co.systemwest.domain.dto.SearchConditionDto;
import jp.co.systemwest.domain.entity.User;
import jp.co.systemwest.web.form.AddressListForm;
import jp.co.systemwest.web.form.UserRegistForm;

@Controller

public class AddressBookController {

	@Autowired

	HttpSession session;

	// 変更用フラグ
	public Boolean updateFlg = false;
	// 登録用フラグ
	public Boolean insertFlg = false;

	@RequestMapping("/addressSearchInitView")
	public ModelAndView addressSearchInitView(SearchConditionDto searchConditionDto, ModelAndView mav) {

		mav.setViewName("/view/addressSearch");

		return mav;
	}

	@RequestMapping(value="/addressSearchController",params="returnView")
	public ModelAndView addressSearchReturnView(SearchConditionDto searchConditionDto, ModelAndView mav) {

		searchConditionDto.setMei("");
		searchConditionDto.setSei("");
		searchConditionDto.setPhoneNumber("");

		mav.setViewName("/view/addressSearch");

		return mav;
	}

	@RequestMapping(value="/addressSearchController",params="search")
	public ModelAndView addressSearch(@ModelAttribute("searchConditionDto")
		@Validated SearchConditionDto searchConditionDto,BindingResult result,
			ModelAndView mav) {

		if(result.hasErrors()) {
			mav.setViewName("/view/addressSearch");

		}else {
			SearchConditionDto scd  = new SearchConditionDto();

			scd.setSei(searchConditionDto.getSei());
			scd.setMei(searchConditionDto.getMei());
			String str = searchConditionDto.getPhoneNumber();

			//電話番号のハイフンを検索
			String str2 = "-";
			if(str != null) {
				int result2 = str.indexOf(str2);
				if(result2 != -1){
				 	String[] phone = str.split("-");
				 	str = phone[0]+phone[1]+phone[2];
				}
			}
			scd.setPhoneNumber(str);

			//セッションセット
			session.setAttribute("scd", scd);

			mav.setViewName("forward:/addressListController?list");
		}
		return mav;
	}

	@RequestMapping(value="/addressSearchController",params="newEntry")
	public ModelAndView addressSearchNewEntry(UserRegistForm userRegistForm, ModelAndView mav) {

		mav.addObject("searchFlg", true);

		session.setAttribute("transitionFlg", mav);

		mav.setViewName("forward:/userRegistController?createView");

		return mav;
	}

	@RequestMapping(value="/addressListController",params="list")
	public ModelAndView addressList(AddressListForm addressListForm, ModelAndView mav) {

		SearchConditionDto scd  = new SearchConditionDto();

		//セッションゲット
		scd = (SearchConditionDto) session.getAttribute("scd");

		UserSearchDao usd = new UserSearchDao();

		List <User> userlist = new ArrayList<User>();

		userlist = usd.searchUsers(scd);

		mav.addObject("user",userlist);

		mav.setViewName("/view/addressList");

		return mav;
	}

	@RequestMapping(value="/addressListController",params="newEntry")
	public ModelAndView addressListNewEntry(AddressListForm addressListForm, ModelAndView mav) {

		mav.addObject("listFlg", true);

		session.setAttribute("transitionFlg", mav);

		mav.setViewName("forward:/userRegistController?createView");

		return mav;
	}

	@RequestMapping(value="/addressListController",params="updateView")
	public ModelAndView userRegistUpdateView(UserRegistForm userRegistForm, ModelAndView mav) {

		mav.addObject("listFlg", true);

		UserSelectDao usd = new UserSelectDao();
		User user = new User();

		user = usd.getUser(Integer.parseInt(userRegistForm.getRegistNo()));

		userRegistForm.setSei(user.getSei());
		userRegistForm.setMei(user.getMei());
		userRegistForm.setSeiKana(user.getSeiKana());
		userRegistForm.setMeiKana(user.getMeiKana());
		userRegistForm.setPostalCode(user.getPostalCode());
		userRegistForm.setAddress1(user.getAddress1());
		userRegistForm.setAddress2(user.getAddress2());
		userRegistForm.setPhoneNumber(user.getPhoneNumber());
		userRegistForm.setMailAddress(user.getMailAddress());
		userRegistForm.setRegistNo(user.getRegistNo().toString());

		this.updateFlg = true;
		this.insertFlg = false;

		session.setAttribute("transitionFlg", mav);
		mav.setViewName("/view/userRegist");

		return mav;
	}

	@RequestMapping(value="/addressListController",params="delete")
	public ModelAndView addressListDelete(AddressListForm addressListForm, ModelAndView mav){
		AddressListForm addressList = new AddressListForm();

		addressList.setRegistNo(addressListForm.getRegistNo());

		UserDeleteDao udd = new UserDeleteDao();

		udd.deleteUser(Integer.parseInt(addressList.getRegistNo()));

		mav.setViewName("redirect:/addressListController?list");

		return mav;
	}

	@RequestMapping(value="/userRegistController",params="createView")
	public ModelAndView userRegistCreateView(UserRegistForm userRegistForm , ModelAndView mav) {

		userRegistForm.setMei("");
		userRegistForm.setSei("");
		userRegistForm.setPhoneNumber("");

		this.insertFlg = true;
		this.updateFlg = false;

		mav.setViewName("view/userRegist");

		return mav;
	}

	@RequestMapping(value="/userRegistController",params="returnView")
	public ModelAndView userRegistReturnView(UserRegistForm userRegistForm, ModelAndView mav) {

		mav.setViewName("forward:/addressSearchController?returnView");

		return mav;
	}

	@RequestMapping(value="/userRegistController",params="regist", method= {RequestMethod.POST})
	public ModelAndView userRegist(@ModelAttribute("userRegistForm")@Validated UserRegistForm userRegistForm,
			BindingResult result,
			ModelAndView mav) {

		if(result.hasErrors()) {
			mav = (ModelAndView) session.getAttribute("transitionFlg");

			mav.setViewName("/view/UserRegist");

		}else if(this.updateFlg == true) {
			User user = new User();

			user.setSei(userRegistForm.getSei());
			user.setMei(userRegistForm.getMei());
			user.setSeiKana(userRegistForm.getSeiKana());
			user.setMeiKana(userRegistForm.getMeiKana());
			user.setPostalCode(userRegistForm.getPostalCode());
			user.setAddress1(userRegistForm.getAddress1());
			user.setAddress2(userRegistForm.getAddress2());
			user.setPhoneNumber(userRegistForm.getPhoneNumber());
			user.setMailAddress(userRegistForm.getMailAddress());
			user.setRegistNo(Integer.parseInt(userRegistForm.getRegistNo()));

			UserUpdateDao uud = new UserUpdateDao();
			uud.updateUser(user);

			this.updateFlg = false;
			this.insertFlg = false;

			mav.setViewName("forward:/addressSearchController?returnView");

		}else if(this.insertFlg == true){
			User user = new User();

			user.setSei(userRegistForm.getSei());
			user.setMei(userRegistForm.getMei());
			user.setSeiKana(userRegistForm.getSeiKana());
			user.setMeiKana(userRegistForm.getMeiKana());
			user.setPostalCode(userRegistForm.getPostalCode());
			user.setAddress1(userRegistForm.getAddress1());
			user.setAddress2(userRegistForm.getAddress2());
			user.setPhoneNumber(userRegistForm.getPhoneNumber());
			user.setMailAddress(userRegistForm.getMailAddress());

			UserInsertDao uid = new UserInsertDao();
			uid.insertUser(user);

			this.updateFlg = false;
			this.insertFlg = false;

			mav.setViewName("forward:/addressSearchController?returnView");
		}

		return mav;
	}

}
