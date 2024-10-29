package com.t1.techradar.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class Utils {

    public static final ResponseStatusException BAD_REQUEST
            = new ResponseStatusException(HttpStatus.BAD_REQUEST, "Некорректные параметры запроса");
    public static final ResponseStatusException NOT_FOUND
            = new ResponseStatusException(HttpStatus.NOT_FOUND, "Технология не найдена");

}
