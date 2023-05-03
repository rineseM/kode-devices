package io.kodelabs.base.exceptions.custom;

public class BadRequestException extends BaseException {


    public BadRequestException(String message) {
        super(message);
    }

    @Override
    public int getstatusCode() {
        return 400;
    }
}
