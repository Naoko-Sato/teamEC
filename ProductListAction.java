package com.internousdev.olive.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.olive.dao.MCategoryDAO;
import com.internousdev.olive.dao.ProductInfoDAO;
import com.internousdev.olive.dto.MCategoryDTO;
import com.internousdev.olive.dto.ProductInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class ProductListAction extends ActionSupport implements SessionAware{

	private Map<String, Object> session;
	private List<ProductInfoDTO> productInfoDTOList;

	public String execute(){

		//全ての商品情報を取得
		ProductInfoDAO productInfoDAO = new ProductInfoDAO();
		productInfoDTOList = productInfoDAO.getProductInfoListAll();

		//カテゴリーリストがsessionに保持されていない場合、画面上部にカテゴリーが表示されない為
		//カテゴリーリストをsessionに保持する。
		if(!session.containsKey("mCategoryDTOList")){
			List<MCategoryDTO> mCategoryDTOList = new ArrayList<MCategoryDTO>();
			MCategoryDAO mCategoryDAO = new MCategoryDAO();

			mCategoryDTOList = mCategoryDAO.getMcategoryList();

			session.put("mCategoryDTOList", mCategoryDTOList);
		}

		return SUCCESS;

	}

	public List<ProductInfoDTO> getProductInfoDTOList(){
		return productInfoDTOList;
	}

	public void setProductInfoDTOList(List<ProductInfoDTO> productInfoDTOList){
		this.productInfoDTOList = productInfoDTOList;
	}

	public Map<String, Object> getSesson(){
		return session;
	}

	@Override
	public void setSession(Map<String, Object> session){
		this.session = session;
	}

}

