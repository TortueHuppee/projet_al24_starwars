package manufacture.web.util;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("manufacture.web.util.IntegerConverter")
public class IntegerConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component,
            String value) {
        
        String inputValue = value.toString();

        Integer integerValue = 0;
        try {
            integerValue = Integer.parseInt(inputValue);
        } catch (Exception e) {
            FacesMessage message = new FacesMessage("Valeur invalide, veuillez entrer un nombre. Exemple : 99.", "Valeur invalide");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ConverterException(message);
        }
        
        if (integerValue <= 0)
        {
            FacesMessage message = new FacesMessage("Valeur invalide, veuillez entrer un nombre. Exemple : 99.", "Valeur invalide");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ConverterException(message);
        }
        return integerValue;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component,
            Object value) {
        return value.toString();
    }
}
