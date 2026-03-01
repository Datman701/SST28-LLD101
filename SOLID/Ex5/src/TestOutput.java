public class TestOutput {
    public static void main(String[] args) {
        ExportRequest req = new ExportRequest("Weekly Report", SampleData.longBody());
        
        System.out.println("=== Original body ===");
        System.out.println(SampleData.longBody());
        System.out.println("Length: " + SampleData.longBody().length());
        
        System.out.println("\n=== CSV Output ===");
        CsvExporter csv = new CsvExporter();
        ExportResult csvResult = csv.export(req);
        System.out.println(new String(csvResult.bytes));
        System.out.println("Bytes: " + csvResult.bytes.length);
        
        System.out.println("\n=== JSON Output ===");
        JsonExporter json = new JsonExporter();
        ExportResult jsonResult = json.export(req);
        System.out.println(new String(jsonResult.bytes));
        System.out.println("Bytes: " + jsonResult.bytes.length);
    }
}
