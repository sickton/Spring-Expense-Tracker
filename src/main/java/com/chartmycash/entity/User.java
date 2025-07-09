package com.chartmycash.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Class User to specify the entity of a user in system
 * @author Srivathsa
 */
@Entity
@Table(name = "users")
public class User {

    /** Constants required for invalid input validations */
    private static final int TEN = 10;
    private static final int EIGHT = 8;

    /** Field to store first name */
    @Column
    private String first;

    /** Field to store last name */
    @Column
    private String last;

    /** Field to store the email */
    @Column
    private String email;

    /** Field to store the password */
    @Column
    private String password;

    /** Field to store the username */
    @Id
    private String username;

    /** Field to store the age */
    @Column
    private int age;

    /**
     * Constructor to create a new User
     * @param first first name
     * @param last last name
     * @param email email
     * @param password password for account
     * @param username username of the account
     * @param age age of the user
     */
    public User(String first, String last, String email, String password, String username, int age)
    {
        setPwd(password);
        setFirst(first);
        setLast(last);
        setAge(age);
        setMail(email);
        setUserName(username);
    }

    /**
     * Default constructor to initialize a dummy object
     */
    public User()
    {
    }

    /**
     * Helper method to check a string for null or empty
     * @param sentence string to be checked
     * @return exception if sentence is invalid
     */
    private IllegalArgumentException checkString(String sentence)
    {
        if(sentence == null || sentence.isEmpty())
            return new IllegalArgumentException("Invalid name!");
        return null;
    }

    /**
     * Setter method to set the username
     * @param username username of user
     */
    private void setUserName(String username) {
        Exception e = checkString(username);
        if(e == null)
        {
            if(username.length() > TEN)
                throw new IllegalArgumentException("Username too long!");
            else
                this.username = username;
        }
        else
            throw new IllegalArgumentException(e.getMessage());
    }

    /**
     * Setter method to set the email and check for invalid input
     * @param email email of the user
     */
    private void setMail(String email) {
        Exception e = checkString(email);
        if(e == null)
        {
            boolean foundDot = false;
            boolean foundAt = false;
            for(int i = 0; i < email.length(); i++)
            {
                if(email.charAt(i) == '.')
                    foundDot = true;
                else if(email.charAt(i) == '@')
                    foundAt = true;
            }
            if(!foundDot && !foundAt)
                throw new IllegalArgumentException("Invalid email!");
            else
                this.email = email;
        }
        else
            throw new IllegalArgumentException(e.getMessage());
    }

    /**
     * Setter method to set the age of the user
     * @param age age of the user
     */
    private void setAge(int age) {
        if(age <= 0)
            throw new IllegalArgumentException("Invalid age!");
        else
            this.age = age;
    }

    /**
     * Setter method to set the last name of the user
     * @param last last name of the user
     */
    private void setLast(String last) {
        Exception e = checkString(last);
        if(e == null)
        {
            for(int i = 0; i < last.length(); i++)
            {
                if(!Character.isLetter(last.charAt(i)) && last.charAt(i) != '\'' && last.charAt(i) != '-')
                    throw new IllegalArgumentException("Invalid last name!");
            }
            this.last = last;
        }
        else
            throw new IllegalArgumentException(e.getMessage());
    }

    /**
     * Setter method to set the password of the user
     * @param password password of the user
     */
    private void setPwd(String password) {
        Exception e = checkString(password);
        if(e == null)
        {
            if(password.length() < EIGHT)
                throw new IllegalArgumentException("Invalid password length!");
            boolean foundLower = false;
            boolean foundUpper = false;
            boolean foundDigit = false;
            boolean foundSpecial = false;
            for(int i = 0; i < password.length(); i++)
            {
                if(Character.isUpperCase(password.charAt(i)))
                    foundUpper = true;
                else if(Character.isLowerCase(password.charAt(i)))
                    foundLower = true;
                else if(Character.isDigit(password.charAt(i)))
                    foundDigit = true;
                else
                    foundSpecial = true;
            }
            if(!foundUpper)
                throw new IllegalArgumentException("Password must have at least one Uppercase Letter!");
            else if(!foundLower)
                throw new IllegalArgumentException("Password must have at least one lowercase Letter!");
            else if(!foundDigit)
                throw new IllegalArgumentException("Password must have at least one digit!");
            else if(!foundSpecial)
                throw new IllegalArgumentException("Password must have at least one special character!");
            this.password = password;
        }
        else
            throw new IllegalArgumentException(e.getMessage());
    }

    /**
     * Setter method to set the first name of the user
     * @param first first name of the user
     */
    private void setFirst(String first) {
        Exception e = checkString(first);
        if(e == null)
        {
            for(int i = 0; i < first.length(); i++)
            {
                if(!Character.isLetter(first.charAt(i)) && first.charAt(i) != '\'' && first.charAt(i) != '-')
                    throw new IllegalArgumentException("Invalid first name!");
            }
            this.first = first;
        }
        else
            throw new IllegalArgumentException(e.getMessage());
    }

    /**
     * Method to reset a user's password by delegating to setter
     * @param newPassword new password for the user
     */
    public void resetPassword(String newPassword) {
        this.setPwd(newPassword);
    }

    /**
     * Method to return the first name of the user
     * @return first name
     */
    public String getFirst() {
        return first;
    }

    /**
     * Method to return the last name of the user
     * @return last name of the user
     */
    public String getLast() {
        return last;
    }

    /**
     * Method to return the email of the user
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Method to return the password of the user
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Method to return the username of the user
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Method to return age of the user
     * @return age of the user
     */
    public int getAge() {
        return age;
    }

    /**
     * Method to return the string representation of the user
     * @return string of user
     */
    @Override
    public String toString() {
        return this.first + ":" + this.last + ":" + this.email + ":"
                + this.password + ":" + this.username + ":" + this.age;
    }

    /**
     * Method to check if two objects are equal
     * @param o- object to be checked with this
     * @return true if equal else false
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;
        User user = (User) o;
        return user.username.equals(this.username);
    }
}