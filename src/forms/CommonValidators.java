package forms;

public class CommonValidators {

    public static void validateEmptyString(String value) throws ValidationError {
        if (value.length() == 0) {
            throw new ValidationError("No ha ingresado algún caracter. "
                    + "Por favor, ingrese algúno");
        }
    }

    public static void validateStringBoundries(String value, int min, int max) 
        throws ValidationError {
        if (!(value.length() >= min && value.length() <= max)) {
            throw new ValidationError(
                String.format(
                    "El numero de caracteres, del valor digitado, " +
                    "debe ser mayor o igual que %d y menor o igual " +
                    "que %d. Por favor, intente con uno que esté en el rango",
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
            throw new ValidationError("El valor ingresado no es un decimal. "
                    + "Pof favor ingresa un valor que sea decimal");
        }
    }

    public static void validateDoubleBoundries(double value, int min, int max) 
        throws ValidationError{
        if (!(value >= min && value <= max)){
            throw new ValidationError(
                String.format(
                    "El valor ingresado debe ser mayor o igual que %d y menor "
                            + "o igual que %d. Por favor, intente con uno que "
                            + "esté en el rango",
                    min, 
                    max
                )
            );
        }
    }
    }
