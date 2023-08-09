package com.epam.mjc;

import java.io.IOException;
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
        String methodName;
        String accessModifier = null;
        String returnType;
        List<MethodSignature.Argument> arguments;
        try {
            StringTokenizer st = new StringTokenizer(signatureString, "()");
            ArrayList<String> signatures = new ArrayList<>();
            ArrayList<String> parameters = new ArrayList<>();

            for (int i = 0; i <= st.countTokens() && st.hasMoreTokens(); i++) {
                String delim = i < 1 ? " " : ",";
                StringTokenizer parts = new StringTokenizer(st.nextToken(), delim);

                if (i < 1) {
                    while (parts.hasMoreTokens()) {
                        signatures.add(parts.nextToken());
                    }
                } else {
                    while (parts.hasMoreTokens()) {
                        parameters.add(parts.nextToken().trim());
                    }
                }
            }

            if (signatures.size() > 2) {
                accessModifier = signatures.get(0);
                returnType = signatures.get(1);
                methodName = signatures.get(2);
            } else {
                returnType = signatures.get(0);
                methodName = signatures.get(1);
            }
            arguments = makeArguments(parameters);
        } catch (Exception e) {
            throw new UnsupportedOperationException("You should implement this method.");
        }

        MethodSignature ms = new MethodSignature(methodName,arguments);
        ms.setAccessModifier(accessModifier);
        ms.setReturnType(returnType);

        return ms;
    }

    private List<MethodSignature.Argument> makeArguments (ArrayList<String> arguments) {
        List<MethodSignature.Argument> listArguments = new ArrayList<>() ;

        for( String arg : arguments) {
            String[] argArray = arg.split(" ");
            MethodSignature.Argument argObject = new MethodSignature.Argument(argArray[0],argArray[1]);
            listArguments.add(argObject);
        }
        return  listArguments;
    }
}
