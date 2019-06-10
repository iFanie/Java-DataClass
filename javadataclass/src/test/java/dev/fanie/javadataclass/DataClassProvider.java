package dev.fanie.javadataclass;

class DataClassProvider {

    Data getEmptyData() {
        return new Data();
    }

    Data getData() {
        return new Data("data", 1);
    }

    OtherData getOtherData() {
        return new OtherData();
    }

    String getDataDescription() {
        return "Data(aString = data, anInt = 1)";
    }

    static class Data extends DataClass {

        private final String aString;
        private final int anInt;

        public Data(String aString, int anInt) {
            this.aString = aString;
            this.anInt = anInt;
        }

        public Data() {
            this(null, 0);
        }

        public String getaString() {
            return aString;
        }

        public int getAnInt() {
            return anInt;
        }

    }

    static class OtherData extends DataClass {

        private final int anInt;

        public OtherData() {
            anInt = 0;
        }

        public int getAnInt() {
            return anInt;
        }

    }

}
