package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {

        MethodSignature methodSignature;

        String nonArgumentValues = signatureString.substring(0, signatureString.indexOf("("));
        StringTokenizer st = new StringTokenizer(nonArgumentValues, " ");
        int nonArgumentValuesCount = st.countTokens();

        if(nonArgumentValuesCount == 2) {
            String returnType = st.nextToken();
            String methodName = st.nextToken();

            String valuesInsideParenthesis = signatureString.substring(signatureString.indexOf("("));
            StringTokenizer paramsInsideParenthesis = new StringTokenizer(valuesInsideParenthesis, " ,()");

            List<MethodSignature.Argument> arguments = new ArrayList<>();

            while (paramsInsideParenthesis.hasMoreTokens()) {
                String type = paramsInsideParenthesis.nextToken();
                String name = paramsInsideParenthesis.nextToken();
                arguments.add(new MethodSignature.Argument(type, name));
            }

            methodSignature = new MethodSignature(methodName, arguments);
            methodSignature.setReturnType(returnType);
            return methodSignature;

        } else {
            String accessModifier = st.nextToken();
            String returnType = st.nextToken();
            String methodName = st.nextToken();

            StringTokenizer insideParenthesis = new StringTokenizer(signatureString, "()");

            if (insideParenthesis.countTokens() == 0) {
                methodSignature = new MethodSignature(methodName);
                methodSignature.setAccessModifier(accessModifier);
                methodSignature.setReturnType(returnType);
                return methodSignature;
            } else {
                String valuesInsideParenthesis = signatureString.substring(signatureString.indexOf("("));
                StringTokenizer paramsInsideParenthesis = new StringTokenizer(valuesInsideParenthesis, " ,()");

                List<MethodSignature.Argument> arguments = new ArrayList<>();

                while (paramsInsideParenthesis.hasMoreTokens()) {
                    String type = paramsInsideParenthesis.nextToken();
                    String name = paramsInsideParenthesis.nextToken();
                    arguments.add(new MethodSignature.Argument(type, name));
                }

                methodSignature = new MethodSignature(methodName, arguments);
                methodSignature.setAccessModifier(accessModifier);
                methodSignature.setReturnType(returnType);
                return methodSignature;
            }
        }
    }

    public static void main(String[] args) {

        String s = "String repeat(String value, int times)";
        s = s.substring(s.indexOf("("));
        StringTokenizer stringTokenizer1 = new StringTokenizer(s, "()");
        System.out.println(stringTokenizer1.countTokens());



        while(stringTokenizer1.hasMoreTokens())
            System.out.println(stringTokenizer1.nextToken());


    }
}
