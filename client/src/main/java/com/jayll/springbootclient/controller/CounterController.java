package com.jayll.springbootclient.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

public interface CounterController {
    @RequestMapping("counter/{id}")
    Long get(@PathVariable("id") String key);

    @PostMapping("counter/{id}/incr")
    void incr(@PathVariable("id") String key);
}
