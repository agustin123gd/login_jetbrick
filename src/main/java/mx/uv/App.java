package mx.uv;

import static spark.Spark.*;
import com.google.gson.*;

import mx.uv.datos.Usuario;
import spark.ModelAndView;
import spark.template.jetbrick.JetbrickTemplateEngine;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Hello world!
 *
 */
public class App 
{
    private static Usuario uriel = new Usuario("1a","uriel@uriel.com", "123");
    private static Usuario agustin = new Usuario("2b","agustin@agustin.com", "123");
    private static Usuario irving = new Usuario("3c","irving@irving.com", "123");
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        staticFiles.location("/");

        get("/", (req, res) -> {
            return "respuesta";
        });


        get("/entrar", (req, res) -> {        
            String email = req.queryParams("email");
            String password = req.queryParams("password");

            Usuario u = new Usuario();
            u.setEmail(email);
            u.setPassword(password);
            Map<String, Object> model = new HashMap<>();

            if(u.getEmail().equals(uriel.getEmail()) || u.getEmail().equals(agustin.getEmail()) || u.getEmail().equals(irving.getEmail())){
                if(u.getPassword().equals(uriel.getPassword()) || u.getPassword().equals(agustin.getPassword()) || u.getPassword().equals(irving.getPassword()) ){
                    model.put("nombre", u);
                    return new JetbrickTemplateEngine().render(new ModelAndView(model, "templates/lista.jetx"));
                }
            }else{
                model.put("nombre", "Error: usuario incorrecto");
                return new JetbrickTemplateEngine().render(new ModelAndView(model, "templates/error.jetx"));
            }
            return "";
        });
    }
}
