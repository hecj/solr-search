package com.freedom.search.admin.exception;
/**
 * 模块角色已存在异常
 */
public class ModuleRoleExistException extends Exception {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	
	private static final long serialVersionUID = 1L;

	public ModuleRoleExistException() {
		super();
	}

	public ModuleRoleExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public ModuleRoleExistException(String message) {
		super(message);
	}

	public ModuleRoleExistException(Throwable cause) {
		super(cause);
	}

	
}
