window.onload = function() {
    this.fetch('http://localhost:9090/BhankWebApp/CustomerHome')
    .then(response => {
        return response.json();
    })
    .then(json => {
        for(let account in json) {
            this.console.log(account);
            let accountTR = document.createElement('tr');
            let accountIdTD = document.createElement('td');
            accountIdTD.innerText = json[account].id;
            let accountBalanceTD = document.createElement('td');
            accountBalanceTD.innerText = json[account].balance;

            accountTR.appendChild(accountIdTD);
            accountTR.appendChild(accountBalanceTD);

            this.document.getElementById('accountsTableBody').appendChild(accountTR);
        }
    })
    .catch(error => {
        this.console.error(error);
    })
}