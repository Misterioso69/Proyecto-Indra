document.addEventListener('DOMContentLoaded', function(){
    const contenedor = document.querySelector('.carrusel-contenedor');
    const items = document.querySelectorAll('.carrusel-item');
    
    let indiceAC = 0;
    const totalItems = items.length;
    let intervalo;

    function actualizarCarru (suave = true){
        if(!suave){
            contenedor.style.transition = 'none';
        }else{
            contenedor.style.transition = 'transform 0.5s ease-in-out'
        }
        contenedor.style.transform = `translateX(-${indiceAC * 100}%)`;
        if(!suave){
            void contenedor.offsetWidth;
        }
    }
    function siguiente(){
       indiceAC++;
       if(indiceAC >= totalItems){
            actualizarCarru(false);
            indiceAC=0;
            setTimeout(()=> {actualizarCarru(true);},1);
       }else{
        actualizarCarru(true);
       }
    }
    intervalo = setInterval(siguiente,10000);
    actualizarCarru(true);

});