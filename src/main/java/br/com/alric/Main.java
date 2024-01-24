package br.com.alric;

import br.com.alric.core.BotCore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private final static Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        try{
            BotCore.init();
        } catch(Exception e){
            logger.error("Erro ao iniciar o bot!", e);
        }
    }
}