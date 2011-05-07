package br.com.bronx.memes.creator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;


public class MemeAnnotationsCreator {

	public static void main(String[] args) throws IOException {
		
		if (args.length == 0) {
			args = new String[1];
			args[0] = "memes.memes";
		}
		
		Scanner scanner = new Scanner(new FileInputStream(args[0]));

		while(scanner.hasNextLine()) {
			String annotation = scanner.nextLine().trim();
			createAnnotation(annotation);
		}
		
	}

	private static void createAnnotation(String annotation) throws FileNotFoundException {
		
		boolean hasStringParam = annotation.endsWith("(String)");
		if (hasStringParam) {
			System.out.println("Index: " + annotation.lastIndexOf("(String)"));
			annotation = annotation.substring(0,annotation.lastIndexOf("(String)"));
		}
		createAnnotationFile(annotation, hasStringParam);
		
	}

	private static void createAnnotationFile(String annotation, boolean hasStringParam) throws FileNotFoundException {
		if (annotation.startsWith("@"))
			annotation = annotation.substring(1);
		
		PrintStream printStream = new PrintStream(annotation + ".java");
		printLicence(printStream);
		
		printStream.println("");
		printStream.println("package br.com.bronx.memes.annotation;");
		printStream.println("");
		printStream.println("public @interface " + annotation + " {");
		if (hasStringParam)
			printStream.println("	String value() default \"\";");
		printStream.println("}");
		
	}

	private static void printLicence(PrintStream printStream) {
		printStream.println("/***");
		printStream.println(" * Copyright (c) 2011 Diego Maia da Silva www.diegomaia.net/memeannotations");
		printStream.println(" * All rights reserved.");
		printStream.println(" *");
		printStream.println(" * Licensed under the Apache License, Version 2.0 (the \"License\");");
		printStream.println(" * you may not use this file except in compliance with the License.");
		printStream.println(" * You may obtain a copy of the License at");
		printStream.println(" *");
		printStream.println(" * 	http://www.apache.org/licenses/LICENSE-2.0");
		printStream.println(" *");
		printStream.println(" * Unless required by applicable law or agreed to in writing, software");
		printStream.println(" * distributed under the License is distributed on an \"AS IS\" BASIS,");
		printStream.println(" * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.");
		printStream.println(" * See the License for the specific language governing permissions and");
		printStream.println(" * limitations under the License.");
		printStream.println(" */");

	}
}
