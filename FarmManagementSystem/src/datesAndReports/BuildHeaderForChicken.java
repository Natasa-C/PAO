package datesAndReports;
import animals.Chicken;
import java.util.ArrayList;
import java.util.List;

public class BuildHeaderForChicken implements BuildHeader<Chicken> {
    @Override
    public List<String> getHeaderLine() {
        List<String> list = new ArrayList<>();
        list.add("id");
        list.add("dateOfBirth");
        list.add("isMale");
        return list;
    }

    @Override
    public List<String> getEntryLine(Chicken chicken) {
        List<String> list = new ArrayList<>();
        list.add(String.valueOf(chicken.getId()));
        list.add(String.valueOf(chicken.getDateOfBirth()));
        list.add(String.valueOf(chicken.isMale()));
        return list;
    }
}
