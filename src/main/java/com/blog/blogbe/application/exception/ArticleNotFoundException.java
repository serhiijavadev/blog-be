package com.blog.blogbe.application.exception;


public class ArticleNotFoundException extends RuntimeException {

    private static final String MSG_TEMPLATE = "Article with id '%s' not found";

    public ArticleNotFoundException(String id) {
        super(String.format(MSG_TEMPLATE, id));
    }
}
