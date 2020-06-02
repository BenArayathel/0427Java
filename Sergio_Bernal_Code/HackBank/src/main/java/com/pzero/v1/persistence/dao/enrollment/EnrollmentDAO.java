package com.pzero.v1.persistence.dao.enrollment;

import java.util.List;

import com.pzero.v1.business.exceptions.BusinessException;
import com.pzero.v1.persistence.models.BankAccountEnrollment;

public interface EnrollmentDAO {
	
	public boolean createEnrollment(BankAccountEnrollment enrollment) throws BusinessException;
	public List<BankAccountEnrollment> getEnrollmentsByCustomer(String id) throws BusinessException;

}
