package com.hengsu.sure.core.controller;

import com.hengsu.sure.core.ErrorCode;
import com.hkntv.pylon.web.rest.ResponseEnvelope;
import com.hkntv.pylon.web.rest.annotation.RestApiController;
import com.hkntv.pylon.web.rest.exception.RestApiError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice(annotations = RestApiController.class)
public class RestApiRequestEntityExceptionHandler {

    private final Logger logger = LoggerFactory
            .getLogger(RestApiRequestEntityExceptionHandler.class);

    /**
     * handler the field validate exception
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> processValidationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        Map<String, String> fieldErrorsMap = new HashMap<String, String>();
        for (FieldError fieldError : fieldErrors) {
            fieldErrorsMap.put(fieldError.getField(), fieldError.getDefaultMessage());
            logger.error(fieldError.getField() + "-" + fieldError.getDefaultMessage());
        }

        RestApiError restApiError = new RestApiError();
        restApiError.setStatusCode(ErrorCode.FIELD_MUST.getCode().toString());
        restApiError.setMessage(fieldErrorsMap.toString());
        restApiError.setRawMessage(fieldErrorsMap.toString());

        ResponseEnvelope<Object> envelope = new ResponseEnvelope<Object>(restApiError, false);
        return new ResponseEntity<Object>(envelope, HttpStatus.OK);
    }

    /**
     * handler the json format exception
     *
     * @param ex
     * @param webRequest
     * @return
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> processValidationError(HttpMessageNotReadableException ex,
                                                         WebRequest webRequest) {

        logger.error(webRequest.getContextPath() + ":" + ex.getMessage());

        RestApiError restApiError = new RestApiError();
        restApiError.setStatusCode(ErrorCode.JSON_FORMAT_ERROR.getCode().toString());
        restApiError.setMessage(ErrorCode.JSON_FORMAT_ERROR.getErrorMsg());
        restApiError.setRawMessage(ex.getMessage());
        ResponseEnvelope<Object> envelope = new ResponseEnvelope<Object>(restApiError, false);
        return new ResponseEntity<Object>(envelope, HttpStatus.OK);
    }

    /**
     * handle parameter missing
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Object> processMissing(MissingServletRequestParameterException ex) {

        logger.error(ex.getMessage());

        RestApiError restApiError = new RestApiError();
        restApiError.setStatusCode(ErrorCode.PARAMETER_MUST.getCode().toString());
        restApiError.setMessage(ex.getMessage());
        restApiError.setRawMessage(ex.getMessage());
        ResponseEnvelope<Object> envelope = new ResponseEnvelope<Object>(restApiError, false);
        return new ResponseEntity<Object>(envelope, HttpStatus.OK);
    }


    //
}
