package pl.solsoft.helloboot.hello.enumeration;

public enum EyeColor
{
    green("Green"),
    brown("Brown"),
    blue("Blue");

    private final String color;

    //private constructor
    EyeColor(String color)
    {
        this.color = color;
    }

    public boolean equalsColor(String color)
    {
        return this.color.equals(color);
    }

    public String toString()
    {
        return this.color;
    }
}
