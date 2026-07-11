package org.skypro.skyshop.exeptions;

public class ShopError {
    private final String message;
    private final String code;
    public ShopError() {
        this.message = "Такого товар нет!";
        this.code = "404";
    }
    @Override
    public String toString() {
        return  "Код ошибки: " + this.code + " : " + this.message;
    }

    public String getMessage() {
        return message;
    }
    public String getCode() {
        return code;
    }
}
