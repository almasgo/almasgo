package com.luthfihariz.almasgocore.exception;

import com.luthfihariz.almasgocore.controller.dto.ErrorCode;
import org.springframework.http.HttpStatus;

public class SendEmailFailException extends ApplicationException {

    public SendEmailFailException() {
        super(ErrorCode.ERROR_SEND_EMAIL_FAILURE.toString(),
                "error send email failure", HttpStatus.BAD_REQUEST);
    }
}
