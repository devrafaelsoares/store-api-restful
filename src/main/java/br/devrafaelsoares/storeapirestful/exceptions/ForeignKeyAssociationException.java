package br.devrafaelsoares.storeapirestful.exceptions;

public class ForeignKeyAssociationException extends RuntimeException {

    public ForeignKeyAssociationException(String message) {
        super(message);
    }

}
