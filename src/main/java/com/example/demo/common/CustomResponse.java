package com.example.demo.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

@Builder
@AllArgsConstructor
@ToString
public class CustomResponse<T> {

	private String message;
	private String status;
	private T data;
	private String errorDescription;

	public String getMessage() {
		return message;
	}

	public void setMessage(String mensaje) {
		this.message = mensaje;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String descripcionError) {
		this.errorDescription = descripcionError;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String estatus) {
		this.status = estatus;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public CustomResponse() {
		super();
	}

	public CustomResponse(String mensaje, String descripcionError, String estatus) {
		super();
		this.message = mensaje;
		this.errorDescription = descripcionError;
		this.status = estatus;
	}

	public CustomResponse(String estatus, T data) {
		super();
		this.status = estatus;
		this.data = data;
	}

	public CustomResponse(String mensaje, String estatus, T data) {
		super();
		this.message = mensaje;
		this.status = estatus;
		this.data = data;
	}
	
}
