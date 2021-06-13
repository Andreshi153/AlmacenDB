const DOM = {
    btnMenu: document.getElementById("mostrarMenu"),
    menu: document.getElementById("navMenu"),
    header: document.getElementById("hdrMenu")
}

const mostrarMenu = () => {
    DOM.menu.classList.toggle("oculto");
    DOM.btnMenu.classList.toggle("boton-grande");
}

DOM.btnMenu.addEventListener("click", mostrarMenu);