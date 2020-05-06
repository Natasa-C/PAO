package barns;

public final class GrainBarn extends Barn {
    private static short noGrainBarns = 0;

    public GrainBarn(int length, int width, int height) {
        super(length, width, height);
        noGrainBarns++;
    }
}