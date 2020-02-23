package com.beinsport;

import org.testng.ITestContext ;
import org.testng.ITestListener ;
import org.testng.ITestResult ;

public class TestListener implements ITestListener {

    @Override
    public void onFinish(ITestContext Result) { }

    @Override
    public void onStart(ITestContext Result) { }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult Result) { }

    @Override
    public void onTestFailure(ITestResult Result) {
        System.out.println("Testcase failed is ######################## : "+ Result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult Result) {
        System.out.println("Testcase Skipped is ######################## : "+Result.getName());

    }

    @Override
    public void onTestStart(ITestResult Result) {
        System.out.println(" test case started ######################## : " + Result.getName());

    }

    @Override
    public void onTestSuccess(ITestResult Result) {
        System.out.println("Testcase passed is ######################## : "+Result.getName());

    }


}
