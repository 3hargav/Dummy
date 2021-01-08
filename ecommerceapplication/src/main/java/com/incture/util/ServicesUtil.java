package com.incture.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.SplittableRandom;
import java.util.TimeZone;

import com.incture.DO.CartUserDO;

/**
 * @author Akula.Veerraju
 *
 */
public class ServicesUtil {

	public static void main(String[] args) {
		System.out.println(encodeString("546306"));
		
	}
	
	public static int getCartTotalPrice(List<CartUserDO> cartItems) {
		int grandTotal=0;
		for(CartUserDO item: cartItems){
			grandTotal += item.getPrice();
		}
		return grandTotal;
	}

	public static String GenerateOtp(int Otplength){
		SplittableRandom random=new SplittableRandom();

		StringBuilder sb=new StringBuilder();

		for(int i=0;i<Otplength;i++){
			sb.append(random.nextInt(0,10));
		}
		return sb.toString();
	}

	public static boolean isEqualsIgnoreCase(String str1,String str2) {
		if (!isEmpty(str1) && !isEmpty(str2)){
			if(str1.equalsIgnoreCase(str2))
				return true;
			else 
				return false;
		}else
			return false;
	}
public static String encodeString(String imput){
	return Base64.getEncoder().encodeToString(imput.getBytes());
}

	public static boolean isEmpty(String str) {
		if (str == null || str.trim().isEmpty()) {
			return true;
		}
		return false;
	}

	public static boolean isEmpty(Object[] objs) {
		if (objs == null || objs.length == 0) {
			return true;
		}
		return false;
	}

	public static boolean isEmpty(Object o) {
		if (o == null) {
			return true;
		}
		return false;
	}

	public static boolean isEmpty(Collection<?> o) {
		if (o == null || o.isEmpty()) {
			return true;
		}
		return false;
	}

	public static boolean isEmpty(Integer num) {
		if ((num == null) || (num == 0)) {
			return true;
		}
		return false;
	}

	public static String getString(Integer inpInteger) {
		return inpInteger != null ? inpInteger.toString() : null;
	}

	public static BigDecimal getBigDecimal(String inpStr) {
		return inpStr != null && !inpStr.trim().isEmpty() ? new BigDecimal(inpStr.trim()) : null;
	}

	public static String DateToString(Date date) {

		String newstr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date);
		return newstr;
	}

	public static Date convertStringToDate(String stringDate) {
		try {
			String sDate1 = stringDate;
			Date date1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(sDate1);
			return date1;
		} catch (ParseException e) {
			System.err.println("Exception:-" + e.getMessage());
			return getTime();
		}

	}

	public static Date getTime() {
		Date date1 = null;
		try {
			Date time = new Date();
			SimpleDateFormat ft1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
			TimeZone.setDefault(TimeZone.getTimeZone("IST"));
			String s1 = ft1.format(time);
			date1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a").parse(s1);
		} catch (ParseException e) {
			System.err.println("getTime:-" + e.getMessage());
			e.printStackTrace();
		}
		return date1;
	}


}