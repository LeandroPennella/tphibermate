package ar.edu.uces.web2.tphibernate.modelo.validadores;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ar.edu.uces.web2.tphibernate.modelo.form.UsuarioForm;



@Component
public class UsuarioFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return UsuarioForm.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object object, Errors errors) {
	
	}
}
