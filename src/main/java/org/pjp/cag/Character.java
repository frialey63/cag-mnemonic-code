package org.pjp.cag;

/**
 * This class represents a character stored in the computer.
 * @author developer
 *
 */
public enum Character {
    A;

    public char asChar() {
        return name().charAt(0);
    }
}
