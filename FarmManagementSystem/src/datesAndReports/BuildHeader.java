package datesAndReports;

import java.util.List;

public interface BuildHeader<T> {
    public List<String> getHeaderLine();
    public List<String> getEntryLine(T object);
}
