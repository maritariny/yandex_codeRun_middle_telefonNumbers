import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        List<String> numbers = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
           numbers.add(reader.readLine());
        }
        int m = Integer.parseInt(reader.readLine());
        ArrayList<Template> list = new ArrayList<>(m);
        for (int i = 0; i < m; i++) {
            String temp = reader.readLine();
            String onlyNumbers = temp.replaceAll("[^\\dX]", "");
            Integer length = onlyNumbers.length();
            int index = temp.indexOf("(");
            int index2 = temp.indexOf(")");
            int index3 = temp.indexOf("-");
            int index4 = temp.lastIndexOf(" ");
            String countryCode = temp.substring(1, index - 1);
            String operatorCode = temp.substring(index + 1, index2);
            String personalNumber = temp.substring(index2 + 2, index3 - 1);
            String country = temp.substring(index3 + 2, index4);
            String operator = temp.substring(index4 + 1);
            int index5 = personalNumber.indexOf("X");
            String personalNumberRegEx = personalNumber;
            if (index5 != -1) {
                int numberOfX = personalNumber.length() - index5;
                personalNumberRegEx = personalNumber.substring(0, index5) + "\\d{" + numberOfX + "}";
            }
            String regEx = countryCode + operatorCode + personalNumberRegEx;
            Template t = new Template();
            t.setRegEx(regEx);
            t.setTemplate(temp);
            t.setLength(length);
            t.setCountryCode(countryCode);
            t.setOperatorCode(operatorCode);
            t.setPersonalNumber(personalNumber);
            t.setCountry(country);
            t.setOperator(operator);
            list.add(t);
        }

        for (String number : numbers) {
            String onlyNumbers = number.replaceAll("[^\\d]", "");
            for (Template t : list) {
                if (Pattern.matches(t.getRegEx(), onlyNumbers)) {
                    String PersonalNumber = onlyNumbers.substring(onlyNumbers.length() - t.getPersonalNumber().length());
                    String result = "+" + t.getCountryCode() + " (" + t.getOperatorCode() + ") "
                            + PersonalNumber + " - " + t.getCountry() + " " + t.getOperator();
                    System.out.println(result);
                    break;
                }
            }
        }
        reader.close();
    }
}

class Template {
    private String template = "";
    private String regEx = "";
    private Integer length = 0;
    private String countryCode = "";
    private String country = "";
    private String operatorCode = "";
    private String operator = "";
    private String personalNumber = "";

    public Template() {
    }

    public int getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getOperatorCode() {
        return operatorCode;
    }

    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getRegEx() {
        return regEx;
    }

    public void setRegEx(String regEx) {
        this.regEx = regEx;
    }
}