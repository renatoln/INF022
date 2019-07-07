const $header = document.querySelector("header");//por convenção usar "$" para elementos que referencie itens do html
const $navBar = document.querySelectorAll('.nav-bar')[0];
window.addEventListener("scroll", toggleHeader, false);

let hoverColor = "#2574A9";
let normalColor = "#EEDDB3";


function toggleHeader(){
    if  (window.pageYOffset > 60 && $header.classList.contains("max-header")){
        $header.classList.remove("max-header");
        $header.classList.add("min-header");
        $logo.firstElementChild.setAttribute("src", "imgs/omnitech-logo-2.png");
        $navBar.classList.remove('max-nav');
        $navBar.classList.add('min-nav');
    }
    else if (window.pageYOffset <= 60 && $header.classList.contains("min-header")){
        $header.classList.add("max-header");
        $header.classList.remove("min-header");
        $logo.firstElementChild.setAttribute("src", "imgs/omnitech-logo-1.png");
        $navBar.classList.add('max-nav');
        $navBar.classList.remove('min-nav');        
    }

}

let vetor = document.getElementsByClassName("mun str2");

for(let i = 0;i < vetor.length;i++){
    vetor[i].addEventListener('click',function(){
        //
    });

    vetor[i].addEventListener('mouseover',function(){
        vetor[i].style.fill = hoverColor;
    });

    vetor[i].addEventListener('mouseout',function(){
        vetor[i].style.fill = normalColor;
    });
}