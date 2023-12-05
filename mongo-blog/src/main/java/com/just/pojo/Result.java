package com.just.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private Object data;
    private boolean error;
    private String reason;
    public static Result success(Object data) {
        return new Result(data, false, null);
    }
    public static Result error(String reason) {
        return new Result(null, true, reason);
    }
}
