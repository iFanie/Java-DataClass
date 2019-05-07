package dev.fanie.javadataclass;

import org.junit.Test;

public class DataClassTest {

    private final DataClassProvider provider = new DataClassProvider();

    @Test
    public void givenOneInstance_whenComparingInstances_theyTheyAreEqual() {
        final DataClassProvider.Data input1 = provider.getData();
        final DataClassProvider.Data input2 = input1;

        assert input1.equals(input2);
    }

    @Test
    public void givenSameInstances_whenComparingInstances_thenTheyAreEqual() {
        final DataClassProvider.Data input1 = provider.getData();
        final DataClassProvider.Data input2 = provider.getData();

        assert input1.equals(input2);
    }

    @Test
    public void givenDifferentInstances_whenComparingInstances_thenTheyAreNotEqual() {
        final DataClassProvider.Data input1 = provider.getEmptyData();
        final DataClassProvider.Data input2 = provider.getData();

        assert !input1.equals(input2);
    }

    @Test
    public void givenNullInstance_whenComparingInstances_thenTheyAreNotEqual() {
        final DataClassProvider.Data input1 = provider.getEmptyData();
        final DataClassProvider.Data input2 = null;

        assert !input1.equals(input2);
    }

    @Test
    public void givenDifferentClassInstances_whenComparingInstances_thenTheyAreNotEqual() {
        final DataClassProvider.Data input1 = provider.getEmptyData();
        final DataClassProvider.OtherData input2 = provider.getOtherData();

        assert !input1.equals(input2);
    }

    @Test
    public void whenRequestingToString_thenTheResultIsTheExpected() {
        final DataClassProvider.Data input = provider.getData();

        final String result = input.toString();

        assert provider.getDataDescription().equals(result);
    }

}
