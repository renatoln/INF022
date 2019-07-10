const $header = document.querySelector("header");//por convenção usar "$" para elementos que referencie itens do html
const $navBar = document.querySelectorAll('.nav-bar')[0];
window.addEventListener("scroll", toggleHeader, false);

let hoverColor = "#2574A9";
let normalColor = "#EEDDB3";


function toggleHeader(){
    if  (window.pageYOffset > 60 && $header.classList.contains("max-header")){
        $header.classList.remove("max-header");
        $header.classList.add("min-header");
        //$logo.firstElementChild.setAttribute("src", "imgs/omnitech-logo-2.png");
        $navBar.classList.remove('max-nav');
        $navBar.classList.add('min-nav');
    }
    else if (window.pageYOffset <= 60 && $header.classList.contains("min-header")){
        $header.classList.add("max-header");
        $header.classList.remove("min-header");
        //$logo.firstElementChild.setAttribute("src", "imgs/omnitech-logo-1.png");
        $navBar.classList.add('max-nav');
        $navBar.classList.remove('min-nav');        
    }

}

//preenche a cidade com o hover e diz o nome
let vetor = document.getElementsByClassName("mun str2");

for(let i = 0;i < vetor.length;i++){
    vetor[i].addEventListener('click',function(){
        alert(vetor[i].getAttribute("nome"));
    });

    vetor[i].addEventListener('mouseover',function(){
        vetor[i].style.fill = hoverColor;
    });

    vetor[i].addEventListener('mouseout',function(){
        vetor[i].style.fill = normalColor;
    });
}



//preenche a mesorregião com o hover e diz o nome
let meso = document.getElementsByClassName("mesoreg");

for(let i = 0; i < meso.length;i++){
    meso[i].style.fill = normalColor;

    meso[i].addEventListener('click',function(){
        alert(meso[i].getAttribute("name"));
    })

    meso[i].addEventListener('mouseover',function(){
        meso[i].style.fill = hoverColor;
    })

    meso[i].addEventListener('mouseout',function(){
        meso[i].style.fill = normalColor;
    })
}

//preenche a microrregião com o hover e diz o nome
let micro = document.getElementsByClassName("micreg");
//console.log(micro);

for(let i = 0; i < micro.length;i++){
    micro[i].style.fill = normalColor;

    micro[i].addEventListener('click',function(){
        alert(micro[i].getAttribute("name"));
    })

    micro[i].addEventListener('mouseover',function(){
        micro[i].style.fill = hoverColor;
    })

    micro[i].addEventListener('mouseout',function(){
        micro[i].style.fill = normalColor;
    })
}



