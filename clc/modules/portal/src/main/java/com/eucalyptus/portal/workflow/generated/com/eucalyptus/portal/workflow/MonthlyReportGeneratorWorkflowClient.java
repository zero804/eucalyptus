/*
 * This code was generated by AWS Flow Framework Annotation Processor.
 * Refer to Amazon Simple Workflow Service documentation at http://aws.amazon.com/documentation/swf 
 *
 * Any changes made directly to this file will be lost when 
 * the code is regenerated.
 */
 package com.eucalyptus.portal.workflow;

import com.amazonaws.services.simpleworkflow.flow.core.Promise;
import com.amazonaws.services.simpleworkflow.flow.StartWorkflowOptions;
import com.amazonaws.services.simpleworkflow.flow.WorkflowClient;

/**
 * Generated from {@link com.eucalyptus.portal.workflow.MonthlyReportGeneratorWorkflow}. 
 * Used to invoke child workflows asynchronously from parent workflow code.
 * Created through {@link MonthlyReportGeneratorWorkflowClientFactory#getClient}.
 * <p>
 * When running outside of the scope of a workflow use {@link MonthlyReportGeneratorWorkflowClientExternal} instead.
 */
public interface MonthlyReportGeneratorWorkflowClient extends WorkflowClient
{

    /**
     * Generated from {@link com.eucalyptus.portal.workflow.MonthlyReportGeneratorWorkflow#generateMonthlyReport}
     */
    Promise<Void> generateMonthlyReport(String year, String month, java.util.Date until);

    /**
     * Generated from {@link com.eucalyptus.portal.workflow.MonthlyReportGeneratorWorkflow#generateMonthlyReport}
     */
    Promise<Void> generateMonthlyReport(String year, String month, java.util.Date until, Promise<?>... waitFor);

    /**
     * Generated from {@link com.eucalyptus.portal.workflow.MonthlyReportGeneratorWorkflow#generateMonthlyReport}
     */
    Promise<Void> generateMonthlyReport(String year, String month, java.util.Date until, StartWorkflowOptions optionsOverride, Promise<?>... waitFor);

    /**
     * Generated from {@link com.eucalyptus.portal.workflow.MonthlyReportGeneratorWorkflow#generateMonthlyReport}
     */
    Promise<Void> generateMonthlyReport(Promise<String> year, Promise<String> month, Promise<java.util.Date> until);

    /**
     * Generated from {@link com.eucalyptus.portal.workflow.MonthlyReportGeneratorWorkflow#generateMonthlyReport}
     */
    Promise<Void> generateMonthlyReport(Promise<String> year, Promise<String> month, Promise<java.util.Date> until, Promise<?>... waitFor);

    /**
     * Generated from {@link com.eucalyptus.portal.workflow.MonthlyReportGeneratorWorkflow#generateMonthlyReport}
     */
    Promise<Void> generateMonthlyReport(Promise<String> year, Promise<String> month, Promise<java.util.Date> until, StartWorkflowOptions optionsOverride, Promise<?>... waitFor);

}