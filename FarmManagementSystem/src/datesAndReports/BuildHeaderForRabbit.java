package datesAndReports;

import animals.Rabbit;


import java.util.ArrayList;
import java.util.List;

public class BuildHeaderForRabbit implements BuildHeader<Rabbit> {
    @Override
    public List<String> getHeaderLine() {
        List<String> list = new ArrayList<>();
        list.add("id");
        list.add("dateOfBirth");
        list.add("isMale");
        return list;
    }

    @Override
    public List<String> getEntryLine(Rabbit rabbit) {
        List<String> list = new ArrayList<>();
        list.add(String.valueOf(rabbit.getId()));
        list.add(String.valueOf(rabbit.getDateOfBirth()));
        list.add(String.valueOf(rabbit.isMale()));
        return list;
    }
}
