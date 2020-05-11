package bankapp;

import java.util.Date;

public class NewCustomerTemplate 
{
	private String un;//username
	private String pw;//password
	private String fn;//first name
	private String ln;//last name
	private String dob;//date of birth
	private String pn;//phone number
	private String email;//email
	private String ssn;//social security number
	private int cs;//credit score
	private double sb;//starting balance
	private String ut;//user type (employee or customer)
	
	public String getUn() 
	{
		return un;
	}
	public void setUn(String un) 
	{
		this.un = un;
	}
	public String getPw() 
	{
		return pw;
	}
	public void setPw(String pw) 
	{
		this.pw = pw;
	}
	public String getFn() 
	{
		return fn;
	}
	public void setFn(String fn) 
	{
		this.fn = fn;
	}
	public String getLn() 
	{
		return ln;
	}
	public void setLn(String ln) 
	{
		this.ln = ln;
	}
	public String getDob() 
	{
		return dob;
	}
	public void setDob(String dob) 
	{
		this.dob = dob;
	}
	public String getPn() 
	{
		return pn;
	}
	public void setPn(String pn) 
	{
		this.pn = pn;
	}
	public String getEmail() 
	{
		return email;
	}
	public void setEmail(String email) 
	{
		this.email = email;
	}
	public String getSsn() 
	{
		return ssn;
	}
	public void setSsn(String ssn) 
	{
		this.ssn = ssn;
	}
	public int getCs() 
	{
		return cs;
	}
	public void setCs(int cs) 
	{
		this.cs = cs;
	}
	public double getSb() 
	{
		return sb;
	}
	public void setSb(double sb) 
	{
		this.sb = sb;
	}
	public String getUt() {
		return ut;
	}
	public void setUt(String ut) {
		this.ut = ut;
	}
	@Override
	public String toString() 
	{
		return "Customer [First Name =" + fn + "/Last Name = " + ln + "/DOB = " 
				+ dob + "/Phone Number = " + pn + "/Email = " + email + "/SSN = " 
				+ ssn + "/Credit Score = " + cs + "]";
	}
	
}
