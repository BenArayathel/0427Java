(function () {

    window.addEventListener('load', function () {

        let footer = document.querySelector("footer");
        footer.setAttribute("class", "footer mt-auto py-3 bg-dark text-center fixed-bottom");

        let fContainer = document.createElement("div");
        fContainer.setAttribute("class", "container");

        let ftext = document.createElement("div");
        ftext.setAttribute("class", "text-muted");
        ftext.innerText = "Hack Bank © Created by Sergio B.";

        fContainer.appendChild(ftext);
        footer.appendChild(fContainer);

    }, false);

})();