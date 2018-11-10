import org.junit.Assert;
import org.junit.Test;

public class Range {
    private final long lowerBound;
    private final long upperBound;

    public Range(long lowerBound, long upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    public boolean isInRange(long number) {
        return number >= lowerBound && number <= upperBound;
    }
    @Test
    public void shouldSayThat15rIsInRange() {
        Range range = new Range(10, 20);
        Assert.assertTrue(range.isInRange(15));
    }

}

