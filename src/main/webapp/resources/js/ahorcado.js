/* CUBIKTIMER - SOFTWARE DE APOYO AL APRENDIZAJE Y LA PRÁCTICA DEL SPEEDCUBING EN COLOMBIA
 * Copyright (c) 2020-present Nelson Ariza
 * Licensed under GPLv3 (https://github.com/njarizas/cubiktimer/blob/master/LICENSE.md) */
$(document).ready(function () {
	document.getElementById('forma:letra').focus();
    $(document).keyup(function (tecla) {
      console.log(tecla.keyCode);
      console.log(tecla.key);
      if ((tecla.keyCode>=65&&tecla.keyCode<=90)||tecla.key=='ñ'||tecla.key=='Ñ' ){
      document.getElementById('forma:letra').value=tecla.key;
      document.getElementById('forma:comprobar').click();
      }
    });
});

function accion(input){
	document.getElementById('forma:letra').value=input.innerHTML;
	document.getElementById('forma:comprobar').click();
}