package io.github.jvondoellinger.rising_helpdesk.sharedkernel;

public class KernelException extends Exception{
	public KernelException() {
	}

	public KernelException(String message) {
		super(message);
	}

	public KernelException(String message, Throwable cause) {
		super(message, cause);
	}

	public KernelException(Throwable cause) {
		super(cause);
	}

	public KernelException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public static KernelException unexpected(String message) {
		return new KernelException(message);
	}

	public static KernelException conflict(String message) {
		return new KernelException(message);
	}

	public static KernelException external(String message) {
		return new KernelException(message);
	}

	public static KernelException validation(String message) {
		return new KernelException(message);
	}
}
