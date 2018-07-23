      $(function() {



		$('#calendarioenlinea').datepicker({
		    format: "dd/mm/yyyy",
		    language: localeLanguage,
		    
		    todayHighlight: true,
		    isInline: true
		}).on('changeDate', seleccionarFecha);

        $("#toggleBoton").click(function() {alternar();});
        alternar();
      });


  	function seleccionarFecha(){
  		var value = $('#calendarioenlinea').datepicker('getDate');
  		$(location).attr('href','../calendario/mostrarCalendario.do?anio='+value.getFullYear()+'&mes='+(value.getMonth()+1)+'&dia='+value.getDate());  	
  	}

      function alternar() {
        $('#mySidenav').toggleClass('active');
        if (!$('#mySidenav').hasClass('active')) {
          $('#mySidenav').css('width', '20vw');
          $('#main').css('margin-left', '20vw');

          /*$('[class^="mitable"]').css('width', 'calc(96vw-250px)');*/
          /*$('.mitable').css('width', 'calc(96vw-250px)');*/
          $('.calendario-cabecera').css('width', 'calc((96vw - 20vw) + 1px)'); /*1847.4*/
          $('.calendario-cuerpo').css('width', 'calc((96vw - 20vw) + 0.5vw + 0.5px)'); //96+0.5>scrollbar
          $('.columnas-horas').css('width', 'calc((96vw - 20vw)/36)');
          $('.columnas-dias').css('width', 'calc(((96vw - 20vw)/36)*5)');
          //calc((100vw-250px)/36)
          //calc(((100vw-250px)/36)*5)

        } else {
          $('#mySidenav').css('width', '0px');
          $('#main').css('margin-left', '0px');
          
          $('.calendario-cabecera').css('width', 'calc(96vw + 1px)'); /*1847.4*/
          $('.calendario-cuerpo').css('width', 'calc(96.5vw + 0.5px)'); //96+0.5>scrollbar
          $('.columnas-horas').css('width', 'calc(96vw/36)');
          $('.columnas-dias').css('width', 'calc((96vw/36)*5)');
          //calc((100vw-8px)/36)
          //calc(((100vw - 8px)/36)*5)";
        }
      }