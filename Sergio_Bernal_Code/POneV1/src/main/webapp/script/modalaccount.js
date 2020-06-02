function createModalAccount() {
    let user = getSession('user');
    if (user) {
        let divModal = `
            <!-- Modal -->
            <div class="modal fade" id="newAccountModal" tabindex="-1" role="dialog" aria-labelledby="modalTitle"
                aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="modalTitle">New Bank Account</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <form action="" id="cAccount">
                                <div class="form-group">
                                    <label class="mr-sm-2" for="selAType">Account type:</label>
                                    <select class="custom-select mr-sm-2" id="selAType" required>
                                        <option value="1" selected>Saving</option>
                                        <option value="2">Checking</option>
                                    </select>
                                    <div class="invalid-feedback">
                                        To apply for the new account selected a type.
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inpBalance" class="text-left">Starting Balance:</label>
                                    <div class="input-group mb-3">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text" id="inpAddon">$</span>
                                        </div>
                                        <input type="number" id="inpBalance" class="form-control" step="0.01"
                                            placeholder="Starting Balance:" aria-label="Starting Balance" min=0
                                            aria-describedby="inpAddon" required>
                                        <div class="invalid-feedback">
                                            Enter an amount equal or grater than zero.
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            <button type="button" class="btn btn-danger" id="btnCreateAccount">Submit</button>
                        </div>
                    </div>
                </div>
            </div>
        `;

        let containerModal = document.querySelector("#containerModal");
        containerModal.innerHTML = divModal;
        $('#newAccountModal').modal('show');

        let cAccount = document.querySelector("#cAccount");
        let btnCreateAccount = document.querySelector('#btnCreateAccount');
        btnCreateAccount.addEventListener('click', function (event) {
            event.preventDefault();

            if (!cAccount.checkValidity()) {
                event.stopPropagation();
            } else {

                let typeAccount = document.querySelector("#selAType").value;
                let startBalance = document.querySelector('#inpBalance').value;
                let auxSBalance = parseFloat(startBalance);

                if (typeAccount > "0" && auxSBalance >= 0) {
                    createAccount(typeAccount, auxSBalance);
                } else {
                    console.log("Your start balance can not be less than zero!");
                }
            }
            cAccount.classList.add('was-validated');
        })
    }
}

let createAccount = async (type, balance) => {

    let user = getSession('user');
    let nameAccount = '';

    if (type === '1') nameAccount = 'Saving';
    if (type === '2') nameAccount = 'Checking';

    let url = `${BASE_URL}approval`;
    let options = {
        method: 'POST',
        headers: {
            'Content-type': 'application/json'
        },
        body: JSON.stringify({
            'accountTypeId': type,
            'accountTypeName': nameAccount,
            'status': 'Pending',
            'balance': balance,
            'personId': user["personId"]
        })
    }

    try {
        let response = await fetch(url, options);
        let result = await response.json();
        if (result.status === 'ok') {
            $('#newAccountModal').modal('hide');
            location.reload();
        } else {
            console.log("NO DATA - Account doesn't exist.");
        }
    } catch (error) {
        console.error("ACCOUNT ERROR", error);
    }

}