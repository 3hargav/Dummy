package com.incture.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.incture.DO.CartUserDO;
import com.incture.DTO.CartUserDTO;

@Repository
public class CartUserDAO extends BaseDao{

	public void additems(CartUserDTO cartuser) {
		CartUserDO cartUserDO=importToDB(cartuser);
		Session session= getSession();
		session.save(cartUserDO);
	}

	private CartUserDO importToDB(CartUserDTO cartuser) {
		CartUserDO cartUserDO = new CartUserDO();
		cartUserDO.setUserId(cartuser.getUserId());
		cartUserDO.setProductDO(cartuser.getProduct());
		cartUserDO.setPrice(cartuser.getPrice());
		cartUserDO.setQuantity(cartuser.getQuantity());
		cartUserDO.setPlaced(Boolean.FALSE);
		
		return cartUserDO;
	}

	@SuppressWarnings("unchecked")
	public List<CartUserDO> getAllData(int userId) {
		Session session= getSession();
		List<CartUserDO> cartList = session.createQuery("select s from CartUserDO s where s.userId='"+userId+"' and s.isPlaced="+Boolean.FALSE+"").list();
		return cartList;
	}

	public Object ExportFromDB(CartUserDO item) {
		
		CartUserDTO cartUserDTO = new CartUserDTO();
		cartUserDTO.setUserId(item.getUserId());
		cartUserDTO.setCartItemId(item.getCartItemId());
		cartUserDTO.setProduct(item.getProductDO());
		cartUserDTO.setQuantity(item.getQuantity());
		cartUserDTO.setPrice(item.getPrice());
		
		return cartUserDTO;
	}

	public void deleteById(int cartItemId) {
		Session session= getSession();
		session.createQuery("delete from CartUserDO r where r.cartItemId="+cartItemId+"").executeUpdate();
		session.flush();
	}

	public void saveToDB(CartUserDO item) {
		Session session= getSession();
		session.save(item);
	}

	@SuppressWarnings("unchecked")
	public CartUserDO getCartitemById(Integer cartItemId) {
		Session session= getSession();
		Query<CartUserDO> query = session.createQuery("select r from CartUserDO r where r.cartItemId="+cartItemId+"");
		CartUserDO itemY = query.uniqueResult();
		return itemY;
	}


}