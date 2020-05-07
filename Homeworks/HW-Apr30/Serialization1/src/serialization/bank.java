package serialization;
import java.io.Serializable;

public class bank implements Serializable{
	private String accName; 
	private String money;
	
	private static final long serialVersionUID = 1L;
	
	public bank() {
		// TODO Auto-generated constructor stub
	}
	
	public bank(String accName, String money) {
		super();
		this.accName = accName;
		this.money = money;
	}
	@Override
	public String toString() {
		return "bank [name=" + accName + ", money=" + money + "]";
	}

	public String getAccName() {
		return accName;
	}

	public void setAccName(String accName) {
		this.accName = accName;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}
	
}
