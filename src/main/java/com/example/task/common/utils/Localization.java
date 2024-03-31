package com.example.task.common.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class Localization {
    private final MessageSource messageSource;

    /**
     * Gets message from message bundle
     */
    public String getMessage(String message) {
        return messageSource.getMessage(message,
                null,
                LocaleContextHolder.getLocale());
    }
}
