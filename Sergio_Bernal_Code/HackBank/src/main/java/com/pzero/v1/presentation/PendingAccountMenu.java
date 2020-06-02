package com.pzero.v1.presentation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.pzero.v1.Main;
import com.pzero.v1.business.exceptions.BusinessException;
import com.pzero.v1.persistence.models.Account;
import com.pzero.v1.persistence.models.PendingApproval;

public class PendingAccountMenu {

	public static void showPendingApproval(Scanner sc) {
		List<PendingApproval> list = new ArrayList<>();
		Account account = null;
		try {
			list = Main.pApprovalSrv.listApproval();
			if(!list.isEmpty()) {
				Main.loggy.info("\n--- Pending Accounts ---\n");
				Main.loggy.info("No  -	Customer Id		| Name		|   Last Name   |     Type      |  Start Balance  |  Status ");
				Main.loggy.info("-- |	-----------------------	| ------------- | ------------- | ----- ------- | --------------- | ----------");
				for (PendingApproval a : list) {
					Main.loggy.info((list.indexOf(a)+1)+"  -	"+a.getPerson().getId()+"	| "+a.getPerson().getName()+"	| "+a.getPerson().getLastName()+"	| "+a.getAccountType().getName()+" 	|	"+a.getStartBalance()+"	  |  "+a.getStatus()+" ");
					Main.loggy.info("-- |	-----------------------	| ------------- | ------------- | ----- ------- | --------------- | ----------");
				}
				Main.loggy.info("\n-- Select a pending account to change Status.");
				int index = Integer.parseInt(sc.nextLine());
				if (index <= list.size()) {
					PendingApproval iApproval = list.get(index-1);
					iApproval.setStatus(ChangeStatus.status(sc));
					account = Main.pApprovalSrv.updateApproval(iApproval);
					if(account != null)
						Main.loggy.info(account.preview()+"\n");
					else {
						Main.loggy.info("The account was rejected.\n");
					}
				}else {
					Main.loggy.info("You don't have any pending account.");
				}
			}
		} catch (BusinessException e1) {
			Main.loggy.info(e1.getMessage());
		} catch (NumberFormatException e) {
			Main.loggy.info("This is not a valid option.");
		}
		
		
	}
	
}
