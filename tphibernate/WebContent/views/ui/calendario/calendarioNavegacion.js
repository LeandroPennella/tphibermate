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
    	  var anchoSideBar=11;//vw
    	  var anchoScrollBar=17;//px
        $('#mySidenav').toggleClass('active');
        if (!$('#mySidenav').hasClass('active')) {
          $('#mySidenav').css('width', anchoSideBar+'vw');
          $('#main').css('margin-left', anchoSideBar+'vw');

          //12vw sidebar
          //1vw main padd left
          //17px scrollbar
          
          $('.calendario-cabecera').css('width', 'calc((99vw - 12vw))'); //96vw-12vw(sidebar)-1vw(main-padd-left)
          $('.calendario-cuerpo').css('width', 'calc((99vw - 12vw))'); //100vw-12vw(side)-1vw(main-padd-left)
          $('.columnas-horas').css('width', 'calc((99vw - 12vw - 17px)/36)');// 17px(scrollbar)
          $('.columnas-dias').css('width', 'calc(((99vw - 12vw - 17px)/36)*5)');


        } else {
          $('#mySidenav').css('width', '0px');
          $('#main').css('margin-left', '0px');
          
          $('.calendario-cabecera').css('width', 'calc(99vw )'); 
          $('.calendario-cuerpo').css('width', 'calc(99vw)'); //1vw(main) 
          $('.columnas-horas').css('width', 'calc((99vw - 17px) / 36)'); // 17px(scrollbar)
          $('.columnas-dias').css('width', 'calc(((99vw - 17px) / 36)*5)');
          
          //calc((100vw-8px)/36)
          //calc(((100vw - 8px)/36)*5)";
        }
      }