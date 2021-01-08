package com.incture.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.incture.DO.CartOrder;
import com.incture.DO.OrderDO;
import com.incture.DTO.OrderDTO;

@Repository
public class OrderDAO extends BaseDao {

	public void addOrderDetails(OrderDO order) {
		Session session = getSession();
		session.save(order);
	}

	@SuppressWarnings("unchecked")
	public List<OrderDO> getAllOrderes(int userId) {
		Session session= getSession();
		List<OrderDO> orderList = session.createQuery("select s from OrderDO s where s.userOrderId="+userId+"").list();
		return orderList;
	}

	public OrderDTO ExportFromDB(OrderDO item) {
		
		OrderDTO orderDTO = new OrderDTO();

		orderDTO.setOrderId(item.getOrderId());
		orderDTO.setTotalCartValue(item.getTotalCartValue());
		orderDTO.setOrderStatus(item.getOrderStatus());
		orderDTO.setAddressOrderId(item.getAddressOrderId());
		orderDTO.setPaymentMethod(item.getPaymentMethod());
		orderDTO.setUserOrderId(item.getUserOrderId());
		orderDTO.setPaymentMethod(item.getPaymentMethod());
		orderDTO.setDateOrdered(item.getDateOrdered());

		return orderDTO;
	}

	@SuppressWarnings("unchecked")
	public List<OrderDO> getAllData() {
		Session session= getSession();
		List<OrderDO> orderList = session.createQuery("from OrderDO").list();
		return orderList;
	}

	@SuppressWarnings("unchecked")
	public OrderDO getOrderId(int orderId) {
		Session session= getSession();
		Query<OrderDO> query = session.createQuery("select r from OrderDO r where r.orderId="+orderId+"");
		OrderDO orderY = query.uniqueResult();
		return orderY;
	}

	public void saveToDB(OrderDO orderoOld) {
		Session session= getSession();
		session.save(orderoOld);
	}

	public void saveToJoinTable(Integer cartItemId, Integer orderId) {
		CartOrder cartorder = new CartOrder();
		cartorder.setCartItemId(cartItemId);
		cartorder.setOrderId(orderId);
		Session session= getSession();
		session.save(cartorder);
		
	}

	@SuppressWarnings("unchecked")
	public List<CartOrder> getCartOrderData(Integer orderId) {
		Session session= getSession();
		List<CartOrder> cartorderList = session.createQuery("select s from CartOrder s where s.OrderId="+orderId+"").list();
		return cartorderList;
	}

	public void delOrder(int orderId) {
		Session session= getSession();
		session.createQuery("delete from OrderDO r where r.orderId="+orderId+"").executeUpdate();
		session.createQuery("delete from CartOrder r where r.OrderId="+orderId+"").executeUpdate();
		session.flush();
	}

}
