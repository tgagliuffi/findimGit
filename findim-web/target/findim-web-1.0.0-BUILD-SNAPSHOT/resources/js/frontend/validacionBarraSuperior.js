$(document).ready(function() {

    var formNavigation = $("#form_navigation");
    
    /// Controlador del formulario 
    /// Realiza la validacion del formulario y redirecciona a la ventana de muestra
    formNavigation.validate({
        rules: {
            form_navigation_documento: {
                required: true,
                number: true,
                minlength:8,
                maxlength:8
            }
        },
        
        messages: {
            form_navigation_documento: {
                number: "Utilice solo numeros",
                minlength: "Minimo 8 caracteres",
                maxlength:"Maximo 8 caracteres"
            }
        },
        
     
        
        
        submitHandler: function(form) { 
            
            switch( $( "#form_navigation_accion" ).val() ) {
                case '1':
                    formNavigation.attr("action", "filtro-resultado.html");                
                    break;
                case '2':
                    formNavigation.attr("action", "busqueda-resultado.html");  
                    break;
                default:
                    //code block
                    break;
            };          
            console.log("listo");
            form.submit();
            console.log("envio realizado ");
    
        }
        
    });

    // Accion
/*
    function procesarFormNav(e){        
        var url = ""; // la url        
        globalDNI = e.elements[2].value;
        globalDocType = e.elements[1].value;
        
        switch( $( "#form_navigation_accion" ).val() ) {
            case '1':
                formNavigation.attr("action", "filtro-resultado.html");                
                break;
            case '2':
                formNavigation.attr("action", "busqueda-resultado.html");  
                break;
            default:
                //code block
                break;
        }
        
       
        
    }
    */

});//doc Ready