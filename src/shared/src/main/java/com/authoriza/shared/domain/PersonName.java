package com.authoriza.shared.domain;

public class PersonName {
    private final String firstName;
    private final String lastName;
    private final String title;

    public PersonName(String firstName, String lastName, String title) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
    }

    public PersonName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        title = null;
    }

    public String fullName()
    {
        var fullName = String.format("%s %s", firstName, lastName);

        if (title != null) {
            fullName = String.format("%s %s", title, fullName);
        }

        return fullName;
    }

    public String firstName()
    {
        return firstName;
    }

    public String lastName()
    {
        return lastName;
    }

    public String fullNameLastNameFirst()
    {
        return String.format("%s, %s", lastName, firstName);
    }
}
