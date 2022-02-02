package org.pjp.cag;

import org.pjp.cag.exception.FaultyWordException;

import com.google.common.base.Preconditions;

/**
 * The Word represents the contents of a storage location as either an Order, number or character.
 * @author developer
 *
 */
public final class Word {

    /**
     * @param order The order from which the word is created
     * @return The word
     */
    public static Word create(Order order) {
        return new Word(order);
    }

    /**
     * @param number The number from which the word is created
     * @return The word
     */
    public static Word create(float number) {
        return new Word(number);
    }

    /**
     * @param character The character from which the word is created
     * @return The word
     */
    public static Word create(Character character) {
        return new Word(character);
    }

    /**
     * @return The empty word
     */
    public static Word empty() {
        return EMPTY;
    }

    private static final Word EMPTY = new Word();

    private Order order;

    private Float number;

    private Character character;

    private Word() {
        super();
    }

    private Word(Order order) {
        this.order = Preconditions.checkNotNull(order, "order cannot be null");
        number = null;
        character = null;
    }

    private Word(float number) {
        this.number = number;
        order = null;
        character = null;
    }

    private Word(Character character) {
        this.character = Preconditions.checkNotNull(character, "character cannot be null");
        order = null;
        number = null;
    }

    /**
     * @return True if the word is empty
     */
    public boolean isEmpty() {
        return (order == null) && (number == null) && (character == null);
    }

    /**
     * @return The order contained in the word
     */
    public Order order() {
        if (order == null) {
            throw new FaultyWordException("not an order");
        }

        return order;
    }

    /**
     * @return The number contained in the word
     */
    public float number() {
        if (number == null) {
            throw new FaultyWordException("not a number");
        }

        return number;
    }

    /**
     * @return The character contained in the word
     */
    public Character character() {
        if (character == null) {
            throw new FaultyWordException("not a character");
        }

        return character;
    }

    @Override
    public String toString() {
        if (order != null) {
            return order.toString();
        } else if (number != null) {
            return number.toString();
        } else if (character != null) {
            return character.toString();
        }

        return "<empty>";
    }

    // CHECKSTYLE:OFF auto-generated

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((character == null) ? 0 : character.hashCode());
        result = prime * result + ((number == null) ? 0 : number.hashCode());
        result = prime * result + ((order == null) ? 0 : order.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Word other = (Word) obj;
        if (character != other.character)
            return false;
        if (number == null) {
            if (other.number != null)
                return false;
        } else if (!number.equals(other.number))
            return false;
        if (order == null) {
            if (other.order != null)
                return false;
        } else if (!order.equals(other.order))
            return false;
        return true;
    }

    // CHECKSTYLE:ON

}
