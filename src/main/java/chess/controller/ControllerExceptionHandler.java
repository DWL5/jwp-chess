package chess.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MissingRequestCookieException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(Controller.class);
    private static final Logger fileLogger = LoggerFactory.getLogger("file");

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> checkChessGameException(Exception exception) {
        log.error("checkChessGameException called. exception : " + exception.getMessage());
        fileLogger.info("파일 로깅 입니다.");
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler({MissingRequestCookieException.class})
    public ResponseEntity<String> checkCookieMissing(Exception exception) {
        log.error("checkChessGameException called. exception : " + exception.getMessage());
        fileLogger.info("파일 로깅 입니다.");
        return ResponseEntity.badRequest().body("로그인을 해 주세요.");
    }


}
