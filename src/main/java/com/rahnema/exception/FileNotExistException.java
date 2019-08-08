package com.rahnema.exception;

import java.io.FileNotFoundException;

public class FileNotExistException extends FileNotFoundException {

    public FileNotExistException(String msg) {
        super(msg);
    }
}
