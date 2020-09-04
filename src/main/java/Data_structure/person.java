package Data_structure;

import java.util.Objects;

public class person {
    int sum;
    int add;
    String string;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        person person = (person) o;
        return sum == person.sum &&
                add == person.add &&
                Objects.equals(string, person.string);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sum, add, string);
    }
}
