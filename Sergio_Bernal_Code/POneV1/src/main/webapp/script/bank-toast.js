let createToast = (message, className) => {
    let toastContainer = document.querySelector("#toastContainer");
    toastContainer.innerHTML = `
    <div class="alert alert-${className} alert-dismissible fade show" role="alert" id="bankAlert">
        <strong>Holy guacamole!</strong> ${message}
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
    </div>
    `;
    $('#bankAlert').on('closed.bs.alert', function () {
        location.reload();
    });
}