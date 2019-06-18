package dev.fanie.javadataclass;

import java.lang.reflect.Field;
import java.util.Locale;
import java.util.Objects;

public abstract class DataClass {

    @Override
    public boolean equals(Object obj) {
        if (isSameInstance(obj)) {
            return true;
        }

        if (isNull(obj)) {
            return false;
        }

        if (isDifferentClassInstance(obj)) {
            return false;
        }

        return hasEqualFields(obj);
    }

    private boolean isSameInstance(Object object) {
        return this == object;
    }

    private boolean isNull(Object object) {
        return object == null;
    }

    private boolean isDifferentClassInstance(Object object) {
        return this.getClass() != object.getClass();
    }

    private boolean hasEqualFields(Object object) {
        for (Field field : getFields()) {
            if (hasDifferentFieldValue(field, object)) {
                return false;
            }
        }

        return true;
    }

    private boolean hasDifferentFieldValue(Field field, Object object) {
        return !Objects.equals(getSafeFieldValue(field, this), getSafeFieldValue(field, object));
    }

    @Override
    public String toString() {
        return String.format(Locale.ENGLISH,
                "%1$s(%2$s)",
                getClassName(),
                getFieldsDescription()
        );
    }

    private Field[] getFields() {
        return this.getClass().getDeclaredFields();
    }

    private Object getSafeFieldValue(Field field, Object object) {
        final boolean isAccessible = field.isAccessible();
        field.setAccessible(true);

        Object value = null;

        try {
            value = field.get(object);
        } catch (Exception ignored) {

        }

        field.setAccessible(isAccessible);
        return value;
    }

    private String getClassName() {
        return this.getClass().getSimpleName();
    }

    private String getFieldsDescription() {
        final StringBuilder stringBuilder = new StringBuilder();

        for (Field field : getFields()) {
            final String fieldDescription = getFieldDescription(field);

            stringBuilder.append(fieldDescription);
            stringBuilder.append(", ");
        }

        return cleanupString(stringBuilder.toString());
    }

    private String getFieldDescription(Field field) {
        final String name = field.getName();
        final Object value = getSafeFieldValue(field, this);

        return String.format(Locale.ENGLISH, "%1$s = %2$s", name, value);
    }

    private String cleanupString(String string) {
        if (string.endsWith(", ")) {
            return string.substring(0, string.length() - 2);
        } else {
            return string;
        }
    }

}
