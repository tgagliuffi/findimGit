jQuery.extend(jQuery.validator.messages, {
    required: "Este campo es requerido.",
    remote: "Por favor ingrese este campo.",
    date: "Por favor ingrese una fecha valida.",
    number: "Por favor ingrese un numero valido.",
    digits: "Por favor ingrese sólo numeros.",
    
    maxlength: jQuery.validator.format("Por favor ingrese no mas de {0} caracteres."),
    minlength: jQuery.validator.format("Por favor ingrese al menos {0} caracteres."),
    rangelength: jQuery.validator.format("Por favor ingrese un valor entre  {0} y {1} caracteres."),
    range: jQuery.validator.format("Por favor ingrese un valor entre  {0} y {1}."),
    max: jQuery.validator.format("Por favor ingrese un valor igual o menor a {0}."),
    min: jQuery.validator.format("Por favor ingrese un valor igual o superior a {0}.")
});