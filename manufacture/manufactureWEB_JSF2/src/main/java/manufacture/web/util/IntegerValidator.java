package manufacture.web.util;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("manufacture.web.util.IntegerValidator")
public class IntegerValidator implements Validator {
    
    public void validate(FacesContext contexte, UIComponent component,
            Object value) throws ValidatorException {
        
        String inputValue = value.toString();

        Integer integerValue = 0;
        try {
            integerValue = Integer.parseInt(inputValue);
        } catch (Exception e) {
            FacesMessage message = new FacesMessage("Valeur invalide, veuillez entrer un nombre. Exemple : 99.", "Valeur invalide");
            message.setSeverity(FacesMessage.SEVERITY_FATAL);
            throw new ValidatorException(message);
        }

        if (integerValue <= 0)
        {
            FacesMessage message = new FacesMessage("Valeur invalide, veuillez entrer un nombre. Exemple : 99.", "Valeur invalide");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }

}
