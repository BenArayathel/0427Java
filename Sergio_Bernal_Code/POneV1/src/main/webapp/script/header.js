(function () {

    window.addEventListener("load", function () {

        let header = document.querySelector("header");
        header.setAttribute("class", "bg-danger fixed-top header");

    }, false)

})();

function changeTitle(menuArray) {
    let nContainer = document.querySelector("header");
    nContainer.innerHTML = `
    <nav class="navbar navbar-expand-lg navbar-light bg-danger">
        <a class="navbar-brand" href="javascript:void()">
            <strong class="hb-logo">H<span class="b-animation">B</span></strong>
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarMenu"
            aria-controls="navbarMenu" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarMenu">
            <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                <li class="nav-item active">
                    <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
                </li>
            </ul>
            <ul class="navbar-nav my-2 my-lg-0">
                <li class="nav-item active">
                    <a class="nav-link" href="javascript:void()" onclick="logout()"><strong>Logout</strong></a>
                </li>
            </ul>
        </div>
    </nav>
    `;

    $('#navbarMenu').on('show.bs.collapse', function () {
        let header = document.querySelector("header");
        header.classList.remove('header');
    })
    $('#navbarMenu').on('hide.bs.collapse', function () {
        let header = document.querySelector("header");
        header.classList.add('header');
    })
    navbarMenu(menuArray);
}

function navbarMenu(menuArray) {
    let menu = document.querySelector("#navbarMenu > ul");
    menu.innerHTML = '';

    for (let idx in menuArray) {
        let navItem = document.createElement('li');
        navItem.setAttribute('class', 'nav-item');
        let navLink = document.createElement('a');
        navLink.id = menuArray[idx]['btn'];
        navLink.setAttribute('class', 'nav-link text-white');
        navLink.setAttribute('href', menuArray[idx]['link']);
        navLink.innerHTML = `<strong>${menuArray[idx]['title']}</strong>`;
        navItem.appendChild(navLink);
        menu.appendChild(navItem);
    }
}

function logout() {
    deleteCookie('ctrlApproval');
    deleteCookie('customerId');
    sessionStorage.clear();
    window.location.assign('index.html');
}