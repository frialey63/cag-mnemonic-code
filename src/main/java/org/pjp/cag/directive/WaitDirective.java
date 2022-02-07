package org.pjp.cag.directive;

/**
 * This class represents the WAIT directive.
 * @author developer
 *
 */
public final class WaitDirective extends Directive {

    /**
     * The WAIT directive.
     */
    public static final String WAIT = "WAIT";

     /**
     * Constructor.
     */
    public WaitDirective() {
        super(WAIT);
    }

    @Override
    public String toString() {
        return "WaitDirective [getType()=" + getType() + "]";
    }

}
