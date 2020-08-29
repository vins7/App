package com.app.service;

import java.sql.Timestamp;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.VoucherDao;
import com.app.model.Items;
import com.app.model.Voucher;

@Service
@Transactional
public class VoucherService {

	@Autowired
	private VoucherDao voucherDao;
	
	public Voucher getById(String id) throws Exception{
		Voucher voucher = voucherDao.getById(id);
		if(voucher == null) {
			throw new Exception("Voucher not found !");
		}else {
			return voucher;
		}
	}
	
	public void add(Voucher voucher) throws Exception{
		try {
			voucher.setCreatedBy("admin");
			voucher.setUpdatedBy("admin");
			voucherDao.add(voucher);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void editActive(Voucher tempVoucher) throws Exception{
		try {
			Voucher voucher = voucherDao.getById(tempVoucher.getId());
			voucher.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
			voucher.setIsActive(false);
			edit(voucher);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void editVoucher(Voucher tempVoucher) throws Exception{
		try {
			Voucher voucher = voucherDao.getById(tempVoucher.getId());
			voucher.setCode(tempVoucher.getCode());
			voucher.setName(tempVoucher.getName());
			voucher.setPrice(tempVoucher.getPrice());
			voucher.setUpdatedAt(new Timestamp(System.currentTimeMillis()));			
			edit(voucher);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void edit(Voucher voucher) throws Exception{
		try {			
			voucherDao.edit(voucher);
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	
}
