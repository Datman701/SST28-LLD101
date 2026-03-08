package com.example.reports;

/**
 * TODO (student):
 * Implement Proxy responsibilities here:
 * - access check
 * - lazy loading
 * - caching of RealReport within the same proxy
 */
public class ReportProxy implements Report {

    private final String reportId;
    private final String title;
    private final String classification;
    private final AccessControl accessControl = new AccessControl();
    private RealReport realReport;

    public ReportProxy(String reportId, String title, String classification) {
        this.reportId = reportId;
        this.title = title;
        this.classification = classification;
        this.realReport = null;
    }

    public RealReport getInstance(){
        if(realReport ==null){
            realReport = new RealReport(reportId , title  , classification);
        }
        return realReport;
    }

    @Override
    public void display(User user) {
        // Starter placeholder: intentionally incorrect.
        // Students should remove direct real loading on every call.
        if(accessControl.canAccess(user , classification)){
            this.getInstance().display(user);
        }
        else{
            System.out.println("Access Denied for " + user.getName() + " to report: " + title);
        }

    }
}
