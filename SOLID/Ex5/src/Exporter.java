/**
 * Base contract for exporters:
 * Preconditions: req must not be null, req.title and req.body can be null or any length
 * Postconditions: returns non-null ExportResult, never throws exceptions
 * Invariant: must not modify or lose data semantics from input
 */
public abstract class Exporter {
    /**
     * Exports the request to a specific format.
     * @param req the export request (must not be null)
     * @return the export result (never null)
     */
    public abstract ExportResult export(ExportRequest req);

    /**
     * Validates if this exporter can handle the given request.
     * Default implementation accepts all requests.
     * @param req the export request
     * @return true if can export, false otherwise
     */
    public boolean canExport(ExportRequest req) {
        return req != null;
    }
}
