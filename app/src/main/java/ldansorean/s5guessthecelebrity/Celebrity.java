package ldansorean.s5guessthecelebrity;

import java.util.Objects;

public class Celebrity {

    private final String name;
    private final String photoUrl;

    public Celebrity(String name, String photoUrl) {
        this.name = name;
        this.photoUrl = photoUrl;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Celebrity celebrity = (Celebrity) o;
        return Objects.equals(photoUrl, celebrity.photoUrl) &&
                Objects.equals(name, celebrity.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(photoUrl, name);
    }
}
