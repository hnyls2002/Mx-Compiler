package IR;

public class Tag {
    public String tagName;

    public Tag(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Tag tag1)
            return this.tagName.equals(tag1.tagName);
        return false;
    }
}
