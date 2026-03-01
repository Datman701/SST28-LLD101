import java.nio.charset.StandardCharsets;

public class PdfExporter extends Exporter {
    @Override
    public ExportResult export(ExportRequest req) {
        // Honor base contract: handle any valid request without throwing
        // For large content, return a failure result instead of throwing
        String body = req.body == null ? "" : req.body;
        String title = req.title == null ? "" : req.title;

        if (body.length() > 20) {
            // Return failure result - no exception thrown
            return ExportResult.failure("PDF cannot handle content > 20 chars");
        }

        String content = "PDF(" + title + "):" + body;
        return new ExportResult("application/pdf", content.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public boolean canExport(ExportRequest req) {
        return super.canExport(req) &&
               (req.body == null || req.body.length() <= 20);
    }
}
