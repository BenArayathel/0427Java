package com.pzero.v1.business.services.enrollment;

import java.util.List;

import com.pzero.v1.business.exceptions.BusinessException;
import com.pzero.v1.persistence.dao.enrollment.EnrollmentDAO;
import com.pzero.v1.persistence.dao.enrollment.EnrollmentDAOImpl;
import com.pzero.v1.persistence.models.BankAccountEnrollment;

public class EnrollmentSrvcImpl implements EnrollmentService{

	private EnrollmentDAO enrollmentDao = new EnrollmentDAOImpl();
	
	@Override
	public boolean createEnrollment(BankAccountEnrollment enrollment) throws BusinessException {
		boolean flag = false;
		if(enrollment == null) {
			throw new BusinessException("Obj Enrollment is invalid!");
		}else {
			flag = enrollmentDao.createEnrollment(enrollment);
		}
		return flag;
	}

	@Override
	public List<BankAccountEnrollment> getEnrollmentsByCustomer(String id) throws BusinessException {
		return enrollmentDao.getEnrollmentsByCustomer(id);
	}

}
