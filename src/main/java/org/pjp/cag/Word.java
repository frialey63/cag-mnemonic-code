package org.pjp.cag;

import org.pjp.cag.exception.FaultyWordException;

import com.google.common.base.Preconditions;

public final class Word {

    public static Word create(Order order) {
        return new Word(order);
    }

    public static Word create(float number) {
        return new Word(number);
    }

    public static Word create(Character character) {
        return new Word(character);
    }

    private Order order;

    private Float number;

    private Character character;

    Word() {
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

    public Order order() {
        if (order == null) {
            throw new FaultyWordException("not an order");
        }

        return order;
    }

    public float number() {
        if (number == null) {
            throw new FaultyWordException("not a number");
        }

        return number;
    }

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

}
