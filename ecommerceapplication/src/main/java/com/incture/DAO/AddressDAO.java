package com.incture.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.incture.DO.ShippingAddressDO;
import com.incture.DTO.AddressDTO;

@Repository
public class AddressDAO extends BaseDao{

	@SuppressWarnings("unchecked")
	public List<ShippingAddressDO> getAddressByUser(int userId) {
		Session session= getSession();
		List<ShippingAddressDO> addressList = session.createQuery("select r from ShippingAddressDO r where r.userId='"+userId+"'").list();
		return addressList;	
	}

	public Object ExportFromDB(ShippingAddressDO address) {
		AddressDTO addressDTO = new AddressDTO();
		addressDTO.setAddress(address.getAddress());
		addressDTO.setCity(address.getCity());
		addressDTO.setState(address.getState());
		addressDTO.setCountry(address.getCountry());
		addressDTO.setAddressId(address.getAddressID());
		addressDTO.setPincode(address.getPincode());
		addressDTO.setUserId(address.getUserId());

		return addressDTO;
	}

	public void addAdrs(AddressDTO addressDTO) {
		ShippingAddressDO addressDO = importToDB(addressDTO);
		Session session= getSession();
		session.save(addressDO);
	}

	private ShippingAddressDO importToDB(AddressDTO addressDTO) {
		ShippingAddressDO addressDO = new ShippingAddressDO();
		addressDO.setAddress(addressDTO.getAddress());
		addressDO.setCity(addressDTO.getCity());
		addressDO.setState(addressDTO.getState());
		addressDO.setCountry(addressDTO.getCountry());
		addressDO.setPincode(addressDTO.getPincode());
		addressDO.setUserId(addressDTO.getUserId());

		return addressDO;
	}

	@SuppressWarnings("unchecked")
	public ShippingAddressDO getAddressById(int addressId) {
		Session session= getSession();
		Query<ShippingAddressDO> query = session.createQuery("select r from ShippingAddressDO r where r.addressID='"+addressId+"'");
		ShippingAddressDO addressX = query.uniqueResult();
		return addressX;
	}

	public void saveToDB(ShippingAddressDO addressOld) {
		Session session= getSession();
		session.save(addressOld);
	}

	public void deleteAddress(int addressId) {
		Session session= getSession();
		session.createQuery("delete from ShippingAddressDO r where r.addressID="+addressId+"").executeUpdate();
		session.flush();
	}


}
