package io.peaches.academy.service.base.exception;

import io.peaches.academy.common.base.result.ResultCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
//@EqualsAndHashCode(callSuper = true)
public class AcademyException extends RuntimeException {

    private Integer code;

    public AcademyException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public AcademyException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }

    @Override
    public String toString() {
        return "AcademyException{" +
                "code=" + code +
                ", message" + this.getMessage() +
                '}';
    }
}

