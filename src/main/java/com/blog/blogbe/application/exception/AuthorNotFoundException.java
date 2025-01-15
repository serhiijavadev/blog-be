package com.blog.blogbe.application.exception;


public class AuthorNotFoundException extends RuntimeException {

    private static final String MSG_TEMPLATE = "Author with id '%s' not found";

    public AuthorNotFoundException(String id) {
        super(String.format(MSG_TEMPLATE, id));
    }
}
