package com.crud.tasks.service.mail;

@FunctionalInterface
public interface EmailCreator {
    String buildEmailMessage(final String message);
}
