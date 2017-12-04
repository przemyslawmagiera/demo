package pl.solsoft.helloboot.hello.enumeration;

public enum Sex
{
    male("Male"),
    female("Female");

    private final String name;

    //private constructor
    Sex(String s)
    {
        name = s;
    }

    public boolean equalsSex(String sex)
    {
        return name.equals(sex);
    }

    public String toString()
    {
        return this.name;
    }
}
