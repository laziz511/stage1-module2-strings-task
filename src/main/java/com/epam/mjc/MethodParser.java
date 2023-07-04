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
        StringTokenizer tokenizer = new StringTokenizer(signatureString, "()");
        String[] nonArgumentValues = tokenizer.nextToken().split(" ");

        String accessModifier = null;
        String returnType;
        String methodName;
        List<MethodSignature.Argument> arguments = new ArrayList<>();

        if (nonArgumentValues.length == 2) {
            returnType = nonArgumentValues[0];
            methodName = nonArgumentValues[1];
        } else {
            accessModifier = nonArgumentValues[0];
            returnType = nonArgumentValues[1];
            methodName = nonArgumentValues[2];
        }

        if (tokenizer.hasMoreTokens()) {
            StringTokenizer paramsInsideParenthesis = new StringTokenizer(tokenizer.nextToken(), " ,");
            while (paramsInsideParenthesis.hasMoreTokens()) {
                String type = paramsInsideParenthesis.nextToken();
                String name = paramsInsideParenthesis.nextToken();
                arguments.add(new MethodSignature.Argument(type, name));
            }
        }

        MethodSignature methodSignature = new MethodSignature(methodName, arguments);
        methodSignature.setAccessModifier(accessModifier);
        methodSignature.setReturnType(returnType);
        return methodSignature;
    }
}
