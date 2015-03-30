package com.macuyiko.canaryconsole;

import org.python.util.PythonInterpreter;

public class CanaryParser {
	static public boolean parse(final PythonInterpreter interpreter, final String code, final boolean exec) throws Exception {
		try {
			if (exec) {
				interpreter.exec(code);
				return false;
			}
			interpreter.exec(code);
			return true;
		} catch (Throwable e) {
			throw new Exception(e);
		}
	}
}
