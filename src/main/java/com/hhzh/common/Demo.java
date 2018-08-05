package com.hhzh.common;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Demo {

    public static void main(String[] args) {
        try {
            int a = 3 / 0;
        } catch (Exception e) {

            log.error("calc error e", e);
            log.info("---");
        }
    }
}
