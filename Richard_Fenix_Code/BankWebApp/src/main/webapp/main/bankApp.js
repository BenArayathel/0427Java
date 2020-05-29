/**
 * 
 */
$("#myLogin").validate();

$("#mySignUp").validate();

$("#myEnroll").validate();

$("#myViewCustomer").validate();

// loginPage
$("#myNewAccount").validate();
  
//$("#myNewAccount").validate({
//  rules: {
//		initDeposit: { digits: true}
//	}
//  })


// customerPage
$("#myDeposit").validate();
$("#myWithdraw").validate();
$("#myTransfer").validate();



$('.currency-usd').each(function() { 
    var monetary_value = $(this).text(); 
    var i = new Intl.NumberFormat('en-US', { 
        style: 'currency', 
        currency: 'USD' 
    }).format(monetary_value); 
    $(this).text(i); 
}); 


