package com.pzero.v1.business.services.enrollment;

import java.util.List;

import com.pzero.v1.business.exceptions.BusinessException;
import com.pzero.v1.persistence.models.BankAccountEnrollment;

public interface EnrollmentService {

	public boolean createEnrollment(BankAccountEnrollment enrollment) throws BusinessException;
	public List<BankAccountEnrollment> getEnrollmentsByCustomer(String id) throws BusinessException;
	
}
