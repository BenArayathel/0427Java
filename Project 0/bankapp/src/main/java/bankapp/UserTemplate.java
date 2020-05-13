package bankapp;

import java.util.Date;
import java.util.Scanner;

public class UserTemplate 
{
	private String un;//username
	private String pw;//password
	private String fn;//first name
	private String ln;//last name
	private String dob;//date of birth
	private String pn;//phone number
	private String ssn;//social security number
	private int cs;//credit score
	private double sb;//starting balance
	private String email;//email
	private String ut;//user type (employee or customer)
	
	public UserTemplate() 
	{
		
	}
	
	public UserTemplate(String un, String pw, String fn, String ln, String dob, String pn, String ssn, int cs, double sb, String email, String ut) 
	{
		super();
		this.un = un;
		this.pw = pw;
		this.fn = fn;
		this.ln = ln;
		this.dob = dob;
		this.pn = pn;
		this.ssn = ssn;
		this.cs = cs;
		this.sb = sb;
		this.email = email;
		this.ut = ut;
	}
	
	public String getUn() 
	{
		Scanner a = new Scanner(System.in);
		String un = a.next();
		return un;
	}
	public void setUn(String un) 
	{
		this.un = un;
	}
	public String getPw() 
	{
		Scanner b = new Scanner(System.in);
		String pw = b.next();
		return pw;
	}
	public void setPw(String pw) 
	{
		this.pw = pw;
	}
	public String getFn() 
	{
		Scanner c = new Scanner(System.in);
		String fn = c.next();
		return fn;
	}
	public void setFn(String fn) 
	{
		this.fn = fn;
	}
	public String getLn() 
	{
		Scanner d = new Scanner(System.in);
		String ln = d.next();
		return ln;
	}
	public void setLn(String ln) 
	{
		this.ln = ln;
	}
	public String getDob() 
	{
		Scanner e = new Scanner(System.in);
		String dob = e.next();
		return dob;
	}
	public void setDob(String dob) 
	{
		this.dob = dob;
	}
	public String getPn() 
	{
		Scanner f = new Scanner(System.in);
		String pn = f.next();
		return pn;
	}
	public void setPn(String pn) 
	{
		this.pn = pn;
	}
	public String getEmail() 
	{
		Scanner g = new Scanner(System.in);
		String email = g.next();
		return email;
	}
	public void setEmail(String email) 
	{
		this.email = email;
	}
	public String getSsn() 
	{
		Scanner h = new Scanner(System.in);
		String ssn = h.next();
		return ssn;
	}
	public void setSsn(String ssn) 
	{
		this.ssn = ssn;
	}
	public int getCs() 
	{
		Scanner i = new Scanner(System.in);
		String j = i.next();
		cs = Integer.parseInt(j);
		return cs;
	}
	public void setCs(int cs) 
	{
		this.cs = cs;
	}
	public double getSb() 
	{
		Scanner k = new Scanner(System.in);
		String l = k.next();
		sb = Double.parseDouble(l);
		return sb;
	}
	public void setSb(double sb) 
	{
		this.sb = sb;
	}
	public String getUt() 
	{
		Scanner m = new Scanner(System.in);
		String ut = m.next();
		return ut;
	}
	public void setUt(String ut) 
	{
		this.ut = ut;
	}
	@Override
	public String toString() 
	{
		return "Customer [Username = " + un + "/Password = " + pw + "First Name =" + fn + "/Last Name = " + ln + "/DOB = " 
				+ dob + "/Phone Number = " + pn + "/SSN = " 
				+ ssn + "/Credit Score = " + cs + "/Starting Balance = " + sb + "/Email = " + email + "/User Type = " + ut + "]";
	}
	
}
