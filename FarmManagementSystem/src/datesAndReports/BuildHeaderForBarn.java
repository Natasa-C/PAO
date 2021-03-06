package datesAndReports;

import barns.Barn;

import java.util.ArrayList;
import java.util.List;

public class BuildHeaderForBarn implements BuildHeader<Barn>{
    @Override
    public List<String> getHeaderLine() {
            List<String> list = new ArrayList<>();
            list.add("length");
            list.add("width");
            list.add("height");
            list.add("barnIndex");
            list.add("totalCapacity");
            list.add("occupiedCapacity");
            return list;
        }

        @Override
        public List<String> getEntryLine(Barn barn) {
            List<String> list = new ArrayList<>();
            list.add(String.valueOf(barn.getLength()));
            list.add(String.valueOf(barn.getWidth()));
            list.add(String.valueOf(barn.getHeight()));
            list.add(String.valueOf(barn.getBarnIndex()));
            list.add(String.valueOf(barn.getTotalCapacity()));
            list.add(String.valueOf(barn.getOccupiedCapacity()));
            return list;
    }
}
