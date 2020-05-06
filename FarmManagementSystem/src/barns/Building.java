package barns;

public class Building {
    private final Integer length;
    private final Integer width;
    private final Integer height;

    public Integer getLength() {
        return length;
    }

    public Integer getWidth() {
        return width;
    }

    public Integer getHeight() {
        return height;
    }

    public Building(Integer length, Integer width, Integer height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }
}