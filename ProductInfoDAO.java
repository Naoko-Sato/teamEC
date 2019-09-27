package com.internousdev.olive.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.internousdev.olive.dto.ProductInfoDTO;
import com.internousdev.olive.util.DBConnector;

public class ProductInfoDAO {

	//全ての商品情報を取得する
	public  List<ProductInfoDTO> getProductInfoListAll(){

		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();

		List<ProductInfoDTO> productInfoDTOList = new ArrayList<ProductInfoDTO>();

		String sql = "select * from product_info";

		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();

			while(resultSet.next()){

				ProductInfoDTO productInfoDTO = new ProductInfoDTO();

				productInfoDTO.setId(resultSet.getInt("id"));
				productInfoDTO.setProductId(resultSet.getInt("product_id"));
				productInfoDTO.setProductName(resultSet.getString("product_name"));
				productInfoDTO.setProductNameKana(resultSet.getString("product_name_kana"));
				productInfoDTO.setProductDescription(resultSet.getString("product_description"));
				productInfoDTO.setCategoryId(resultSet.getInt("category_id"));
				productInfoDTO.setPrice(resultSet.getInt("price"));
				productInfoDTO.setImageFilePath(resultSet.getString("image_file_path"));
				productInfoDTO.setImageFileName(resultSet.getString("image_file_name"));
				productInfoDTO.setReleaseDate(resultSet.getDate("release_date"));
				productInfoDTO.setReleaseCompany(resultSet.getString("release_company"));

				productInfoDTOList.add(productInfoDTO);

			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				connection.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}

		return productInfoDTOList;
	}

	//商品IDを条件として商品情報を取り出す（商品一覧から商品詳細に遷移する際に使用）
	public ProductInfoDTO getProductInfoByProductId(int productId){

		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();
		ProductInfoDTO productInfoDTO = new ProductInfoDTO();

		String sql = "select * from product_info where product_id=?";

		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, productId);
			ResultSet resultSet = preparedStatement.executeQuery();

			while(resultSet.next()){

				productInfoDTO.setId(resultSet.getInt("id"));
				productInfoDTO.setProductId(resultSet.getInt("product_id"));
				productInfoDTO.setProductName(resultSet.getString("product_name"));
				productInfoDTO.setProductNameKana(resultSet.getString("product_name_kana"));
				productInfoDTO.setProductDescription(resultSet.getString("product_description"));
				productInfoDTO.setCategoryId(resultSet.getInt("category_id"));
				productInfoDTO.setPrice(resultSet.getInt("price"));
				productInfoDTO.setImageFilePath(resultSet.getString("image_file_path"));
				productInfoDTO.setImageFileName(resultSet.getString("image_file_name"));
				productInfoDTO.setReleaseDate(resultSet.getDate("release_date"));
				productInfoDTO.setReleaseCompany(resultSet.getString("release_company"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				connection.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}

		return productInfoDTO;
	}

	//関連商品を取得する
	public List<ProductInfoDTO> getRelatedProductList(int categoryId, int productId, int limitOffset, int limitRowCount){

		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();
		List<ProductInfoDTO> productInfoDTOList = new ArrayList<ProductInfoDTO>();

		String sql = "select * from product_info where category_id=? and product_id not in(?) order by rand() limit ?,?";

		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, categoryId);
			preparedStatement.setInt(2, productId);
			preparedStatement.setInt(3, limitOffset);
			preparedStatement.setInt(4, limitRowCount);

			ResultSet resultSet = preparedStatement.executeQuery();

			while(resultSet.next()){

				ProductInfoDTO productInfoDTO = new ProductInfoDTO();

				productInfoDTO.setId(resultSet.getInt("id"));
				productInfoDTO.setProductId(resultSet.getInt("product_id"));
				productInfoDTO.setProductName(resultSet.getString("product_name"));
				productInfoDTO.setProductNameKana(resultSet.getString("product_name_kana"));
				productInfoDTO.setProductDescription(resultSet.getString("product_description"));
				productInfoDTO.setCategoryId(resultSet.getInt("category_id"));
				productInfoDTO.setPrice(resultSet.getInt("price"));
				productInfoDTO.setImageFilePath(resultSet.getString("image_file_path"));
				productInfoDTO.setImageFileName(resultSet.getString("image_file_name"));
				productInfoDTO.setReleaseDate(resultSet.getDate("release_date"));
				productInfoDTO.setReleaseCompany(resultSet.getString("release_company"));

				productInfoDTOList.add(productInfoDTO);
			}

		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				connection.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}

