public class Main {
    public static void main(String[] args) {
        System.out.println("=== Evaluation Pipeline ===");

        // Create dependencies
        PlagiarismDetector detector = new PlagiarismChecker();
        Grader grader = new CodeGrader();
        ReportGenerator reporter = new ReportWriter();
        Rubric rubric = new Rubric();

        // Inject dependencies into pipeline
        EvaluationPipeline pipeline = new EvaluationPipeline(detector, grader, reporter, rubric);

        Submission sub = new Submission("23BCS1007", "public class A{}", "A.java");
        pipeline.evaluate(sub);
    }
}
