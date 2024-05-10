package org.example.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.app.exceptions.ConnectException;
import org.example.app.utils.logging.LoggingErrorsMsg;
import org.example.app.utils.logging.LoggingSuccessMsg;
import org.example.app.views.AppView;

public class App {
    private final AppView startApp = new AppView();
    private static final Logger CONSOLE_LOGGER =
            LogManager.getLogger("console_logger");

    public void run() {
        try{
            CONSOLE_LOGGER.info(LoggingSuccessMsg.APP_RUN.getMsg());
            startApp.appViewProcessing();
            CONSOLE_LOGGER.info(LoggingSuccessMsg.APP_EXIT.getMsg());
        } catch (ConnectException e) {
            CONSOLE_LOGGER.error(LoggingErrorsMsg.DB_CONNECTION_ERROR.getMsg());
            CONSOLE_LOGGER.info(LoggingSuccessMsg.APP_EXIT.getMsg());
        }

    }

   public static void main(String[] args) {
        new App().run();
    }
}
