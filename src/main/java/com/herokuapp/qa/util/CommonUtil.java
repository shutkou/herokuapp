package com.herokuapp.qa.util;

import java.io.File;

public class CommonUtil {

	static final String USER_DIR = "user.dir";
	
	public static File getClasspathRoot() {
		return new File(System.getProperty(USER_DIR));
	}
}
