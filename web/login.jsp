<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ejercicio fotograma perdido ajax</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.0.0.js"></script>
<script src="https://code.jquery.com/jquery-migrate-3.0.0.js"></script>
</head>
<style type="text/css">
.progress-bar {
    color: #333;
} 
* {
    -webkit-box-sizing: border-box;
       -moz-box-sizing: border-box;
            box-sizing: border-box;
    outline: none;
}
    .form-control {
      position: relative;
      font-size: 16px;
      height: auto;
      padding: 10px;
        @include box-sizing(border-box);
        &:focus {
          z-index: 2;
        }
    }
      .navbar-default .navbar-fixed-top {
    background-color: #000000;
}
body {
    color: black;
    background: url(http://i.imgur.com/GHr12sH.jpg) no-repeat center center fixed;
    -webkit-background-size: cover;
    -moz-background-size: cover;
    -o-background-size: cover;
    background-size: cover;
}
.login-form {
    margin-top: 60px;
}
form[role=login] {
    color: #5d5d5d;
    background: #f2f2f2;
    padding: 26px;
    border-radius: 10px;
    -moz-border-radius: 10px;
    -webkit-border-radius: 10px;
}
    form[role=login] img {
        display: block;
        margin: 0 auto;
        margin-bottom: 35px;
    }
    form[role=login] input,
    form[role=login] button {
        font-size: 18px;
        margin: 16px 0;
    }
    form[role=login] > div {
        text-align: center;
    }
    
.form-links {
    text-align: center;
    margin-top: 1em;
    margin-bottom: 50px;
}
    .form-links a {
        color: #fff;
    }
</style>
<body>
<nav class="navbar navbar-light" style="background-color: #e3f2fd;">
  <h4 class="glyphicon glyphicon-user user nombrenav pull-right" name="nombrenav login"></h4>
  <p class="navbar-brand">Acertados partida:</p><p name="puntos" class="navbar-brand acert"></p>
<!--    <p class="navbar-brand">Total aciertos:</p><p name="puntosTotal" class="navbar-brand acerTotal"></p> -->
  <p class="navbar-brand pull-right glyphicon glyphicon-off salir" href="">  </p>
</nav>
<br>
<br> 

    <div class="col-md-4 divlog center-block">
      <section class="login-form">
        <form method="post" action="" role="login">
          <input type="text" name="usuario" placeholder="login" required class="form-control input-lg useradmin" value="" required="" />
		  <input type="password" class="form-control input-lg password" placeholder="clave" required="" />
          	<div class="pwstrength_viewport_progress"></div>
          <button type="button" name="login" class="btn btn-lg btn-primary btn-block login">Iniciar sesión</button>
          <button type="button" name="registro" class="btn btn-lg btn-primary btn-block registro">Regístrate</button>
          <button type="button" name="rankingd" class="btn btn-lg btn-primary btn-block rankingt">Ver ranking</button>
        </form>
      </section>  
	</div>

<div class="container menuinicio">
          <button type="button" name="juegoptd" class="btn btn-lg btn-primary btn-block juegoptd">Jugar</button>
          <button type="button" name="rankingt" class="btn btn-lg btn-primary btn-block rankingt">Ver ranking</button>
</div>

<div class="container rankingnoreg">
  <div class="row rankingnoreg">
    <h2>Ranking</h2>
      <div class="col-xs-12 col-sm-9">Lista ranking:</div>
  </div>
    <table class="table" id="table-ranking">
      <thead>
        <tr>
          <th>Usuario</th>
          <th>Puntuación</th>
        </tr>
      </thead>
      <tbody id="ranking"></tbody>
	</table>
</div>

<div class="container partidaj">
    <div class="row partidaj">
     <h2>Juego Fotograma Perdido</h2>
      <div class="col-xs-12 col-sm-9">¿A qué pelicula o serie pertence este fotograma?:</div>
	</div>
		<form method="post" action="" class="formp" role="comprobar">
			<img class="imgp" height='242' width='242'></img>
		         <select class="groupid" style="width:50%;"></select>
					<input type="hidden" id="archivo" name="archivo"></input>
						<button type="button" name="comp" class="btn btn-primary btn-block comp">Siguiente</button>
		</form>
</div>

</body>   
    
<script>
   
$(document).ready(function() {
	$('.rankingnoreg').hide();
	$('.menuinicio').hide();
	$('.navbar').hide();
	$('.partidaj').hide();
		
		var tlista=[];
		var flista=[];
		var acertada=0;
		var titulo;
		var foto;
		var ale;
		var nombreusu;
		var numpartida=0;
	
	//PETICION LOGIN
    $('.login').click(function(){
        var usu = $('.useradmin').val();
        var usupass = $('.password').val();

        $.ajax({
            type: "POST",
            url: "controlador?accion=validar",
            dataType: "json",
            data: { "login": usu, "clave": usupass },
            success: function(data) {
 
                if (data.error!=null) {

					alert(data.error);
				} else {
				$('.divlog').hide();
				$('.rankingnoreg').hide();
				$('.nombrenav').text(usu);
				//puntosjgd(usu);
				$('.acert').text('0');
				$('.navbar').show();
				$('.menuinicio').show();
				
				}

            },
            error: function (xhr, ajaxOptions, thrownError) {
                console.log(xhr.headers);
                console.log(xhr.status);
                console.log(xhr.responseText);
                console.log(ajaxOptions);
                console.log(thrownError);
            }           
        });
    });
    

    
    
    // PETICION PARA VER NUMERO TOTAL DE ACERTADOS
    function puntosjgd(usu){
    
        $.ajax({
            type: "POST",
            url: "controlador?accion=acertados",
            dataType: "json",
            data: { "user": usu },
            success: function(data) {
                
                if (data.error!=null) {

					alert(data.error);
				} else {
					$('.acerTotal').text(data.length);
					
				}
            },
            error: function (xhr, ajaxOptions, thrownError) {
                console.log(xhr.headers);
                console.log(xhr.status);
                console.log(xhr.responseText);
                console.log(ajaxOptions);
                console.log(thrownError);
            }           
        });
    
    }
    
           $('.salir').click(function(){ 
                $('.divlog').show();
                $('.useradmin').val('');
         		$('.password').val('');
       			
				$('.rankingnoreg').hide();
				$('.nombrenav').text('');
				$('.acert').text('0');
				$('.navbar').hide();
				$('.menuinicio').hide();
				$('.partidaj').hide();
       });
    
        
    // PETICION DE REGISTRO
    $('.registro').click(function(){
        var usu = $('.useradmin').val();
        var usupass = $('.password').val();
        $.ajax({
            type: "POST",
            url: "controlador?accion=registrar",
            dataType: "json",
            data: { "login": usu, "clave": usupass },
            success: function(data) {
                
                if (data.error!=null) {

					alert(data.error);
				} else {
				$('.divlog').hide();
				$('.nombrenav').text(usu);
				//puntosjgd(usu);
				$('.acert').text('0');
				$('.navbar').show();
				$('.menuinicio').show();
 				$('.rankingnoreg').hide();
				}
            },
            error: function (xhr, ajaxOptions, thrownError) {
                console.log(xhr.headers);
                console.log(xhr.status);
                console.log(xhr.responseText);
                console.log(ajaxOptions);
                console.log(thrownError);
            }           
        });
    });

		//PETICION PARA UNA PARTIDA
		function pjuego(){
				$('.menuinicio').hide();
				$('.groupid').find('option').remove().end();
				nombreusu=$('.nombrenav').text();
				//partidap(nombreusu);
				
				if (numpartida==10) {
					
				if (acertada>0) {
					modiranking(nombreusu,acertada);
					//acertada=0;
				}
					alert('El porcentaje de acierto en este juego ha sido:'+acertada/numpartida);
					//acertada=0;
					$('.acert').text('0');
					$('.partidaj').hide();
					$('.menuinicio').show();
					numpartida=0;
				} else {
              $.ajax({
            type: "POST",
            url: "controlador?accion=partida",
            dataType: "json",
            data: { "user": nombreusu },
            success: function(data) {
                      	numpartida++;
                		ale=data[Math.round(Math.random() * (2 - 0) + 0)].archivo;
                	//	while (fvisto.indexOf(ale)!=-1) {		
						//ale=data[Math.round(Math.random() * (2 - 0) + 0)].archivo;
					//	console.log("entra a cambiar");
					//	pjuego();
					//	}
                		
                		                		
                		var url="img/";
                		url +=ale;
               			$('.imgp').attr('src', url); 
    					$('#archivo').val(ale);
    					
                for(i = 0; i < data.length; i++){
						//fvisto.push(data[i].titPelicula);
						$('.groupid').append(new Option(data[i].titPelicula, data[i].titPelicula));
						$('<input>').attr({type: 'hidden', id:'titPelicula', name: 'titPelicula', value: data[i].titPelicula}).appendTo('.formp');
                      }
                      
                      $('.partidaj').show();
                      console.log(numpartida);
                      //console.log(fvisto);
            }, 
  	         error: function (xhr, ajaxOptions, thrownError) {
                console.log(xhr.headers);
                console.log(xhr.status);
                console.log(xhr.responseText);
                console.log(ajaxOptions);
                console.log(thrownError);
            }
        });    
        }    
		}

//ESTA NO LA USO ESTA TAMBIEN PETICION DE PARTIDA
		function partidap(nombreusu){
		$.ajax({
            type: "POST",
            url: "controlador?accion=partidap",
            dataType: "json",
            data: { "user": nombreusu },
            success: function(data) {
                        var url="img/";
                		url +=data.foto;
               			$('.imgp').attr('src', url); 
    					$('#archivo').val(data.foto);
						$('.groupid').append(new Option(data.titUno, data.titUno));
						$('.groupid').append(new Option(data.titDos, data.titDos));
						$('.groupid').append(new Option(data.titTres, data.titTres));
						$('<input>').attr({type: 'hidden', id:'titPelicula', name: 'titPelicula', value: data.titUno}).appendTo('.formp');
						$('<input>').attr({type: 'hidden', id:'titPelicula', name: 'titPelicula', value: data.titDos}).appendTo('.formp');
						$('<input>').attr({type: 'hidden', id:'titPelicula', name: 'titPelicula', value: data.titTres}).appendTo('.formp');
                        $('.partidaj').show();
            }, 
  	         error: function (xhr, ajaxOptions, thrownError) {
                console.log(xhr.headers);
                console.log(xhr.status);
                console.log(xhr.responseText);
                console.log(ajaxOptions);
                console.log(thrownError);
            }
        });
		}
		
		
	// PARTIDA OBTIENE 10 FOTOGRAMAS UNA SOLA PETICION  (NO USO)
		function darpartidadiez(){

				$('.menuinicio').hide();
				$('.groupid').find('option').remove().end();
				nombreusu=$('.nombrenav').text();
				
				if (numpartida>=1) {
					numpartida++;
				if(numpartida==10){
				if (acertada>0) {
					modiranking(nombreusu,acertada);
					
				}
					alert('El porcentaje de acierto en este juego ha sido:'+acertada/numpartida);
					
					$('.acert').text('0');
					$('.partidaj').hide();
					$('.menuinicio').show();
					numpartida=0;
				}
                        var rand=Math.round(Math.random() * (flista.length - 1) + 0);
						ale=flista[rand];		                		
                		var url="img/";
                		url +=ale;
               			$('.imgp').attr('src', url); 
    					$('#archivo').val(ale);
    					flista.unshift(ale);
    					console.log(rand);
    					console.log(tlista.length);
    					$('.groupid').append(new Option(tlista[rand], tlista[rand]));
						$('<input>').attr({type: 'hidden', id:'titPelicula', name: 'titPelicula', value: tlista[rand]}).appendTo('.formp');
                      for (var int = 0; int < 2; int++) {
                      var rando=Math.round(Math.random() * (tlista.length - 1) + 0);
						$('.groupid').append(new Option(tlista[rando], tlista[rando]));
						$('<input>').attr({type: 'hidden', id:'titPelicula', name: 'titPelicula', value: tlista[rando]}).appendTo('.formp');
					  }
                      
                      $('.partidaj').show();
				
				} else {
              $.ajax({
            type: "POST",
            url: "controlador?accion=partida",
            dataType: "json",
            data: { "user": nombreusu },
            success: function(data) {
                      	numpartida++;

    					
                for(i = 0; i < data.length; i++){
						tlista.push(data[i].titPelicula);
						flista.push(data[i].archivo);

                      }
                      
                        var rand=Math.round(Math.random() * (flista.length - 1) + 0);
						ale=flista[rand];		                		
                		var url="img/";
                		url +=ale;
               			$('.imgp').attr('src', url); 
    					$('#archivo').val(ale);
    					flista.unshift(ale);
    					$('.groupid').append(new Option(data[rand].titPelicula, data[rand].titPelicula));
						$('<input>').attr({type: 'hidden', id:'titPelicula', name: 'titPelicula', value: data[rand].titPelicula}).appendTo('.formp');
                      for (var int = 0; int < 2; int++) {
                      var rando=Math.round(Math.random() * (tlista.length - 1) + 0);
						$('.groupid').append(new Option(tlista[rando], tlista[rando]));
						$('<input>').attr({type: 'hidden', id:'titPelicula', name: 'titPelicula', value: tlista[rando]}).appendTo('.formp');
					  }
                      
                      $('.partidaj').show();
                      console.log(numpartida);
                      console.log(fvisto);
            }, 
  	         error: function (xhr, ajaxOptions, thrownError) {
                console.log(xhr.headers);
                console.log(xhr.status);
                console.log(xhr.responseText);
                console.log(ajaxOptions);
                console.log(thrownError);
            }
        });    
        } 
		
		
		}
		
		
		//LLAMA A LA FUNCTION PJUEGO AL PULSAR EL BOTON JUGAR
		$('.juegoptd').click(function(){
			$('.rankingnoreg').hide();
			pjuego();
			//darpartidadiez();
			
      	});
      	
      	// MODIFICA LOS PUNTOS DEL RANKING DEL USUARIO
      	function modiranking(login,punt){
      	        $.ajax({
            type: "POST",
            url: "controlador?accion=modificarank",
            dataType: "json",
            data: { "login": login, "puntos": punt },
            success: function(data) {
                
                if (data.error!=null) {

					alert(data.error);
				} else {

				}
            },
            error: function (xhr, ajaxOptions, thrownError) {
                console.log(xhr.headers);
                console.log(xhr.status);
                console.log(xhr.responseText);
                console.log(ajaxOptions);
                console.log(thrownError);
            }           
        });
      	}
      	
      	
      	function regitaciert(titulo, nombreusu){
      	        $.ajax({
            type: "POST",
            url: "controlador?accion=registraraci",
            dataType: "json",
            data: { "titPelicula": titulo, "user": nombreusu },
            success: function(data) {
                
                if (data.error!=null) {

					alert(data.error);
				} else {
				
 
				}
            },
            error: function (xhr, ajaxOptions, thrownError) {
                console.log(xhr.headers);
                console.log(xhr.status);
                console.log(xhr.responseText);
                console.log(ajaxOptions);
                console.log(thrownError);
            }           
        });
      	}
		

		//PETICION PARA COMPROBAR SI HA ACERTADO EL FOTOGRAMA
		    $('.comp').click(function(){
   				titulo= $('.groupid').prop('value'); 
   				foto=$('#archivo').val();
				var user =$('.nombrenav').text();
			 $.ajax({
            type: "POST",
            url: "controlador?accion=comprobar",
            dataType: "json",
            data: { "archivo": foto, "titPelicula": titulo },
            success: function(data) {
                if (data.error!=null) {
                	
					pjuego();
				} else {
				acertada++;
				regitaciert(titulo,user);
				$('.acert').text(acertada);
				pjuego();	
				//darpartidadiez();			
				}
            },
            error: function (xhr, ajaxOptions, thrownError) {
                console.log(xhr.headers);
                console.log(xhr.status);
                console.log(xhr.responseText);
                console.log(ajaxOptions);
                console.log(thrownError);
            }           
        });
  				});

	//PETICION RANKING 10 PRIMEROS
	   $('.rankingt').click(function(){
              $.ajax({
            type: "GET",
            url: "controlador?accion=rankingdiez",
            dataType: "json",
            success: function(data) {
                      var htmlNuevaFila="";

                for(i = 0; i < data.length; i++){
                          htmlNuevaFila+= '<tr><td>'+data[i].login+'</td><td>'+data[i].puntos+'</td></tr>';

                      }
                      $('#ranking').html(htmlNuevaFila);
            }, 
            error: function (xhr, ajaxOptions, thrownError) {
                console.log(xhr.headers);
                console.log(xhr.status);
                console.log(xhr.responseText);
                console.log(ajaxOptions);
                console.log(thrownError);
            }
        });
        
        $('.rankingnoreg').show();
      });		    

	//PETICION RANKING
	   $('.rankingt').click(function(){
              $.ajax({
            type: "GET",
            url: "controlador?accion=ranking",
            dataType: "json",
            success: function(data) {
                      var htmlNuevaFila="";

                for(i = 0; i < data.length; i++){
                          htmlNuevaFila+= '<tr><td>'+data[i].login+'</td><td>'+data[i].puntos+'</td></tr>';

                      }
                      $('#ranking').html(htmlNuevaFila);
            }, 
            error: function (xhr, ajaxOptions, thrownError) {
                console.log(xhr.headers);
                console.log(xhr.status);
                console.log(xhr.responseText);
                console.log(ajaxOptions);
                console.log(thrownError);
            }
        });
        
        $('.rankingnoreg').show();
      });


});
</script>
    
    
    
</html>