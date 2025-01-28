package com.shieldteq.customer.dtos.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(
        name = "Success Response",
        description = "Response type when request process successfully"
)
@Data
@AllArgsConstructor
public class ResponseDTO<T> {
    @Schema(description = "Status code")
    private String status;
    @Schema(description = "Status message")
    private String message;
    @Schema(description = "Response data")
    private T data;
}
