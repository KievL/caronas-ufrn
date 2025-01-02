package br.ufrn.umbrella.caronasufrn.models.constants;

public class Paths {
    public final static String[] GET_PUBLIC_PATHS = {
        "/v3/api-docs/**",
        "/swagger-ui/**",
        "/swagger-ui.html",
        "/documentation",
        
    };
    public final static String[] GET_PRIVATE_PATHS ={
        "/user",
        "/ride"
    };

    public final static String[] POST_PUBLIC_PATHS = {
        "/login",
        "/user",
        "/ride"
    };

    public final static String[] POST_PRIVATE_PATHS = {

    };

    public final static String[] PUT_PRIVATE_PATHS = {

    };

    public final static String[] DELETE_PRIVATE_PATHS = {

    };
}