		return productInfoDTOList;
	}

	//キーワードを条件に商品情報を取得する
	public List<ProductInfoDTO> getProductInfoListByKeyword(String[] keywordsList){

		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();
		List<ProductInfoDTO> productInfoDTOList = new ArrayList<ProductInfoDTO>();

		String sql = "select * from product_info";

		boolean initializeFlag = true; //複数のキーワードを条件にするための条件分岐用フラグ

		if(!keywordsList[0].equals("")){
			for(String keyword : keywordsList){
				if(initializeFlag){
					sql += " where (product_name like '%" + keyword + "%' or product_name_kana like '%" + keyword + "%')";
					initializeFlag = false;
				}else{
					sql += " or (product_name like '%" + keyword + "%' or product_name_kana like '%" + keyword + "%')";
				}
			}
		}

		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();

			while(resultSet.next()){

				ProductInfoDTO productInfoDTO = new ProductInfoDTO();

				productInfoDTO.setId(resultSet.getInt("id"));
				productInfoDTO.setProductId(resultSet.getInt("product_id"));
				productInfoDTO.setProductName(resultSet.getString("product_name"));
				productInfoDTO.setProductNameKana(resultSet.getString("product_name_kana"));
				productInfoDTO.setProductDescription(resultSet.getString("product_description"));
				productInfoDTO.setCategoryId(resultSet.getInt("category_id"));
				productInfoDTO.setPrice(resultSet.getInt("price"));
				productInfoDTO.setImageFilePath(resultSet.getString("image_file_path"));
				productInfoDTO.setImageFileName(resultSet.getString("image_file_name"));
				productInfoDTO.setReleaseDate(resultSet.getDate("release_date"));
				productInfoDTO.setReleaseCompany(resultSet.getString("release_company"));

				productInfoDTOList.add(productInfoDTO);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				connection.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return productInfoDTOList;
	}

	//カテゴリとキーワードを条件に商品を検索する
	public List<ProductInfoDTO> getProductInfoListByCategoryAndKeyword(int categoryId, String[] keywordsList){

		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();
		List<ProductInfoDTO> productInfoDTOList = new ArrayList<ProductInfoDTO>();

		String sql = "select * from product_info where category_id =" + categoryId;

		boolean initializeFlag = true; //複数のキーワードを条件にするための条件分岐用フラグ

		if(!keywordsList[0].equals("")){
			for(String keyword : keywordsList){
				if(initializeFlag){
					sql += " and((product_name like '%" + keyword + "%' or product_name_kana like '%" + keyword + "%')";
					initializeFlag = false;
				}else{
					sql += " or (product_name like '%" + keyword + "%' or product_name_kana like '%" + keyword + "%')";
				}
			}
			sql += ")";
		}

		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();

			while(resultSet.next()){

				ProductInfoDTO productInfoDTO = new ProductInfoDTO();

				productInfoDTO.setId(resultSet.getInt("id"));
				productInfoDTO.setProductId(resultSet.getInt("product_id"));
				productInfoDTO.setProductName(resultSet.getString("product_name"));
				productInfoDTO.setProductNameKana(resultSet.getString("product_name_kana"));
				productInfoDTO.setProductDescription(resultSet.getString("product_description"));
				productInfoDTO.setCategoryId(resultSet.getInt("category_id"));
				productInfoDTO.setPrice(resultSet.getInt("price"));
				productInfoDTO.setImageFilePath(resultSet.getString("image_file_path"));
				productInfoDTO.setImageFileName(resultSet.getString("image_file_name"));
				productInfoDTO.setReleaseDate(resultSet.getDate("release_date"));
				productInfoDTO.setReleaseCompany(resultSet.getString("release_company"));

				productInfoDTOList.add(productInfoDTO);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				connection.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return productInfoDTOList;
	}

}