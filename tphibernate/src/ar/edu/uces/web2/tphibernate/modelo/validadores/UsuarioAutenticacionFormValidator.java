package ar.edu.uces.web2.tphibernate.modelo.validadores;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ar.edu.uces.web2.tphibernate.modelo.form.UsuarioAutenticacionForm;


@Component
public class UsuarioAutenticacionFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return UsuarioAutenticacionForm.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object object, Errors errors) {
		//UsuarioAutenticacionForm usuario= (UsuarioAutenticacionForm) object;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombreUsuario", "error.nombreUsuario.vacio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contrasenia", "error.contrasenia.vacio");
		/*
		if (!errors.hasFieldErrors("valorElegido")) {
			if ((usuario.getValorElegido()<1)||(usuario.getValorElegido()>100)) {
				errors.rejectValue("valorElegido", "error.intento.valor.fueraDeRango");
			}
		}
		*/
	}
	

}
