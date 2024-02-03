package com.quind.pruebatecnica.configuration;

public class Constants {
    private Constants() {
        throw new IllegalStateException("Utility class");
    }
    public static final String RESPONSE_MESSAGE_KEY = "message";
    public static final String CUSTOMER_CREATED_MESSAGE = "Customer created successfully";
    public static final String CUSTOMER_UPDATED_MESSAGE = "Customer updated successfully";
    public static final String CUSTOMER_DELETED_MESSAGE = "Customer deleted successfully";
    public static final String RESPONSE_ERROR_MESSAGE_KEY = "error";
    public static final String CUSTOMER_IS_MINOR_MESSAGE = "El cliente es menor de edad";
    public static final String CUSTOMER_NOT_FOUND_MESSAGE = "Cliente no encontrado";
    public static final String CUSTOMER_ALREADY_EXISTS_MESSAGE = "El cliente ya existe";
    public static final String CUSTOMER_ALREADY_EXISTS_WITH_ID_MESSAGE = "No se puede actualizar sus datos, ya existe otro cliente con los datos proporcionados";

    public static final String PRODUCT_CREATED_MESSAGE = "Product created successfully";
    public static final String PRODUCT_ALREADY_EXISTS_WITH_ID_MESSAGE = "La cuenta ya existe, no es posible crear una nueva";

    public static final String ACCOUNT_TYPE_INVALID_MESSAGE = "No existe un tipo de cuenta para el numero de cuenta asociado";
    public static final String BALANCE_NEGATIVE_MESSAGE = "No puede tener un saldo negativo";


}
