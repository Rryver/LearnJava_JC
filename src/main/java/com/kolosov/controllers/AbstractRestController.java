package com.kolosov.controllers;

import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = AbstractRestController.BASE_REST_URL)
abstract class AbstractRestController {

    public static final String BASE_REST_URL = "/api/";
}
