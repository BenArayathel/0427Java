/**
 * 
 */

$('.currency-usd').each(function() { 
    var monetary_value = $(this).text(); 
    var i = new Intl.NumberFormat('en-US', { 
        style: 'currency', 
        currency: 'USD' 
    }).format(monetary_value); 
    $(this).text(i); 
}); 


