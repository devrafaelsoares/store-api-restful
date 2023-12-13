package br.devrafaelsoares.storeapirestful.exceptions.entity;

public class ForeignKeyAssociationException extends RuntimeException {

    public ForeignKeyAssociationException(String message) {
        super(message);
    }

}
