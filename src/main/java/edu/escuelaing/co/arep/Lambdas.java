package edu.escuelaing.co.arep;

import java.util.HashMap;
import java.util.Map;


/**
 * Hello world!
 *
 */
public class Lambdas
{
    private static Map<String, ParamStrService> services = new HashMap();
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        get("/hello", str -> "Hola " + str);
        get("/author", str -> "Camilo");
        get("/cos", str -> {return "" + Math.cos(Double.parseDouble(str));});
        get("/sen", new ParamStrService() {
            @Override
            public String execute(String str) {
                return "" + Math.sin(Double.parseDouble(str));
            }
        });
        ParamStrService s = findBy("/hello");
        System.out.println("La funcion \"/hello\" retorna: " +s.execute("Camilo"));
        System.out.println(findBy("/author").execute("AAAAAAAA"));
        System.out.println(findBy("/sen").execute("1.5707963267948966192313216916397514420985846996875529104874722961"));
        System.out.println(findBy("/cos").execute("0"));
    }

    public static ParamStrService findBy(String nombreService) {
        return services.get(nombreService);
    }

    public static void get(String param, ParamStrService service){
        services.put(param, service);
    }
}
