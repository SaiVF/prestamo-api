package py.com.prestamos.constants;

public class ApiConstants {
    public static final String ACTION_CONSULTAR_PRESTAMOS = "cobr-consultar-prestamos";
    public static final String ACTION_COBRAR_PRESTAMO = "cobr-cobrar-prestamo";
    public static final String ACTION_CONFIRMAR_TRANSACCION_TARJETA = "cobr-confirmar-transaccion";
    //Nuevas acciones para agentes con sistemas externos
    public static final String ACTION_CONSULTAR_PRESTAMOS_EXT = "cobr-consultar-prestamos-ext";
    public static final String ACTION_COBRAR_PRESTAMO_EXT = "cobr-cobrar-prestamo-ext";
    public static final String ACTION_REVERSAR_PRESTAMO_EXT = "cobr-reversar-prestamo-ext";

    //MAPPING
    public static final String URL_MAPPING_COBRO_PRESTAMO = "cobro-prestamos";
    public static final String URL_MAPPING_CONSULTAR_PRESTAMOS = URL_MAPPING_COBRO_PRESTAMO +"/consultar-prestamos";
    public static final String URL_MAPPING_COBRAR_PRESTAMO = URL_MAPPING_COBRO_PRESTAMO+"/cobrar";
    //nuevos url para agencias con sistemas externos
    public static final String URL_MAPPING_CONSULTAR_PRESTAMOS_EXT = URL_MAPPING_COBRO_PRESTAMO +"/ext/consultar-prestamos";
    public static final String URL_MAPPING_COBRAR_PRESTAMO_EXT = URL_MAPPING_COBRO_PRESTAMO+"/ext/cobrar";

    public static final String URL_CONFIRMAR_COBRO_PRESTAMO = URL_MAPPING_COBRO_PRESTAMO +"/%s/confirmar";

    //PARAMETERS
    public static final String PARAM_BODY = "body";
    public static final String PARAM_MONEDA = "moneda";
    public static final String PARAM_NUMERO_DOC = "numeroDoc";
    public static final String PARAM_TIPO_DOC = "tipoDoc";
    public static final String PARAM_PAIS_DOC = "paisDoc";
    public static final String PARAM_CUENTA = "cuenta";
    public static final String PARAM_TX_TOKEN = "token";
    public static final String PARAM_REFERENCIA = "ref";
    public static final String PARAM_TELEFONO = "tel";

    //info adicional KEY
    public static final String COMPROBANTE_KEY = "COMPROBANTE";
    public static final String MOVIMIENTO_RAA_KEY = "MOVIMIENTO_RAA";
    public static final String TOKEN_TICKET_KEY = "TOKEN_TICKET";
    public static final String DATOS_KEY = "DATOS";
    public static final String MOVIMIENTO_KEY = "MOVIMIENTO";
    public static final String TOKEN_CONTROL_KEY = "CONTROL_TOKEN";
    public static final String REFERENCIA_KEY = "REFERENCIA";
    public static final String TELEFONO_KEY = "TELEFONO";


    //OPERACIONES
    public static final Integer OPERACION_CREDITO_COBRO_USD = 316;
    public static final Integer OPERACION_CREDITO_COBRO_GS = 315;


}
