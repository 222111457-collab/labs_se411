package edu.spu.se411.exception;

@SuppressWarnings("serial")
public class MissingInformationException extends Exception {
    public MissingInformationException(String message) {
        super(message);
    }
}