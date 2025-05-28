/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package laboratoryotomation;

/**
 *
 * @author safiyekantar
 */
public class TestParameter {

    private String parameterName; // (Example: "Hemoglobin", "RBC")
    private String result;        //  (Example: "12 g/dl", "4.5 million/uL")
    private String evaluation = "";

    public TestParameter(String parameterName, String result) {
        this.parameterName = parameterName;
        this.result = result;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getParameterName() {
        return parameterName;
    }

    public String getResult() {
        return result;
    }

    @Override
    public String toString() {
        return parameterName + ": " + result;
    }
}
