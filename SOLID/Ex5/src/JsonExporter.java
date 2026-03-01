import java.nio.charset.StandardCharsets;

public class JsonExporter extends Exporter {
    @Override
    public ExportResult export(ExportRequest req) {
        // Honor base contract: handle req consistently (base ensures req is not null)
        // Keep simple escaping for output compatibility
        String json = "{\"title\":\"" + escape(req.title) + "\",\"body\":\"" + escape(req.body) + "\"}";
        return new ExportResult("application/json", json.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Escapes double quotes in JSON strings.
     * Handles null by converting to empty string.
     */
    private String escape(String s) {
        if (s == null) return "";
        return s.replace("\"", "\\\"");
    }
}
