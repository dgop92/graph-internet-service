package forms;

public class CommonValidators {

    public static void validateEmptyString(String value) throws ValidationError {
        if (value.length() == 0) {
            throw new ValidationError("Por favor, debe ingresar algun nombre");
        }
    }

    public static void validateStringBoundries(String value, int min, int max) 
        throws ValidationError {
        if (!(value.length() >= min && value.length() <= max)) {
            throw new ValidationError(
                String.format(
                    "El nombre ingresado no debe ser mayor a %d o menor a %d",
                    min, 
                    max
                )
            );
        }
    }
    public static void validateCanBeDouble(String value) throws ValidationError {
        try {
            Double.parseDouble(value);
        } catch (Exception e) {
            throw new ValidationError("El valor ingresado no es un decimal");
        }
    }

    public static void validateDoubleBoundries(double value, int min, int max) 
        throws ValidationError{
        if (!(value >= min && value <= max)){
            throw new ValidationError(
                String.format(
                    "El valor ingresado debe ser mayor a %d o menor a %d",
                    min, 
                    max
                )
            );
        }
    }
    }
